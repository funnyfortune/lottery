package com.tc.draw.demo.api.draw.mapper;

import com.tc.draw.demo.api.draw.model.dto.DrawCustomerListDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

import java.util.StringJoiner;
import java.util.List;

/**
 * drawCustomerMapperSQL
 *
 * @author TC
 * @date 2021-03-14
 */
public class DrawCustomerMapperSQL {

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
        sql.UPDATE("draw_customer").SET("is_delete=1,update_time=#{updateTime},operator_id=#{operatorId}")
                .WHERE("id in " + values.toString());
        return sql.toString();
    }


    /**
     * 查询抽奖客户列表
     *
     * @return
     */
    public String selectDrawCustomerList(DrawCustomerListDTO drawCustomer) {
        SQL sql = new SQL();
        sql.SELECT(" id, name, mobile, company,probability,theme_id, status, remark, update_time").FROM("draw_customer");
        sql.WHERE("is_delete=0");
        if (drawCustomer.getName() != null && !drawCustomer.getName().trim().equals("")) {
            sql.WHERE("name like concat('%', #{name}, '%')");
        }
        if (drawCustomer.getMobile() != null && !drawCustomer.getMobile().trim().equals("")) {
            sql.WHERE("mobile like concat('%', #{mobile}, '%')");
        }
        if (drawCustomer.getThemeId() != null && drawCustomer.getThemeId() > 0) {
            sql.WHERE("theme_id=#{themeId}");
        }
        sql.ORDER_BY("update_time desc");
        return sql.toString();
    }
}
