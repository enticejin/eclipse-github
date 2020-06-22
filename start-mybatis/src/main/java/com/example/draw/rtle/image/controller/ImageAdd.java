package com.example.draw.rtle.image.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
* @version 创建时间：2020年6月22日 上午11:45:40
*/
@Controller
public class ImageAdd {

	@RequestMapping("image")
	public String imageAdd() {
		
		return "image/image_add";
		
	}
	@RequestMapping("imageTest")
	public String imageTest() {
		
		return "image/image_test";
		
	}
	@RequestMapping("Gis")
	public String Gis() {
		
		return "Gis/map";
		
	}
	@RequestMapping("draw")
	public String draw() {
		
		return "Gis/draw_xy";
		
	}
}
