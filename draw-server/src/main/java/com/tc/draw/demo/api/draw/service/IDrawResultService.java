package com.tc.draw.demo.api.draw.service;

import java.util.List;
import com.tc.draw.demo.api.draw.model.domain.DrawResult;
import com.tc.draw.demo.api.draw.model.vo.DrawResultListVO;
import com.tc.draw.demo.api.draw.model.dto.DrawResultListDTO;

/**
 * 抽奖中奖Service接口
 *
 * @author TC
 * @date 2021-03-15
 */
public interface IDrawResultService
{


    /**
     * 查询抽奖中奖列表
     *
     * @param drawResult 抽奖中奖
     * @return 抽奖中奖集合
     */
     List<DrawResultListVO> selectDrawResultList(DrawResultListDTO drawResult);

    /**
     * 新增抽奖中奖
     *
     * @param drawResult 抽奖中奖
     * @return 结果
     */
     int insertDrawResult(DrawResult drawResult);

}
