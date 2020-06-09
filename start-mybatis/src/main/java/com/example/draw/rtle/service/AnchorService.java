package com.example.draw.rtle.service;

import java.util.List;
import java.util.Map;

import com.example.draw.rtle.model.Anchor;

/** 
* @version 创建时间：2020年6月2日 下午3:12:06
*/
public interface AnchorService {
	//获取所有基站
	List<Anchor> getAnchorLsit();
	//添加基站
	void addAnchor(Anchor anchor);
	//删除基站
	void deleteAnchor(int id);
	//更新基站
	void updateAnchor(Anchor anchor);
	//根据id查询基站
	Anchor getAnchorById(int id);
	//根据基站名称查询基站
	List<Anchor> getAnchorByName(String anchorName);
	Map<String, Object> anchorList(Map<String, Object> map);
}
