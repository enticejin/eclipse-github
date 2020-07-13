package com.example.draw.rtle.area.mapper;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.draw.rtle.area.model.Area;
@Repository
public interface AreaMapper extends JpaRepository<Area, Integer>, Serializable {

	Page<Area> findAll(Specification<Area> specification, Pageable pageable);

	Long count(Specification<Area> specification);
	
	List<Area> findByAreaName(String areaName);
	
}