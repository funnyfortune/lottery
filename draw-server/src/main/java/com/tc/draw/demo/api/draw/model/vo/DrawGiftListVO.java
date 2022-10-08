package com.tc.draw.demo.api.draw.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.tc.draw.demo.framework.aspectj.lang.annotation.Excel;
import lombok.Data;
import lombok.ToString;

/**
 * 抽奖礼品列表视图对象
 *
 * @author TC
 * @date 2021-03-15
 */
@Data
@ToString
@ApiModel("抽奖礼品列表视图对象")
public class DrawGiftListVO {

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
    @Excel(name = "名称")
    @ApiModelProperty("名称")
    private String name;

    private Long themeId;

    private String themeName;

    /**
     * 说明：名称
     * 类型：Long
     */
    @Excel(name = "数量")
    @ApiModelProperty("数量")
    private Long num;
    /**
     * 说明：数量
     * 类型：Integer
     */
    @Excel(name = "0未完成，1完成")
    @ApiModelProperty("0未完成，1完成")
    private Integer completed;
    /**
     * 说明：0未完成，1完成
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

    private Long completeTime;


}

