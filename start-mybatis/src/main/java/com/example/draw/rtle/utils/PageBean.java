package com.example.draw.rtle.utils;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


/** 
* @version 创建时间：2020年6月4日 下午4:03:40
*/
public class PageBean {
	/**
	 * 查询所有的页面
	 * @param pages
	 * @param map
	 * @param num
	 * @param request
	 * @param obj
	 */
		public static void conMap(int pages,Map<String, Object> map,int num,HttpServletRequest request,Object obj){
			String name = obj.getClass().getSimpleName().toLowerCase();
			int beg = (pages-1)*4;
			map.put("beg", beg);
			map.put("pages", pages);
			map.put("num", num);
			map.put(name, obj);
			map.put("url", request.getServletPath().substring(1));
		}
		public static void serMap(Map<String, Object> map,int count,List<?> list,Class<?> clazz){
			String name = clazz.getSimpleName().toLowerCase();
			int num = Integer.parseInt(map.get("num").toString());
			int sumPage = (count%num==0)?(count/num):(count/num+1);
			map.put(name+"List", list);
			map.put("count", count);
			map.put("sumPage", sumPage);
		}
}
