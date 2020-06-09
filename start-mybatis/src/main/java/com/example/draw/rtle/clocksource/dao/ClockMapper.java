package com.example.draw.rtle.clocksource.dao;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.draw.rtle.clocksource.model.Clock;
@Mapper
public interface ClockMapper {
    //删除时钟
    int deleteByPrimaryKey(Integer id);
    //添加时钟
    int insert(Clock record);
    //根据id查找时钟
    Clock selectByPrimaryKey(Integer id);
    //获取所有时钟
    List<Clock> selectAll();
    //更新时钟
    int updateByPrimaryKey(Clock record);
    //查询所有时钟
	List<Clock> clockList(Map<String, Object> map);
	//计算数量
	int count(Map<String, Object> map);
	//根据名称查找时钟
	List<Clock> getClockByName(String clockName);
}