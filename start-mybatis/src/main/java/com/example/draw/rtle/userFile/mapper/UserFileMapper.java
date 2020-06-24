package com.example.draw.rtle.userFile.mapper;

import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.draw.rtle.userFile.model.UserFile;

/** 
* @version 创建时间：2020年6月23日 下午2:28:04
*/
public interface UserFileMapper extends JpaRepository<UserFile, Integer>, Serializable {
	
}
