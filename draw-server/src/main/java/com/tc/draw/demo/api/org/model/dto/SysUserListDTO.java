package com.tc.draw.demo.api.org.model.dto;

import com.tc.draw.demo.framework.web.model.ListFormDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("人员表列表传输对象")
public class SysUserListDTO extends ListFormDTO{
    
    @ApiModelProperty("名字")
    private String userName;
    
    @ApiModelProperty("部门id")
    private long deptId;
    
    private String status;
    
    private String phonenumber;
    
 
}
