package com.tc.draw.demo.api.sys.controller;

import java.util.List;

import com.tc.draw.demo.api.sys.service.ISysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tc.draw.demo.api.sys.model.domain.SysDictData;
import com.tc.draw.demo.api.sys.model.dto.SysDictDataListDTO;
import com.tc.draw.demo.api.sys.model.vo.dict.SysDictDataListVO;
import com.tc.draw.demo.common.utils.poi.ExcelUtil;

import com.tc.draw.demo.framework.web.controller.BaseController;
import com.tc.draw.demo.framework.web.model.Res;
import com.tc.draw.demo.framework.web.model.page.PageVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * 字典数据Controller
 *
 * @author TC
 * @date 2020-05-19
 */
@Api(value = "字典数据接口文档", tags = { "字典数据相关接口" })
@RestController
@RequestMapping("/system/dict/data")
public class SysDictDataController extends BaseController
{
    @Autowired
    private ISysDictDataService sysDictDataService;

    /**
     * 查询字典数据列表
     */
    @ApiOperation(value = "字典数据列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    @PreAuthorize("@ss.hasPermi('system:dict:list')")
    @GetMapping("/list")
    public Res<PageVO<SysDictDataListVO>, Void> list(SysDictDataListDTO sysDictData)
    {
        startPage();
        List<SysDictDataListVO> list = sysDictDataService.selectDictDataList(sysDictData);
        return Res.ok(getDataTable(list));
    }

    /**
     * 导出字典数据列表
     */
    @ApiOperation(value = "导出字典数据列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    // @OperateAction(title = "字典数据", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('system:dict:export')")
    @GetMapping("/export")
    public Res<String, Void> export(SysDictDataListDTO sysDictData)
    {
        List<SysDictDataListVO> list = sysDictDataService.selectDictDataList(sysDictData);
        ExcelUtil<SysDictDataListVO> util = new ExcelUtil<SysDictDataListVO>(SysDictDataListVO.class);
        return util.exportExcel(list, "字典数据");
    }

    /**
     * 获取字典数据详细信息
     */
    @ApiOperation(value = "字典数据详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    @PreAuthorize("@ss.hasPermi('system:dict:list')")
    @GetMapping(value = "/{dictCode}")
    public Res<SysDictData, Void> getInfo(@PathVariable("dictCode") Long dictCode)
    {
        return Res.ok(sysDictDataService.selectDictDataById(dictCode));
    }
    
    /**
     * 根据字典类型查询字典数据信息
     */
    @GetMapping(value = "/dictType/{dictType}")
    public Res<List<SysDictDataListVO>, Void> dictType(@PathVariable String dictType)
    {
        return Res.ok(sysDictDataService.selectDictDataByType(dictType));
    }

    /**
     * 新增字典数据
     */
    @ApiOperation(value = "新增字典数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    @PreAuthorize("@ss.hasPermi('system:dict:save')")
    // @OperateAction(title = "字典数据", businessType = BusinessType.INSERT)
    @PostMapping("/save")
    public Res<Integer, Void> save(@RequestBody SysDictData sysDictData)
    {
        return Res.ok(sysDictDataService.insertDictData(sysDictData));
    }

    /**
     * 修改字典数据
     */
    @ApiOperation(value = "修改字典数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    @PreAuthorize("@ss.hasPermi('system:dict:update')")
    // @OperateAction(title = "字典数据", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public Res<Integer, Void> update(@RequestBody SysDictData sysDictData)
    {
        return Res.ok(sysDictDataService.updateDictData(sysDictData));
    }

    /**
     * 删除字典数据
     */
    @ApiOperation(value = "删除字典数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    @PreAuthorize("@ss.hasPermi('system:dict:delete')")
    // @OperateAction(title = "字典类型", businessType = BusinessType.DELETE)
	@PostMapping("/delete")
    public Res<Integer, Void> delete(@RequestBody List<Long> dictCodes)
    {
        return Res.ok(sysDictDataService.deleteDictDataByIds(dictCodes));
    }
}
