package com.tc.draw.demo.api.draw.model.dto;

import com.tc.draw.demo.framework.web.model.ListFormDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel("抽奖行为列表传输对象")
public class DrawActionListDTO extends ListFormDTO {
    /**
     * 说明：主键ID
     * 类型：Long
     */
    @ApiModelProperty("主键ID")
    private Long id;
    /**
     * 说明：主题Id
     * 类型：Long
     */
    @ApiModelProperty("主题Id")
    private Long themeId;
    /**
     * 说明：主题奖项Id
     * 类型：Long
     */
    @ApiModelProperty("主题奖项Id")
    private Long giftId;
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


