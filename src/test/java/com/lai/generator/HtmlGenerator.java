package com.lai.generator;

import static com.lai.generator.Config.BASE_PACKAGE;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.lai.base.HtmlParams;
import com.lai.pojo.UserInfo;

import freemarker.template.TemplateExceptionHandler;

public class HtmlGenerator {
	
	private static final String PROJECT_PATH = System.getProperty("user.dir");//项目在硬盘上的基础路径
	private static final String TEMPLATE_FILE_PATH = PROJECT_PATH + "/src/test/resources/template";//模板位置
	private static final String RESOURCE_PATH = "/src/main/resources/templates/";
	

	public static void main(String[] args) {
		
		genHtml(UserInfo.class);
		
	}

	private static void genHtml(Class<UserInfo> clazz) {
		String model = clazz.getSimpleName();
		model = model.substring(0, 1).toLowerCase() + model.substring(1);
		List<Items> search = new ArrayList<>();
		List<Items> list = new ArrayList<>();
		List<Items> input = new ArrayList<>();
		List<Items> detail = new ArrayList<>();
		
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if(field.isAnnotationPresent(HtmlParams.class)){
				HtmlParams params = field.getAnnotation(HtmlParams.class);
				if(params.search()){
					Items it = new Items();
					it.setLabel(params.lable());
					String tips = params.tips().equals("") ? "请输入"+params.lable() :
						params.tips();
					it.setTips(tips);
					it.setName(field.getName());
					search.add(it);
				}
				if(params.list()){
					Items it = new Items();
					it.setLabel(params.lable());
					it.setName(field.getName());
					it.setType(field.getType().getSimpleName());
					list.add(it);
				}
				if(params.input()){
					Items it = new Items();
					it.setLabel(params.lable());
					String tips = params.tips().equals("") ? "请输入"+params.lable() :
						params.tips();
					it.setTips(tips);
					it.setName(field.getName());
					it.setMust(params.must());
					input.add(it);
				}
				if(params.detail()){
					Items it = new Items();
					it.setLabel(params.lable());
					it.setName(field.getName());
					detail.add(it);
				}
			}
		}
		
		genList(model,search,list);
		genInput(model,input);
		genDetail(model,detail);
	}

	private static void genDetail(String model, List<Items> detail) {
		try {
            freemarker.template.Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            data.put("model", model);
            data.put("details", detail);
            

            File file = new File(PROJECT_PATH + RESOURCE_PATH + model + "_detail.html");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate("detail_html.ftl").process(data, new FileWriter(file));

            System.out.println(model + "_detail 生成成功");
        } catch (Exception e) {
            throw new RuntimeException("生成detail失败", e);
        }
		
	}

	private static void genInput(String model, List<Items> input) {
		try {
            freemarker.template.Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            data.put("model", model);
            data.put("inputs", input);
            

            File file = new File(PROJECT_PATH + RESOURCE_PATH + model + "_input.html");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate("input_html.ftl").process(data, new FileWriter(file));

            System.out.println(model + "_input 生成成功");
        } catch (Exception e) {
            throw new RuntimeException("生成input失败", e);
        }
		
	}

	private static void genList(String model, List<Items> search, List<Items> list) {
		try {
            freemarker.template.Configuration cfg = getConfiguration();

            Map<String, Object> data = new HashMap<>();
            data.put("model", model);
            data.put("searchs", search);
            data.put("lists", list);

            File file = new File(PROJECT_PATH + RESOURCE_PATH + model + "_list.html");
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            cfg.getTemplate("list_html.ftl").process(data, new FileWriter(file));

            System.out.println(model + "_list 生成成功");
        } catch (Exception e) {
            throw new RuntimeException("生成list失败", e);
        }
	}
	
	private static freemarker.template.Configuration getConfiguration() throws IOException {
        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_FILE_PATH));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        return cfg;
    }
}
