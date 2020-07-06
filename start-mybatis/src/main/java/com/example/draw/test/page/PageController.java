package com.example.draw.test.page;
/** 
* @version 创建时间：2020年7月6日 上午10:14:35
*/

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.draw.rtle.model.Anchor;
import com.example.draw.rtle.service.AnchorService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/page")
public class PageController {
	@Autowired
	private AnchorService anchorService;
	@RequestMapping("/pageList")
	public Object pageList(Model model) {
		model.addAttribute("pageList", anchorService.getAnchorLsit());
		return "test/pages";
	}
	@RequestMapping("/selectusertable")
	@ResponseBody
	public Map<String,Object>selectByFy12(HttpSession session, @RequestParam(required=false,defaultValue="1") Integer page,
            @RequestParam(required=false) Integer limit) {
		// 使用Pagehelper传入当前页数和页面显示条数会自动为我们的select语句加上limit查询
		// 从他的下一条sql开始分页
		PageHelper.startPage(page, limit);
		List<Anchor> anchors = anchorService.getAnchorLsit();
		// 使用pageInfo包装查询
		PageInfo<Anchor> rolePageInfo = new PageInfo<>(anchors);//
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("code",0);
		map.put("msg","success");
		map.put("count",rolePageInfo.getTotal());
		map.put("data",rolePageInfo.getList());
		return map;
	}
}
