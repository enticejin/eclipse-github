package com.example.draw.rtle.userFile.service;

import java.util.List;
import java.util.Optional;
import com.example.draw.rtle.userFile.model.UserFile;

/** 
* @version 创建时间：2020年6月23日 下午2:29:35
*/
public interface UserFileService {
	/*
	 *获取所有文件信息
	 */
	public List<UserFile> findAll();
	/*
	 * 保存
	 */
	public void save(UserFile userFile);
	
	/*
	 * 删除
	 */
	public void delete(int id);
	/*
	 * 根据id查询
	 */
	public UserFile getOne(int id);
	/*
	 * 根据id查询
	 */
	public Optional<UserFile>  findById(int id);
	
	
}
