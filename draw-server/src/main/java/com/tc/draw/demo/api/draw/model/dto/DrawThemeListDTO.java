package com.tc.draw.demo.api.draw.model.dto;

import com.tc.draw.demo.framework.web.model.ListFormDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("抽奖主题列表传输对象")
public class DrawThemeListDTO extends ListFormDTO {
    /**
     * 说明：主键ID
     * 类型：Long
     */
    @ApiModelProperty("主键ID")
    private Long id;
    /**
     * 说明：活动名称
     * 类型：String
     */
    @ApiModelProperty("活动名称")
    private String name;
    /**
     * 说明：0随机，1概率
     * 类型：Integer
     */
    @ApiModelProperty("0随机，1概率")
    private Integer drawType;
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

}


