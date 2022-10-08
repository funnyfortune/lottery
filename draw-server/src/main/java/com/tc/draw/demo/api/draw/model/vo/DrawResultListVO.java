package com.tc.draw.demo.api.draw.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.tc.draw.demo.framework.aspectj.lang.annotation.Excel;
import lombok.Data;
import lombok.ToString;

/**
 * 抽奖中奖列表视图对象
 *
 * @author TC
 * @date 2021-03-15
 */
@Data
@ToString
@ApiModel("抽奖中奖列表视图对象")
public class DrawResultListVO {

    /**
     * 说明：$comment
     * 类型：Long
     */
    @Excel(name = "主键ID")
    @ApiModelProperty("主键ID")
    private Long id;
    /**
     * 说明：主键ID
     * 类型：Long
     */
    @Excel(name = "主题名称")
    @ApiModelProperty("主题ID")
    private String themeName;
    /**
     * 说明：主题ID
     * 类型：Long
     */
    @Excel(name = "奖项名称")
    @ApiModelProperty("奖项ID")
    private String giftName;
    /**
     * 说明：奖项ID
     * 类型：Long
     */
    @Excel(name = "客户公司")
    @ApiModelProperty("客户表Id")
    private String customerCompany;

    @Excel(name = "客户名称")
    @ApiModelProperty("客户表Id")
    private String customerName;

    @Excel(name = "客户手机号")
    @ApiModelProperty("客户表Id")
    private String mobile;
    /**
     * 说明：客户表Id
     * 类型：Long
     */
    @Excel(name = "中奖时间")
    @ApiModelProperty("创建时间")
    private Long createTime;

    private Long completeTime;

}

