package com.datasource;

public class DynamicDataSourceHolder {
	//线程安全，线程局部变量
	public static final ThreadLocal<String> holder = new ThreadLocal<String>();  
	  
    public static void putDataSource(String name) {  
        holder.set(name);  
    }  
  
    public static String getDataSouce() {  
        return holder.get();  
    } 
}
