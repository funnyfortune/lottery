package com.tc.draw.demo.api.draw.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import com.tc.draw.demo.framework.web.controller.BaseController;
import com.tc.draw.demo.framework.web.model.Res;
import com.tc.draw.demo.framework.web.model.page.PageVO;

import com.tc.draw.demo.api.draw.model.vo.DrawResultListVO;
import com.tc.draw.demo.api.draw.model.dto.DrawResultListDTO;
import com.tc.draw.demo.api.draw.service.IDrawResultService;


/**
 * 抽奖中奖Controller
 *
 * @author TC
 * @date 2021-03-15
 */
@Api(value = "抽奖中奖接口文档", tags = { "抽奖中奖相关接口" })
@RestController
@RequestMapping("/draw/result")
public class DrawResultController extends BaseController
{
    @Autowired
    private IDrawResultService drawResultService;

    /**
     * 查询抽奖中奖列表
     */
    @ApiOperation(value = "抽奖中奖列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorities", value = "用户token", required = true, dataTypeClass = String.class, paramType = "header")
    })
    @PreAuthorize("@ss.hasPermi('draw:result:list')")
    @GetMapping("/list")
    public Res<PageVO<DrawResultListVO>, Void> list(DrawResultListDTO drawResult)
    {
        startPage();
        List<DrawResultListVO> list = drawResultService.selectDrawResultList(drawResult);
        return Res.ok(getDataTable(list));
    }

}
