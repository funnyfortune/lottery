package com.tc.draw.demo.api.draw.model.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 抽奖中奖对象 draw_result
 *
 * @author TC
 * @date 2021-03-15
 */
@Data
@ToString
public class DrawResult implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 说明：主键ID
     * 字段：id
     * 类型：bigint(11)
     */
    @TableId
    private Long id;

    /**
     * 说明：主题ID
     * 字段：theme_id
     * 类型：bigint(11)
     */
    private Long themeId;

    /**
     * 说明：奖项ID
     * 字段：gift_id
     * 类型：bigint(11)
     */
    private Long giftId;

    /**
     * 说明：客户表Id
     * 字段：customer_id
     * 类型：bigint(11)
     */
    private Long customerId;

    /**
     * 说明：创建时间
     * 字段：create_time
     * 类型：bigint(11)
     */
    private Long createTime;

}
