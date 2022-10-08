package com.tc.draw.demo.api.draw.mapper;

import com.tc.draw.demo.api.draw.model.dto.DrawThemeListDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.StringJoiner;
import java.util.List;

/**
 * drawThemeMapperSQL
 *
 * @author TC
 * @date 2021-03-15
 */
public class DrawThemeMapperSQL {

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
        sql.UPDATE("draw_theme").SET("is_delete=1,update_time=#{updateTime},operator_id=#{operatorId}").WHERE("id in " + values.toString());
        return sql.toString();
    }


    /**
     * 查询抽奖主题列表
     *
     * @return
     */
    public String selectDrawThemeList(DrawThemeListDTO drawTheme) {
        SQL sql = new SQL();
        sql.SELECT(" id, name, draw_type, creator_id, create_time").FROM("draw_theme")
        .WHERE("is_delete=0 ");
        if (drawTheme.getName() != null && !drawTheme.getName().trim().equals("")) {
            sql.WHERE("name like concat('%', #{name}, '%')");
        }
        return sql.toString();
    }
}
