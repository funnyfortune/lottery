package com.tc.draw.demo.api.org.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tc.draw.demo.api.org.model.domain.SysUser;
import com.tc.draw.demo.api.org.model.dto.SysUserListDTO;
import com.tc.draw.demo.api.org.model.vo.SysUserListVO;
import org.apache.ibatis.annotations.*;

import com.tc.draw.demo.framework.security.SysUserInfo;

/**
 * 用户表 数据层
 * 
 *
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser>
{
    /**
     * 根据条件分页查询用户列表
     * 
     * @param sysUser 用户信息
     * @return 用户信息集合信息
     */
	@SelectProvider(type=SysUserMapperSQL.class, method="selectUserList")
     List<SysUserListVO> selectUserList(SysUserListDTO dto);

    /**
     * 通过用户名查询用户
     * 
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Select("select u.id id,u.name,u.status,u.dept_id deptId,d.dept_name deptName,u.avatar,"
    		+ "u.login_name loginName,u.password password,"
    		+ "u.work_number workNumber,u.org_email orgEmail,"
    		+ "u.mobile_no mobileNo from sys_user u left join sys_dept d on u.dept_id=d.id "
    		+ "where u.login_name=#{userName} and u.is_delete=0")
    SysUserInfo selectUserByUserName(String userName);
    
    @Select("select id from sys_user where login_name=#{userName} and is_delete=0 ")
    Long countUserByUserName(String userName);

    /**
     * 通过用户ID查询用户
     * 
     * @param userId 用户ID
     * @return 用户对象信息
     */
    @Select("select id id,name name,sex,status,remark,dept_id deptId,avatar,"
    		+ "login_name loginName,password password,entry_time entryTime,"
    		+ "work_number workNumber,org_email orgEmail,"
    		+ "create_time createTime,update_time updateTime,creator_id creatorId,operator_id operatorId,"
    		+ "mobile_no mobileNo from sys_user where id=#{userId}")
     SysUser selectUserById(Long userId);

    /**
     * 批量删除用户信息
     * 
     * @param userIds 需要删除的用户ID
     * @return 结果
     */
    @UpdateProvider(type = SysUserMapperSQL.class, method = "batDelete")
    int batDelete(@Param("ids")List<Long> ids, @Param("updateTime") long updateTime);

    /**
     * 校验用户名称是否唯一
     * 
     * @param userName 用户名称
     * @return 结果
     */
    @Select("select count(1) from sys_user where login_name = #{userName}  and is_delete=0")
    int checkUserNameUnique(String userName);

    /**
     * 校验手机号码是否唯一
     *
     * @param phonenumber 手机号码
     * @return 结果
     */
    @Select("select count(*) from sys_user where mobile_no = #{phoneNumber} and id<>#{id} and is_delete=0")
    int checkPhoneUnique(@Param("phoneNumber") String phoneNumber, @Param("id") long id);

    /**
     * 校验email是否唯一
     *
     * @param email 用户邮箱
     * @return 结果
     */
    @Select("select count(*) from sys_user where org_email = #{email} and id<>#{id} and is_delete=0")
    int checkEmailUnique(@Param("email") String email, @Param("id") long id);

    @SelectProvider(type = SysUserMapperSQL.class, method = "selectEngineerListByKeyword")
    List<SysUserListVO> selectEngineerListByKeyword(@Param("userName") String userName,@Param("deptId") long deptId, @Param("roleId") long roleId);
}
