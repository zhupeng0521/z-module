package com.module.zhupeng.data.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 基础的实体
 *
 * @author 柠檬水
 * @date 2020/04/14
 */
@Data
@ApiModel(description = "实体类工具类")
public abstract class BaseEntity<Integer extends Serializable> implements Serializable {

    /**
     * 乐观锁标识，初始版本：1
     */
    @Version
    @ApiModelProperty(value = "版本号",example = "1")
    private Integer version;

    /**
     * 逻辑删除字段： 0，未删除； 1，已删除
     */
    @TableLogic
    @ApiModelProperty(value = "逻辑删除",example = "0")
    private Integer deleted;

    @ApiModelProperty(value = "创建日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新日期")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
