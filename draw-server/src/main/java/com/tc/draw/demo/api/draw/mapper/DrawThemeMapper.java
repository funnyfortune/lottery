package com.tc.draw.demo.api.draw.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tc.draw.demo.api.draw.model.dto.DrawThemeListDTO;
import org.apache.ibatis.annotations.*;

import com.tc.draw.demo.api.draw.model.domain.DrawTheme;
import com.tc.draw.demo.api.draw.model.vo.DrawThemeListVO;
import com.tc.draw.demo.api.draw.model.vo.DrawThemeInfoVO;

/**
 * 抽奖主题Mapper接口
 *
 * @author TC
 * @date 2021-03-15
 */
@Mapper
public interface DrawThemeMapper extends BaseMapper<DrawTheme> {


    /**
     * 批量删除抽奖主题
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @UpdateProvider(type =  DrawThemeMapperSQL.class, method = "deleteByIds")
    int deleteByIds(@Param("list") List<Long> ids, @Param("updateTime") long updateTime, @Param("operatorId") long operatorId);

    /**
     * 查询抽奖主题
     *
     * @param id 抽奖主题ID
     * @return 抽奖主题
     */
    @Select("select id, name, draw_type, creator_id, create_time from draw_theme where id = #{id}")
     DrawThemeInfoVO selectDrawThemeById(Long id);

    /**
     * 查询抽奖主题列表
     *
     * @param drawThemeListDTO 抽奖主题
     * @return 抽奖主题ListVO集合
    */
    @SelectProvider(type =  DrawThemeMapperSQL.class, method = "selectDrawThemeList")
    List<DrawThemeListVO>selectDrawThemeList(DrawThemeListDTO drawTheme);
}
