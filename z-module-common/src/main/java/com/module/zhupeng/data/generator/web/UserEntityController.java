package com.module.zhupeng.data.generator.web;
import com.module.zhupeng.data.generator.core.Result;
import com.module.zhupeng.data.generator.core.ResultGenerator;
import com.module.zhupeng.data.generator.model.UserEntity;
import com.module.zhupeng.data.generator.service.UserEntityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by 祝大朋 on 2020/09/02.
*/
@RestController
@RequestMapping("/user/entity")
public class UserEntityController {
    @Resource
    private UserEntityService userEntityService;

    @PostMapping("/add")
    public Result add(UserEntity userEntity) {
        userEntityService.save(userEntity);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        userEntityService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(UserEntity userEntity) {
        userEntityService.update(userEntity);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        UserEntity userEntity = userEntityService.findById(id);
        return ResultGenerator.genSuccessResult(userEntity);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<UserEntity> list = userEntityService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
