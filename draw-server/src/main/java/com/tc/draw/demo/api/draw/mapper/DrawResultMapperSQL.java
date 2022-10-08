package com.tc.draw.demo.api.draw.mapper;

import com.tc.draw.demo.api.draw.model.dto.DrawResultListDTO;
import org.apache.ibatis.jdbc.SQL;

/**
 * drawResultMapperSQL
 *
 * @author TC
 * @date 2021-03-15
 */
public class DrawResultMapperSQL {



    /**
     * 查询抽奖中奖列表
     *
     * @return
     */
    public String selectDrawResultList(DrawResultListDTO drawResult) {
        SQL sql = new SQL();
        sql.SELECT( "r.id,t.name themeName,g.name giftName,c.company customerCompany,c.name customerName,c.mobile,r.create_time,g.complete_time").FROM("draw_result r")
                .INNER_JOIN("draw_customer c on c.id=r.customer_id")
        .INNER_JOIN("draw_theme t on t.id=r.theme_id")
        .INNER_JOIN("draw_gift g on g.id=r.gift_id");
        if (drawResult.getThemeId() != null && drawResult.getThemeId() > 0) {
            sql.WHERE("r.theme_id = #{themeId}");
        }
        if (drawResult.getGiftId() != null && drawResult.getGiftId() > 0) {
            sql.WHERE("r.gift_id = #{giftId}");
        }
        if (drawResult.getThemeName() != null && !drawResult.getThemeName() .trim().equals("")) {
            sql.WHERE("t.name like concat('%', #{themeName}, '%')");
        }
        if (drawResult.getMobile() != null && !drawResult.getMobile().trim().equals("")) {
            sql.WHERE("c.mobile like concat('%', #{mobile}, '%')");
        }
        if (drawResult.getCustomerCompany() != null && !drawResult.getCustomerCompany() .trim().equals("")) {
            sql.WHERE("c.company like concat('%', #{customerCompany}, '%')");
        }
        if (drawResult.getCustomerName() != null && !drawResult.getCustomerName().trim().equals("")) {
            sql.WHERE("c.name like concat('%', #{customerName}, '%')");
        }
        return sql.toString();
    }
}
