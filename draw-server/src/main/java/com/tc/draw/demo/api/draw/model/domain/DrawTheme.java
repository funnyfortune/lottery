package com.tc.draw.demo.api.draw.model.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 抽奖主题对象 draw_theme
 *
 * @author TC
 * @date 2021-03-15
 */
@Data
@ToString
public class DrawTheme implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 说明：主键ID
     * 字段：id
     * 类型：bigint(11)
     */
    @TableId
    private Long id;

    /**
     * 说明：活动名称
     * 字段：name
     * 类型：varchar(256)
     */
    private String name;

    /**
     * 说明：0随机，1概率
     * 字段：draw_type
     * 类型：tinyint(1)
     */
    private Integer drawType;

    /**
     * 说明：创建者id
     * 字段：creator_id
     * 类型：bigint(11)
     */
    private Long creatorId;

    /**
     * 说明：创建时间
     * 字段：create_time
     * 类型：bigint(11)
     */
    private Long createTime;


    private Integer isDelete;

    private Long OperatorId;

    private Long updateTime;

}
