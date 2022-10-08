package com.tc.draw.demo.api.draw.service;

import java.util.List;
import com.tc.draw.demo.api.draw.model.domain.DrawCustomer;
import com.tc.draw.demo.api.draw.model.vo.DrawCustomerListVO;
import com.tc.draw.demo.api.draw.model.vo.DrawCustomerInfoVO;
import com.tc.draw.demo.api.draw.model.dto.DrawCustomerListDTO;
import com.tc.draw.demo.framework.web.model.Res;

/**
 * 抽奖客户Service接口
 *
 * @author TC
 * @date 2021-03-14
 */
public interface IDrawCustomerService
{
    /**
     * 查询抽奖客户
     *
     * @param id 抽奖客户ID
     * @return 抽奖客户infoVO
     */
     DrawCustomerInfoVO selectDrawCustomerById(Long id);

    /**
     * 查询抽奖客户列表
     *
     * @param drawCustomer 抽奖客户
     * @return 抽奖客户集合
     */
     List<DrawCustomerListVO> selectDrawCustomerList(DrawCustomerListDTO drawCustomer);

    /**
     * 新增抽奖客户
     *
     * @param drawCustomer 抽奖客户
     * @return 结果
     */
     int insertDrawCustomer(DrawCustomer drawCustomer);

    /**
     * 修改抽奖客户
     *
     * @param drawCustomer 抽奖客户
     * @return 结果
     */
     int updateDrawCustomer(DrawCustomer drawCustomer);

    /**
     * 批量删除抽奖客户
     *
     * @param ids 需要删除的抽奖客户ID
     * @return 结果
     */
     int deleteDrawCustomerByIds(List<Long> ids);

    boolean checkValueUnique(String mobile,long themeId, long id);

    Res<String, Void> importData(List<DrawCustomerListVO> list);
}
