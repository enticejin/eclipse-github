package com.example.draw.test.delete;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
* @version 创建时间：2020年7月1日 下午5:25:07
*/
@Controller
@RequestMapping("/delete")
public class DeleteController {
	@RequestMapping("/deleteAll")
	public String deleteAll() {
		return "test/deleteMany";
	}
}
