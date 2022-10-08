package com.tc.draw.demo.api.org.controller;

import com.tc.draw.demo.api.org.model.domain.SysDept;
import com.tc.draw.demo.api.org.model.dto.dept.RoleDeptTreeVO;
import com.tc.draw.demo.api.org.model.dto.dept.SysDeptListDTO;
import com.tc.draw.demo.api.org.model.vo.SysDeptListVO;
import com.tc.draw.demo.api.org.model.vo.SysOrgDeptSelectVO;
import com.tc.draw.demo.api.org.service.ISysDeptService;
import com.tc.draw.demo.api.sys.model.vo.TreeSelectVO;
import com.tc.draw.demo.common.constant.UserConstants;
import com.tc.draw.demo.framework.web.controller.BaseController;
import com.tc.draw.demo.framework.web.model.Res;
import com.tc.draw.demo.framework.web.model.code.SysStatusCode;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门信息
 * 
 *
 */
@RestController
@RequestMapping("/system/dept")
public class SysDeptController extends BaseController {
	@Autowired
	private ISysDeptService deptService;
	


	/**
	 * 获取部门列表
	 */
	@PreAuthorize("@ss.hasPermi('system:dept:list')")
	@GetMapping("/list")
	public Res<List<SysDeptListVO>, Void> list(SysDeptListDTO dept) {
		List<SysDeptListVO> depts = deptService.selectDeptList(dept);
		return Res.ok(depts);
	}

	/**
	 * 根据部门编号获取详细信息
	 */
	@PreAuthorize("@ss.hasPermi('system:dept:list')")
	@GetMapping(value = "/{deptId}")
	public Res<SysDept, Void> getInfo(@PathVariable Long deptId) {
		return Res.ok(deptService.selectDeptById(deptId));
	}

	/**
	 * 获取部门下拉树列表
	 */
	@GetMapping("/treeselect")
	public Res<List<TreeSelectVO>, Void> treeselect(SysDeptListDTO dept) {
		List<SysDeptListVO> depts = deptService.selectDeptList(dept);
		return Res.ok(deptService.buildDeptTreeSelect(depts));
	}

	/**
	 * 加载对应角色部门列表树
	 */
	@GetMapping(value = "/roleDeptTreeselect/{roleId}")
	public Res<RoleDeptTreeVO, Void> roleDeptTreeselect(@PathVariable("roleId") Long roleId) {
		List<SysDeptListVO> depts = deptService.selectDeptList(new SysDeptListDTO());
		RoleDeptTreeVO vo = new RoleDeptTreeVO();
		vo.setCheckedKeys(deptService.selectDeptListByRoleId(roleId));
		vo.setDepts(deptService.buildDeptTreeSelect(depts));
		return Res.ok(vo);
	}

	/**
	 * 新增部门
	 */
	@PreAuthorize("@ss.hasPermi('system:dept:save')")
	@PostMapping("save")
	public Res<Integer, Void> add(@Validated @RequestBody SysDept dept) {
		if (UserConstants.NOT_UNIQUE.equals(deptService.checkDeptNameUnique(dept))) {
			return new Res<>(SysStatusCode.DB_EXIST_RECODE.getCode(), "新增部门'" + dept.getDeptName() + "'失败，部门名称已存在");
		}
		return Res.ok(deptService.insertDept(dept));
	}

	/**
	 * 修改部门
	 */
	@PreAuthorize("@ss.hasPermi('system:dept:update')")
	@PostMapping("update")
	public Res<Integer, Void> edit(@Validated @RequestBody SysDept dept) {
		if (UserConstants.NOT_UNIQUE.equals(deptService.checkDeptNameUnique(dept))) {
			return new Res<>(SysStatusCode.DB_EXIST_RECODE.getCode(), "修改部门'" + dept.getDeptName() + "'失败，部门名称已存在");
		} else if (dept.getParentId().equals(dept.getId())) {
			return new Res<>(SysStatusCode.PARAM_ERROR.getCode(), "修改部门'" + dept.getDeptName() + "'失败，上级部门不能是自己");
		}
		return Res.ok(deptService.updateDept(dept));
	}

	/**
	 * 删除部门
	 */
	@PreAuthorize("@ss.hasPermi('system:dept:delete')")
	@GetMapping("delete")
	public Res<Integer, Void> remove(Long deptId) {
		if (deptService.hasChildByDeptId(deptId)) {
			return new Res<>(SysStatusCode.DB_EXIST_RECODE.getCode(), "存在下级部门,不允许删除");
		}
		if (deptService.checkDeptExistUser(deptId)) {
			return new Res<>(SysStatusCode.DB_EXIST_RECODE.getCode(), "部门存在用户,不允许删除");
		}
		return Res.ok(deptService.deleteDeptById(deptId));
	}
	
	
	@ApiOperation("树形列表")
	@ApiImplicitParams({ 
		//API文档中第一行的信息，name参数名称，value说明，require是否必传，dataTypeClass数据类型,paramType参数类型
	})
	@GetMapping("selectList")
	public Res<List<SysOrgDeptSelectVO>, Void> selectList(){
		List<SysOrgDeptSelectVO> list = deptService.getDeptAll();
		return Res.ok(list);
	}
}
