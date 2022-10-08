package com.tc.draw.demo.api.draw.service;

import java.util.List;
import com.tc.draw.demo.api.draw.model.domain.DrawTheme;
import com.tc.draw.demo.api.draw.model.vo.DrawThemeListVO;
import com.tc.draw.demo.api.draw.model.vo.DrawThemeInfoVO;
import com.tc.draw.demo.api.draw.model.dto.DrawThemeListDTO;

/**
 * 抽奖主题Service接口
 *
 * @author TC
 * @date 2021-03-15
 */
public interface IDrawThemeService
{
    /**
     * 查询抽奖主题
     *
     * @param id 抽奖主题ID
     * @return 抽奖主题infoVO
     */
     DrawThemeInfoVO selectDrawThemeById(Long id);

    /**
     * 查询抽奖主题列表
     *
     * @param drawTheme 抽奖主题
     * @return 抽奖主题集合
     */
     List<DrawThemeListVO> selectDrawThemeList(DrawThemeListDTO drawTheme);

    /**
     * 新增抽奖主题
     *
     * @param drawTheme 抽奖主题
     * @return 结果
     */
     int insertDrawTheme(DrawTheme drawTheme);

    /**
     * 修改抽奖主题
     *
     * @param drawTheme 抽奖主题
     * @return 结果
     */
     int updateDrawTheme(DrawTheme drawTheme);

    /**
     * 批量删除抽奖主题
     *
     * @param ids 需要删除的抽奖主题ID
     * @return 结果
     */
     int deleteDrawThemeByIds(List<Long> ids);

}
