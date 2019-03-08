package ${basePackage}.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ${basePackage}.pojo.${modelNameUpperCamel};
import ${basePackage}.service.${modelNameUpperCamel}Service;
import com.lai.vo.Result;
import com.lai.vo.ResultGenerator;
/**
* Created by ${author} on ${date}.
*/
@RestController
@RequestMapping("/${modelNameLowerCamel}")
public class ${modelNameUpperCamel}Controller {
    
    @Autowired
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    @RequestMapping("/save")
	public Result save(${modelNameUpperCamel} ${modelNameLowerCamel}){
	
		${modelNameLowerCamel}Service.save(${modelNameLowerCamel});
		return ResultGenerator.genSuccessResult();
	}
	
	@RequestMapping("/deletes")
	public Result delete(String ids){
		
		${modelNameLowerCamel}Service.deletes(ids);
		return ResultGenerator.genSuccessResult();
	}
	
	@RequestMapping("/find")
	public Result find(Integer id){
		
		${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.find(id);
		return ResultGenerator.genSuccessResult(${modelNameLowerCamel});
	}
	
	@RequestMapping("/list")
	public Result list(@RequestParam(defaultValue = "0") Integer start,
			@RequestParam(defaultValue = "20") Integer size,${modelNameUpperCamel} ${modelNameLowerCamel}) throws Exception{
		if(start != 0) start--;
		Page<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.findByPage(start,size,${modelNameLowerCamel});
		return ResultGenerator.genSuccessResult(list);
	}
}
