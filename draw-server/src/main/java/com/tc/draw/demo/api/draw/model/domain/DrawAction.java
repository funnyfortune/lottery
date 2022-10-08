package com.tc.draw.demo.api.draw.model.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 抽奖行为对象 draw_action
 *
 * @author TC
 * @date 2021-03-15
 */
@Data
@ToString
public class DrawAction implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 说明：主键ID
     * 字段：id
     * 类型：bigint(11)
     */
    @TableId
    private Long id;

    /**
     * 说明：主题Id
     * 字段：theme_id
     * 类型：bigint(11)
     */
    private Long themeId;

    /**
     * 说明：主题奖项Id
     * 字段：gift_id
     * 类型：bigint(11)
     */
    private Long giftId;

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
}
