package com.example.draw.rtle.area.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.draw.rtle.area.mapper.AreaMapper;
import com.example.draw.rtle.area.model.Area;
import com.example.draw.rtle.area.service.AreaService;
import com.example.draw.rtle.utils.PageBean;

/** 
* @version 创建时间：2020年6月16日 上午9:22:14
*/
@Service
public class AreaServiceImpl implements AreaService {
	@Autowired
	private AreaMapper areaMapper;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		return areaMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Area record) {
		
		return areaMapper.insert(record);
	}

	@Override
	public int insertSelective(Area record) {
		
		return areaMapper.insertSelective(record);
	}

	@Override
	public Area selectByPrimaryKey(Integer id) {
		
		return areaMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Area record) {
		
		return areaMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Area record) {
		
		return areaMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Area> getAreaLsit() {
		
		return areaMapper.getAreaLsit();
	}

	@Override
	public Map<String, Object> areaList(Map<String, Object> map) {
		List<Area> areaList =  areaMapper.areaList(map);
		int count = areaMapper.count(map);
		PageBean.serMap(map, count, areaList, Area.class);
		return map;
	}

}
