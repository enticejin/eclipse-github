package com.example.draw.rtle.area.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.draw.rtle.area.mapper.AreaMapper;
import com.example.draw.rtle.area.model.Area;
import com.example.draw.rtle.area.service.AreaService;
import com.example.draw.rtle.userFile.model.UserFile;

/** 
* @version 创建时间：2020年7月10日 下午3:49:42
*/
@Service
public class AreaServiceImpl implements AreaService {
	@Autowired
	private AreaMapper areaMapper;
	@Override
	public List<Area> findAll() {
		
		return areaMapper.findAll();
	}

	@Override
	public void save(Area area) {
		areaMapper.save(area);
	}

	@Override
	public void delete(int id) {
		areaMapper.deleteById(id);

	}

	@Override
	public Area getOne(int id) {
		
		return areaMapper.getOne(id);
	}

	@Override
	public Optional<Area> findById(int id) {
		
		return areaMapper.findById(id);
	}

	@Override
	public List<Area> list(Area area, int page, int pageSize) {
		Pageable pageable = PageRequest.of(page-1,pageSize,Sort.Direction.DESC,"id");
		Page<Area> pageUserFile = areaMapper.findAll(new Specification<Area>() {
			@Override
			public javax.persistence.criteria.Predicate toPredicate(Root<Area> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				javax.persistence.criteria.Predicate predicate=cb.conjunction();
				predicate.getExpressions().add(cb.equal(root.get("flag"), 0));
				return predicate;
			}
		}, pageable);
		return pageUserFile.getContent();
	}

	@Override
	public Long getCount(Area area) {
		return areaMapper.count(new Specification<Area>() {
			@Override
			public javax.persistence.criteria.Predicate toPredicate(Root<Area> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				javax.persistence.criteria.Predicate predicate = cb.conjunction();
				predicate.getExpressions().add(cb.equal(root.get("flag"), 0));
				return predicate;
			}
		});
	}

	@Override
	public List<Area> getAreaByName(String areaName) {
		return areaMapper.findByAreaName(areaName);
	}

}
