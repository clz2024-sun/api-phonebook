package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.PhonebookService;
import com.javaex.util.JsonResult;
import com.javaex.vo.PersonVo;

@RestController
public class PhonebookController {

	@Autowired
	private PhonebookService phonebookService;
	
	
	@GetMapping("/api/persons")
	public List<PersonVo> getList() {
		System.out.println("PhonebookController.getList()");
		
		List<PersonVo> personList = phonebookService.exeGetPersonList();
		System.out.println(personList);
		return personList;
	}
	
	
	@PostMapping("/api/persons")
	public int addPerson(@RequestBody PersonVo personVo) {
		System.out.println("PhonebookController.addPerson()");
		
		int count = phonebookService.exeWritePerson(personVo);
		System.out.println(count);
		return count;
	}
	
	
	@DeleteMapping("/api/persons/{no}")
	public JsonResult delPerson(@PathVariable(value="no") int no) {
		System.out.println("PhonebookController.addPerson()");
		System.out.println(no);
		
		int count = phonebookService.exePersonDelete(no);
		
		if(count != 1) { //삭제안됨
			return JsonResult.fail("해당번호가 없습니다.");
		}else { //삭제됨
			return JsonResult.success(count);
		}
		
	}
	
	@GetMapping("/api/persons/{no}")
	public JsonResult getPerson(@PathVariable(value="no") int personId) {
		System.out.println("PhonebookController.getPerson()");
		
		PersonVo personVo = phonebookService.exeEditForm(personId);
		
		if(personVo == null) {
			return JsonResult.fail("번호가 없습니다.");
		
		}else {
			return JsonResult.success(personVo);
		}
		
	}
	
	
	@PutMapping("/api/persons/{no}")
	public JsonResult updatePerson() {
		System.out.println("PhonebookController.updatePerson()");
		
		return null;
	}
	
	
	
	
}
