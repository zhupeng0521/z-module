package com.module.zhupeng.data.generator.service.impl;

import com.module.zhupeng.data.generator.dao.UserEntityMapper;
import com.module.zhupeng.data.generator.model.UserEntity;
import com.module.zhupeng.data.generator.service.UserEntityService;
import com.module.zhupeng.data.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
* Created by @author zhupeng
* @date 2020/09/03
*/
@Service
@Transactional
public class UserEntityServiceImpl extends AbstractService<UserEntity> implements UserEntityService {
    @Resource
    private UserEntityMapper userMapper;

}
