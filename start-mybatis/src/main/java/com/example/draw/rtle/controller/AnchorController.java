package com.example.draw.rtle.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.example.draw.rtle.model.Anchor;
import com.example.draw.rtle.model.User;
import com.example.draw.rtle.service.AnchorService;
import com.example.draw.rtle.utils.PageBean;

/** 
* @version 创建时间：2020年6月2日 下午3:18:54
*/
@Controller
@RequestMapping("/anchor")
public class AnchorController {
	@Autowired
	private AnchorService anchorService;
	@RequestMapping("/anchorList")
	public String anchorList() {
		return "anchor/anchor_config";
	}
	/**
	 * 添加基站
	 * @param model
	 * @param flag 判断是添加还是更新
	 * @param id 基站id
	 * @return
	 */
	@RequestMapping("/addAnchor")
	public String addAnchor(Model model,String flag,String id) {
		if(flag == "" || flag == null) {
			model.addAttribute("flag", "add");
		}else {
			model.addAttribute("flag", "update");
			Anchor anchor = anchorService.getAnchorById(Integer.parseInt(id));
			model.addAttribute("anchor", anchor);
		}
		return "anchor/anchor_add";
	}
	//基站配置页面
	@RequestMapping("/anchor_config")
	public String anchor_config(HttpServletRequest request,Model model,String ids,
			Anchor anchor,Map<String,Object> map1,
			@RequestParam(required=false,defaultValue="1") int pages,
			@RequestParam(required=false,defaultValue="10") int num) {
		PageBean.conMap(pages, map1, num, request, anchor);
		anchorService.anchorList(map1);
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user != null && ids == null) {
			Map<String , String> map = new HashMap<String, String>();
			//装入登录的用户信息
			map.put("attachment_url", "staticres/images/adminphoto.jpg");
			map.put("nickname", user.getName());
			map.put("name", user.getName());
			map.put("sysname", "RTLEweb管理端");
			map1.put("flag", "anchor");
			//model.addAttribute("anchorlist",anchorService.getAnchorLsit());
			model.addAttribute("current_login_user", map);
			model.addAttribute("pageMap",map1);
			return "anchor/anchor_config";
		}else if(ids != null) {
			List<Anchor> anchorList = new ArrayList<Anchor>();
			if(ids.contains(",")) {
				String[] idArray = ids.split(",");
				for(String id : idArray) {
					anchorList.add(anchorService.getAnchorById(Integer.parseInt(id)));
				}
			}else {
				anchorList.add(anchorService.getAnchorById(Integer.parseInt(ids)));
			}
			Map<String , String> map = new HashMap<String, String>();
			//装入登录的用户信息
			map.put("attachment_url", "staticres/images/adminphoto.jpg");
			map.put("nickname", user.getName());
			map.put("name", user.getName());
			map.put("sysname", "RTLEweb管理端");
			model.addAttribute("anchorlistSelect",anchorList);
			model.addAttribute("current_login_user", map);
			
			return "anchor/anchor_config";
		}else
		return "redirect:/login";
	}
	//基站保存
	@CrossOrigin
	@RequestMapping(value="/save", method = RequestMethod.POST)
	@ResponseBody
	public Object save(@RequestBody JSONObject params) throws UnknownHostException {
		Map<String,Object> map = new HashMap<>();
		String flag = params.getString("flag");
		String x = params.getString("x");
		String y = params.getString("y");
		String z = params.getString("z");
		String anchorName = params.getString("anchorName");
		String online = params.getString("online");
		//查询所有基站
		List<Anchor> anchorList = anchorService.getAnchorLsit();
		if(flag.equals("add")) {
			Anchor anchor = new Anchor();
			//判断是否基站重名
			if(validateName(anchorName, anchorList)) {
				anchor.setAnchorName(anchorName);
				anchor.setOnline(online);
				anchor.setEnable(online);
				anchor.setX(Float.parseFloat(x));
				anchor.setY(Float.parseFloat(y));
				anchor.setZ(Float.parseFloat(z));
				Properties props = System.getProperties();
				anchor.setMac(props.get("os.name").toString());
				anchor.setIp(InetAddress.getLocalHost().getHostAddress());
				String serNo = "";
				for(int i = 0;i<=7;i++){   //生成一个6位的序列号
					Integer spy=(int)(Math.random()*10);
					serNo+=spy.toString();
				}
				String AnchorSerNo = "AN"+serNo;
				anchor.setSerialNo(AnchorSerNo);
				SimpleDateFormat df = new SimpleDateFormat("yyyyddHHmmss");
				anchor.setAnchorId(df.format(new Date()));
				anchorService.addAnchor(anchor);
				map.put("msg", "success");
			}else {
				map.put("msg", "fail");
			}
		}else if(flag.equals("update")) {
			if(validateName(anchorName, anchorList)) {
				String id = params.getString("id");
				Anchor anchor = anchorService.getAnchorById(Integer.parseInt(id));
				anchor.setX(Float.parseFloat(x));
				anchor.setY(Float.parseFloat(y));
				anchor.setZ(Float.parseFloat(z));
				anchor.setAnchorName(anchorName);
				anchor.setOnline(online);
				anchorService.updateAnchor(anchor);
				map.put("msg", "success");
			}else {
				map.put("msg", "fail");
			}
		}
		return map;
	}	
	//设置XYZ坐标
	@RequestMapping("/setXYZ")
	@ResponseBody
	public Object setXYZ(@RequestBody JSONObject params) {
		Map<String,Object> map = new HashMap<>();
		String id = params.getString("id");
		Anchor anchor = anchorService.getAnchorById(Integer.parseInt(id));
		map.put("anchor", anchor);
		map.put("msg", "update");
		return map;
	}
	//删除基站
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(int id) {
		Map<String,Object> map = new HashMap<>();
		anchorService.deleteAnchor(id);
		map.put("msg", "success");
		return map;
	}
	
	//查询基站
	@RequestMapping("/select")
	@ResponseBody
	public Object select(String anchorName) {
		List<Anchor> anchorList = anchorService.getAnchorByName(anchorName);
		
		Map<String,Object> map = new HashMap<>();
		if(anchorList != null && anchorList.size() > 0) {
			List<Integer> ids = new ArrayList<Integer>();
			for(Anchor anchor: anchorList) {
				ids.add(anchor.getId());
			}
			map.put("ids", ids);
			map.put("msg", "success");
		}else {
			map.put("msg", "fail");
		}
		return map;
	}
	//判断基站是否重名
	public boolean validateName(String anchorName, List<Anchor> anchorList) {
		boolean judge = true;
		for(int i =0;i < anchorList.size();i++) {
				if(anchorName.equals(anchorList.get(i).getAnchorName())) {
					//重名
					return !judge;
				}
				if(judge == false) {
					break;
				}
		}
		return judge;
	}
}
