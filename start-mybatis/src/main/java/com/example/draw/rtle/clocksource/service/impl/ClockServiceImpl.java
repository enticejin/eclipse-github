package com.example.draw.rtle.clocksource.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.draw.rtle.clocksource.dao.ClockMapper;
import com.example.draw.rtle.clocksource.model.Clock;
import com.example.draw.rtle.clocksource.service.ClockService;
import com.example.draw.rtle.utils.PageBean;

/** 
* @version 创建时间：2020年6月5日 上午11:41:16
*/
@Service
public class ClockServiceImpl implements ClockService {
	@Autowired
	private ClockMapper clockMapper;
	//删除
	@Override
	public void deleteByPrimaryKey(Integer id) {
		clockMapper.deleteByPrimaryKey(id);
	}
	//添加
	@Override
	public void insert(Clock record) {
		clockMapper.insert(record);
	}
	//根据id选择
	@Override
	public Clock selectByPrimaryKey(Integer id) {
		return clockMapper.selectByPrimaryKey(id);
	}
	//查询所有
	@Override
	public List<Clock> selectAll() {
		return clockMapper.selectAll();
	}
	//更新
	@Override
	public void updateByPrimaryKey(Clock record) {
		clockMapper.updateByPrimaryKey(record);
	}
	@Override
	public Map<String, Object> clockList(Map<String, Object> map) {
		List<Clock> clockList = clockMapper.clockList(map);
		int count = clockMapper.count(map);
		PageBean.serMap(map, count, clockList, Clock.class);
		return map;
	}
	@Override
	public List<Clock> getClockByName(String clockName) {
		
		return clockMapper.getClockByName(clockName);
	}

}
