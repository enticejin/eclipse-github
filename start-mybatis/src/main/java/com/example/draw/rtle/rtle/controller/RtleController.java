package com.example.draw.rtle.rtle.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.draw.rtle.area.model.Area;
import com.example.draw.rtle.area.service.AreaService;
import com.example.draw.rtle.model.Anchor;
import com.example.draw.rtle.rtle.model.Rtle;
import com.example.draw.rtle.rtle.service.RtleService;
import com.example.draw.rtle.service.AnchorService;

/** 
* @version 创建时间：2020年6月9日 上午9:42:37
*/
@Controller
@RequestMapping("/rtle")
public class RtleController {
	@Autowired
	private RtleService rtleService;
	@Autowired
	private AnchorService anchorService;
	@Autowired
	private AreaService areaService;
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
	//选择基站调试
	@RequestMapping("/selectAnchor")
	public String selectAnchor(Model model) {
		List<String> anchorNameList = new ArrayList<String>();
		List<Anchor> anchorList = anchorService.getAnchorLsit();
		for(Anchor anchor : anchorService.getAnchorLsit()) {
			anchorNameList.add(anchor.getAnchorName());
		}
		model.addAttribute("varList", anchorNameList);
		model.addAttribute("anchorList", anchorList);
		model.addAttribute("flag", "anchor");
		return "rtle/debug_list";
	}
	//选择区域调试
	@RequestMapping("/selectArea")
	public String selectArea(Model model) {
		List<String> areaNameList = new ArrayList<String>();
		List<Area> areaList = areaService.getAreaLsit();
		for(Area area : areaList) {
			areaNameList.add(area.getAreaName());
		}
		model.addAttribute("varList", areaNameList);
		model.addAttribute("areaList", areaList);
		model.addAttribute("flag", "area");
		return "rtle/debug_list";
	}
	//选择基站调试
	@RequestMapping("/selectTag")
	public String selectTag(Model model) {
		List<String> tagNameList = new ArrayList<String>();
		tagNameList.add("tag-01");
		tagNameList.add("tag-02");
		tagNameList.add("tag-03");
		tagNameList.add("tag-04");
		tagNameList.add("tag-05");
		tagNameList.add("tag-06");
		model.addAttribute("tagNameList", tagNameList);
		model.addAttribute("flag", "tag");
		return "rtle/debug_list";
	}
}
