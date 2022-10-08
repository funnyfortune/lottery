package com.tc.draw.demo.api.org.model.dto;

import com.tc.draw.demo.api.org.model.domain.SysUser;
import lombok.Data;

import java.util.List;

@Data
public class SysUserDTO {
    /** 角色组 */
    private List<Long> roleIds;

    /** 岗位组 */
    private List<Long> postIds;
    
    private SysUser user;

}
