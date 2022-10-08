package com.tc.draw.demo.api.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tc.draw.demo.api.sys.mapper.SysDictDataMapper;
import com.tc.draw.demo.api.sys.model.domain.SysDictData;
import com.tc.draw.demo.api.sys.model.dto.SysDictDataListDTO;
import com.tc.draw.demo.api.sys.model.vo.dict.SysDictDataListVO;
import com.tc.draw.demo.api.sys.service.ISysDictDataService;
import com.tc.draw.demo.common.utils.SecurityUtils;

/**
 * 字典 业务层处理
 * 
 *
 */
@Service
public class SysDictDataServiceImpl implements ISysDictDataService {
	@Autowired
	private SysDictDataMapper dictDataMapper;

	/**
	 * 根据条件分页查询字典数据
	 * 
	 * @param dictData 字典数据信息
	 * @return 字典数据集合信息
	 */
	@Override
	public List<SysDictDataListVO> selectDictDataList(SysDictDataListDTO dictData) {
		return dictDataMapper.selectDictDataList(dictData);
	}

	/**
	 * 根据字典类型查询字典数据
	 * 
	 * @param dictType 字典类型
	 * @return 字典数据集合信息
	 */
	@Override
	public List<SysDictDataListVO> selectDictDataByType(String dictType) {
		return dictDataMapper.selectDictDataByType(dictType);
	}

	/**
	 * 根据字典类型和字典键值查询字典数据信息
	 * 
	 * @param dictType  字典类型
	 * @param dictValue 字典键值
	 * @return 字典标签
	 */
	@Override
	public String selectDictLabel(String dictType, String dictValue) {
		return dictDataMapper.selectDictLabel(dictType, dictValue);
	}

	/**
	 * 根据字典数据ID查询信息
	 * 
	 * @param dictCode 字典数据ID
	 * @return 字典数据
	 */
	@Override
	public SysDictData selectDictDataById(Long dictCode) {
		return dictDataMapper.selectDictDataById(dictCode);
	}

	/**
	 * 批量删除字典数据信息
	 * 
	 * @param dictCodes 需要删除的字典数据ID
	 * @return 结果
	 */
	public int deleteDictDataByIds(List<Long> dictCodes) {
		return dictDataMapper.deleteByIds(dictCodes, System.currentTimeMillis(), SecurityUtils.getUserId());
	}

	/**
	 * 新增保存字典数据信息
	 * 
	 * @param dictData 字典数据信息
	 * @return 结果
	 */
	@Override
	public int insertDictData(SysDictData dictData) {
		dictData.setCreateTime(System.currentTimeMillis());
		dictData.setCreatorId(SecurityUtils.getUserId());
		dictData.setOperatorId(dictData.getCreatorId());
		dictData.setUpdateTime(dictData.getCreateTime());
		dictData.setIsDelete(0);
		return dictDataMapper.insert(dictData);
	}

	/**
	 * 修改保存字典数据信息
	 * 
	 * @param dictData 字典数据信息
	 * @return 结果
	 */
	@Override
	public int updateDictData(SysDictData dictData) {
		dictData.setUpdateTime(System.currentTimeMillis());
		dictData.setOperatorId(SecurityUtils.getUserId());
		return dictDataMapper.updateById(dictData);
	}
}
