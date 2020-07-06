package com.example.draw.rtle.userFile.mapper;

import java.io.Serializable;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.draw.rtle.userFile.model.UserFile;


/** 
* @version 创建时间：2020年6月23日 下午2:28:04
*/
@Repository
public interface UserFileMapper extends JpaRepository<UserFile, Integer>, Serializable {

	Long count(Specification<UserFile> specification);

	Page<UserFile> findAll(Specification<UserFile> specification, Pageable pageable);
    
}
