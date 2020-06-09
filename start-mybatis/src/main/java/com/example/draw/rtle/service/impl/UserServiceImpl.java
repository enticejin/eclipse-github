package com.example.draw.rtle.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.draw.rtle.dao.UserDao;
import com.example.draw.rtle.model.User;
import com.example.draw.rtle.service.UserService;

/** 
* @version 创建时间：2020年6月2日 下午3:46:36
*/
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserDao userDao;
	@Override
	public List<User> getUserList() {
		return userDao.getUserList();
	}

	@Override
	public void addUser(User user) {
		userDao.addUser(user);
		
	}

	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
		
	}

	@Override
	public void deleteUser(int id) {
		userDao.deleteUser(id);
		
	}

	@Override
	public User getUserById(int id) {
		
		return userDao.getUserById(id);
	}
	
}
