package com.example.draw.rtle.userFile.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.draw.rtle.userFile.mapper.UserFileMapper;
import com.example.draw.rtle.userFile.model.UserFile;
import com.example.draw.rtle.userFile.service.UserFileService;

/** 
* @version 创建时间：2020年6月23日 下午2:33:13
*/
@Service
public class UserFileServiceImpl implements UserFileService {
	@Autowired
	private UserFileMapper userFileMapper;
	@Override
	public List<UserFile> findAll() {
		
		return userFileMapper.findAll();
	}

	@Override
	public void save(UserFile userFile) {
		userFileMapper.save(userFile);

	}

	@Override
	public void delete(int id) {
		userFileMapper.deleteById(id);
		
	}

	@Override
	public UserFile getOne(int id) {
		return userFileMapper.getOne(id);
	}

	@Override
	public Optional<UserFile> findById(int id) {
		
		return userFileMapper.findById(id);
	}

	
	

}
