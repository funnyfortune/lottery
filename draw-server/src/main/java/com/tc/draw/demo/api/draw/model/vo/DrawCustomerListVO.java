package com.tc.draw.demo.api.draw.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import com.tc.draw.demo.framework.aspectj.lang.annotation.Excel;

/**
 * 抽奖客户列表视图对象
 *
 * @author TC
 * @date 2021-03-14
 */
@Data
@ToString
@ApiModel("抽奖客户列表视图对象")
public class DrawCustomerListVO {

    /**
     * 说明：$comment
     * 类型：Long
     */
    @ApiModelProperty("主键ID")
    private Long id;

    @Excel(name="抽奖主题ID")
    private Long themeId;

    @Excel(name = "公司")
    @ApiModelProperty("公司")
    private String company;

    /**
     * 说明：主键ID
     * 类型：String
     */
    @Excel(name = "名称")
    @ApiModelProperty("名称")
    private String name;
    /**
     * 说明：名称
     * 类型：String
     */
    @Excel(name = "手机")
    @ApiModelProperty("手机")
    private String mobile;
    /**
     * 说明：手机
     * 类型：Double
     */
    @ApiModelProperty("概率")
    private Double probability;
    /**
     * 说明：概率
     * 类型：String
     */
    @ApiModelProperty("状态")
    private String status;
    /**
     * 说明：状态
     * 类型：String
     */
    @Excel(name = "备注")
    @ApiModelProperty("备注")
    private String remark;


    @ApiModelProperty("是否删除")
    private Integer isDelete;
    /**
     * 说明：是否删除
     * 类型：Long
     */
    @ApiModelProperty("创建者id")
    private Long creatorId;
    /**
     * 说明：创建者id
     * 类型：Long
     */
    @ApiModelProperty("最后操作者id")
    private Long operatorId;
    /**
     * 说明：最后操作者id
     * 类型：Long
     */
    @ApiModelProperty("创建时间")
    private Long createTime;
    /**
     * 说明：创建时间
     * 类型：Long
     */
    @ApiModelProperty("修改时间")
    private Long updateTime;
}

