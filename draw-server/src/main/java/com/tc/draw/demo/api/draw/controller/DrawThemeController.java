package com.tc.draw.demo.api.draw.controller;

import com.tc.draw.demo.api.draw.model.domain.DrawTheme;
import com.tc.draw.demo.api.draw.model.dto.DrawThemeListDTO;
import com.tc.draw.demo.api.draw.model.vo.DrawThemeInfoVO;
import com.tc.draw.demo.api.draw.model.vo.DrawThemeListVO;
import com.tc.draw.demo.api.draw.service.IDrawThemeService;
import com.tc.draw.demo.common.utils.poi.ExcelUtil;
import com.tc.draw.demo.framework.web.controller.BaseController;
import com.tc.draw.demo.framework.web.model.Res;
import com.tc.draw.demo.framework.web.model.page.PageVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 抽奖主题Controller
 *
 * @author TC
 * @date 2021-03-15
 */
@Api(value = "抽奖主题接口文档", tags = { "抽奖主题相关接口" })
@RestController
@RequestMapping("/draw/theme")
public class DrawThemeController extends BaseController
{
    @Autowired
    private IDrawThemeService drawThemeService;

    /**
     * 查询抽奖主题列表
     */
    @ApiOperation(value = "抽奖主题列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    @PreAuthorize("@ss.hasPermi('draw:theme:list')")
    @GetMapping("/list")
    public Res<PageVO<DrawThemeListVO>, Void> list(DrawThemeListDTO drawTheme)
    {
        startPage();
        List<DrawThemeListVO> list = drawThemeService.selectDrawThemeList(drawTheme);
        return Res.ok(getDataTable(list));
    }

    /**
     * 查询抽奖主题列表
     */
    @ApiOperation(value = "抽奖主题列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    @GetMapping("/select")
    public Res<List<DrawThemeListVO>, Void> select(DrawThemeListDTO drawTheme)
    {
        List<DrawThemeListVO> list = drawThemeService.selectDrawThemeList(drawTheme);
        return Res.ok(list);
    }

    /**
     * 导出抽奖主题列表
     */
    @ApiOperation(value = "导出抽奖主题列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    @PreAuthorize("@ss.hasPermi('draw:theme:export')")
    @GetMapping("/export")
    public Res<String, Void> export(DrawThemeListDTO drawTheme)
    {
        List<DrawThemeListVO> list = drawThemeService.selectDrawThemeList(drawTheme);
        ExcelUtil<DrawThemeListVO> util = new ExcelUtil<DrawThemeListVO>(DrawThemeListVO.class);
        return util.exportExcel(list, "theme");
    }

    /**
     * 获取抽奖主题详细信息
     */
    @ApiOperation(value = "抽奖主题详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    @PreAuthorize("@ss.hasPermi('draw:theme:list')")
    @GetMapping(value = "/{id}")
    public Res<DrawThemeInfoVO, Void> getInfo(@PathVariable("id") Long id)
    {
        return Res.ok(drawThemeService.selectDrawThemeById(id));
    }

    /**
     * 新增抽奖主题
     */
    @ApiOperation(value = "新增抽奖主题")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    @PreAuthorize("@ss.hasPermi('draw:theme:save')")
    @PostMapping("/save")
    public Res<Integer, Void> save(@RequestBody DrawTheme drawTheme)
    {
        return Res.ok(drawThemeService.insertDrawTheme(drawTheme));
    }

    /**
     * 修改抽奖主题
     */
    @ApiOperation(value = "修改抽奖主题")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    @PreAuthorize("@ss.hasPermi('draw:theme:update')")
    @PostMapping("/update")
    public Res<Integer, Void> update(@RequestBody DrawTheme drawTheme)
    {
        return Res.ok(drawThemeService.updateDrawTheme(drawTheme));
    }

    /**
     * 删除抽奖主题
     */
    @ApiOperation(value = "删除抽奖主题")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    @PreAuthorize("@ss.hasPermi('draw:theme:delete')")
	@PostMapping("/delete")
    public Res<Integer, Void> delete(@RequestBody List<Long> ids)
    {
        return Res.ok(drawThemeService.deleteDrawThemeByIds(ids));
    }
}
