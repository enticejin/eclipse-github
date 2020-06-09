package com.example.draw.rtle.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.example.draw.rtle.model.User;
import com.example.draw.rtle.service.UserService;

/** 
* @version 创建时间：2020年6月2日 下午3:54:31
*/
@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	//前往登录页面
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	//处理登录请求
	@RequestMapping(value = "/loginDo")
	@ResponseBody//加上该注解表明该方法返回值均自动转为json格式传给前端
	public Object loginDo(@RequestBody JSONObject params,HttpServletRequest request) {
		
		//@RequestBody只能给方法的参数注解，表明一次接收到请求参数
        //JSONObject为alibaba的fastjson包下类，推荐使用。另外也可以使用String来接收前端的json格式传参。
		Map<String,Object> map = new HashMap<>();
		//接收参数
		String userName = params.getString("userName");
		String password = params.getString("password");
		//调用service
		List<User> userList = userService.getUserList();
		User user = validateLogin(userName, password, userList);
		//验证成功
		if(user != null) {
			map.put("msg", "success");
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
		}else {
			map.put("msg", "fail");
		}
		return map;
	}
	//前往首页
	@RequestMapping("/index")
	public String index(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user != null) {
			Map<String, String> map = new HashMap<String, String>();
			//装入登录的用户信息
			map.put("attachment_url", "staticres/images/adminphoto.jpg");
			map.put("nickname", user.getName());
			map.put("name", user.getName());
			map.put("sysname", "RTLEweb管理端");
			model.addAttribute("current_login_user", map);
			return "index";
		}else
		return "login";
	}
	
	//验证登录信息
	public User validateLogin(String userName, String password, List<User> userList) {
		for(int i =0; i < userList.size();i++) {
			if(userName != null && password != null
					&&userName.trim().length() > 0 && password.trim().length() > 0
					&&userList.get(i).getName().equals(userName) 
					&& userList.get(i).getPassword().equals(password)) {
				//登录名密码正确
				return userList.get(i);
			}
		}
		//登录名密码错误
		return null;
	}
}
