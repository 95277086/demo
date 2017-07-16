package com.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.bean.DataRow;
import com.bean.User;
import com.sun.javafx.collections.MappingChange.Map;

/** 
* �����@MapperScan��������������Mapperɨ����������Ҫ�����ã����Զ����ɴ������ 
* ע�⣬�ӿ��еķ�������Ҫ�Ͷ�Ӧ��MyBatisӳ���ļ��е�����idֵһ������Ϊ���ɵ� 
* ��̬������������ƥ����Ӧ��Sql���ִ�С�������Ƿ����Ĳ����ͷ���ֵҲ��Ҫע 
* �⡣�ӿ��еķ�����ζ��壬��Ӧ��MyBatisӳ���ļ���Ӧ�ý�����Ӧ�Ķ��塣 
* ��󣬱�ע�е�userDao��������ΪSpring��Bean��id(��name)����ʹ�õģ������� 
* ����Service�����ע��ʹ�á� 
*/ 

public interface UserDao {
	/*�˴��ķ����������mapper�е�ӳ���ļ��е�idͬ��
	������ȥӳ���ļ���ͨ��com.hua.saf.dao.UserDao.getUser,��this.getClass().getName()+".getUser"
	*/
	public User getUser(int id);
	public HashMap<Object, Object> getData(int id);
	public List<DataRow> getAll();
	public List<DataRow> getNameAndId(HashMap<String,Object> params);
}
