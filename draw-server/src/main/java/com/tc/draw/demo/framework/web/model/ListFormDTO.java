package com.tc.draw.demo.framework.web.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 分页基础类
 *
 * @TC
 */
@Data
public class ListFormDTO {

    
    @ApiModelProperty("开始时间")
    private long beginTime;
    
    @ApiModelProperty("结束时间")
    private long endTime;
	
}
