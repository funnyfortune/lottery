package com.tc.draw.demo.api.draw.controller;

import com.tc.draw.demo.api.draw.model.vo.DrawLotteryVO;
import com.tc.draw.demo.api.draw.service.IDrawLotteryService;
import com.tc.draw.demo.framework.web.model.Res;
import com.tc.draw.demo.framework.web.model.code.SysStatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("draw/lottery")
public class DrawLotteryController {

    @Autowired
    private IDrawLotteryService drawLotteryService;

    @PreAuthorize("@ss.hasPermi('draw:start')")
    @GetMapping("info")
    public Res<DrawLotteryVO, Void> info(Long themeId, Long giftId){
        DrawLotteryVO vo = drawLotteryService.getInfo(themeId, giftId);
        return Res.ok(vo);
    }

    @PreAuthorize("@ss.hasPermi('draw:start')")
    @GetMapping("result")
    public Res<DrawLotteryVO, Void> result(Long themeId, Long giftId){
        DrawLotteryVO vo = drawLotteryService.getResult(themeId,giftId);
        if(vo == null){
            return Res.error(SysStatusCode.UNKNOW_ERROR.getCode(),"该活动已经抽完");
        }
        return Res.ok(vo);
    }

    @GetMapping("update")
    public Res<Void, Void> updateResult(Long themeId, Long giftId){
        drawLotteryService.updateResult(themeId,giftId);
        return Res.ok();
    }
}
