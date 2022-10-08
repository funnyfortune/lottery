package com.tc.draw.demo.api.draw.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import com.tc.draw.demo.api.draw.model.domain.DrawResult;
import com.tc.draw.demo.api.draw.model.vo.DrawResultListVO;
import com.tc.draw.demo.api.draw.model.dto.DrawResultListDTO;

/**
 * 抽奖中奖Mapper接口
 *
 * @author TC
 * @date 2021-03-15
 */
@Mapper
public interface DrawResultMapper extends BaseMapper<DrawResult> {

    /**
     * 查询抽奖中奖列表
     *
     * @param drawResult 抽奖中奖
     * @return 抽奖中奖ListVO集合
    */
    @SelectProvider(type =  DrawResultMapperSQL.class, method = "selectDrawResultList")
    List<DrawResultListVO>selectDrawResultList(DrawResultListDTO drawResult);
}
