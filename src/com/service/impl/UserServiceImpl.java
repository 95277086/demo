package com.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bean.DataRow;
import com.bean.User;
import com.dao.UserDao;
import com.service.IUserService;
import com.sun.javafx.collections.MappingChange.Map;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Resource
	private UserDao userDao;
	@Override
	public User getUser(int id) {
		return userDao.getUser(id);
	}
	@Override
	public HashMap<Object, Object> getData(int id) {
		return userDao.getData(id);
	}
	@Override
	public List<DataRow> getAll() {
		return userDao.getAll();
	}
	@Override
	public void testTimeOut() {
		Callable<List<DataRow>> task=new Callable<List<DataRow>>() {

			@Override
			public List<DataRow> call() throws Exception {
				return userDao.getAll();
			}
		};
		ExecutorService executorService=Executors.newSingleThreadExecutor();
		Future<List<DataRow>> future=executorService.submit(task);
		try {
			List<DataRow> list=future.get(1,TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			System.out.println("调用超过1分钟，调用超时");
			e.printStackTrace();
		}
		
	}
	@Override
	public List<DataRow> getNameAndId(HashMap<String, Object> params) {
		return userDao.getNameAndId(params);
	}
	
}
