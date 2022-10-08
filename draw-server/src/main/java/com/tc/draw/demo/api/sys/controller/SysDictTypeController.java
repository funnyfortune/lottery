package com.tc.draw.demo.api.sys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tc.draw.demo.api.sys.model.domain.SysDictType;
import com.tc.draw.demo.api.sys.model.dto.SysDictTypeListDTO;
import com.tc.draw.demo.api.sys.model.vo.dict.SysDictTypeListVO;
import com.tc.draw.demo.api.sys.service.ISysDictTypeService;
import com.tc.draw.demo.common.constant.UserConstants;
import com.tc.draw.demo.common.utils.poi.ExcelUtil;

import com.tc.draw.demo.framework.web.controller.BaseController;
import com.tc.draw.demo.framework.web.model.Res;
import com.tc.draw.demo.framework.web.model.code.SysStatusCode;
import com.tc.draw.demo.framework.web.model.page.PageVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 字典类型Controller
 *
 * @author TC
 * @date 2020-05-19
 */
@Api(value = "字典类型接口文档", tags = { "字典类型相关接口" })
@RestController
@RequestMapping("/system/dict/type")
public class SysDictTypeController extends BaseController {
	@Autowired
	private ISysDictTypeService sysDictTypeService;

	/**
	 * 查询字典类型列表
	 */
	@ApiOperation(value = "字典类型列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header") })
	@PreAuthorize("@ss.hasPermi('system:dict:list')")
	@GetMapping("/list")
	public Res<PageVO<SysDictTypeListVO>, Void> list(SysDictTypeListDTO sysDictType) {
		startPage();
		List<SysDictTypeListVO> list = sysDictTypeService.selectDictTypeList(sysDictType);
		return Res.ok(getDataTable(list));
	}

	/**
	 * 导出字典类型列表
	 */
	@ApiOperation(value = "导出字典类型列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header") })
	// @OperateAction(title = "字典类型", businessType = BusinessType.EXPORT)
	@PreAuthorize("@ss.hasPermi('system:dict:export')")
	@GetMapping("/export")
	public Res<String, Void> export(SysDictTypeListDTO sysDictType) {
		List<SysDictTypeListVO> list = sysDictTypeService.selectDictTypeList(sysDictType);
		ExcelUtil<SysDictTypeListVO> util = new ExcelUtil<SysDictTypeListVO>(SysDictTypeListVO.class);
		return util.exportExcel(list, "字典类型");
	}

	/**
	 * 获取字典类型详细信息
	 */
	@ApiOperation(value = "字典类型详细信息")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header") })
	@PreAuthorize("@ss.hasPermi('system:dict:list')")
	@GetMapping(value = "/{dictId}")
	public Res<SysDictType, Void> getInfo(@PathVariable Long dictId) {
		return Res.ok(sysDictTypeService.selectDictTypeById(dictId));
	}

	/**
	 * 新增字典类型
	 */
	@ApiOperation(value = "新增字典类型")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header") })
	@PreAuthorize("@ss.hasPermi('system:dict:save')")
	// @OperateAction(title = "字典类型", businessType = BusinessType.INSERT)
	@PostMapping("/save")
	public Res<Integer, Void> save(@RequestBody SysDictType dict) {
		if (UserConstants.NOT_UNIQUE.equals(sysDictTypeService.checkDictTypeUnique(dict))) {
			return new Res<>(SysStatusCode.DB_EXIST_RECODE.getCode(), "新增字典'" + dict.getDictName() + "'失败，字典类型已存在");
		}
		return Res.ok(sysDictTypeService.insertDictType(dict));
	}

	/**
	 * 修改字典类型
	 */
	@ApiOperation(value = "修改字典类型")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header") })
	@PreAuthorize("@ss.hasPermi('system:dict:update')")
	// @OperateAction(title = "字典类型", businessType = BusinessType.UPDATE)
	@PostMapping("/update")
	public Res<Integer, Void> update(@RequestBody SysDictType dict) {
		if (UserConstants.NOT_UNIQUE.equals(sysDictTypeService.checkDictTypeUnique(dict))) {
			return new Res<>(SysStatusCode.DB_EXIST_RECODE.getCode(), "修改字典'" + dict.getDictName() + "'失败，字典类型已存在");
		}
		return Res.ok(sysDictTypeService.updateDictType(dict));
	}

	/**
	 * 删除字典类型
	 */
	@ApiOperation(value = "删除字典类型")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header") })
	@PreAuthorize("@ss.hasPermi('system:dict:delete')")
	// @OperateAction(title = "字典类型", businessType = BusinessType.DELETE)
	@PostMapping("/delete")
	public Res<Integer, Void> delete(@RequestBody List<Long> ids) {
		return Res.ok(sysDictTypeService.deleteDictTypeByIds(ids));
	}

	/**
	 * 获取字典选择框列表
	 */
	@ApiOperation(value = "字典选择框列表")
	@GetMapping("/optionselect")
	public Res<List<SysDictTypeListVO>, Void> optionselect() {
		List<SysDictTypeListVO> dictTypes = sysDictTypeService.selectDictTypeAll();
		return Res.ok(dictTypes);
	}
}
