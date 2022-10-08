package com.tc.draw.demo.api.org.service.impl;

import com.tc.draw.demo.api.org.mapper.SysDeptMapper;
import com.tc.draw.demo.api.org.model.domain.SysDept;
import com.tc.draw.demo.api.org.model.dto.dept.SysDeptListDTO;
import com.tc.draw.demo.api.org.model.vo.SysDeptListVO;
import com.tc.draw.demo.api.org.model.vo.SysOrgDeptSelectVO;
import com.tc.draw.demo.api.org.service.ISysDeptService;
import com.tc.draw.demo.api.sys.model.vo.TreeSelectVO;
import com.tc.draw.demo.common.constant.UserConstants;
import com.tc.draw.demo.common.utils.SecurityUtils;
import com.tc.draw.demo.common.utils.StringUtils;
import com.tc.draw.demo.exception.CustomException;
import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 部门管理 服务实现
 * 
 *
 */
@Service
public class SysDeptServiceImpl implements ISysDeptService {
	
	private static final Log LOG = LogFactory.getLog(SysDeptServiceImpl.class);
	
	@Autowired
	private SysDeptMapper deptMapper;


	/**
	 * 查询部门管理数据
	 * 
	 * @param dept 部门信息
	 * @return 部门信息集合
	 */
	@Override
	public List<SysDeptListVO> selectDeptList(SysDeptListDTO dept) {
		return deptMapper.selectDeptList(dept);
	}

	/**
	 * 构建前端所需要树结构
	 * 
	 * @param depts 部门列表
	 * @return 树结构列表
	 */
	@Override
	public List<SysDeptListVO> buildDeptTree(List<SysDeptListVO> depts) {
		List<SysDeptListVO> returnList = new ArrayList<>();
		List<Long> tempList = new ArrayList<Long>();
		for (SysDeptListVO dept : depts) {
			tempList.add(dept.getId());
		}
		for (Iterator<SysDeptListVO> iterator = depts.iterator(); iterator.hasNext();) {
			SysDeptListVO dept = (SysDeptListVO) iterator.next();
			// 如果是顶级节点, 遍历该父节点的所有子节点
			if (!tempList.contains(dept.getParentId())) {
				recursionFn(depts, dept);
				returnList.add(dept);
			}
		}
		if (returnList.isEmpty()) {
			returnList = depts;
		}
		return returnList;
	}

	/**
	 * 构建前端所需要下拉树结构
	 * 
	 * @param depts 部门列表
	 * @return 下拉树结构列表
	 */
	@Override
	public List<TreeSelectVO> buildDeptTreeSelect(List<SysDeptListVO> depts) {
		List<SysDeptListVO> deptTrees = buildDeptTree(depts);
		return deptTrees.stream().map(TreeSelectVO::new).collect(Collectors.toList());
	}

	/**
	 * 根据角色ID查询部门树信息
	 * 
	 * @param roleId 角色ID
	 * @return 选中部门列表
	 */
	@Override
	public List<Long> selectDeptListByRoleId(Long roleId) {
		return deptMapper.selectDeptListByRoleId(roleId);
	}

	/**
	 * 根据部门ID查询信息
	 * 
	 * @param deptId 部门ID
	 * @return 部门信息
	 */
	@Override
	public SysDept selectDeptById(Long deptId) {
		return deptMapper.selectDeptById(deptId);
	}

	/**
	 * 是否存在子节点
	 * 
	 * @param deptId 部门ID
	 * @return 结果
	 */
	@Override
	public boolean hasChildByDeptId(Long deptId) {
		int result = deptMapper.hasChildByDeptId(deptId);
		return result > 0 ? true : false;
	}

	/**
	 * 查询部门是否存在用户
	 * 
	 * @param deptId 部门ID
	 * @return 结果 true 存在 false 不存在
	 */
	@Override
	public boolean checkDeptExistUser(Long deptId) {
		int result = deptMapper.checkDeptExistUser(deptId);
		return result > 0 ? true : false;
	}

	/**
	 * 校验部门名称是否唯一
	 * 
	 * @param dept 部门信息
	 * @return 结果
	 */
	@Override
	public String checkDeptNameUnique(SysDept dept) {
		Long deptId = StringUtils.isNull(dept.getId()) ? -1L : dept.getId();
		SysDept info = deptMapper.checkDeptNameUnique(dept.getDeptName(), dept.getParentId());
		if (StringUtils.isNotNull(info) && info.getId().longValue() != deptId.longValue()) {
			return UserConstants.NOT_UNIQUE;
		}
		return UserConstants.UNIQUE;
	}

	/**
	 * 新增保存部门信息
	 * 
	 * @param dept 部门信息
	 * @return 结果
	 */
	@Override
	public int insertDept(SysDept dept) {
		if(dept.getParentId() != null){
			SysDept info = deptMapper.selectDeptById(dept.getParentId());
			// 如果父节点不为正常状态,则不允许新增子节点
			if (!UserConstants.DEPT_NORMAL.equals(info.getStatus())) {
				throw new CustomException("部门停用，不允许新增");
			}
			dept.setAncestors(info.getAncestors() + "," + dept.getParentId());
		} else {
			dept.setAncestors("0");
		}
		dept.setCreateTime(System.currentTimeMillis());
		dept.setCreatorId(SecurityUtils.getUserId());
		dept.setUpdateTime(dept.getCreateTime());
		dept.setOperatorId(dept.getCreatorId());
		try {
			dept.setNamePinyin(PinyinHelper.getShortPinyin(dept.getDeptName()));
		} catch (PinyinException e) {
			LOG.error(e,e);
			dept.setNamePinyin("");
		}
		int count = deptMapper.insert(dept);
		return count;
	}

	/**
	 * 修改保存部门信息
	 * 
	 * @param dept 部门信息
	 * @return 结果
	 */
	@Override
	public int updateDept(SysDept dept) {
		SysDept newParentDept = deptMapper.selectDeptById(dept.getParentId());
		SysDept oldDept = deptMapper.selectDeptById(dept.getId());
		if (StringUtils.isNotNull(newParentDept) && StringUtils.isNotNull(oldDept)) {
			String newAncestors = newParentDept.getAncestors() + "," + newParentDept.getId();
			String oldAncestors = oldDept.getAncestors();
			dept.setAncestors(newAncestors);
			dept.setOperatorId(SecurityUtils.getUserId());
			dept.setUpdateTime(System.currentTimeMillis());
			updateDeptChildren(dept.getId(), newAncestors, oldAncestors);
		}
		try {
			dept.setNamePinyin(PinyinHelper.getShortPinyin(dept.getDeptName()));
		} catch (PinyinException e) {
			LOG.error(e,e);
			dept.setNamePinyin("");
		}
		int result = deptMapper.updateById(dept);
		if (UserConstants.DEPT_NORMAL.equals(dept.getStatus())) {
			// 如果该部门是启用状态，则启用该部门的所有上级部门
			updateParentDeptStatus(dept);
		}
		return result;
	}

	/**
	 * 修改该部门的父级部门状态
	 * 
	 * @param dept 当前部门
	 */
	private void updateParentDeptStatus(SysDept dept) {
		dept = deptMapper.selectDeptById(dept.getId());
		deptMapper.updateDeptStatus(dept);
	}

	/**
	 * 修改子元素关系
	 * 
	 * @param deptId       被修改的部门ID
	 * @param newAncestors 新的父ID集合
	 * @param oldAncestors 旧的父ID集合
	 */
	public void updateDeptChildren(Long deptId, String newAncestors, String oldAncestors) {
		List<SysDept> children = deptMapper.selectChildrenDeptById(deptId);
		for (SysDept child : children) {
			child.setAncestors(child.getAncestors().replace(oldAncestors, newAncestors));
		}
		if (children.size() > 0) {
			deptMapper.updateDeptChildren(children);
		}
	}

	/**
	 * 删除部门管理信息
	 * 
	 * @param deptId 部门ID
	 * @return 结果
	 */
	@Override
	public int deleteDeptById(Long deptId) {
		return deptMapper.delete(deptId, System.currentTimeMillis());
	}

	@Override
	public List<SysOrgDeptSelectVO> getDeptAll() {
		return deptMapper.selectDeptInfoById(0L);
	}

	/**
	 * 递归列表
	 */
	private void recursionFn(List<SysDeptListVO> list, SysDeptListVO t) {
		// 得到子节点列表
		List<SysDeptListVO> childList = getChildList(list, t);
		t.setChildren(childList);
		for (SysDeptListVO tChild : childList) {
			if (hasChild(list, tChild)) {
				// 判断是否有子节点
				Iterator<SysDeptListVO> it = childList.iterator();
				while (it.hasNext()) {
					SysDeptListVO n = (SysDeptListVO) it.next();
					recursionFn(list, n);
				}
			}
		}
	}

	/**
	 * 得到子节点列表
	 */
	private List<SysDeptListVO> getChildList(List<SysDeptListVO> list, SysDeptListVO t) {
		List<SysDeptListVO> tlist = new ArrayList<>();
		Iterator<SysDeptListVO> it = list.iterator();
		while (it.hasNext()) {
			SysDeptListVO n = it.next();
			if (StringUtils.isNotNull(n.getParentId()) && n.getParentId().longValue() == t.getId().longValue()) {
				tlist.add(n);
			}
		}
		return tlist;
	}

	/**
	 * 判断是否有子节点
	 */
	private boolean hasChild(List<SysDeptListVO> list, SysDeptListVO t) {
		return getChildList(list, t).size() > 0 ? true : false;
	}
}
