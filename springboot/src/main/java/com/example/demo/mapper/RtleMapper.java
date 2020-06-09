package com.example.demo.mapper;

import com.example.demo.model.Rtle;

public interface RtleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Rtle record);

    int insertSelective(Rtle record);

    Rtle selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Rtle record);

    int updateByPrimaryKey(Rtle record);
}