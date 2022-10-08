package com.tc.draw.demo.api.draw.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.tc.draw.demo.framework.aspectj.lang.annotation.Excel;
import lombok.Data;
import lombok.ToString;

/**
 * 抽奖主题列表视图对象
 *
 * @author TC
 * @date 2021-03-15
 */
@Data
@ToString
@ApiModel("抽奖主题列表视图对象")
public class DrawThemeListVO {

    /**
     * 说明：$comment
     * 类型：Long
     */
    @Excel(name = "主键ID")
    @ApiModelProperty("主键ID")
    private Long id;
    /**
     * 说明：主键ID
     * 类型：String
     */
    @Excel(name = "活动名称")
    @ApiModelProperty("活动名称")
    private String name;
    /**
     * 说明：活动名称
     * 类型：Integer
     */
    @Excel(name = "0随机，1概率")
    @ApiModelProperty("0随机，1概率")
    private Integer drawType;
    /**
     * 说明：0随机，1概率
     * 类型：Long
     */
    @Excel(name = "创建者id")
    @ApiModelProperty("创建者id")
    private Long creatorId;
    /**
     * 说明：创建者id
     * 类型：Long
     */
    @Excel(name = "创建时间")
    @ApiModelProperty("创建时间")
    private Long createTime;

}

