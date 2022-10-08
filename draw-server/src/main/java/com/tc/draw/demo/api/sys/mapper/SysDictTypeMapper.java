package com.tc.draw.demo.api.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tc.draw.demo.api.sys.model.domain.SysDictType;
import com.tc.draw.demo.api.sys.model.dto.SysDictTypeListDTO;
import com.tc.draw.demo.api.sys.model.vo.dict.SysDictTypeListVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 字典表 数据层
 * 
 *
 */
@Mapper
public interface SysDictTypeMapper extends BaseMapper<SysDictType> {
	/**
	 * 根据条件分页查询字典类型
	 * 
	 * @param dictType 字典类型信息
	 * @return 字典类型集合信息
	 */
	@SelectProvider(type = SysDictTypeMapperSQL.class, method = "selectSysDictTypeList")
	List<SysDictTypeListVO> selectSysDictTypeList(SysDictTypeListDTO dictType);

	/**
	 * 根据所有字典类型
	 * 
	 * @return 字典类型集合信息
	 */
	@Select("select dict_id, dict_name, dict_type, status, creator_id, create_time, remark from sys_dict_type where is_delete=0")
	List<SysDictTypeListVO> selectDictTypeAll();

	/**
	 * 根据字典类型ID查询信息
	 * 
	 * @param dictId 字典类型ID
	 * @return 字典类型
	 */
	@Select("select dict_id, dict_name, dict_type, status, creator_id, create_time, remark from sys_dict_type where dict_id = #{dictId}")
	SysDictType selectDictTypeById(Long dictId);

	/**
	 * 根据字典类型查询信息
	 * 
	 * @param dictType 字典类型
	 * @return 字典类型
	 */
	@Select(" where dict_type = #{dictType} and is_delete=0")
	SysDictType selectDictTypeByType(String dictType);


	/**
	 * 批量删除字典类型
	 *
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */
	@UpdateProvider(type = SysDictTypeMapperSQL.class, method = "deleteByIds")
	int deleteByIds(@Param("list") List<Long> ids, @Param("updateTime") long updateTime,
			@Param("operatorId") long operatorId);

	/**
	 * 校验字典类型称是否唯一
	 * 
	 * @param dictType 字典类型
	 * @return 结果
	 */
	@Select("select dict_id from sys_dict_type where dict_type = #{dictType} and is_delete=0")
	Long checkDictTypeUnique(String dictType);
}
