package com.example.draw.rtle.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.draw.rtle.dao.AnchorDao;
import com.example.draw.rtle.model.Anchor;
import com.example.draw.rtle.service.AnchorService;
import com.example.draw.rtle.utils.PageBean;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/** 
* @version 创建时间：2020年6月2日 下午3:12:54
*/
@Service
public class AnchorServiceImpl implements AnchorService {
	@Autowired
	private AnchorDao anchorDao;
	//返回所有基站数据
	@Override
	public List<Anchor> getAnchorLsit() {
		return anchorDao.getAnchorLsit();
	}
	//添加基站
	@Override
	public void addAnchor(Anchor anchor) {
		anchorDao.addAnchor(anchor);
		
	}
	//删除基站
	@Override
	public void deleteAnchor(int id) {
		anchorDao.deleteAnchor(id);
		
	}
	//更新基站
	@Override
	public void updateAnchor(Anchor anchor) {
		anchorDao.updateAnchor(anchor);
		
	}
	//根据id查询基站
	@Override
	public Anchor getAnchorById(int id) {
		
		return anchorDao.getAnchorById(id);
	}
	//根据基站名称查询基站
	@Override
	public List<Anchor> getAnchorByName(String anchorName) {
		
		return anchorDao.getAnchorByName(anchorName);
	}
	@Override
	public Map<String, Object> anchorList(Map<String, Object> map) {
		List<Anchor> anchorList = anchorDao.anchorList(map);
		int count = anchorDao.count(map);
		PageBean.serMap(map, count, anchorList, Anchor.class);
		return map;
	}

}
