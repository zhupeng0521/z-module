package ${basePackage}.generator.service.impl;

import ${basePackage}.generator.dao.${modelNameUpperCamel}Mapper;
import ${basePackage}.generator.model.${modelNameUpperCamel};
import ${basePackage}.generator.service.${modelNameUpperCamel}Service;
import ${basePackage}.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
* Created by @author ${author}
* @date ${date}
*/
@Service
@Transactional
public class ${modelNameUpperCamel}ServiceImpl extends AbstractService<${modelNameUpperCamel}> implements ${modelNameUpperCamel}Service {
    @Resource
    private ${modelNameUpperCamel}Mapper ${modelNameLowerCamel}Mapper;

}
