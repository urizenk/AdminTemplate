package com.example.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

/**
@author yf
@create 2023-08-01 22:14
*/
@ApiModel(value="用户实体")
@Data
public class User {
    /**
    * 主键
    */
    @ApiModelProperty(value="主键")
    private Long id;

    /**
    * 手机号码
    */
    @ApiModelProperty(value="手机号码")
    private String phone;

    /**
    * 密码，加密存储
    */
    @ApiModelProperty(value="密码，加密存储")
    private String password;

    /**
    * 昵称，默认是用户id
    */
    @ApiModelProperty(value="昵称，默认是用户id")
    private String nickName;

    /**
    * 人物头像
    */
    @ApiModelProperty(value="人物头像")
    private String icon;

    /**
    * 创建时间
    */
    @ApiModelProperty(value="创建时间")
    private Date createTime;

    /**
    * 更新时间
    */
    @ApiModelProperty(value="更新时间")
    private Date updateTime;
}