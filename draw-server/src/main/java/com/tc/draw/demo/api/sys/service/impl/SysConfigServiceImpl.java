package com.tc.draw.demo.api.sys.service.impl;

import com.tc.draw.demo.api.sys.mapper.SysConfigMapper;
import com.tc.draw.demo.api.sys.model.domain.SysConfig;
import com.tc.draw.demo.api.sys.model.dto.SysConfigListDTO;
import com.tc.draw.demo.api.sys.model.vo.SysConfigListVO;
import com.tc.draw.demo.api.sys.service.ISysConfigService;
import com.tc.draw.demo.common.constant.UserConstants;
import com.tc.draw.demo.common.utils.RandomUtil;
import com.tc.draw.demo.common.utils.SecurityUtils;
import com.tc.draw.demo.common.utils.StringUtils;
import com.tc.draw.demo.framework.redis.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 参数配置 服务层实现
 * 
 *
 */
@Service
public class SysConfigServiceImpl implements ISysConfigService {

	@Autowired
	private SysConfigMapper configMapper;

	@Autowired
	private RedisCache redisService;

	/**
	 * 查询参数配置信息
	 * 
	 * @param configId 参数配置ID
	 * @return 参数配置信息
	 */
	@Override
	public SysConfig selectConfigById(Long configId) {
		SysConfig config = new SysConfig();
		config.setConfigId(configId);
		return configMapper.selectConfig(config);
	}

	/**
	 * 根据键名查询参数配置信息
	 * 
	 * @param configKey 参数key
	 * @return 参数键值
	 */
	@Override
	public String selectConfigByKey(String configKey) {
		SysConfig config = new SysConfig();
		config.setConfigKey(configKey);
		SysConfig retConfig = configMapper.selectConfig(config);
		return StringUtils.isNotNull(retConfig) ? retConfig.getConfigValue() : "";
	}

	/**
	 * 查询参数配置列表
	 * 
	 * @param config 参数配置信息
	 * @return 参数配置集合
	 */
	@Override
	public List<SysConfigListVO> selectConfigList(SysConfigListDTO config) {
		return configMapper.selectConfigList(config);
	}

	/**
	 * 新增参数配置
	 * 
	 * @param config 参数配置信息
	 * @return 结果
	 */
	@Override
	public int insertConfig(SysConfig config) {
		config.setCreateTime(System.currentTimeMillis());
		config.setCreatorId(SecurityUtils.getUserId());
		config.setUpdateTime(config.getCreateTime());
		config.setOperatorId(config.getCreatorId());
		return configMapper.insert(config);
	}

	/**
	 * 修改参数配置
	 * 
	 * @param config 参数配置信息
	 * @return 结果
	 */
	@Override
	public int updateConfig(SysConfig config) {
		config.setUpdateTime(System.currentTimeMillis());
		config.setOperatorId(SecurityUtils.getUserId());
		int count = configMapper.updateById(config);
		if(count > 0) {
			setCache(config);
		}
		return count;
	}

	/**
	 * 批量删除参数信息
	 * 
	 * @param configIds 需要删除的参数ID
	 * @return 结果
	 */
	@Override
	public int deleteConfigByIds(List<Long> configIds) {
		int count = configMapper.deleteByIds(configIds, System.currentTimeMillis(), SecurityUtils.getUserId());
		if(count > 0) {
			List<String> configKeyList = configMapper.selectConfigKeyByIds(configIds);
			configKeyList.stream().parallel().forEach(key->{
				redisService.deleteObject(key);
			});
		}
		return count;
	}

	/**
	 * 校验参数键名是否唯一
	 * 
	 * @param config 参数配置信息
	 * @return 结果
	 */
	@Override
	public String checkConfigKeyUnique(SysConfig config) {
		Long configId = StringUtils.isNull(config.getConfigId()) ? -1L : config.getConfigId();
		SysConfig info = configMapper.checkConfigKeyUnique(config.getConfigKey());
		if (StringUtils.isNotNull(info) && info.getConfigId().longValue() != configId.longValue()) {
			return UserConstants.NOT_UNIQUE;
		}
		return UserConstants.UNIQUE;
	}

	@Override
	public SysConfig selectConfigCacheByKey(String configKey) {
		SysConfig retConfig = redisService.getCacheObject(configKey);
		if (StringUtils.isNull(retConfig)) {
		   retConfig = configMapper.checkConfigKeyUnique(configKey);
			setCache(retConfig);
		}
		return retConfig;
	}
	
	private void setCache(SysConfig retConfig) {
		if(retConfig == null) {
			return;
		}
		redisService.setCacheObject(retConfig.getConfigKey(), retConfig, 864000 + RandomUtil.getInt(1000, 2000), TimeUnit.SECONDS);
	}
	
	
}
