package com.tc.draw.demo.api.org.mapper;

import java.util.List;

import com.tc.draw.demo.api.org.model.domain.SysUserPost;
import org.apache.ibatis.annotations.*;

/**
 * 用户与岗位关联表 数据层
 * 
 *
 */
@Mapper
public interface SysUserPostMapper {
	/**
	 * 通过用户ID删除用户和岗位关联
	 * 
	 * @param userId 用户ID
	 * @return 结果
	 */
	@Delete("delete from sys_user_post where user_id=#{userId} ")
	int deleteUserPostByUserId(Long userId);

	/**
	 * 通过岗位ID查询岗位使用数量
	 * 
	 * @param postId 岗位ID
	 * @return 结果
	 */
	@Select("select count(1) from sys_user_post where post_id=#{postId}  ")
	int countUserPostById(Long postId);

	/**
	 * 批量删除用户和岗位关联
	 * 
	 * @param ids 需要删除的数据ID
	 * @return 结果
	 */

	@DeleteProvider(type = SysUserPostMapperSQL.class, method = "deleteUserPost")
	int deleteUserPost(@Param("list") List<Long> ids);

	/**
	 * 批量新增用户岗位信息
	 * 
	 * @param userPostList 用户角色列表
	 * @return 结果
	 */
	@InsertProvider(type = SysUserPostMapperSQL.class, method = "insert")
	int insert(@Param("list") List<SysUserPost> userPostList);

	@SelectProvider(type = SysUserPostMapperSQL.class, method = "checkPostIdsUsedByPostIds")
    List<String> checkPostIdsUsedByPostIds(@Param("list") List<Long> postIds);
}
