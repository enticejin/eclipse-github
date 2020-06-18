package com.example.draw.rtle.area.service;

import java.util.List;
import java.util.Map;

import com.example.draw.rtle.area.model.Area;

/** 
* @version 创建时间：2020年6月16日 上午9:21:18
*/
public interface AreaService {
	int deleteByPrimaryKey(Integer id);

    int insert(Area record);

    int insertSelective(Area record);

    Area selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);

	List<Area> getAreaLsit();

	Map<String, Object> areaList(Map<String, Object> map);
}
