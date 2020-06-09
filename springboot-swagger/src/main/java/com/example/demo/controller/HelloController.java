package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
/*类注解*/
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
@Api(value = "desc of class")
@RestController
public class HelloController {
	//方法注解
	@ApiOperation(value = "desc of the method", notes = "")
	@GetMapping("/hello")
	public Object hello(
			//参数注解
			@ApiParam(value="desc of param", required = true) @RequestParam String name
			) {
		return "Hello"+name+"!";
	}
}
