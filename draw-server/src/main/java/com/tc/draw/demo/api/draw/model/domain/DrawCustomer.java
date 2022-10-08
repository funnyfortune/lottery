package com.tc.draw.demo.api.draw.model.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 抽奖客户对象 draw_customer
 *
 * @author TC
 * @date 2021-03-14
 */
@Data
@ToString
public class DrawCustomer implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 说明：主键ID
     * 字段：id
     * 类型：bigint(11)
     */
    @TableId
    private Long id;

    /**
     * 说明：名称
     * 字段：name
     * 类型：varchar(50)
     */
    private String name;

    /**
     * 主题Id
     */
    private Long themeId;

    /**
     * 说明：手机
     * 字段：mobile
     * 类型：varchar(50)
     */
    private String mobile;

    /**
     * 说明：概率
     * 字段：probability
     * 类型：double(10,3)
     */
    private Double probability;

    /**
     * 说明：状态（0=正常,1=停用）
     * 字段：status
     * 类型：char(1)
     */
    private String status;

    /**
     * 说明：备注
     * 字段：remark
     * 类型：varchar(256)
     */
    private String remark;


    /**
     * 说明：是否删除
     * 字段：is_delete
     * 类型：tinyint(1)
     */
    private Integer isDelete;

    /**
     * 说明：创建者id
     * 字段：creator_id
     * 类型：bigint(11)
     */
    private Long creatorId;

    /**
     * 说明：最后操作者id
     * 字段：operator_id
     * 类型：bigint(11)
     */
    private Long operatorId;

    /**
     * 说明：创建时间
     * 字段：create_time
     * 类型：bigint(11)
     */
    private Long createTime;

    /**
     * 说明：修改时间
     * 字段：update_time
     * 类型：bigint(11)
     */
    private Long updateTime;

    private String company;


}
