package com.module.zhupeng.data.generator.service.impl;

import com.module.zhupeng.data.generator.dao.UserEntityMapper;
import com.module.zhupeng.data.generator.model.UserEntity;
import com.module.zhupeng.data.generator.service.UserEntityService;
import com.module.zhupeng.data.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by 祝大朋 on 2020/09/02.
 */
@Service
@Transactional
public class UserEntityServiceImpl extends AbstractService<UserEntity> implements UserEntityService {
    @Resource
    private UserEntityMapper userMapper;

}
