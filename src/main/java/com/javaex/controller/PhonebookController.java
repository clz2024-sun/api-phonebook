package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaex.service.PhonebookService;
import com.javaex.util.JsonResult;
import com.javaex.vo.PersonVo;

@RestController
public class PhonebookController {

	@Autowired
	private PhonebookService phonebookService;
	
	//전체리스트
	@GetMapping("/api/persons")
	public JsonResult getList() {
		System.out.println("PhonebookController.getList()");
		
		List<PersonVo> personList = phonebookService.exeGetPersonList();
		return JsonResult.success(personList);
	}
	
	//등록
	@PostMapping("/api/persons")
	public JsonResult addPerson(@RequestBody PersonVo personVo) {
		System.out.println("PhonebookController.addPerson()");
		
		int count = phonebookService.exeWritePerson(personVo);
		if(count != 1) { //등록안됨
			return JsonResult.fail("등록에 실패했습니다.");
		}else { //등록됨
			return JsonResult.success(count);
		}
	}
	
	//삭제
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
	
	//수정폼, 1명데이터 가져오기
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
	
	//수정
	@PutMapping("/api/persons/{no}")
	public JsonResult updatePerson(@PathVariable(value="no") int personId,
								   @RequestBody	PersonVo personVo) {
		System.out.println("PhonebookController.updatePerson()");
		personVo.setPersonId(personId);
		
		int count = phonebookService.exeEditPerson(personVo);
		
		if(count == 0) {
			return JsonResult.fail("수정할 데이터가 없습니다.");
		}else {
			return JsonResult.success(count);
		}
		
	}
	
}

