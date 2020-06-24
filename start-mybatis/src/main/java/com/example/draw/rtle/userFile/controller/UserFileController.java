package com.example.draw.rtle.userFile.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.example.draw.rtle.userFile.model.UserFile;
import com.example.draw.rtle.userFile.service.UserFileService;
import com.example.draw.rtle.utils.FileUtil;


/** 
* @version 创建时间：2020年6月23日 下午2:44:06
*/
@Controller
@RequestMapping("/userFile")
public class UserFileController {
	
	@Autowired 
	private UserFileService userFileService;
	/*
	 * 用户文件列表页
	 */
	@RequestMapping("/userFileList")
	public String findAll(Model model) {
		List<UserFile> userFileList = userFileService.findAll();
		model.addAttribute("varList", userFileList);
		return "userfile/userfile_list";
	}
	/*
	 * 用户文件添加
	 */
	@RequestMapping("/addFile")
	public String userFileAdd() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flag", "add");
		return "userfile/userfile_add";
	}
	/**
	 * 预览图片
	 * @param id 文件id
	 * @param model model对象
	 * @return
	 */
	@RequestMapping("/openFile")
	public String openFile(int id, Model model) {
		 String fileName = getUserFile(userFileService.findById(id)).getFilName();
		 model.addAttribute("fileName", fileName);
		 model.addAttribute("flag", "open");
		 return "userfile/userfile_add";
	}
	/**
	 * 删除文件
	 * @param id 文件id
	 * @return
	 */
	@RequestMapping("deleteFile")
	@ResponseBody
	public Object deleteFile(int id) {
		Map<String, Object> map = new HashMap<String, Object>();
		FileUtil fileUtil = new FileUtil();
		Optional<UserFile> userFileOpt = userFileService.findById(id);
		
		String fileName = getUserFile(userFileOpt).getFilName();
		File upload = new File("src\\main\\resources\\static\\upload");
		String[] list=new File(upload.getAbsolutePath()).list();
	    for(String li : list) {
	    	if(li.equals(fileName)) {
	    		//从数据库删除文件名称
	    		userFileService.delete(id);
	    		//从本地删除文件
	    		fileUtil.deleteDirectory(upload, fileName);
	    	}
	    }
		map.put("msg", "success");
		return map;
	}
	//Opt使用
	private UserFile getUserFile(Optional<UserFile> userFileOpt) {
		return userFileOpt.isPresent()?userFileOpt.get():null;
	}
	/**
	 * 文件上传
	 * @param file 前端获取的文件
	 * @param request servlet请求
	 * @return
	 * @throws IOException IO异常
	 */
	@RequestMapping(value="/uploadSource" , method = RequestMethod.POST)
	@ResponseBody
	public Object uploadSource(@RequestParam("file") MultipartFile file , HttpServletRequest request) throws IOException {
		JSONObject json = new JSONObject();
		String pre= UUID.randomUUID().toString();
		pre = pre.replace("-", "");
		String fileName = file.getOriginalFilename();
		fileName = fileName.substring(fileName.lastIndexOf('.'), fileName.length());
		fileName= pre + fileName;
		UserFile userFile = new UserFile(); 
		userFile.setFilName(fileName);
		userFile.setFileSize(String.valueOf(file.getSize()));
		userFileService.save(userFile);
		String pathString = null;
		File upload = new File("src\\main\\resources\\static\\upload");
		String absolutePath = upload.getAbsolutePath();
		System.out.println("绝对路径："+absolutePath);
		if(file!=null) {
			pathString = absolutePath+"\\"+fileName;
		}
		
		try {
			File files=new File(pathString);
            //打印查看上传路径
            if(!files.getParentFile().exists()){
                files.getParentFile().mkdirs();
            }
			file.transferTo(files);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		json.put("flag", "add");
		json.put("code", 1);
		json.put("msg", "success");
		return json;
	}
}
