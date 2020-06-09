package com.example.draw.rtle.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.draw.rtle.dao.PersonDao;
import com.example.draw.rtle.model.Person;
import com.example.draw.rtle.service.PersonService;

/** 
* @version 创建时间：2020年6月2日 下午2:47:02
*/
@Service
public class PersonServiceImpl implements PersonService{
	@Autowired
	private PersonDao personDao;
	@Override
	public List<Person> getAll() {
		
		return personDao.getAll();
	}

	@Override
	public Person getPersonByID(int id) {
		
		return personDao.getPersonByID(id);
	}

	@Override
	public void delete(int id) {
		
		personDao.delete(id);
	}

	@Override
	public void update(Person p) {
		personDao.update(p);
		
	}

	@Override
	public void newp(Person p) {
		
		personDao.newp(p);
	}

}
