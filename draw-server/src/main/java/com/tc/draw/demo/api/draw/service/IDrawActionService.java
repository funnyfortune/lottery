package com.tc.draw.demo.api.draw.service;

import java.util.List;
import com.tc.draw.demo.api.draw.model.domain.DrawAction;
import com.tc.draw.demo.api.draw.model.vo.DrawActionListVO;
import com.tc.draw.demo.api.draw.model.vo.DrawActionInfoVO;
import com.tc.draw.demo.api.draw.model.dto.DrawActionListDTO;

/**
 * 抽奖行为Service接口
 *
 * @author TC
 * @date 2021-03-15
 */
public interface IDrawActionService
{
    /**
     * 查询抽奖行为
     *
     * @param id 抽奖行为ID
     * @return 抽奖行为infoVO
     */
     DrawActionInfoVO selectDrawActionById(Long id);

    /**
     * 查询抽奖行为列表
     *
     * @param drawAction 抽奖行为
     * @return 抽奖行为集合
     */
     List<DrawActionListVO> selectDrawActionList(DrawActionListDTO drawAction);

    /**
     * 新增抽奖行为
     *
     * @param drawAction 抽奖行为
     * @return 结果
     */
     int insertDrawAction(DrawAction drawAction);

    /**
     * 修改抽奖行为
     *
     * @param drawAction 抽奖行为
     * @return 结果
     */
     int updateDrawAction(DrawAction drawAction);

    /**
     * 批量删除抽奖行为
     *
     * @param ids 需要删除的抽奖行为ID
     * @return 结果
     */
     int deleteDrawActionByIds(List<Long> ids);

}
