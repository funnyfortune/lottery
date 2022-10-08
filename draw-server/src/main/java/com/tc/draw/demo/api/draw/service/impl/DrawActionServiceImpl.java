package com.tc.draw.demo.api.draw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tc.draw.demo.api.draw.mapper.DrawActionMapper;
import com.tc.draw.demo.api.draw.model.domain.DrawAction;
import com.tc.draw.demo.api.draw.model.vo.DrawActionInfoVO;
import com.tc.draw.demo.api.draw.model.vo.DrawActionListVO;
import com.tc.draw.demo.api.draw.model.dto.DrawActionListDTO;
import com.tc.draw.demo.api.draw.service.IDrawActionService;

import com.tc.draw.demo.common.utils.SecurityUtils;

/**
 * 抽奖行为Service业务层处理
 *
 * @author TC
 * @date 2021-03-15
 */
@Service
public class DrawActionServiceImpl implements IDrawActionService {
    @Autowired
    private DrawActionMapper drawActionMapper;

    /**
     * 查询抽奖行为
     *
     * @param id 抽奖行为ID
     * @return 抽奖行为
     */
    @Override
    public DrawActionInfoVO selectDrawActionById(Long id) {
        return drawActionMapper.selectDrawActionById(id);
    }

    /**
     * 查询抽奖行为列表
     *
     * @param drawAction 抽奖行为
     * @return 抽奖行为
     */
    @Override
    public List<DrawActionListVO> selectDrawActionList(DrawActionListDTO drawAction) {
        return drawActionMapper.selectDrawActionList(drawAction);
    }

    /**
     * 新增抽奖行为
     *
     * @param drawAction 抽奖行为
     * @return 结果
     */
    @Override
    public int insertDrawAction(DrawAction drawAction) {
        drawAction.setCreatorId(SecurityUtils.getUserId());
        drawAction.setCreateTime(System.currentTimeMillis());
        return drawActionMapper.insert(drawAction);
    }

    /**
     * 修改抽奖行为
     *
     * @param drawAction 抽奖行为
     * @return 结果
     */
    @Override
    public int updateDrawAction(DrawAction drawAction) {
        return drawActionMapper.updateById(drawAction);
    }

    /**
     * 批量删除抽奖行为
     *
     * @param ids 需要删除的抽奖行为ID
     * @return 结果
     */
    @Override
    public int deleteDrawActionByIds(List<Long> ids) {
        return drawActionMapper.deleteByIds(ids, System.currentTimeMillis(), SecurityUtils.getUserId());
    }
}
