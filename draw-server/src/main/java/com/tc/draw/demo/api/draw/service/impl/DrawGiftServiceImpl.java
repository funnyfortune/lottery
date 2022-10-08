package com.tc.draw.demo.api.draw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tc.draw.demo.api.draw.mapper.DrawGiftMapper;
import com.tc.draw.demo.api.draw.model.domain.DrawGift;
import com.tc.draw.demo.api.draw.model.vo.DrawGiftInfoVO;
import com.tc.draw.demo.api.draw.model.vo.DrawGiftListVO;
import com.tc.draw.demo.api.draw.model.dto.DrawGiftListDTO;
import com.tc.draw.demo.api.draw.service.IDrawGiftService;

import com.tc.draw.demo.common.utils.SecurityUtils;

/**
 * 抽奖礼品Service业务层处理
 *
 * @author TC
 * @date 2021-03-15
 */
@Service
public class DrawGiftServiceImpl implements IDrawGiftService {
    @Autowired
    private DrawGiftMapper drawGiftMapper;

    /**
     * 查询抽奖礼品
     *
     * @param id 抽奖礼品ID
     * @return 抽奖礼品
     */
    @Override
    public DrawGiftInfoVO selectDrawGiftById(Long id) {
        return drawGiftMapper.selectDrawGiftById(id);
    }

    /**
     * 查询抽奖礼品列表
     *
     * @param drawGift 抽奖礼品
     * @return 抽奖礼品
     */
    @Override
    public List<DrawGiftListVO> selectDrawGiftList(DrawGiftListDTO drawGift) {
        return drawGiftMapper.selectDrawGiftList(drawGift);
    }

    /**
     * 新增抽奖礼品
     *
     * @param drawGift 抽奖礼品
     * @return 结果
     */
    @Override
    public int insertDrawGift(DrawGift drawGift) {
        drawGift.setCreatorId(SecurityUtils.getUserId());
        drawGift.setCreateTime(System.currentTimeMillis());
        return drawGiftMapper.insert(drawGift);
    }

    /**
     * 修改抽奖礼品
     *
     * @param drawGift 抽奖礼品
     * @return 结果
     */
    @Override
    public int updateDrawGift(DrawGift drawGift) {
        return drawGiftMapper.updateById(drawGift);
    }

    /**
     * 批量删除抽奖礼品
     *
     * @param ids 需要删除的抽奖礼品ID
     * @return 结果
     */
    @Override
    public int deleteDrawGiftByIds(List<Long> ids) {
        return drawGiftMapper.deleteByIds(ids, System.currentTimeMillis(), SecurityUtils.getUserId());
    }

    @Override
    public List<DrawGiftInfoVO> selectGiftByThemeId(long themeId) {
        return drawGiftMapper.selectGiftByThemeId(themeId);
    }
}
