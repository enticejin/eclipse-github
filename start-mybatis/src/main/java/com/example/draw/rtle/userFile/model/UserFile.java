package com.example.draw.rtle.userFile.model;
/** 
* @version 创建时间：2020年6月23日 下午2:13:47
*/

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity//实体类
@Table(name="user_file",indexes = {@Index(name="id", columnList = "id" , unique = true),
			@Index(name="file_name", columnList = "file_name", unique = true)
})

public class UserFile {
	@Id//主键ID
	@GeneratedValue(strategy = GenerationType.IDENTITY)//@GeneratedValue： 表明是否自动生成, 必须, strategy也是必写, 指明主键生成策略, 默认是Oracle
	private int id;
	
	@Column(name = "file_name", nullable = false)// @Column： 对应数据库列名,可选, nullable 是否可以为空, 默认true
	private String filName;
	@Column(name = "file_size", nullable = false)
	private String fileSize;
	@Column(name = "flag", nullable = false)
	private String flag;
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFilName() {
		return filName;
	}
	public void setFilName(String filName) {
		this.filName = filName;
	}
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	@Override
	public String toString() {
		return "UserFile [id=" + id + ", filName=" + filName + ", fileSize=" + fileSize + ", flag=" + flag + "]";
	}
	
	

}
