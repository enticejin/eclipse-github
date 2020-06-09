package com.example.draw.rtle.service;

import java.util.List;

import com.example.draw.rtle.model.Person;

/** 
* @version 创建时间：2020年6月2日 下午2:45:53
*/
public interface PersonService {
	 /*
	   查所有
	   return List<Person>
	    */
	    List<Person> getAll();

	    /*
	    根据ID查询
	    {id} 要查询人员的 id
	     */
	    Person getPersonByID(int id);

	    /*
	    删除
	    {id} 要删除人员的 id
	     */
	    void delete(int id);

	    /*
	    更新
	    {p} 要更新的Person实例
	     */
	    void update(Person p);

	    /*
	    增加
	    {p} 要新增的Person实例
	     */
	    void newp(Person p);
}
