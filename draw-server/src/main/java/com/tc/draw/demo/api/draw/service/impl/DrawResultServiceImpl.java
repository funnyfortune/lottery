package com.tc.draw.demo.api.draw.service.impl;

import java.util.List;

import com.tc.draw.demo.api.draw.mapper.DrawResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tc.draw.demo.api.draw.model.domain.DrawResult;
import com.tc.draw.demo.api.draw.model.vo.DrawResultListVO;
import com.tc.draw.demo.api.draw.model.dto.DrawResultListDTO;
import com.tc.draw.demo.api.draw.service.IDrawResultService;

/**
 * 抽奖中奖Service业务层处理
 *
 * @author TC
 * @date 2021-03-15
 */
@Service
public class DrawResultServiceImpl implements IDrawResultService {
    @Autowired
    private DrawResultMapper drawResultMapper;


    /**
     * 查询抽奖中奖列表
     *
     * @param drawResult 抽奖中奖
     * @return 抽奖中奖
     */
    @Override
    public List<DrawResultListVO> selectDrawResultList(DrawResultListDTO drawResult) {
        return drawResultMapper.selectDrawResultList(drawResult);
    }

    /**
     * 新增抽奖中奖
     *
     * @param drawResult 抽奖中奖
     * @return 结果
     */
    @Override
    public int insertDrawResult(DrawResult drawResult) {
        drawResult.setCreateTime(System.currentTimeMillis());
        return drawResultMapper.insert(drawResult);
    }

}
