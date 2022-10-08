package com.tc.draw.demo.api.draw.service;

import com.tc.draw.demo.api.draw.model.vo.DrawLotteryVO;

public interface IDrawLotteryService {


    DrawLotteryVO getInfo(Long themeId, Long giftId);

    DrawLotteryVO getResult(Long themeId, Long giftId);

    void updateResult(Long themeId, Long giftId);
}
