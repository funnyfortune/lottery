package com.tc.draw.demo.api.draw.service;

import java.util.List;
import com.tc.draw.demo.api.draw.model.domain.DrawGift;
import com.tc.draw.demo.api.draw.model.vo.DrawGiftListVO;
import com.tc.draw.demo.api.draw.model.vo.DrawGiftInfoVO;
import com.tc.draw.demo.api.draw.model.dto.DrawGiftListDTO;

/**
 * 抽奖礼品Service接口
 *
 * @author TC
 * @date 2021-03-15
 */
public interface IDrawGiftService
{
    /**
     * 查询抽奖礼品
     *
     * @param id 抽奖礼品ID
     * @return 抽奖礼品infoVO
     */
     DrawGiftInfoVO selectDrawGiftById(Long id);

    /**
     * 查询抽奖礼品列表
     *
     * @param drawGift 抽奖礼品
     * @return 抽奖礼品集合
     */
     List<DrawGiftListVO> selectDrawGiftList(DrawGiftListDTO drawGift);

    /**
     * 新增抽奖礼品
     *
     * @param drawGift 抽奖礼品
     * @return 结果
     */
     int insertDrawGift(DrawGift drawGift);

    /**
     * 修改抽奖礼品
     *
     * @param drawGift 抽奖礼品
     * @return 结果
     */
     int updateDrawGift(DrawGift drawGift);

    /**
     * 批量删除抽奖礼品
     *
     * @param ids 需要删除的抽奖礼品ID
     * @return 结果
     */
     int deleteDrawGiftByIds(List<Long> ids);

    List<DrawGiftInfoVO> selectGiftByThemeId(long themeId);
}
