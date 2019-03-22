package ${basePackage}.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ${basePackage}.pojo.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
import com.lai.framework.vo.Result;
import com.lai.framework.vo.ResultGenerator;
/**
* Created by ${author} on ${date}.
*/
@RestController
@RequestMapping("/api/${modelNameLowerCamel}")
public class ${modelNameUpperCamel}Controller {
    
    @Autowired
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    @RequestMapping("/save")
	public Result save(${modelNameUpperCamel} ${modelNameLowerCamel}){
	
		${modelNameLowerCamel}Service.save(${modelNameLowerCamel});
		return ResultGenerator.success();
	}
	
	@RequestMapping("/deletes")
	public Result delete(String ids){
		
		${modelNameLowerCamel}Service.deletes(ids);
		return ResultGenerator.success();
	}
	
	@RequestMapping("/find")
	public Result find(Integer id){
		
		${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.find(id);
		return ResultGenerator.success(${modelNameLowerCamel});
	}
	
	@RequestMapping("/list")
	public Result list(@RequestParam(defaultValue = "0") Integer start,
			@RequestParam(defaultValue = "20") Integer size,${modelNameUpperCamel} ${modelNameLowerCamel}) throws Exception{
		if(start != 0) start--;
		Page<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.findByPage(start,size,${modelNameLowerCamel});
		return ResultGenerator.success(list);
	}
}
