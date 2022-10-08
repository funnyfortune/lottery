package com.tc.draw.demo.api.draw.model.dto;

import com.tc.draw.demo.framework.web.model.ListFormDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("抽奖中奖列表传输对象")
public class DrawResultListDTO extends ListFormDTO {
    /**
     * 说明：主题ID
     * 类型：Long
     */
    @ApiModelProperty("主题ID")
    private Long themeId;

    private Long giftId;

    private String themeName;

    /**
     * 说明：客户表Id
     * 类型：Long
     */
    @ApiModelProperty("客户表Id")
    private String customerCompany;
    private String customerName;
    private String mobile;

}


