package com.tc.draw.demo.api.draw.controller;

import java.util.List;

import com.tc.draw.demo.framework.web.model.code.SysStatusCode;
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

import com.tc.draw.demo.api.draw.model.domain.DrawCustomer;
import com.tc.draw.demo.api.draw.model.vo.DrawCustomerListVO;
import com.tc.draw.demo.api.draw.model.vo.DrawCustomerInfoVO;
import com.tc.draw.demo.api.draw.model.dto.DrawCustomerListDTO;
import com.tc.draw.demo.api.draw.service.IDrawCustomerService;
import org.springframework.web.multipart.MultipartFile;


/**
 * 抽奖客户Controller
 *
 * @author TC
 * @date 2021-03-14
 */
@Api(value = "抽奖客户接口文档", tags = { "抽奖客户相关接口" })
@RestController
@RequestMapping("/draw/customer")
public class DrawCustomerController extends BaseController
{
    @Autowired
    private IDrawCustomerService drawCustomerService;

    /**
     * 查询抽奖客户列表
     */
    @ApiOperation(value = "抽奖客户列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    @PreAuthorize("@ss.hasPermi('draw:customer:list')")
    @GetMapping("/list")
    public Res<PageVO<DrawCustomerListVO>, Void> list(DrawCustomerListDTO drawCustomer)
    {
        startPage();
        List<DrawCustomerListVO> list = drawCustomerService.selectDrawCustomerList(drawCustomer);
        return Res.ok(getDataTable(list));
    }

    /**
     * 导出抽奖客户列表
     */
    @ApiOperation(value = "导出抽奖客户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    @PreAuthorize("@ss.hasPermi('draw:customer:export')")
    @GetMapping("/export")
    public Res<String, Void> export(DrawCustomerListDTO drawCustomer)
    {
        List<DrawCustomerListVO> list = drawCustomerService.selectDrawCustomerList(drawCustomer);
        ExcelUtil<DrawCustomerListVO> util = new ExcelUtil<DrawCustomerListVO>(DrawCustomerListVO.class);
        return util.exportExcel(list, "customer");
    }

    @PreAuthorize("@ss.hasPermi('draw:customer:import')")
    @PostMapping("/importData")
    public Res<String, Void> importData(MultipartFile file) throws Exception
    {
        ExcelUtil<DrawCustomerListVO> util = new ExcelUtil<>(DrawCustomerListVO.class);
        List<DrawCustomerListVO> list = util.importExcel(file.getInputStream());
        return drawCustomerService.importData(list);
    }

    @GetMapping("/importTemplate")
    public Res<String, Void> importTemplate()
    {
        ExcelUtil<DrawCustomerListVO> util = new ExcelUtil<>(DrawCustomerListVO.class);
        return util.importTemplateExcel("客户信息");
    }


    /**
     * 获取抽奖客户详细信息
     */
    @ApiOperation(value = "抽奖客户详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    @PreAuthorize("@ss.hasPermi('draw:customer:list')")
    @GetMapping(value = "/{id}")
    public Res<DrawCustomerInfoVO, Void> getInfo(@PathVariable("id") Long id)
    {
        return Res.ok(drawCustomerService.selectDrawCustomerById(id));
    }

    /**
     * 新增抽奖客户
     */
    @ApiOperation(value = "新增抽奖客户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    @PreAuthorize("@ss.hasPermi('draw:customer:save')")
    @PostMapping("/save")
    public Res<Integer, Void> save(@RequestBody DrawCustomer drawCustomer)
    {
        if(!drawCustomerService.checkValueUnique(drawCustomer.getMobile(), drawCustomer.getThemeId(),0L)) {
            return new Res<>(SysStatusCode.PARAM_ERROR.getCode(), "新增失败，该手机号已存在");
        }
        return Res.ok(drawCustomerService.insertDrawCustomer(drawCustomer));
    }

    /**
     * 修改抽奖客户
     */
    @ApiOperation(value = "修改抽奖客户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    @PreAuthorize("@ss.hasPermi('draw:customer:update')")
    @PostMapping("/update")
    public Res<Integer, Void> update(@RequestBody DrawCustomer drawCustomer)
    {
        if(!drawCustomerService.checkValueUnique(drawCustomer.getMobile(),drawCustomer.getThemeId(), drawCustomer.getId())) {
            return new Res<>(SysStatusCode.PARAM_ERROR.getCode(), "更新失败，该手机号已存在");
        }
        return Res.ok(drawCustomerService.updateDrawCustomer(drawCustomer));
    }

    /**
     * 删除抽奖客户
     */
    @ApiOperation(value = "删除抽奖客户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    @PreAuthorize("@ss.hasPermi('draw:customer:delete')")
	@PostMapping("/delete")
    public Res<Integer, Void> delete(@RequestBody List<Long> ids)
    {
        return Res.ok(drawCustomerService.deleteDrawCustomerByIds(ids));
    }
}
