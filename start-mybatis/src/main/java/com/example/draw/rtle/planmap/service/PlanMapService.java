package com.example.draw.rtle.planmap.service;

import java.util.List;

import com.example.draw.rtle.planmap.model.PlanMap;

/** 
* @version 创建时间：2020年7月14日 上午10:26:00
*/
public interface PlanMapService {
	List<PlanMap> findAll();

	Object list(PlanMap planMap, int page, int limit);

	Object getCount(PlanMap planMap);

	void insert(PlanMap planMap);

	void delelte(int id);

	PlanMap findPlan(int id);

	PlanMap getOne(int id);

	List<PlanMap> findAllByName(String name);
}
