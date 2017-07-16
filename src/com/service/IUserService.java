package com.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.bean.DataRow;
import com.bean.User;
import com.datasource.DataSource;
import com.sun.javafx.collections.MappingChange.Map;

public interface IUserService {
	@DataSource("test2")
	public User getUser(int id);
	@DataSource("test1")
	public HashMap<Object, Object> getData(int id);
	@DataSource("test1")
	public List<DataRow> getAll();
	@DataSource("test1")
	public void testTimeOut(); 
	@DataSource("test1")
	public List<DataRow> getNameAndId(HashMap<String,Object> params);
}
