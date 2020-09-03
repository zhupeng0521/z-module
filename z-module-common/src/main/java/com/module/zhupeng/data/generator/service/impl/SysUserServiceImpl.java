package com.module.zhupeng.data.generator.service.impl;

import com.module.zhupeng.data.generator.dao.SysUserMapper;
import com.module.zhupeng.data.generator.model.SysUser;
import com.module.zhupeng.data.generator.service.SysUserService;
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
public class SysUserServiceImpl extends AbstractService<SysUser> implements SysUserService {
    @Resource
    private SysUserMapper sysUserMapper;

}
