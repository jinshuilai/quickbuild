package ${basePackage}.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.lai.pojo.${modelNameUpperCamel};

public interface ${modelNameUpperCamel}Repository extends JpaRepository<${modelNameUpperCamel},Integer>,JpaSpecificationExecutor<${modelNameUpperCamel}> {

}
