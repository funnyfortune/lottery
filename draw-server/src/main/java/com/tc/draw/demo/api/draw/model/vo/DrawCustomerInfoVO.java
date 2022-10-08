package com.tc.draw.demo.api.draw.model.vo;

import lombok.Data;
import lombok.ToString;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@Data
@ToString
@ApiModel("抽奖客户视图对象")
public class DrawCustomerInfoVO {

    /**
     * 说明：主键ID
     * 类型：Long
     */
    @ApiModelProperty("主键ID")
    private Long id;

    private String company;

    private Long themeId;

    /**
     * 说明：名称
     * 类型：String
     */
    @ApiModelProperty("名称")
    private String name;
    /**
     * 说明：手机
     * 类型：String
     */
    @ApiModelProperty("手机")
    private String mobile;
    /**
     * 说明：概率
     * 类型：Double
     */
    @ApiModelProperty("概率")
    private Double probability;
    /**
     * 说明：状态（0=正常,1=停用）
     * 类型：String
     */
    @ApiModelProperty("状态（0=正常,1=停用）")
    private String status;
    /**
     * 说明：备注
     * 类型：String
     */
    @ApiModelProperty("备注")
    private String remark;
}

