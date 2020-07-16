package com.example.draw.rtle.planmap.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.example.draw.rtle.area.model.Area;
import com.example.draw.rtle.planmap.model.PlanMap;
import com.example.draw.rtle.planmap.service.PlanMapService;
import com.example.draw.rtle.rtle.service.RtleService;
import com.example.draw.rtle.userFile.model.UserFile;
import com.example.draw.rtle.userFile.service.UserFileService;

/** 
* @version 创建时间：2020年7月14日 上午10:27:03
*/
@Controller
@RequestMapping(value = "/map")
public class PlanMapController {
	@Autowired
	private PlanMapService planMapService;
	@Autowired
	private RtleService rtleService;
	@Autowired
	private UserFileService userFileService;
	@RequestMapping(value = "/mapList")
	public Object MapList(Model model) {
		model.addAttribute("varList", planMapService.findAll());
		model.addAttribute("rtle", rtleService.selectByPrimaryKey(1));
		model.addAttribute("flag", "map");
		return "planmap/map_list";
	}
	//分页
	@RequestMapping("/selectPlanMapTable")
	@ResponseBody
	public Object selectPlanMapTable(@RequestParam(required = false, defaultValue = "1")int page,
			@RequestParam(required = false)int limit) {
		Map<String,Object> map=new HashMap<String,Object>();
		PlanMap planMap = new PlanMap();
		map.put("code", "0");
		map.put("data", planMapService.list(planMap, page, limit));
		map.put("count", planMapService.getCount(planMap));
		return map;
	}
	//添加平面图
	@RequestMapping("/Add")
	public Object add(Model model) {
		model.addAttribute("varList", planMapService.findAll());
		model.addAttribute("rtle", rtleService.selectByPrimaryKey(1));
		model.addAttribute("flag", "add");
		return "planmap/add_map";
	}
	//选择文件
	@RequestMapping("/selectFileList")
	public Object selectFileList(Model model) {
		model.addAttribute("varList", userFileService.findAll());
		return "planmap/select_file";
	}
	//保存
	@RequestMapping("/savePlanMap")
	@ResponseBody
	public Object savePlanMap(@RequestBody JSONObject param) {
		Map<String, Object> map = new HashMap<String, Object>();
		String planMapName = param.getString("planmapname");
		String pictype =  param.getString("pictype");
		String opacity = param.getString("opacity");
		String rotate = param.getString("rotate");
		String centerlon= param.getString("centerlon");
		String centerlat= param.getString("centerlat");
		String scalex= param.getString("scalex");
		String scaley= param.getString("scaley");
		String palnmapfilename= param.getString("palnmapfilename");
		String remarks= param.getString("beizhu");
		String flag= param.getString("flag");
		if(flag.equals("add")) {
			PlanMap planMap = new PlanMap();
			planMap.setFileName(palnmapfilename);
			planMap.setPlanMapName(planMapName);
			planMap.setImageType(Integer.parseInt(pictype));
			planMap.setLongitude(Float.parseFloat(centerlon));
			planMap.setLatitude(Float.parseFloat(centerlat));
			planMap.setZoomX(Float.parseFloat(scalex));
			planMap.setZoomY(Float.parseFloat(scaley));
			planMap.setRotationAngle(Float.parseFloat(rotate));
			planMap.setRotationAngle(Float.parseFloat(rotate));
			planMap.setTransparency(Float.parseFloat(opacity));
			planMap.setRemarks(remarks);
			SimpleDateFormat df = new SimpleDateFormat("yyyyddHHmmss");
			String planMapId = "PL"+df.format(new Date()); 
			planMap.setPlanMapId(planMapId);
			map.put("msg", "success");
			planMapService.insert(planMap);
		}
		else if(flag.equals("update")) {
			String id = param.getString("id");
			PlanMap planMap = planMapService.getOne(Integer.parseInt(id));
			planMap.setFileName(palnmapfilename);
			planMap.setPlanMapName(planMapName);
			planMap.setImageType(Integer.parseInt(pictype));
			planMap.setLongitude(Float.parseFloat(centerlon));
			planMap.setLatitude(Float.parseFloat(centerlat));
			planMap.setZoomX(Float.parseFloat(scalex));
			planMap.setZoomY(Float.parseFloat(scaley));
			planMap.setRotationAngle(Float.parseFloat(rotate));
			planMap.setRotationAngle(Float.parseFloat(rotate));
			planMap.setTransparency(Float.parseFloat(opacity));
			planMap.setRemarks(remarks);
			map.put("msg", "success");
			planMapService.insert(planMap);
		}else {
			map.put("msg", "fail");
		}
		return map;
		}
	//删除
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		planMapService.delelte(id);
		map.put("msg", "success");
		return map;
	}
	//更改
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public Object edit(int id,Model model) {
		PlanMap planMap = planMapService.findPlan(id);
		model.addAttribute("map", planMap);
		model.addAttribute("flag", "update");
		model.addAttribute("id", id);
		return "planmap/add_map";
	}
	//查询
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public Object select(String name,Model model) {
		List<PlanMap> mapList = planMapService.findAllByName(name);
		model.addAttribute("varList",mapList);
		model.addAttribute("name",name);
		model.addAttribute("rtle", rtleService.selectByPrimaryKey(1));
		model.addAttribute("flag","select");
		return "planmap/map_list";
	}
	@RequestMapping("/selectTable")
	@ResponseBody
	public Object selectTable(String name) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("code", "0");
		map.put("data", planMapService.findAllByName(name));
		return map;
	}
}
