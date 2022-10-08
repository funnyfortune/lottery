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

import com.tc.draw.demo.api.sys.model.domain.SysConfig;
import com.tc.draw.demo.api.sys.model.dto.SysConfigListDTO;
import com.tc.draw.demo.api.sys.model.vo.SysConfigListVO;
import com.tc.draw.demo.api.sys.service.ISysConfigService;
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
 * 参数配置Controller
 *
 * @author TC
 * @date 2020-05-19
 */
@Api(value = "参数配置接口文档", tags = { "参数配置相关接口" })
@RestController
@RequestMapping("/system/config")
public class SysConfigController extends BaseController
{
    @Autowired
    private ISysConfigService sysConfigService;

    /**
     * 查询参数配置列表
     */
    @ApiOperation(value = "参数配置列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    @PreAuthorize("@ss.hasPermi('system:config:list')")
    @GetMapping("/list")
    public Res<PageVO<SysConfigListVO>, Void> list(SysConfigListDTO sysConfig)
    {
        startPage();
        List<SysConfigListVO> list = sysConfigService.selectConfigList(sysConfig);
        return Res.ok(getDataTable(list));
    }

    /**
     * 导出参数配置列表
     */
    @ApiOperation(value = "导出参数配置列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    // @OperateAction(title = "参数管理", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('system:config:export')")
    @GetMapping("/export")
    public Res<String, Void> export(SysConfigListDTO sysConfig)
    {
        List<SysConfigListVO> list = sysConfigService.selectConfigList(sysConfig);
        ExcelUtil<SysConfigListVO> util = new ExcelUtil<SysConfigListVO>(SysConfigListVO.class);
        return util.exportExcel(list, "参数数据");
    }

    /**
     * 获取参数配置详细信息
     */
    @ApiOperation(value = "参数配置详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    @PreAuthorize("@ss.hasPermi('system:config:list')")
    @GetMapping(value = "/{configId}")
    public Res<SysConfig, Void> getInfo(@PathVariable("configId") long configId)
    {
        return Res.ok(sysConfigService.selectConfigById(configId));
    }
    
    /**
     * 根据参数键名查询参数值
     */
    @GetMapping(value = "/configKey/{configKey}")
    public Res<String, Void> getConfigKey(@PathVariable String configKey)
    {
        return Res.ok(sysConfigService.selectConfigByKey(configKey));
    }

    /**
     * 新增参数配置
     */
    @ApiOperation(value = "新增参数配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    @PreAuthorize("@ss.hasPermi('system:config:save')")
    // @OperateAction(title = "参数管理", businessType = BusinessType.INSERT)
    @PostMapping("/save")
    public Res<Integer, Void> save(@RequestBody SysConfig sysConfig)
    {
        return Res.ok(sysConfigService.insertConfig(sysConfig));
    }

    /**
     * 修改参数配置
     */
    @ApiOperation(value = "修改参数配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    @PreAuthorize("@ss.hasPermi('system:config:update')")
    // @OperateAction(title = "参数管理", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    public Res<Integer, Void> update(@RequestBody SysConfig sysConfig)
    {
    	  if (UserConstants.NOT_UNIQUE.equals(sysConfigService.checkConfigKeyUnique(sysConfig)))
          {
              return new Res<>(SysStatusCode.DB_EXIST_RECODE.getCode(),"修改参数'" + sysConfig.getConfigName() + "'失败，参数键名已存在");
          }
        return Res.ok(sysConfigService.updateConfig(sysConfig));
    }

    /**
     * 删除参数配置
     */
    @ApiOperation(value = "删除参数配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    @PreAuthorize("@ss.hasPermi('system:config:delete')")
    // @OperateAction(title = "参数管理", businessType = BusinessType.DELETE)
	@PostMapping("/delete")
    public Res<Integer, Void> delete(@RequestBody List<Long> configIds)
    {
        return Res.ok(sysConfigService.deleteConfigByIds(configIds));
    }
}
