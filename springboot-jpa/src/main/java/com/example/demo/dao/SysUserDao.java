package com.example.demo.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.SysUser;

public interface SysUserDao extends JpaRepository<SysUser, Long>, Serializable {

}
