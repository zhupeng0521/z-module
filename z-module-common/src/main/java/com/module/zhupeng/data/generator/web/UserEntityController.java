package com.module.zhupeng.data.generator.web;

import com.module.zhupeng.data.result.Result;
import com.module.zhupeng.data.result.ResultGenerator;
import com.module.zhupeng.data.generator.model.UserEntity;
import com.module.zhupeng.data.generator.service.UserEntityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
* Created by @author zhupeng
* @date 2020/09/03
*/
@RestController
@RequestMapping("/user/entity")
public class UserEntityController {
    @Resource
    private UserEntityService userEntityService;

    @PostMapping
    public Result add(@RequestBody UserEntity userEntity) {
        userEntityService.save(userEntity);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        userEntityService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping
    public Result update(@RequestBody UserEntity userEntity) {
        userEntityService.update(userEntity);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable Integer id) {
        UserEntity userEntity = userEntityService.findById(id);
        return ResultGenerator.genSuccessResult(userEntity);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<UserEntity> list = userEntityService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
