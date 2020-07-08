package com.example.draw.rtle.users.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.example.draw.rtle.model.User;
import com.example.draw.rtle.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/** 
* @version 创建时间：2020年7月7日 下午4:38:12
*/
@Controller
@RequestMapping(value = "user")
public class UserController {
	@Autowired
	private UserService userService;
	/**
	 * 用户页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "userList")
	public String userList(Model model) {
		model.addAttribute("userList", userService.getUserList());
		return "users/user_list";
	}
	/**
	 * 分页显示页面
	 * @param page 第几页
	 * @param limit 每一页多少条
	 * @return
	 */
	@RequestMapping("/selectUserTable")
	@ResponseBody
	public Object selectUserTable(@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false)int limit) {
		//开始分页
		PageHelper.startPage(page, limit);
		Map<String, Object> map = new HashMap<String, Object>();
		//获取所有数据
		List<User> userList = userService.getUserList();
		//执行分页
		PageInfo<User> rolePageInfo= new PageInfo<User>(userList);
		map.put("code", 0);
		map.put("msg", "success");
		map.put("count", rolePageInfo.getTotal());
		map.put("data", rolePageInfo.getList());
		return map;
	}
	//添加用户
	@RequestMapping("/addUser")
	public String addUser(Model model) {
		model.addAttribute("flag", "add");
		return "users/user_edit";
	}
	//更改用户
	@RequestMapping("/updateUser")
	public String updateUser(Model model, int id) {
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		model.addAttribute("id", id);
		model.addAttribute("flag", "update");
		return "users/user_edit";
	}
	//判断名称是否存在
	@RequestMapping("/existName")
	@ResponseBody
	public Object existName(@RequestBody JSONObject params) {
		Map<String, Object>map = new HashMap<String, Object>();
		String userName = params.getString("userName");
		//String flag = params.getString("flag");
		int num  = 0;
		for(User user : userService.getUserList()) {
			if(user.getName().equals(userName)) {
				num = 1;
				//1表示存在该用户
				map.put("msg", num);
			}
		}
		if(num == 0) {
			map.put("msg", num);
		}
		return map;
	}
	//保存
	@RequestMapping("/save")
	@ResponseBody
	public Object save(@RequestBody JSONObject params) {
		Map<String, Object>map = new HashMap<String, Object>();
		String userName = params.getString("userName");
		String flag = params.getString("flag");
		String password = params.getString("password");
		if(flag.equals("add")) {
			User user = new User();
			user.setName(userName);
			user.setPassword(password);
			userService.addUser(user);
			map.put("msg", "success");
		}else if(flag.equals("update")) {
			int id = Integer.parseInt(params.getString("id"));
			User user = userService.getUserById(id);
			user.setName(userName);
			user.setPassword(password);
			userService.updateUser(user);
			map.put("msg", "success");
		}
		return map;
	}
	//删除
	@RequestMapping("deleteUser")
	@ResponseBody
	public Object deleteUser(int id) {
		userService.deleteUser(id);
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("msg", "success");
		return map;
	}
}
