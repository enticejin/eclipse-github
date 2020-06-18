package com.example.draw.rtle.area.mapper;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.draw.rtle.area.model.Area;
@Mapper
public interface AreaMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Area record);

    int insertSelective(Area record);

    Area selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);

	List<Area> getAreaLsit();

	List<Area> areaList(Map<String, Object> map);

	int count(Map<String, Object> map);
}