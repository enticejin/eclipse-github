package com.example.draw.rtle.rtle.service;

import com.example.draw.rtle.rtle.model.Rtle;

/** 
* @version 创建时间：2020年6月9日 上午10:29:45
*/
public interface RtleService {
	int deleteByPrimaryKey(Integer id);

    int insert(Rtle record);

    int insertSelective(Rtle record);

    Rtle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Rtle record);

    int updateByPrimaryKey(Rtle record);
    
    Rtle findAll();
}
