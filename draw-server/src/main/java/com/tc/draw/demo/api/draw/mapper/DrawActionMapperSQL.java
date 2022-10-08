package com.tc.draw.demo.api.draw.mapper;

import com.tc.draw.demo.api.draw.model.dto.DrawActionListDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.StringJoiner;
import java.util.List;

/**
 * drawActionMapperSQL
 *
 * @author TC
 * @date 2021-03-15
 */
public class DrawActionMapperSQL {


    /**
     * 删除SQL
     *
     * @return
     */
    public String deleteByIds(@Param("list") List<Long> ids, @Param("updateTime") long updateTime, @Param("operatorId") long operatorId) {
        SQL sql = new SQL();
        StringJoiner values = new StringJoiner(",", "(", ")");
        int len = ids.size();
        for (int i = 0; i < len; i++) {
            values.add("#{list[" + i + "]}");
        }
        sql.UPDATE("draw_action").SET("is_delete=1,update_time=#{updateTime},operator_id=#{operatorId}").WHERE("id in " + values.toString());
        return sql.toString();
    }


    /**
     * 查询抽奖行为列表
     *
     * @return
     */
    public String selectDrawActionList(DrawActionListDTO drawAction) {
        SQL sql = new SQL();
        sql.SELECT(" id, theme_id, gift_id, creator_id, create_time").FROM("draw_action");
        if (drawAction.getId() != null && drawAction.getId() > 0) {
            sql.WHERE("id = #{id}");
        }
        if (drawAction.getThemeId() != null && drawAction.getThemeId() > 0) {
            sql.WHERE("theme_id = #{themeId}");
        }
        if (drawAction.getGiftId() != null && drawAction.getGiftId() > 0) {
            sql.WHERE("gift_id = #{giftId}");
        }
        if (drawAction.getCreatorId() != null && drawAction.getCreatorId() > 0) {
            sql.WHERE("creator_id = #{creatorId}");
        }
        if (drawAction.getCreateTime() != null && drawAction.getCreateTime() > 0) {
            sql.WHERE("create_time = #{createTime}");
        }
        return sql.toString();
    }
}
