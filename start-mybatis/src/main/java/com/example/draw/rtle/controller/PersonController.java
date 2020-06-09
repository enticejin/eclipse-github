package com.example.draw.rtle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.draw.rtle.model.Person;
import com.example.draw.rtle.service.PersonService;

/** 
* @version 创建时间：2020年6月2日 下午2:44:20
*/
@Controller
public class PersonController {
	@Autowired
	private PersonService personService;
	
	@RequestMapping("/")
	@ResponseBody
	public String all(Model model) {
		List<Person> personList = personService.getAll();
		model.addAttribute("personList", personList);
		return personList.toString();
	}
}
