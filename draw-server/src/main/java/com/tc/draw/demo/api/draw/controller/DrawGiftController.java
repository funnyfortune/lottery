package com.tc.draw.demo.api.draw.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import com.tc.draw.demo.framework.web.controller.BaseController;
import com.tc.draw.demo.common.utils.poi.ExcelUtil;
import com.tc.draw.demo.framework.web.model.Res;
import com.tc.draw.demo.framework.web.model.page.PageVO;

import com.tc.draw.demo.api.draw.model.domain.DrawGift;
import com.tc.draw.demo.api.draw.model.vo.DrawGiftListVO;
import com.tc.draw.demo.api.draw.model.vo.DrawGiftInfoVO;
import com.tc.draw.demo.api.draw.model.dto.DrawGiftListDTO;
import com.tc.draw.demo.api.draw.service.IDrawGiftService;


/**
 * 抽奖礼品Controller
 *
 * @author TC
 * @date 2021-03-15
 */
@Api(value = "抽奖礼品接口文档", tags = { "抽奖礼品相关接口" })
@RestController
@RequestMapping("/draw/gift")
public class DrawGiftController extends BaseController
{
    @Autowired
    private IDrawGiftService drawGiftService;

    /**
     * 查询抽奖礼品列表
     */
    @ApiOperation(value = "抽奖礼品列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    @PreAuthorize("@ss.hasPermi('draw:gift:list')")
    @GetMapping("/list")
    public Res<PageVO<DrawGiftListVO>, Void> list(DrawGiftListDTO drawGift)
    {
        startPage();
        List<DrawGiftListVO> list = drawGiftService.selectDrawGiftList(drawGift);
        return Res.ok(getDataTable(list));
    }

    /**
     * 导出抽奖礼品列表
     */
    @ApiOperation(value = "导出抽奖礼品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    @PreAuthorize("@ss.hasPermi('draw:gift:export')")
    @GetMapping("/export")
    public Res<String, Void> export(DrawGiftListDTO drawGift)
    {
        List<DrawGiftListVO> list = drawGiftService.selectDrawGiftList(drawGift);
        ExcelUtil<DrawGiftListVO> util = new ExcelUtil<DrawGiftListVO>(DrawGiftListVO.class);
        return util.exportExcel(list, "gift");
    }

    /**
     * 获取抽奖礼品详细信息
     */
    @ApiOperation(value = "抽奖礼品详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    @PreAuthorize("@ss.hasPermi('draw:gift:list')")
    @GetMapping(value = "/{id}")
    public Res<DrawGiftInfoVO, Void> getInfo(@PathVariable("id") Long id)
    {
        return Res.ok(drawGiftService.selectDrawGiftById(id));
    }

    /**
     * 新增抽奖礼品
     */
    @ApiOperation(value = "新增抽奖礼品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    @PreAuthorize("@ss.hasPermi('draw:gift:save')")
    @PostMapping("/save")
    public Res<Integer, Void> save(@RequestBody DrawGift drawGift)
    {
        return Res.ok(drawGiftService.insertDrawGift(drawGift));
    }

    /**
     * 修改抽奖礼品
     */
    @ApiOperation(value = "修改抽奖礼品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    @PreAuthorize("@ss.hasPermi('draw:gift:update')")
    @PostMapping("/update")
    public Res<Integer, Void> update(@RequestBody DrawGift drawGift)
    {
        return Res.ok(drawGiftService.updateDrawGift(drawGift));
    }

    /**
     * 删除抽奖礼品
     */
    @ApiOperation(value = "删除抽奖礼品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    @PreAuthorize("@ss.hasPermi('draw:gift:delete')")
	@PostMapping("/delete")
    public Res<Integer, Void> delete(@RequestBody List<Long> ids)
    {
        return Res.ok(drawGiftService.deleteDrawGiftByIds(ids));
    }
}
