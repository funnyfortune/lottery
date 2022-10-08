package com.tc.draw.demo.api.draw.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import com.tc.draw.demo.api.draw.model.domain.DrawAction;
import com.tc.draw.demo.api.draw.model.vo.DrawActionListVO;
import com.tc.draw.demo.api.draw.model.vo.DrawActionInfoVO;
import com.tc.draw.demo.api.draw.model.dto.DrawActionListDTO;

/**
 * 抽奖行为Mapper接口
 *
 * @author TC
 * @date 2021-03-15
 */
@Mapper
public interface DrawActionMapper extends BaseMapper<DrawAction> {


    /**
     * 批量删除抽奖行为
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @UpdateProvider(type =  DrawActionMapperSQL.class, method = "deleteByIds")
    int deleteByIds(@Param("list") List<Long> ids, @Param("updateTime") long updateTime, @Param("operatorId") long operatorId);

    /**
     * 查询抽奖行为
     *
     * @param id 抽奖行为ID
     * @return 抽奖行为
     */
    @Select("select id, theme_id, gift_id, creator_id, create_time from draw_action where id = #{id}")
     DrawActionInfoVO selectDrawActionById(Long id);

    /**
     * 查询抽奖行为列表
     *
     * @param drawActionListDTO 抽奖行为
     * @return 抽奖行为ListVO集合
    */
    @SelectProvider(type =  DrawActionMapperSQL.class, method = "selectDrawActionList")
    List<DrawActionListVO>selectDrawActionList(DrawActionListDTO drawAction);
}
