package com.example.draw.rtle.service;

import java.util.List;

import com.example.draw.rtle.model.User;

/** 
* @version 创建时间：2020年6月2日 下午3:45:10
*/
public interface UserService {
	//获取所有用户
	List<User> getUserList();
	//添加用户
	void addUser(User user);
	//更新用户
	void updateUser(User user);
	//删除用户
	void deleteUser(int id);	
	//查询用户
	User getUserById(int id);
}
