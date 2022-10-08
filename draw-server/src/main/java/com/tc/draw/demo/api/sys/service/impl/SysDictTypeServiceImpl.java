package com.tc.draw.demo.api.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tc.draw.demo.api.sys.mapper.SysDictDataMapper;
import com.tc.draw.demo.api.sys.mapper.SysDictTypeMapper;
import com.tc.draw.demo.api.sys.model.domain.SysDictType;
import com.tc.draw.demo.api.sys.model.dto.SysDictTypeListDTO;
import com.tc.draw.demo.api.sys.model.vo.dict.SysDictTypeListVO;
import com.tc.draw.demo.api.sys.service.ISysDictTypeService;
import com.tc.draw.demo.common.constant.UserConstants;
import com.tc.draw.demo.common.utils.SecurityUtils;
import com.tc.draw.demo.common.utils.StringUtils;

/**
 * 字典 业务层处理
 * 
 *
 */
@Service
public class SysDictTypeServiceImpl implements ISysDictTypeService {
	@Autowired
	private SysDictTypeMapper dictTypeMapper;

	@Autowired
	private SysDictDataMapper dictDataMapper;

	/**
	 * 根据条件分页查询字典类型
	 * 
	 * @param dictType 字典类型信息
	 * @return 字典类型集合信息
	 */
	@Override
	public List<SysDictTypeListVO> selectDictTypeList(SysDictTypeListDTO dictType) {
		return dictTypeMapper.selectSysDictTypeList(dictType);
	}

	/**
	 * 根据所有字典类型
	 * 
	 * @return 字典类型集合信息
	 */
	@Override
	public List<SysDictTypeListVO> selectDictTypeAll() {
		return dictTypeMapper.selectDictTypeAll();
	}

	/**
	 * 根据字典类型ID查询信息
	 * 
	 * @param dictId 字典类型ID
	 * @return 字典类型
	 */
	@Override
	public SysDictType selectDictTypeById(Long dictId) {
		return dictTypeMapper.selectDictTypeById(dictId);
	}

	/**
	 * 根据字典类型查询信息
	 * 
	 * @param dictType 字典类型
	 * @return 字典类型
	 */
	@Override
	public SysDictType selectDictTypeByType(String dictType) {
		return dictTypeMapper.selectDictTypeByType(dictType);
	}

	/**
	 * 批量删除字典类型信息
	 * 
	 * @param dictIds 需要删除的字典ID
	 * @return 结果
	 */
	@Override
	public int deleteDictTypeByIds(List<Long> dictIds) {
		return dictTypeMapper.deleteByIds(dictIds, System.currentTimeMillis(), SecurityUtils.getUserId());
	}

	/**
	 * 新增保存字典类型信息
	 * 
	 * @param dictType 字典类型信息
	 * @return 结果
	 */
	@Override
	public int insertDictType(SysDictType dictType) {
		dictType.setCreateTime(System.currentTimeMillis());
		dictType.setCreatorId(SecurityUtils.getUserId());
		dictType.setOperatorId(dictType.getCreatorId());
		dictType.setUpdateTime(dictType.getCreateTime());
		dictType.setIsDelete(0);
		return dictTypeMapper.insert(dictType);
	}

	/**
	 * 修改保存字典类型信息
	 * 
	 * @param dictType 字典类型信息
	 * @return 结果
	 */
	@Override
	@Transactional
	public int updateDictType(SysDictType dictType) {
		SysDictType oldDict = dictTypeMapper.selectDictTypeById(dictType.getDictId());
		dictDataMapper.updateDictDataType(oldDict.getDictType(), dictType.getDictType(), System.currentTimeMillis(), SecurityUtils
				.getUserId());
		dictType.setUpdateTime(System.currentTimeMillis());
		dictType.setOperatorId(SecurityUtils.getUserId());
		return dictTypeMapper.updateById(dictType);
	}

	/**
	 * 校验字典类型称是否唯一
	 * 
	 * @param dict 字典类型
	 * @return 结果
	 */
	@Override
	public String checkDictTypeUnique(SysDictType dict) {
		Long dictId = StringUtils.isNull(dict.getDictId()) ? -1L : dict.getDictId();
		Long id = dictTypeMapper.checkDictTypeUnique(dict.getDictType());
		if (StringUtils.isNotNull(id) && id.longValue() != dictId.longValue()) {
			return UserConstants.NOT_UNIQUE;
		}
		return UserConstants.UNIQUE;
	}
}
