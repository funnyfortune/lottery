package com.tc.draw.demo.api.org.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tc.draw.demo.api.org.model.domain.SysPost;
import com.tc.draw.demo.api.org.model.dto.post.SysPostListDTO;
import org.apache.ibatis.annotations.*;

/**
 * 岗位信息 数据层
 * 
 *
 */
@Mapper
public interface SysPostMapper extends BaseMapper<SysPost> {
	/**
	 * 查询岗位数据集合
	 * 
	 * @param post 岗位信息
	 * @return 岗位数据集合
	 */
	@SelectProvider(type = SysPostMapperSQL.class, method = "selectPostList")
	List<SysPost> selectPostList(SysPostListDTO post);

	/**
	 * 查询所有岗位
	 * 
	 * @return 岗位列表
	 */
	@Select("select id, post_code, post_name, post_sort, status, creator_id, create_time, remark "
			+ "from sys_post where is_delete = 0 ")
	List<SysPost> selectPostAll();

	/**
	 * 通过岗位ID查询岗位信息
	 * 
	 * @param postId 岗位ID
	 * @return 角色对象信息
	 */
	@Select("select id, post_code, post_name, post_sort, status, creator_id, create_time, remark "
			+ "from sys_post where id = #{postId}")
	SysPost selectPostById(Long postId);

	/**
	 * 根据用户ID获取岗位选择框列表
	 * 
	 * @param userId 用户ID
	 * @return 选中岗位ID列表
	 */
	@Select("select post_id from sys_user_post where user_id=#{userId}")
	List<Long> selectPostListByUserId(Long userId);
	
	/**
	 * 根据用户ID获取岗位列表
	 * 
	 * @param userId 用户ID
	 * @return 选中岗位ID列表
	 */
	@Select("select p.post_name from sys_user_post pu,sys_post p  where pu.user_id=#{userId} and p.id=pu.post_id")
	List<String> selectPosNametListByUserId(Long userId);

	/**
	 * 查询用户所属岗位组
	 * 
	 * @param userName 用户名
	 * @return 结果
	 */
	@Select("	select p.id, p.post_name, p.post_code from sys_post p "
			+ "			 left join sys_user_post up on up.post_id = p.id "
			+ "			 left join sys_user u on u.id = up.user_id where u.name = #{userName}")
	List<SysPost> selectPostsByUserName(@Param("userName")String userName);

	/**
	 * 批量删除岗位信息
	 * 
	 * @param ids 需要删除的岗位ID
	 * @return 结果
	 */
	@UpdateProvider(type = SysPostMapperSQL.class, method = "batDelete")
	int batDelete(@Param("ids") List<Long> ids, @Param("updateTime") long updateTime);

	/**
	 * 校验岗位名称
	 * 
	 * @param postName 岗位名称
	 * @return 结果
	 */
	@Select("select id from sys_post where post_name=#{postName} and is_delete=0")
	Long checkPostNameUnique(String postName);

	/**
	 * 校验岗位编码
	 * 
	 * @param postCode 岗位编码
	 * @return 结果
	 */
	@Select("select id from sys_post where post_code=#{postCode} and is_delete=0")
	Long checkPostCodeUnique(String postCode);
}
