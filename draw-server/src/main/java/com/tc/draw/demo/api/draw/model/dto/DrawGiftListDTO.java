package com.tc.draw.demo.api.draw.model.dto;

import com.tc.draw.demo.framework.web.model.ListFormDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;

@Data
@ApiModel("抽奖礼品列表传输对象")
public class DrawGiftListDTO extends ListFormDTO {
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

}


