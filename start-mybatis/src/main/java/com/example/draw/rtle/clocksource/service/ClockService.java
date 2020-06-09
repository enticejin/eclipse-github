package com.example.draw.rtle.clocksource.service;

import java.util.List;
import java.util.Map;

import com.example.draw.rtle.clocksource.model.Clock;

/** 
* @version 创建时间：2020年6月5日 上午11:40:11
*/
public interface ClockService {
	 //删除时钟
    void deleteByPrimaryKey(Integer id);
    //添加时钟
    void insert(Clock clock);
    //根据id查找时钟
    Clock selectByPrimaryKey(Integer id);
    //获取所有时钟
    List<Clock> selectAll();
    //更新时钟
    void updateByPrimaryKey(Clock clock);
	Map<String, Object> clockList(Map<String, Object> map);
	//根据名称查找时钟
	List<Clock> getClockByName(String clockName);
	
}
