package com.tc.draw.demo.api.sys.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import com.tc.draw.demo.framework.web.model.ListFormDTO;
import lombok.Data;

@Data
@ApiModel("参数配置列表传输对象")
public class SysConfigListDTO extends ListFormDTO {
    /**
     * 说明：参数主键
     * 类型：Integer
     */
    @ApiModelProperty("参数主键")
    private Integer configId;
    /**
     * 说明：参数名称
     * 类型：String
     */
    @ApiModelProperty("参数名称")
    private String configName;
    /**
     * 说明：参数键名
     * 类型：String
     */
    @ApiModelProperty("参数键名")
    private String configKey;
    /**
     * 说明：参数键值
     * 类型：String
     */
    @ApiModelProperty("参数键值")
    private String configValue;
    /**
     * 说明：系统内置（Y是 N否）
     * 类型：String
     */
    @ApiModelProperty("系统内置（Y是 N否）")
    private String configType;
    /**
     * 说明：备注
     * 类型：String
     */
    @ApiModelProperty("备注")
    private String remark;
    /**
     * 说明：创建时间
     * 类型：Long
     */
    @ApiModelProperty("创建时间")
    private Long createTime;
    /**
     * 说明：修改时间
     * 类型：Long
     */
    @ApiModelProperty("修改时间")
    private Long updateTime;
    /**
     * 说明：用户id
     * 类型：Long
     */
    @ApiModelProperty("用户id")
    private Long creatorId;
    /**
     * 说明：最后操作id
     * 类型：Long
     */
    @ApiModelProperty("最后操作id")
    private Long operatorId;
    /**
     * 说明：是否删除
     * 类型：Integer
     */
    @ApiModelProperty("是否删除")
    private Integer isDelete;

}


