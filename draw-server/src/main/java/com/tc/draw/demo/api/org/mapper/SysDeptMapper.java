package com.tc.draw.demo.api.org.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tc.draw.demo.api.org.model.domain.SysDept;
import com.tc.draw.demo.api.org.model.dto.dept.SysDeptListDTO;
import com.tc.draw.demo.api.org.model.vo.SysDeptListVO;
import com.tc.draw.demo.api.org.model.vo.SysOrgDeptSelectVO;
import org.apache.ibatis.annotations.*;

/**
 * 部门管理 数据层
 *
 *
 */
@Mapper
public interface SysDeptMapper extends BaseMapper<SysDept>

{
    /**
     * 查询部门管理数据
     *
     * @param dept 部门信息
     * @return 部门信息集合
     */
	@SelectProvider(type = SysDeptMapperSQL.class, method = "selectDeptList")
    List<SysDeptListVO> selectDeptList(SysDeptListDTO dept);

    /**
     * 根据角色ID查询部门树信息
     *
     * @param roleId 角色ID
     * @return 选中部门列表
     */
    @Select("select d.id from sys_dept d left join sys_role_dept rd on d.id = rd.dept_id"
    		+" where d.is_delete=0 and rd.role_id = #{roleId} and d.id not in (select d.parent_id from sys_dept d"
    		+ " inner join sys_role_dept rd on d.id = rd.dept_id and rd.role_id = #{roleId})"
    		+" order by d.parent_id, d.order_num")
    List<Long> selectDeptListByRoleId(Long roleId);

    /**
     * 根据部门ID查询信息
     *
     * @param deptId 部门ID
     * @return 部门信息
     */
    @Select("select d.id, d.parent_id, d.ancestors, d.dept_name, d.order_num, d.leader_id,u.name leader, d.phone,"
    		+ " d.email, d.status,d.creator_id, d.create_time " +
    		" from sys_dept d left join sys_user u on u.id=d.leader_id where d.id=#{deptId} ")
    SysDept selectDeptById(Long deptId);

    /**
     * 根据ID查询包含该id的部门
     *
     * @param deptId 部门ID
     * @return 部门列表
     */
    @Select("select * from sys_dept where find_in_set(#{deptId}, ancestors)")
    List<SysDept> selectChildrenDeptById(Long deptId);

    /**
     * 根据ID查询包含该id的部门Id
     *
     *
     * @param deptId 部门ID
     * @return 部门列表
     */
    @Select("select id from sys_dept where find_in_set(#{deptId}, ancestors)")
    List<Long> selectChildrenDeptIdsById(Long deptId);

    /**
     * 是否存在子节点
     *
     * @param deptId 部门ID
     * @return 结果
     */
    @Select("select count(1) from sys_dept where is_delete = 0 and parent_id = #{deptId}")
    int hasChildByDeptId(Long deptId);

    /**
     * 查询部门是否存在用户
     *
     * @param deptId 部门ID
     * @return 结果
     */
    @Select("select count(1) from sys_user where id = #{deptId} and is_delete=0")
    int checkDeptExistUser(Long deptId);

    /**
     * 校验部门名称是否唯一
     *
     * @param deptName 部门名称
     * @param parentId 父部门ID
     * @return 结果
     */
    @Select("select id, parent_id, ancestors, dept_name, order_num, phone,"
    		+ " email, status,creator_id, create_time " +
    		" from sys_dept where dept_name=#{deptName} and parent_id = #{parentId} and is_delete=0")
    SysDept checkDeptNameUnique(@Param("deptName") String deptName, @Param("parentId") Long parentId);


    /**
     * 修改所在部门的父级部门状态
     *
     * @param dept 部门
     */
    @Update("update sys_dept set status = #{status},update_time = #{updateTime},operator_id=#{operatorId} "
    		+ "where id in (${ancestors})")
    void updateDeptStatus(SysDept dept);

    /**
     * 修改子元素关系
     *
     * @param depts 子元素
     * @return 结果
     */
    @UpdateProvider(type = SysDeptMapperSQL.class, method = "updateDeptChildren")
    int updateDeptChildren(@Param("list") List<SysDept> depts);

    /**
     * 删除部门管理信息
     *
     * @return 结果
     */
    @Update("update sys_dept set is_delete = 1,update_time=#{updateTime} where id=#{id}")
    int delete(@Param("id") Long id, @Param("updateTime") long updateTime);


    @Select("select id, dept_name name from sys_dept where parent_id=#{id} and is_delete=0")
	List<SysOrgDeptSelectVO> selectDeptInfoById(@Param("id") long id);

}
