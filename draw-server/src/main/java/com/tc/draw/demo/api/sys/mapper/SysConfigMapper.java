package com.tc.draw.demo.api.sys.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import com.tc.draw.demo.api.sys.model.domain.SysConfig;
import com.tc.draw.demo.api.sys.model.dto.SysConfigListDTO;
import com.tc.draw.demo.api.sys.model.vo.SysConfigListVO;

/**
 * 参数配置 数据层
 * 
 *
 */
@Mapper
public interface SysConfigMapper extends BaseMapper<SysConfig>
{
    /**
     * 查询参数配置信息
     * 
     * @param config 参数配置信息
     * @return 参数配置信息
     */
	@SelectProvider(type= SysConfigMapperSQL.class, method ="selectConfig")
    SysConfig selectConfig(SysConfig config);

    /**
     * 查询参数配置列表
     * 
     * @param config 参数配置信息
     * @return 参数配置集合
     */
	@SelectProvider(type= SysConfigMapperSQL.class, method ="selectConfigList")
    List<SysConfigListVO> selectConfigList(SysConfigListDTO config);

    /**
     * 根据键名查询参数配置信息
     * 
     * @param configKey 参数键名
     * @return 参数配置信息
     */
	@Select(" select config_id, config_name, config_key, value_type,config_value, config_type, create_time, update_time, remark " + 
			" from sys_config where config_key = #{configKey} and is_delete=0")
    SysConfig checkConfigKeyUnique(String configKey);

    
    @SelectProvider(type =  SysConfigMapperSQL.class, method = "selectConfigKeyByIds")
    List<String> selectConfigKeyByIds(@Param("list") List<Long> configIds);

    /**
     * 批量删除参数配置
     *
     * @param configIds 需要删除的数据ID
     * @return 结果
     */
    @UpdateProvider(type =  SysConfigMapperSQL.class, method = "deleteByIds")
    int deleteByIds(@Param("list") List<Long> configIds, @Param("updateTime") long updateTime, @Param("operatorId") long operatorId);

}