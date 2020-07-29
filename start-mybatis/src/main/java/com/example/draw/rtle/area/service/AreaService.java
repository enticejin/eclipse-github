package com.example.draw.rtle.area.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.draw.rtle.area.model.Area;


/** 
* @version 创建时间：2020年7月10日 下午3:16:12
*/
public interface AreaService {
	/*
	 *获取所有文件信息
	 */
	public List<Area> findAll();
	/*
	 * 保存
	 */
	public void save(Area area);
	
	/*
	 * 删除
	 */
	public void delete(int id);
	/*
	 * 根据id查询
	 */
	public Area getOne(int id);
	/*
	 * 根据id查询
	 */
	public Optional<Area>  findById(int id);
	/**
	 * 分页
	 * @param Area
	 * @param page
	 * @param limit
	 * @return
	 */
	public List<Area> list(Area area, int page, int limit);
	/**
	 * 获取总数
	 * @param Area
	 * @return
	 */
	public Long getCount(Area area);
	/**
	 * 按名称查询区域
	 * @param areaName
	 * @return
	 */
	public List<Area> findAllByName(String areaName);
}
