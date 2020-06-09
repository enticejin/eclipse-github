package com.example.draw.rtle.rtle.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.draw.rtle.rtle.mapper.RtleMapper;
import com.example.draw.rtle.rtle.model.Rtle;
import com.example.draw.rtle.rtle.service.RtleService;

/** 
* @version 创建时间：2020年6月9日 上午10:31:55
*/
@Service
public class RtleServiceImpl implements RtleService {
	@Autowired
	private RtleMapper rtleMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		return rtleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Rtle record) {
		
		return rtleMapper.insert(record);
	}

	@Override
	public int insertSelective(Rtle record) {
		
		return rtleMapper.insertSelective(record);
	}

	@Override
	public Rtle selectByPrimaryKey(Integer id) {
		
		return rtleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Rtle record) {
		
		return rtleMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Rtle record) {
		
		return rtleMapper.updateByPrimaryKey(record);
	}

	@Override
	public Rtle findAll() {
		
		return rtleMapper.findAll();
	}

}
