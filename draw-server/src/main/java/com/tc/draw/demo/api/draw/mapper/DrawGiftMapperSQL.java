package com.tc.draw.demo.api.draw.mapper;

import com.tc.draw.demo.api.draw.model.dto.DrawGiftListDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
import java.util.StringJoiner;

/**
 * drawGiftMapperSQL
 *
 * @author TC
 * @date 2021-03-15
 */
public class DrawGiftMapperSQL {

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
        sql.UPDATE("draw_gift").SET("is_delete=1,update_time=#{updateTime},operator_id=#{operatorId}").WHERE("id in " + values.toString());
        return sql.toString();
    }


    /**
     * 查询抽奖礼品列表
     *
     * @return
     */
    public String selectDrawGiftList(DrawGiftListDTO drawGift) {
        SQL sql = new SQL();
        sql.SELECT(" g.id,t.name themeName,g.theme_id, g.name, g.num, g.completed, g.complete_time, g.create_time")
                .FROM("draw_gift g,draw_theme t ").WHERE("g.theme_id =t.id");

        if(drawGift.getThemeId() != null && drawGift.getThemeId() > 0){
            sql.WHERE("g.theme_id=#{themeId}");
        }
        if (drawGift.getName() != null && !drawGift.getName().trim().equals("")) {
            sql.WHERE("g.name like concat('%', #{name}, '%')");
        }
        sql.ORDER_BY("g.create_time desc");
        return sql.toString();
    }
}
