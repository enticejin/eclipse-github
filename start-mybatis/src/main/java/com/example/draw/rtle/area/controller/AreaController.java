package com.example.draw.rtle.area.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
* @version 创建时间：2020年6月9日 上午10:19:33
*/
@Controller
@RequestMapping("/area")
public class AreaController {
	@RequestMapping("areaList")
	public String areaList() {
		return "area/area_list";
	}
}
