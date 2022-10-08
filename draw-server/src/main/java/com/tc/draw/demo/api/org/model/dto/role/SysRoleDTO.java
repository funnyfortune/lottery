package com.tc.draw.demo.api.org.model.dto.role;

import com.tc.draw.demo.api.org.model.domain.SysRole;
import lombok.Data;

@Data
public class SysRoleDTO {

	private SysRole role;

    /** 菜单组 */
    private Long[] menuIds;

}
