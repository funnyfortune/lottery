package com.tc.draw.demo.api.org.model.domain;


    import com.baomidou.mybatisplus.annotation.TableId;
    import lombok.Data;

    import java.io.Serializable;


/**
 * 角色和部门关联表
 *
 */
@Data
public class SysRoleDept implements Serializable{
    private static final long serialVersionUID=1L;

    
    /**
     * 说明：角色ID
     * 字段名：role_id
     * 类型：bigint
     */
    @TableId
    private Long roleId;
    
    /**
     * 说明：部门ID
     * 字段名：dept_id
     * 类型：bigint
     */
    @TableId
    private Long deptId;


}
