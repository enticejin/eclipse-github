package com.example.draw.rtle.dao;
/** 
* @version 创建时间：2020年6月2日 下午3:10:49
*/

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.draw.rtle.model.Anchor;

@Mapper
public interface AnchorDao {
	//获取所有基站数据
	List<Anchor> getAnchorLsit();
	//添加基站
	void addAnchor(Anchor anchor);
	//删除基站
	void deleteAnchor(int id);
	//更新基站
	void updateAnchor(Anchor anchor);
	//查询基站
	Anchor getAnchorById(int id);
	//根据基站名称查询基站
	List<Anchor> getAnchorByName(String anchorName);
	List<Anchor> anchorList(Map<String, Object> map);
	int count(Map<String, Object> map);
}
