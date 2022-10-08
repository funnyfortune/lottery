package com.tc.draw.demo.api.org.model.domain;


    import com.baomidou.mybatisplus.annotation.TableId;
    import lombok.Data;

    import java.io.Serializable;


/**
 * 用户与岗位关联表
 */
@Data
public class SysUserPost implements Serializable{
    private static final long serialVersionUID=1L;

    
    /**
     * 说明：用户ID
     * 字段名：user_id
     * 类型：bigint
     */
    @TableId
    private Long userId;
    
    /**
     * 说明：岗位ID
     * 字段名：post_id
     * 类型：bigint
     */
    @TableId
    private Long postId;
}
