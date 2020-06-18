package com.example.draw.rtle.area.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.draw.rtle.area.model.Area;
import com.example.draw.rtle.area.service.AreaService;
import com.example.draw.rtle.clocksource.service.ClockService;
import com.example.draw.rtle.model.Anchor;
import com.example.draw.rtle.service.AnchorService;
import com.example.draw.rtle.utils.PageBean;

/** 
* @version 创建时间：2020年6月9日 上午10:19:33
*/
@Controller
@RequestMapping("/area")
public class AreaController {
	@Autowired
	private AreaService areaService;
	@Autowired
	private ClockService clockService;
	@Autowired
	private AnchorService anchorService;
	//区域展示
	@RequestMapping("/areaList")
	public String areaList(HttpServletRequest request,Model model,String ids,
			Area area,Map<String,Object> map,
			@RequestParam(required=false,defaultValue="1") int pages,
			@RequestParam(required=false,defaultValue="5") int num) {
		//分页
		PageBean.conMap(pages, map, num, request, area);
		areaService.areaList(map);
		if(ids == null) {
			map.put("flag", "area");
			model.addAttribute("pageMap", map);
			return "area/area_list";
		} 
		List<Area> areaList = areaService.getAreaLsit();
		model.addAttribute("areaList", areaList);
		return "area/area_list";
	}
	//添加区域
	@RequestMapping("/areaAdd")
	public String areaAdd(Model model) {
		//时钟
		model.addAttribute("clockList", clockService.selectAll());
		return "area/area_add";
	}
	//选择基站
	@RequestMapping("/selectAnchor")
	public String selectAnchor(Model model) {
		List<Anchor> anchorList = anchorService.getAnchorLsit();
		List<String> anchorNameList = new ArrayList<String>();
		for(Anchor anchor: anchorList) {
			anchorNameList.add(anchor.getAnchorName());
		}
		model.addAttribute("varList", anchorNameList);
		model.addAttribute("anchorList", anchorList);
		model.addAttribute("flag", "anchor");
		return "rtle/debug_list";
	}
}
