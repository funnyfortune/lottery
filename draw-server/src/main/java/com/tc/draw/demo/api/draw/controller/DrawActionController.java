package com.tc.draw.demo.api.draw.controller;

import com.tc.draw.demo.api.draw.model.domain.DrawAction;
import com.tc.draw.demo.api.draw.model.dto.DrawActionListDTO;
import com.tc.draw.demo.api.draw.model.vo.DrawActionInfoVO;
import com.tc.draw.demo.api.draw.model.vo.DrawActionListVO;
import com.tc.draw.demo.api.draw.service.IDrawActionService;
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
 * 抽奖行为Controller
 *
 * @author TC
 * @date 2021-03-15
 */
@Api(value = "抽奖行为接口文档", tags = { "抽奖行为相关接口" })
@RestController
@RequestMapping("/draw/action")
public class DrawActionController extends BaseController
{
    @Autowired
    private IDrawActionService drawActionService;

    /**
     * 查询抽奖行为列表
     */
    @ApiOperation(value = "抽奖行为列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    @PreAuthorize("@ss.hasPermi('draw:action:list')")
    @GetMapping("/list")
    public Res<PageVO<DrawActionListVO>, Void> list(DrawActionListDTO drawAction)
    {
        startPage();
        List<DrawActionListVO> list = drawActionService.selectDrawActionList(drawAction);
        return Res.ok(getDataTable(list));
    }

    /**
     * 导出抽奖行为列表
     */
    @ApiOperation(value = "导出抽奖行为列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    @PreAuthorize("@ss.hasPermi('draw:action:export')")
    @GetMapping("/export")
    public Res<String, Void> export(DrawActionListDTO drawAction)
    {
        List<DrawActionListVO> list = drawActionService.selectDrawActionList(drawAction);
        ExcelUtil<DrawActionListVO> util = new ExcelUtil<DrawActionListVO>(DrawActionListVO.class);
        return util.exportExcel(list, "action");
    }

    /**
     * 获取抽奖行为详细信息
     */
    @ApiOperation(value = "抽奖行为详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    @PreAuthorize("@ss.hasPermi('draw:action:list')")
    @GetMapping(value = "/{id}")
    public Res<DrawActionInfoVO, Void> getInfo(@PathVariable("id") Long id)
    {
        return Res.ok(drawActionService.selectDrawActionById(id));
    }

    /**
     * 新增抽奖行为
     */
    @ApiOperation(value = "新增抽奖行为")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    @PreAuthorize("@ss.hasPermi('draw:action:save')")
    @PostMapping("/save")
    public Res<Integer, Void> save(@RequestBody DrawAction drawAction)
    {
        return Res.ok(drawActionService.insertDrawAction(drawAction));
    }

    /**
     * 修改抽奖行为
     */
    @ApiOperation(value = "修改抽奖行为")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    @PreAuthorize("@ss.hasPermi('draw:action:update')")
    @PostMapping("/update")
    public Res<Integer, Void> update(@RequestBody DrawAction drawAction)
    {
        return Res.ok(drawActionService.updateDrawAction(drawAction));
    }

    /**
     * 删除抽奖行为
     */
    @ApiOperation(value = "删除抽奖行为")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    @PreAuthorize("@ss.hasPermi('draw:action:delete')")
	@PostMapping("/delete")
    public Res<Integer, Void> delete(@RequestBody List<Long> ids)
    {
        return Res.ok(drawActionService.deleteDrawActionByIds(ids));
    }
}
