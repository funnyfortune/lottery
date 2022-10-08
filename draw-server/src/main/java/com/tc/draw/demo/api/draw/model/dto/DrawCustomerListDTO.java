package com.tc.draw.demo.api.draw.model.dto;

import com.tc.draw.demo.framework.web.model.ListFormDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("抽奖客户列表传输对象")
public class DrawCustomerListDTO extends ListFormDTO {
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
    /**
     * 说明：手机
     * 类型：String
     */
    @ApiModelProperty("手机")
    private String mobile;

    private Long themeId;


}


