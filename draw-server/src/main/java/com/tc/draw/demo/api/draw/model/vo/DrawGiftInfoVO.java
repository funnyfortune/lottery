package com.tc.draw.demo.api.draw.model.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel("抽奖礼品视图对象")
public class DrawGiftInfoVO {

    /**
     * 说明：主键ID
     * 类型：Long
     */
    @ApiModelProperty("主键ID")
    private Long id;
    /**
     * 说明：名称
     * 类型：String
     */
    @ApiModelProperty("名称")
    private String name;

    private Long themeId;

    /**
     * 说明：数量
     * 类型：Long
     */
    @ApiModelProperty("数量")
    private Long num;
    /**
     * 说明：0未完成，1完成
     * 类型：Integer
     */
    @ApiModelProperty("0未完成，1完成")
    private Integer completed;
    /**
     * 说明：创建者id
     * 类型：Long
     */
    @ApiModelProperty("创建者id")
    private Long creatorId;
    /**
     * 说明：创建时间
     * 类型：Long
     */
    @ApiModelProperty("创建时间")
    private Long createTime;

    private Long completeTime;

}

