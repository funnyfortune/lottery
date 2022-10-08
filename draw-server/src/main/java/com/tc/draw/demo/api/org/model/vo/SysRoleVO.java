package com.tc.draw.demo.api.org.model.vo;




import com.tc.draw.demo.framework.aspectj.lang.annotation.Excel;
import com.tc.draw.demo.framework.aspectj.lang.annotation.Excel.ColumnType;
import lombok.Data;

/**
 * 角色表 sys_role
 * 
 *
 */
@Data
public class SysRoleVO
{
    /** 角色ID */
    @Excel(name = "角色序号", cellType = ColumnType.NUMERIC)
    private Long id;

    /** 角色名称 */
    @Excel(name = "角色名称")
    private String roleName;

    /** 角色权限 */
    @Excel(name = "角色权限")
    private String roleKey;

    /** 角色排序 */
    @Excel(name = "角色排序")
    private String roleSort;

    /** 数据范围（1：所有数据权限；2：自定义数据权限；3：本部门数据权限；4：本部门及以下数据权限） */
    @Excel(name = "数据范围", readConverterExp = "1=所有数据权限,2=自定义数据权限,3=本部门数据权限,4=本部门及以下数据权限")
    private String dataScope;

    /** 角色状态（0正常 1停用） */
    @Excel(name = "角色状态", readConverterExp = "0=正常,1=停用")
    private String status;
    
    private Long creatorId;
    
    private Long createTime;
    
    private Long operatorId;
    
    private Long updateTime;

    /** 删除标志（0代表存在 1代表删除） */
    private int isDelete;

    private int isSys;

    /** 用户是否存在此角色标识 默认不存在 */
    private boolean flag = false;

    /** 菜单组 */
    private Long[] menuIds;

    /** 部门组（数据权限） */
    private Long[] deptIds;


}
