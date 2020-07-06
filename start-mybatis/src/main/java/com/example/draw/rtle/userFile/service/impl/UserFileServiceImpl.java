package com.example.draw.rtle.userFile.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.sql.rowset.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.draw.rtle.userFile.mapper.UserFileMapper;
import com.example.draw.rtle.userFile.model.UserFile;
import com.example.draw.rtle.userFile.service.UserFileService;

/** 
* @version 创建时间：2020年6月23日 下午2:33:13
*/
@Service
public class UserFileServiceImpl implements UserFileService {
	@Autowired
	private UserFileMapper userFileMapper;
	
	@Override
	public List<UserFile> findAll() {
		return userFileMapper.findAll();
	}

	@Override
	public void save(UserFile userFile) {
		userFileMapper.save(userFile);

	}

	@Override
	public void delete(int id) {
		userFileMapper.deleteById(id);
		
	}

	@Override
	public UserFile getOne(int id) {
		return userFileMapper.getOne(id);
	}

	@Override
	public Optional<UserFile> findById(int id) {
		
		return userFileMapper.findById(id);
	}

	@Override
	public List<UserFile> list(UserFile userFile, int page, int pageSize) {
		Pageable pageable = PageRequest.of(page-1,pageSize,Sort.Direction.DESC,"id");
		Page<UserFile> pageUserFile = userFileMapper.findAll(new Specification<UserFile>() {
			@Override
			public javax.persistence.criteria.Predicate toPredicate(Root<UserFile> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				javax.persistence.criteria.Predicate predicate=cb.conjunction();
				predicate.getExpressions().add(cb.equal(root.get("flag"), 0));
				return predicate;
			}
		}, pageable);
		return pageUserFile.getContent();
	}

	@Override
	public Long getCount(UserFile userFile) {
		return userFileMapper.count(new Specification<UserFile>() {
			@Override
			public javax.persistence.criteria.Predicate toPredicate(Root<UserFile> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				javax.persistence.criteria.Predicate predicate = cb.conjunction();
				predicate.getExpressions().add(cb.equal(root.get("flag"), 0));
				return predicate;
			}
		});
	}
}
