package com.tc.draw.demo.api.draw.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import com.tc.draw.demo.api.draw.model.domain.DrawCustomer;
import com.tc.draw.demo.api.draw.model.vo.DrawCustomerListVO;
import com.tc.draw.demo.api.draw.model.vo.DrawCustomerInfoVO;
import com.tc.draw.demo.api.draw.model.dto.DrawCustomerListDTO;

/**
 * 抽奖客户Mapper接口
 *
 * @author TC
 * @date 2021-03-14
 */
@Mapper
public interface DrawCustomerMapper extends BaseMapper<DrawCustomer> {


    /**
     * 批量删除抽奖客户
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @UpdateProvider(type =  DrawCustomerMapperSQL.class, method = "deleteByIds")
    int deleteByIds(@Param("list") List<Long> ids, @Param("updateTime") long updateTime, @Param("operatorId") long operatorId);

    /**
     * 查询抽奖客户
     *
     * @param id 抽奖客户ID
     * @return 抽奖客户
     */
    @Select("select id,company, name, mobile,theme_id, probability, status, remark from draw_customer where id = #{id} and is_delete = 0")
     DrawCustomerInfoVO selectDrawCustomerById(Long id);

    /**
     * 查询抽奖客户列表
     *
     * @param drawCustomer 抽奖客户
     * @return 抽奖客户ListVO集合
    */
    @SelectProvider(type =  DrawCustomerMapperSQL.class, method = "selectDrawCustomerList")
    List<DrawCustomerListVO>selectDrawCustomerList(DrawCustomerListDTO drawCustomer);

    @Select("select company,name,mobile from draw_customer where is_delete=0 and status=0 and theme_id=#{themeId}")
    List<DrawCustomerInfoVO> selectCustomerByThemeId( @Param("themeId") Long themeId);

    @Select("select c.id, c.company,c.name,c.mobile,c.probability from draw_customer c " +
            "where c.theme_id=#{themeId} and c.id  not in (select customer_id from draw_result where theme_id=#{themeId})   and is_delete=0 and status=0")
    List<DrawCustomerInfoVO> selectNoLotteryCustomerByThemeId(@Param("themeId") long themeId);

    @Select("select id from draw_customer where mobile=#{mobile} and theme_id=#{themeId} and is_delete=0")
    Long selectIdByMobile(@Param("mobile")String mobile, @Param("themeId") Long themeId);

    @Select("select c.id, c.company,c.name,c.mobile,c.probability from draw_customer c " +
            "where c.theme_id=#{themeId} and c.id  not in (select customer_id from draw_result where theme_id=#{themeId})  and is_delete=0 and status=0 and c.probability>0 order by c.probability desc")
    List<DrawCustomerInfoVO> selectNoLotteryCustomerProbability( @Param("themeId") Long themeId);
}
