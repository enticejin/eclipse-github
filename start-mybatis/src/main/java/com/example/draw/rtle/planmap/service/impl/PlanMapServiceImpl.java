package com.example.draw.rtle.planmap.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.draw.rtle.planmap.dao.PlanMapDao;
import com.example.draw.rtle.planmap.model.PlanMap;
import com.example.draw.rtle.planmap.service.PlanMapService;

/** 
* @version 创建时间：2020年7月14日 上午10:26:47
*/
@Service
public class PlanMapServiceImpl implements PlanMapService {
	@Autowired
	private PlanMapDao dao;
	@Override
	public List<PlanMap> findAll() {
		
		return dao.findAll();
	}
	//分页
	@Override
	public Object list(PlanMap planMap, int page, int limit) {
		Pageable pageAble = PageRequest.of(page-1,limit, Sort.Direction.DESC,"id");
		Page<PlanMap> pagePlanMap = dao.findAll(new Specification<PlanMap>() {

			@Override
			public Predicate toPredicate(Root<PlanMap> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				Predicate predicate = criteriaBuilder.conjunction();
				predicate.getExpressions().add(criteriaBuilder.equal(root.get("flag"), 0));
				return predicate;
			}
			
		}, pageAble);
		return pagePlanMap.getContent();
	}
	@Override
	public Object getCount(PlanMap planMap) {
		
		return dao.count(new Specification<PlanMap>() {

			@Override
			public Predicate toPredicate(Root<PlanMap> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				Predicate predicate= criteriaBuilder.conjunction();
				predicate.getExpressions().add(criteriaBuilder.equal(root.get("flag"),0));
				return predicate;
			}
			
		});
	}
	@Override
	public void insert(PlanMap planMap) {
		dao.save(planMap);
	}
	@Override
	public void delelte(int id) {
		dao.deleteById(id);
	}
	@Override
	public PlanMap findPlan(int id) {
		return dao.getOne(id);
	}
	@Override
	public PlanMap getOne(int id) {
		
		return dao.getOne(id);
	}
	@Override
	public List<PlanMap> findAllByName(String name) {
		List<PlanMap> resultList = null;
		Specification querySpecifi = new Specification<PlanMap>() {
            public Predicate toPredicate(Root<PlanMap> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(null != name){
                    predicates.add(criteriaBuilder.like(root.get("planMapName"), "%"+name+"%"));
                }
 
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        resultList =  dao.findAll(querySpecifi);
		return resultList;
	}

}
