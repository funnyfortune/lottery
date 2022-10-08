package com.tc.draw.demo.api.draw.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tc.draw.demo.api.draw.model.dto.DrawGiftListDTO;
import org.apache.ibatis.annotations.*;

import com.tc.draw.demo.api.draw.model.domain.DrawGift;
import com.tc.draw.demo.api.draw.model.vo.DrawGiftListVO;
import com.tc.draw.demo.api.draw.model.vo.DrawGiftInfoVO;

/**
 * 抽奖礼品Mapper接口
 *
 * @author TC
 * @date 2021-03-15
 */
@Mapper
public interface DrawGiftMapper extends BaseMapper<DrawGift> {

    /**
     * 批量删除抽奖礼品
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @UpdateProvider(type =  DrawGiftMapperSQL.class, method = "deleteByIds")
    int deleteByIds(@Param("list") List<Long> ids, @Param("updateTime") long updateTime, @Param("operatorId") long operatorId);

    /**
     * 查询抽奖礼品
     *
     * @param id 抽奖礼品ID
     * @return 抽奖礼品
     */
    @Select("select id, name,completed, num, theme_id from draw_gift where id = #{id}")
     DrawGiftInfoVO selectDrawGiftById(Long id);

    /**
     * 查询抽奖礼品列表
     *
     * @param drawGift 抽奖礼品
     * @return 抽奖礼品ListVO集合
    */
    @SelectProvider(type =  DrawGiftMapperSQL.class, method = "selectDrawGiftList")
    List<DrawGiftListVO>selectDrawGiftList(DrawGiftListDTO drawGift);

    @Select("select  id, name, num from draw_gift where theme_id = #{themeId} and completed=0")
    List<DrawGiftInfoVO> selectGiftByThemeId(long themeId);
}
