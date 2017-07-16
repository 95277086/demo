package com.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bean.DataRow;
import com.bean.User;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.service.IUserService;
import com.sun.javafx.collections.MappingChange.Map;
@Controller
@RequestMapping("/user")
public class DemoController {
	@Resource
	private IUserService userService;
	
	@RequestMapping("/")
    public  String index(Model model) {
        return "system/demo";
    }
	
	
	
	@RequestMapping("/test")
    public  String test(Model model) {
		User user=userService.getUser(1);
		System.out.println(user.getId()+"--"+user.getName());
		System.out.println("½øÀ´controller");
		model.addAttribute("user", user);
        return "system/demo";
    }
	
	//·µ»Øjson
	@RequestMapping("/test2")
	public  @ResponseBody User user(){
		User user=userService.getUser(1);
		return user;
		
	}
	
	@RequestMapping("/test3")
	public  @ResponseBody HashMap<Object, Object> data(){
		HashMap<Object, Object> data=new HashMap();
		data=userService.getData(1);		
		return data;
		
	}
	
	@RequestMapping("/test4")
	public  @ResponseBody List<DataRow> all(){
		List<DataRow> list=new ArrayList<DataRow>();
		list=userService.getAll();
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).get("NAME"));
		}
		return list;
		
	}
	@RequestMapping("/test5")
	public  @ResponseBody List<DataRow> getNameAndId(){
		List<DataRow> list=new ArrayList<DataRow>();
		HashMap<String, Object> params=new HashMap<>();
		params.put("name", "fuck");
		params.put("id", 1);
		list=userService.getNameAndId(params);
		for(int i=0;i<list.size();i++){
			System.out.println(list.get(i).get("NAME"));
		}
		return list;
		
	}
}
