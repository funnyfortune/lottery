package com.tc.draw.demo.api.org.model.domain;


    import com.baomidou.mybatisplus.annotation.TableId;
    import lombok.Data;

    import java.io.Serializable;


/**
 * 角色信息表
 *
 */
@Data
public class SysRole implements Serializable{
    private static final long serialVersionUID=1L;

    
    /**
     * 说明：角色权限字符串
     * 字段名：role_key
     * 类型：varchar
     */
    private String roleKey;
    
    /**
     * 说明：显示顺序
     * 字段名：role_sort
     * 类型：int
     */
    private Integer roleSort;
    
    /**
     * 说明：数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）
     * 字段名：data_scope
     * 类型：char
     */
    private String dataScope;
    
    /**
     * 说明：角色状态（0正常 1停用）
     * 字段名：status
     * 类型：char
     */
    private String status;
    
    /**
     * 说明：最后修改时间
     * 字段名：update_time
     * 类型：bigint
     */
    private Long updateTime;
    
    /**
     * 说明：组织表id
     * 字段名：creator_id
     * 类型：bigint
     */
    private Long creatorId;
    
    /**
     * 说明：最后操作id
     * 字段名：operator_id
     * 类型：bigint
     */
    private Long operatorId;
    
    /**
     * 说明：
     * 字段名：id
     * 类型：bigint
     */
    @TableId
    private Long id;
    
    /**
     * 说明：角色名称
     * 字段名：role_name
     * 类型：varchar
     */
    private String roleName;
    
    /**
     * 说明：备注
     * 字段名：remark
     * 类型：varchar
     */
    private String remark;
    
    /**
     * 说明：创建时间
     * 字段名：create_time
     * 类型：bigint
     */
    private Long createTime;
    
    /**
     * 说明：是否删除
     * 字段名：is_delete
     * 类型：tinyint
     */
    private Integer isDelete;

    private Integer isSys;
}
