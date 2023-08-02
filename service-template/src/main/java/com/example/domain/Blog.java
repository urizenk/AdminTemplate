package com.example.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import lombok.Data;

/**
@author yf
@create 2023-08-01 22:14
*/
@ApiModel(value="博客实体")
@Data
public class Blog {
    /**
    * 主键
    */
    @ApiModelProperty(value="主键")
    private Long id;

    /**
    * 商户id
    */
    @ApiModelProperty(value="商户id")
    private Long shopId;

    /**
    * 用户id
    */
    @ApiModelProperty(value="用户id")
    private Long userId;

    /**
    * 标题
    */
    @ApiModelProperty(value="标题")
    private String title;

    /**
    * 探店的照片，最多9张，多张以","隔开
    */
    @ApiModelProperty(value="探店的照片，最多9张，多张以','隔开")
    private String images;

    /**
    * 探店的文字描述
    */
    @ApiModelProperty(value="探店的文字描述")
    private String content;

    /**
    * 点赞数量
    */
    @ApiModelProperty(value="点赞数量")
    private Integer liked;

    /**
    * 评论数量
    */
    @ApiModelProperty(value="评论数量")
    private Integer comments;

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