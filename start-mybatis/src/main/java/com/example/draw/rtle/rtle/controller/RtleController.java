package com.example.draw.rtle.rtle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.draw.rtle.rtle.model.Rtle;
import com.example.draw.rtle.rtle.service.RtleService;

/** 
* @version 创建时间：2020年6月9日 上午9:42:37
*/
@Controller
@RequestMapping("/rtle")
public class RtleController {
	@Autowired
	private RtleService rtleService;
	//RTLE页面
	@RequestMapping("/rtleList")
	public String rtleList(Model model) {
		Rtle rtle = rtleService.findAll();
		model.addAttribute("rtle", rtle);
		return "rtle/rtle_list";
	}
	//设置中心经纬度
	@RequestMapping("/mapLngLat")
	public String mapLngLat(String longitude, String latitude,Model model) {
		if(longitude != null && longitude.trim().length() > 0) {
			model.addAttribute("longitude", longitude);
		}
		if(latitude != null && latitude.trim().length() > 0) {
			model.addAttribute("latitude", latitude);
		}
		return "rtle/map";
	}
}
