package com.lai.generator;

public class Config {

	public static final String BASE_PACKAGE = "com.lai";//项目基础包名称，根据自己公司的项目修改

    public static final String MODEL_PACKAGE = BASE_PACKAGE + ".pojo";//Model所在包
    public static final String DAO_PACKAGE = BASE_PACKAGE + ".repository";//dao所在包
    public static final String SERVICE_PACKAGE = BASE_PACKAGE + ".service";//Service所在包
    public static final String SERVICE_IMPL_PACKAGE = SERVICE_PACKAGE + ".impl";//ServiceImpl所在包
    public static final String CONTROLLER_PACKAGE = BASE_PACKAGE + ".web";//Controller所在包
    
    //数据库用户名
    public static final String USERNAME = "root";
  	//数据库密码
    public static final String PASSWORD = "";
  	//驱动信息 
    public static final String DRIVER = "com.mysql.jdbc.Driver";
  	//数据库地址
    public static final String URL = "jdbc:mysql://localhost:3306/test?characterEncoding=UTF-8&useOldAliasMetadataBehavior=true";
}
