package com.module.zhupeng.data.generator.model;

import com.module.zhupeng.data.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import javax.persistence.*;

/**
 * @author 15122
 */
@Table(name = "sys_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUser extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private Integer sex;

    private Integer age;

    private Date birthday;

    private String nickname;

    private String photo;

    private String phone;

    private String telephone;

    private String email;

    private Integer status;

}