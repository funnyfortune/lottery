package com.tc.draw.demo.api.draw.model.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 抽奖礼品对象 draw_gift
 *
 * @author TC
 * @date 2021-03-15
 */
@Data
@ToString
public class DrawGift implements Serializable {
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
     * 类型：varchar(256)
     */
    private String name;

    /**
     * 说明：数量
     * 字段：num
     * 类型：int(11)
     */
    private Long num;

    /**
     * 说明：0未完成，1完成
     * 字段：completed
     * 类型：tinyint(1)
     */
    private Integer completed;

    /**
     * 说明：创建者id
     * 字段：creator_id
     * 类型：bigint(11)
     */
    private Long creatorId;

    private Long completeTime;


    /**
     * 说明：创建时间
     * 字段：create_time
     * 类型：bigint(11)
     */
    private Long createTime;

    private Long themeId;

}
