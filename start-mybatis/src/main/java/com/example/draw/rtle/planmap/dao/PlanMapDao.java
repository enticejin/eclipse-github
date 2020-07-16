package com.example.draw.rtle.planmap.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.draw.rtle.planmap.model.PlanMap;

/** 
* @version 创建时间：2020年7月14日 上午10:24:47
*/
@Repository
public interface PlanMapDao extends JpaRepository<PlanMap, Integer>, Serializable {

	Page<PlanMap> findAll(Specification<PlanMap> specification, Pageable pageAble);

	Object count(Specification<PlanMap> specification);

	List<PlanMap> findAll(Specification querySpecifi);
	
}
