package com.tc.draw.demo.api.draw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tc.draw.demo.api.draw.mapper.DrawThemeMapper;
import com.tc.draw.demo.api.draw.model.domain.DrawTheme;
import com.tc.draw.demo.api.draw.model.vo.DrawThemeInfoVO;
import com.tc.draw.demo.api.draw.model.vo.DrawThemeListVO;
import com.tc.draw.demo.api.draw.model.dto.DrawThemeListDTO;
import com.tc.draw.demo.api.draw.service.IDrawThemeService;

import com.tc.draw.demo.common.utils.SecurityUtils;

/**
 * 抽奖主题Service业务层处理
 *
 * @author TC
 * @date 2021-03-15
 */
@Service
public class DrawThemeServiceImpl implements IDrawThemeService {
    @Autowired
    private DrawThemeMapper drawThemeMapper;

    /**
     * 查询抽奖主题
     *
     * @param id 抽奖主题ID
     * @return 抽奖主题
     */
    @Override
    public DrawThemeInfoVO selectDrawThemeById(Long id) {
        return drawThemeMapper.selectDrawThemeById(id);
    }

    /**
     * 查询抽奖主题列表
     *
     * @param drawTheme 抽奖主题
     * @return 抽奖主题
     */
    @Override
    public List<DrawThemeListVO> selectDrawThemeList(DrawThemeListDTO drawTheme) {
        return drawThemeMapper.selectDrawThemeList(drawTheme);
    }

    /**
     * 新增抽奖主题
     *
     * @param drawTheme 抽奖主题
     * @return 结果
     */
    @Override
    public int insertDrawTheme(DrawTheme drawTheme) {
        drawTheme.setCreatorId(SecurityUtils.getUserId());
        drawTheme.setCreateTime(System.currentTimeMillis());
        drawTheme.setUpdateTime(drawTheme.getCreateTime());
        drawTheme.setOperatorId(drawTheme.getCreatorId());
        return drawThemeMapper.insert(drawTheme);
    }

    /**
     * 修改抽奖主题
     *
     * @param drawTheme 抽奖主题
     * @return 结果
     */
    @Override
    public int updateDrawTheme(DrawTheme drawTheme) {
        drawTheme.setOperatorId(SecurityUtils.getUserId());
        drawTheme.setUpdateTime(System.currentTimeMillis());
        return drawThemeMapper.updateById(drawTheme);
    }

    /**
     * 批量删除抽奖主题
     *
     * @param ids 需要删除的抽奖主题ID
     * @return 结果
     */
    @Override
    public int deleteDrawThemeByIds(List<Long> ids) {
        return drawThemeMapper.deleteByIds(ids, System.currentTimeMillis(), SecurityUtils.getUserId());
    }
}
