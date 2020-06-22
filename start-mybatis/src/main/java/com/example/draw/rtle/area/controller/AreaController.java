package com.example.draw.rtle.area.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.example.draw.rtle.area.service.AreaService;
import com.example.draw.rtle.clocksource.service.ClockService;
import com.example.draw.rtle.model.Anchor;
import com.example.draw.rtle.rtle.model.Rtle;
import com.example.draw.rtle.rtle.service.RtleService;
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
	@Autowired
	private RtleService rtleService;
	//区域展示
	@RequestMapping("/areaList")
	public String areaList(HttpServletRequest request,Model model,String ids,
			Area area,Map<String,Object> map,
			@RequestParam(required=false,defaultValue="1") int pages,
			@RequestParam(required=false,defaultValue="5") int num) {
		//分页
		PageBean.conMap(pages, map, num, request, area);
		areaService.areaList(map);
		//获取rtle配置
		Rtle rtle = rtleService.findAll();
		model.addAttribute("rtle", rtle);
		if(ids == null) {
			map.put("flag", "area");
			model.addAttribute("pageMap", map);
			List<Area> areaList = areaService.getAreaLsit();
			model.addAttribute("areaList", areaList);
			return "area/area_list";
		}else if(ids != null &&ids.trim().length() > 0) {
			List<Area> areaList = new ArrayList<Area>();
			if(ids.contains(",")) {
				String[] idArray = ids.split(",");
				for(String id : idArray) {
					areaList.add(areaService.selectByPrimaryKey(Integer.parseInt(id)));
				}
			}else {
				areaList.add(areaService.selectByPrimaryKey(Integer.parseInt(ids)));
			}
			map.put("flag", "area");
			model.addAttribute("areaList", areaList);
			return "area/area_list";
		} else
			return "area/area_list";
	}
	//添加区域
	@RequestMapping("/areaAdd")
	public String areaAdd(Model model,String id) {
		//时钟
		model.addAttribute("clockList", clockService.selectAll());
		//获取维度
		List<Map<String, Object>> wideList = getWides();
		//构造触发定位
		List<Map<String, Object>> locationList = locationTriggerData();
		model.addAttribute("wideList", wideList);
		model.addAttribute("locationList", locationList);
		if(id == null || id.trim().length() == 0) {
			model.addAttribute("flag", "add");
		}else {
			Area area = areaService.selectByPrimaryKey(Integer.parseInt(id));
			String anchorName = area.getAnchorId();
			String[] anchorNames = anchorName.split(",");
			
			model.addAttribute("anchorNames", anchorNames);
			model.addAttribute("area", area);
			model.addAttribute("flag", "update");
		}
		return "area/area_add";
	}
	//删除区域
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		areaService.deleteByPrimaryKey(id);
		map.put("msg", "success");
		return map;
	}
	//复制区域
	@RequestMapping(value = "/copy", method = RequestMethod.GET)
	public Object copy(int id, Model model) {
		Area area = areaService.selectByPrimaryKey(id);
		String areaName=area.getAreaName();
		if(areaName.contains("副本")) {
			areaName = areaName.split("副本")[0];
		}
		//遍历区域列表中的所有区域
		List<Area> areaColl = areaService.getAreaLsit();
		List<String> areaNameList = new ArrayList<String>();
		List<Integer> numList = new ArrayList<Integer>();
		for(Area a : areaColl) {
			areaNameList.add(a.getAreaName());
		}
		//遍历区域名称
		int count = 0;
		for(String name1 :areaNameList) {
			if(name1.split("副本")[0].equals(areaName)) {
				if(name1.split("副本").length >= 2) {
					numList.add(Integer.parseInt(name1.split("副本")[1]));
				}
				count++;
			}
		}
		if(numList.size() > 0 && Collections.min(numList) != 1) {
			count = Collections.min(numList)-1;
		}else if(numList.size() > 0){
			count = Collections.max(numList)+1;
		}
		if(count == 0) {
			areaName = areaName + "副本1";
		}else {
			areaName = areaName + "副本" + count;
		}
		area.setAreaName(areaName);
		model.addAttribute("flag", "copy");
		model.addAttribute("area", area);
		//时钟
		model.addAttribute("clockList", clockService.selectAll());
		//获取维度
		List<Map<String, Object>> wideList = getWides();
		//构造触发定位
		List<Map<String, Object>> locationList = locationTriggerData();
		model.addAttribute("wideList", wideList);
		model.addAttribute("locationList", locationList);	
		String anchorName = area.getAnchorId();
		String[] anchorNames = anchorName.split(",");
		model.addAttribute("anchorNames", anchorNames);
		return "area/area_add";
	}
	//查找区域
	@RequestMapping("/select")
	@ResponseBody
	public Object selectArea(String areaName) {
		List<Area> areaList = areaService.getAreaByName(areaName);
		Map<String,Object> map = new HashMap<>();
		if(areaList != null && areaList.size() > 0) {
			List<Integer> ids = new ArrayList<Integer>();
			for(Area area : areaList) {
				ids.add(area.getId());
			}
			map.put("ids", ids);
			map.put("msg", "success");
		}else {
			map.put("msg", "fail");
		}
		return map;
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
	//保存添加区域参数
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Object save(@RequestBody JSONObject params) {

		Map<String, String> map = new HashMap<String, String>();
		if(params.getString("flag").equals("add")) {
			String areaName = params.getString("areaName");
			String clockName = params.getString("clockName");
			String anchorNames = params.getString("anchorNames");
			String anchorIds="";
			if(anchorNames.length() > 1) {
				anchorIds = anchorNames.substring(0, anchorNames.length()-1);
			}
			String z = params.getString("z");
			String wide = params.getString("wide");
			String locationTrigger = params.getString("locationTrigger");
			String remarks = params.getString("remarks");
			String useAverageFilter = params.getString("useAverageFilter");
			String discardPointsOfOutOfArea = params.getString("discardPointsOfOutOfArea");
			String useKalmanFilter = params.getString("useKalmanFilter");
			String active = params.getString("active");
			String averageFilterSampleTimeLength = params.getString("averageFilterSampleTimeLength");
			String kalman = params.getString("kalmanFilterLevel");
			
			Area area = new Area();
			area.setAction(active);
			area.setUseKalmanFilter(useKalmanFilter);
			area.setKalman(kalman);
			area.setAreaName(areaName);
			area.setClockName(clockName);
			if(anchorIds.trim().length() > 0) {
				area.setAnchorId(anchorIds);
			}
			area.setZ(z);
			area.setAreaWide(wide);
			area.setActionType(locationTrigger);
			area.setRemarks(remarks);
			area.setAverageFiltering(averageFilterSampleTimeLength);
			area.setUseBoundaryValue(discardPointsOfOutOfArea);
			area.setUseAverageFilter(useAverageFilter);
			areaService.insert(area);
			map.put("msg", "success");
		}else if(params.getString("flag").equals("update")) {
			String areaId = params.getString("areaId");
			String areaName = params.getString("areaName");
			String clockName = params.getString("clockName");
			String anchorNames = params.getString("anchorNames");
			String anchorIds = anchorNames.substring(0, anchorNames.length()-1);
			String z = params.getString("z");
			String wide = params.getString("wide");
			String locationTrigger = params.getString("locationTrigger");
			String remarks = params.getString("remarks");
			String useAverageFilter = params.getString("useAverageFilter");
			String discardPointsOfOutOfArea = params.getString("discardPointsOfOutOfArea");
			String useKalmanFilter = params.getString("useKalmanFilter");
			String active = params.getString("active");
			String averageFilterSampleTimeLength = params.getString("averageFilterSampleTimeLength");
			String kalman = params.getString("kalmanFilterLevel");
			
			Area area = areaService.selectByPrimaryKey(Integer.parseInt(areaId));
			area.setAction(active);
			area.setUseKalmanFilter(useKalmanFilter);
			area.setKalman(kalman);
			area.setAreaName(areaName);
			area.setClockName(clockName);
			area.setAnchorId(anchorIds);
			area.setZ(z);
			area.setAreaWide(wide);
			area.setActionType(locationTrigger);
			area.setRemarks(remarks);
			area.setAverageFiltering(averageFilterSampleTimeLength);
			area.setUseBoundaryValue(discardPointsOfOutOfArea);
			area.setUseAverageFilter(useAverageFilter);
			areaService.updateByPrimaryKey(area);
			map.put("msg", "success");
		}
		else if(params.getString("flag").equals("copy")) {
			String areaName = params.getString("areaName");
			String clockName = params.getString("clockName");
			String anchorNames = params.getString("anchorNames");
			String anchorIds = anchorNames.substring(0, anchorNames.length()-1);
			String z = params.getString("z");
			String wide = params.getString("wide");
			String locationTrigger = params.getString("locationTrigger");
			String remarks = params.getString("remarks");
			String useAverageFilter = params.getString("useAverageFilter");
			String discardPointsOfOutOfArea = params.getString("discardPointsOfOutOfArea");
			String useKalmanFilter = params.getString("useKalmanFilter");
			String active = params.getString("active");
			String averageFilterSampleTimeLength = params.getString("averageFilterSampleTimeLength");
			String kalman = params.getString("kalmanFilterLevel");
			
			Area area = new Area();
			area.setAction(active);
			area.setUseKalmanFilter(useKalmanFilter);
			area.setKalman(kalman);
			area.setAreaName(areaName);
			area.setClockName(clockName);
			if(anchorIds.trim().length() > 0) {
				area.setAnchorId(anchorIds);
			}
			area.setZ(z);
			area.setAreaWide(wide);
			area.setActionType(locationTrigger);
			area.setRemarks(remarks);
			area.setAverageFiltering(averageFilterSampleTimeLength);
			area.setUseBoundaryValue(discardPointsOfOutOfArea);
			area.setUseAverageFilter(useAverageFilter);
			areaService.insert(area);
			map.put("msg", "success");
		}
		return map;
	}
	//区域编辑形状
	@RequestMapping("mapLngLat")
	public String mapLngLat(String longitude, String latitude, Model model) {
		if(longitude != null && longitude.trim().length() > 0) {
			model.addAttribute("longitude", longitude);
		}
		if(latitude != null && latitude.trim().length() > 0) {
			model.addAttribute("latitude", latitude);
		}
		return "area/edit_map";
	}
	//维度设置
	public List<Map<String, Object>> getWides(){
		List<Map<String, Object>> wideList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map1 = new HashMap<String, Object>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		Map<String, Object> map3 = new HashMap<String, Object>();
		map1.put("id", "1");
		map1.put("name", "一维");
		map2.put("id", "2");
		map2.put("name", "二维");
		map3.put("id", "3");
		map3.put("name", "三维");
		wideList.add(map1);
		wideList.add(map2);
		wideList.add(map3);
		return wideList;
	}
	//构造触发下拉定位
	public List<Map<String, Object>> locationTriggerData(){
		List<Map<String, Object>> locationTriggerDataList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map1 = new HashMap<String, Object>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		map1.put("id", "1");
		map1.put("name", "消息数量达到最小要求, 触发计算");
		map2.put("id", "2");
		map2.put("name", "下一条消息到达, 触发计算(如果消息数量不够,不触发)");
		locationTriggerDataList.add(map1);
		locationTriggerDataList.add(map2);
		return locationTriggerDataList;
	}
}
