package com.example.draw.rtle.clocksource.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.example.draw.rtle.clocksource.model.Clock;
import com.example.draw.rtle.clocksource.service.ClockService;
import com.example.draw.rtle.utils.PageBean;

/** 
* @version 创建时间：2020年6月5日 上午11:48:33
*/
@Controller
@RequestMapping("/clock")
public class ClockController {
	@Autowired
	private ClockService clockService;
	//时钟页面
	@RequestMapping("/clockList")
	public String clockList(HttpServletRequest request,Model model,String ids,
			Clock clock,Map<String,Object> map,
			@RequestParam(required=false,defaultValue="1") int pages,
			@RequestParam(required=false,defaultValue="10") int num) {
		PageBean.conMap(pages, map, num, request,clock);
		clockService.clockList(map);
		if(ids == null) {
			map.put("flag", "clock");
			model.addAttribute("pageMap", map);
			return "clock/clock_list";
		} else if(ids != null) {
			List<Clock> clockList = new ArrayList<Clock>();
			if(ids.contains(",")) {
				String[] idArray = ids.split(",");
				for(String id : idArray) {
					clockList.add(clockService.selectByPrimaryKey(Integer.parseInt(id)));
				}
			}else {
				clockList.add(clockService.selectByPrimaryKey(Integer.parseInt(ids)));
			}
			model.addAttribute("clockListSelect", clockList);
			return "clock/clock_list";
		}else
			return "redirect:/index";
	}
	//添加时钟
	@RequestMapping("/addClock")
	public String addClock(Map<String,Object> map) {
		map.put("flag", "add");
		return "clock/clock_add";
	}
	//设置xyz坐标
	@RequestMapping("/setXYZ")
	public String setXYZ(Integer id, Model model) {
		Clock clock= clockService.selectByPrimaryKey(id);
		model.addAttribute("flag", "update");
		model.addAttribute("clock", clock);
		return "clock/clock_add";
	}
	//时钟保存
	@RequestMapping("/save")
	@ResponseBody
	public Object save(@RequestBody JSONObject params) throws UnknownHostException {
		Map<String,Object> map = new HashMap<>();
		String flag = params.getString("flag");
		if(flag.equals("add")) {

			String x = params.getString("x");
			String y = params.getString("y");
			String z = params.getString("z");
			String clockName = params.getString("clockName");
			String online = params.getString("online");
			
			Clock clock = new Clock();
			clock.setClockName(clockName);
			clock.setOnline(online);
			clock.setEnable(online);
			clock.setX(Float.parseFloat(x));
			clock.setY(Float.parseFloat(y));
			clock.setZ(Float.parseFloat(z));
			Properties props = System.getProperties();
			clock.setMac(props.get("os.name").toString());
			clock.setIp(InetAddress.getLocalHost().getHostAddress());
			String serNo = "";
			for(int i = 0;i<=7;i++){   //生成一个6位的序列号
				Integer spy=(int)(Math.random()*10);
				serNo+=spy.toString();
			}
			String clockSerNo = "CL"+serNo;
			clock.setSerialNo(clockSerNo);
			SimpleDateFormat df = new SimpleDateFormat("yyyyddHHmmss");
			clock.setColockId(df.format(new Date()));
			clockService.insert(clock);
		}else if(flag.equals("update")) {
			int id = Integer.parseInt((String) params.get("id"));
			String x = params.getString("x");
			String y = params.getString("y");
			String z = params.getString("z");
			String clockName = params.getString("clockName");
			String online = params.getString("online");
			
			Clock clock = clockService.selectByPrimaryKey(id);
			clock.setClockName(clockName);
			clock.setOnline(online);
			clock.setEnable(online);
			clock.setX(Float.parseFloat(x));
			clock.setY(Float.parseFloat(y));
			clock.setZ(Float.parseFloat(z));
			
			clockService.updateByPrimaryKey(clock);
		}

		map.put("msg", "success");
		return map;
	}
	//删除时钟
	@RequestMapping("delete")
	@ResponseBody
	public Object delete( int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		clockService.deleteByPrimaryKey(id);
		map.put("msg", "success");
		return map;
		
	}
	//批量删除时钟
	@RequestMapping("deleteAll")
	@ResponseBody
	public Object deleteAll( String ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(ids.contains(",")) {
			String[] idArray = ids.split(",");
			for(String id : idArray) {
				clockService.deleteByPrimaryKey(Integer.parseInt(id));
			}
		}else if(ids.length() > 0){
			clockService.deleteByPrimaryKey(Integer.parseInt(ids));
		}
		map.put("msg", "success");
		return map;
		
	}
	//查找时钟
	@RequestMapping("/select")
	@ResponseBody
	public Object select(String clockName) {
		List<Clock> clockList = clockService.getClockByName(clockName);
		Map<String,Object> map = new HashMap<>();
		if(clockList != null && clockList.size() > 0) {
			List<Integer> ids = new ArrayList<Integer>();
			for(Clock clock : clockList) {
				ids.add(clock.getId());
			}
			map.put("ids", ids);
			map.put("msg", "success");
		}else {
			map.put("msg", "fail");
		}
		return map;
	}
}
