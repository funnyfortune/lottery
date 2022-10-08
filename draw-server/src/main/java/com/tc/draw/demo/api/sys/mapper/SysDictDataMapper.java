package com.tc.draw.demo.api.sys.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import com.tc.draw.demo.api.sys.model.domain.SysDictData;
import com.tc.draw.demo.api.sys.model.dto.SysDictDataListDTO;
import com.tc.draw.demo.api.sys.model.vo.dict.SysDictDataListVO;

/**
 * 字典表 数据层
 * 
 *
 */
@Mapper
public interface SysDictDataMapper extends BaseMapper<SysDictData> {
	/**
	 * 根据条件分页查询字典数据
	 * 
	 * @param dictData 字典数据信息
	 * @return 字典数据集合信息
	 */
	@SelectProvider(type = SysDictDataMapperSQL.class, method = "selectDictDataList")
	List<SysDictDataListVO> selectDictDataList(SysDictDataListDTO dictData);

	/**
	 * 根据字典类型查询字典数据
	 * 
	 * @param dictType 字典类型
	 * @return 字典数据集合信息
	 */
	@Select("select dict_code, dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default,"
			+ " status, creator_id, create_time, remark from sys_dict_data "
			+ "where status = '0' and dict_type = #{dictType} and is_delete=0 order by dict_sort asc")
	List<SysDictDataListVO> selectDictDataByType(String dictType);

	/**
	 * 根据字典类型和字典键值查询字典数据信息
	 * 
	 * @param dictType  字典类型
	 * @param dictValue 字典键值
	 * @return 字典标签
	 */
	@Select("select dict_label from sys_dict_data where dict_type = #{dictType} and dict_value = #{dictValue} and is_delete=0")
	String selectDictLabel(@Param("dictType") String dictType, @Param("dictValue") String dictValue);

	/**
	 * 根据字典数据ID查询信息
	 * 
	 * @param dictCode 字典数据ID
	 * @return 字典数据
	 */
	@Select(" select dict_code, dict_sort, dict_label, dict_value, dict_type, css_class, list_class, is_default,"
			+ " status, creator_id, create_time, remark from sys_dict_data where dict_code = #{dictCode}")
	SysDictData selectDictDataById(Long dictCode);

	/**
	 * 查询字典数据
	 * 
	 * @param dictType 字典类型
	 * @return 字典数据
	 */
	@Select("select count(1) from sys_dict_data where dict_type=#{dictType} ")
	int countDictDataByType(String dictType);



	/**
	 * 批量删除字典数据
	 *
	 * @param dictCodes 需要删除的数据ID
	 * @return 结果
	 */
	@UpdateProvider(type = SysDictDataMapperSQL.class, method = "deleteByIds")
	int deleteByIds(@Param("list") List<Long> dictCodes, @Param("updateTime") long updateTime,
			@Param("operatorId") long operatorId);

	/**
	 * 同步修改字典类型
	 * 
	 * @param oldDictType 旧字典类型
	 * @param newDictType 新旧字典类型
	 * @return 结果
	 */
	@Update("update sys_dict_data set dict_type = #{newDictType},update_time=#{updateTime},operator_id=#{operatorId} where dict_type = #{oldDictType}")
	int updateDictDataType(@Param("oldDictType") String oldDictType, @Param("newDictType") String newDictType,
			@Param("updateTime") long updateTime, @Param("operatorId") long operatorId);
}
