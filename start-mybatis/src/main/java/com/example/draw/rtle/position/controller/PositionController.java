package com.example.draw.rtle.position.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.draw.rtle.rtle.model.Rtle;
import com.example.draw.rtle.rtle.service.RtleService;

/** 
* @version 创建时间：2020年6月10日 下午5:33:09
*/
@Controller
@RequestMapping("/position")
public class PositionController {
	@Autowired
	private RtleService rtleService;
	@RequestMapping("/positionList")
	public String positionList(Model model) {
		Rtle rtle = rtleService.findAll();
		model.addAttribute("logitude", rtle.getLogitude());
		model.addAttribute("latitude", rtle.getLatitude());
		return "position/position_list";
	}
}
