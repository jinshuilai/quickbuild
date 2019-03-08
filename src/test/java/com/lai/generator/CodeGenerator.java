package com.lai.generator;
import static com.lai.generator.Config.BASE_PACKAGE;
import static com.lai.generator.Config.CONTROLLER_PACKAGE;
import static com.lai.generator.Config.DAO_PACKAGE;
import static com.lai.generator.Config.MODEL_PACKAGE;
import static com.lai.generator.Config.SERVICE_IMPL_PACKAGE;
import static com.lai.generator.Config.SERVICE_PACKAGE;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import freemarker.template.TemplateExceptionHandler;

/**
 * 代码生成器
 */
public class CodeGenerator {

    private static final String PROJECT_PATH = System.getProperty("user.dir");//项目在硬盘上的基础路径
    private static final String TEMPLATE_FILE_PATH = PROJECT_PATH + "/src/test/resources/template";//模板位置

    private static final String JAVA_PATH = "/src/main/java"; //java文件路径

    private static final String PACKAGE_PATH_POJO = packageConvertPath(MODEL_PACKAGE);//生成的POJO存放路径
    private static final String PACKAGE_PATH_REPOSITORY = packageConvertPath(DAO_PACKAGE);//生成的repository存放路径
    private static final String PACKAGE_PATH_SERVICE = packageConvertPath(SERVICE_PACKAGE);//生成的Service存放路径
    private static final String PACKAGE_PATH_SERVICE_IMPL = packageConvertPath(SERVICE_IMPL_PACKAGE);//生成的Service实现存放路径
    private static final String PACKAGE_PATH_CONTROLLER = packageConvertPath(CONTROLLER_PACKAGE);//生成的Controller存放路径

    private static final String AUTHOR = "Mao";//@author
    private static final String DATE = new SimpleDateFormat("yyyy/MM/dd").format(new Date());//@date
    private static final TableDao dao = new TableDao();
    //这里修改你使用的模板，依次为controller，service，service-impl
    private static final String[] FTLS = {"controller.ftl","service.ftl","service-impl.ftl","repository.ftl","pojo.ftl"};

    public static void main(String[] args) {
        genCode("user_info");
        //genCodeByCustomModelName("输入表名","输入自定义Model名称");
    }

    /**
     * 通过数据表名称生成代码，Model 名称通过解析数据表名称获得，下划线转大驼峰的形式。
     * 如输入表名称 "t_user_detail" 将生成 TUserDetail、TUserDetailMapper、TUserDetailService ...
     * @param tableNames 数据表名称...
     */
    public static void genCode(String... tableNames) {
        for (String tableName : tableNames) {
            genCodeByCustomModelName(tableName, null);
        }
    }

    /**
     * 通过数据表名称，和自定义的 Model 名称生成代码
     * 如输入表名称 "t_user_detail" 和自定义的 Model 名称 "User" 将生成 User、UserMapper、UserService ...
     * @param tableName 数据表名称
     * @param modelName 自定义的 Model 名称
     */
    public static void genCodeByCustomModelName(String tableName, String modelName) {
        genModel(tableName, modelName);
        genRepository(tableName, modelName);
        genService(tableName, modelName);
        genController(tableName, modelName);
    }


    private static void genModel(String tableName, String modelName) {
		try {
			List<Columns> list = dao.getColumnsByTable(tableName);
			
			freemarker.template.Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            data.put("date", DATE);
            data.put("author", AUTHOR);
            data.put("columns", list);
            String modelNameUpperCamel = StringUtils.isEmpty(modelName) ? tableNameConvertUpperCamel(tableName) : modelName;
            data.put("modelNameUpperCamel", modelNameUpperCamel);
            data.put("modelNameLowerCamel", tableNameConvertLowerCamel(tableName));
            data.put("basePackage", BASE_PACKAGE);

            File file = new File(PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_POJO + modelNameUpperCamel + ".java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate(FTLS[4]).process(data, new FileWriter(file));

            System.out.println(modelNameUpperCamel + ".java 生成成功");
		} catch (Exception e) {
			System.out.println("pojo 生成失败");
			e.printStackTrace();
		}
		
	}

	private static void genRepository(String tableName, String modelName) {
		try {
            freemarker.template.Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            data.put("date", DATE);
            data.put("author", AUTHOR);
            String modelNameUpperCamel = StringUtils.isEmpty(modelName) ? tableNameConvertUpperCamel(tableName) : modelName;
            data.put("modelNameUpperCamel", modelNameUpperCamel);
            data.put("modelNameLowerCamel", tableNameConvertLowerCamel(tableName));
            data.put("basePackage", BASE_PACKAGE);

            File file = new File(PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_REPOSITORY + modelNameUpperCamel + "Repository.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate(FTLS[3]).process(data, new FileWriter(file));

            System.out.println(modelNameUpperCamel + "Repository.java 生成成功");
        } catch (Exception e) {
            throw new RuntimeException("生成Repository失败", e);
        }
		
	}

    public static void genService(String tableName, String modelName) {
        try {
            freemarker.template.Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            data.put("date", DATE);
            data.put("author", AUTHOR);
            String modelNameUpperCamel = StringUtils.isEmpty(modelName) ? tableNameConvertUpperCamel(tableName) : modelName;
            data.put("modelNameUpperCamel", modelNameUpperCamel);
            data.put("modelNameLowerCamel", tableNameConvertLowerCamel(tableName));
            data.put("basePackage", BASE_PACKAGE);

            File file = new File(PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_SERVICE + modelNameUpperCamel + "Service.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate(FTLS[1]).process(data,
                    new FileWriter(file));
            System.out.println(modelNameUpperCamel + "Service.java 生成成功");

            File file1 = new File(PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_SERVICE_IMPL + modelNameUpperCamel + "ServiceImpl.java");
            if (!file1.getParentFile().exists()) {
                file1.getParentFile().mkdirs();
            }
            List<Columns> list = dao.getColumnsByTable(tableName);
            data.put("columns", list);
            cfg.getTemplate(FTLS[2]).process(data,
                    new FileWriter(file1));
            System.out.println(modelNameUpperCamel + "ServiceImpl.java 生成成功");
        } catch (Exception e) {
            throw new RuntimeException("生成Service失败", e);
        }
    }

    public static void genController(String tableName, String modelName) {
        try {
            freemarker.template.Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            data.put("date", DATE);
            data.put("author", AUTHOR);
            String modelNameUpperCamel = StringUtils.isEmpty(modelName) ? tableNameConvertUpperCamel(tableName) : modelName;
            data.put("modelNameUpperCamel", modelNameUpperCamel);
            data.put("modelNameLowerCamel", tableNameConvertLowerCamel(tableName));
            data.put("basePackage", BASE_PACKAGE);

            File file = new File(PROJECT_PATH + JAVA_PATH + PACKAGE_PATH_CONTROLLER + modelNameUpperCamel + "Controller.java");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate(FTLS[0]).process(data, new FileWriter(file));

            System.out.println(modelNameUpperCamel + "Controller.java 生成成功");
        } catch (Exception e) {
            throw new RuntimeException("生成Controller失败", e);
        }

    }

    private static freemarker.template.Configuration getConfiguration() throws IOException {
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_FILE_PATH));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }

    public static String tableNameConvertLowerCamel(String tableName) {
    	tableName = tableName.toLowerCase();
		Pattern p = Pattern.compile("_([A-Za-z])");
		Matcher m = p.matcher(tableName);
		
		while(m.find()){
			String U = m.group();
			String U1 = m.group(1);
			tableName = tableName.replace(U,U1.toUpperCase());
		}
		return tableName;
    }

    public static String tableNameConvertUpperCamel(String tableName) {
        String temp = tableNameConvertLowerCamel(tableName);
        String name = temp.substring(0,1).toUpperCase() + temp.substring(1);
        return name;
    }

    private static String packageConvertPath(String packageName) {
        return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
    }

}
