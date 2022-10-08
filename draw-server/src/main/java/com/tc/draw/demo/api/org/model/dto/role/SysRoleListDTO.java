package com.tc.draw.demo.api.org.model.dto.role;

import com.tc.draw.demo.framework.web.model.ListFormDTO;
import lombok.Data;

@Data
public class SysRoleListDTO  extends ListFormDTO{

    /** 角色名称 */
    private String roleName;

    /** 角色权限 */
    private String roleKey;

    /** 角色状态（0正常 1停用） */
    private String status;
}
