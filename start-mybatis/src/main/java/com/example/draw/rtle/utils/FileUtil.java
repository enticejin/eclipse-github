package com.example.draw.rtle.utils;

import java.io.File;

/** 
* @version 创建时间：2020年6月24日 上午10:55:39
*/
public class FileUtil {
	/**
	 * 根据文件名删除指定文件
	 * @param file 文件对象
	 * @param name	文件名称
	 */
	public void deleteDirectory(File file,String name) {
		if (file.isFile()) {// 表示该文件不是文件夹
			file.delete();
		} else {
			// 首先得到当前的路径
			String[] childFilePaths = file.list();
			for (String childFilePath : childFilePaths) {
				File childFile = new File(file.getAbsolutePath() + "/" + childFilePath);
				if(name.equals(childFilePath)) {
					deleteDirectory(childFile,name);
				}
			}
			file.delete();
		}
	}
}
