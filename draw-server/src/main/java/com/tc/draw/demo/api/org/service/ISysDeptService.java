package com.tc.draw.demo.api.org.service;

import java.util.List;

import com.tc.draw.demo.api.org.model.domain.SysDept;
import com.tc.draw.demo.api.org.model.dto.dept.SysDeptListDTO;
import com.tc.draw.demo.api.org.model.vo.SysDeptListVO;
import com.tc.draw.demo.api.org.model.vo.SysOrgDeptSelectVO;
import com.tc.draw.demo.api.sys.model.vo.TreeSelectVO;

/**
 * 部门管理 服务层
 * 
 *
 */
public interface ISysDeptService
{
    /**
     * 查询部门管理数据
     * 
     * @param dept 部门信息
     * @return 部门信息集合
     */
     List<SysDeptListVO> selectDeptList(SysDeptListDTO dept);

    /**
     * 构建前端所需要树结构
     * 
     * @param depts 部门列表
     * @return 树结构列表
     */
     List<SysDeptListVO> buildDeptTree(List<SysDeptListVO> depts);

    /**
     * 构建前端所需要下拉树结构
     * 
     * @param depts 部门列表
     * @return 下拉树结构列表
     */
     List<TreeSelectVO> buildDeptTreeSelect(List<SysDeptListVO> depts);

    /**
     * 根据角色ID查询部门树信息
     * 
     * @param roleId 角色ID
     * @return 选中部门列表
     */
     List<Long> selectDeptListByRoleId(Long roleId);

    /**
     * 根据部门ID查询信息
     * 
     * @param deptId 部门ID
     * @return 部门信息
     */
     SysDept selectDeptById(Long deptId);

    /**
     * 是否存在部门子节点
     * 
     * @param deptId 部门ID
     * @return 结果
     */
     boolean hasChildByDeptId(Long deptId);

    /**
     * 查询部门是否存在用户
     * 
     * @param deptId 部门ID
     * @return 结果 true 存在 false 不存在
     */
     boolean checkDeptExistUser(Long deptId);

    /**
     * 校验部门名称是否唯一
     * 
     * @param dept 部门信息
     * @return 结果
     */
     String checkDeptNameUnique(SysDept dept);

    /**
     * 新增保存部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
     int insertDept(SysDept dept);

    /**
     * 修改保存部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
     int updateDept(SysDept dept);

    /**
     * 删除部门管理信息
     * 
     * @param deptId 部门ID
     * @return 结果
     */
     int deleteDeptById(Long deptId);

    List<SysOrgDeptSelectVO> getDeptAll();
}
