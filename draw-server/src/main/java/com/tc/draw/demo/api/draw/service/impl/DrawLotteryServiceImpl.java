package com.tc.draw.demo.api.draw.service.impl;

import cn.hutool.json.JSONUtil;
import com.tc.draw.demo.api.draw.mapper.DrawCustomerMapper;
import com.tc.draw.demo.api.draw.mapper.DrawThemeMapper;
import com.tc.draw.demo.api.draw.model.domain.DrawGift;
import com.tc.draw.demo.api.draw.model.domain.DrawResult;
import com.tc.draw.demo.api.draw.model.vo.DrawCustomerInfoVO;
import com.tc.draw.demo.api.draw.model.vo.DrawGiftInfoVO;
import com.tc.draw.demo.api.draw.model.vo.DrawLotteryVO;
import com.tc.draw.demo.api.draw.model.vo.DrawThemeInfoVO;
import com.tc.draw.demo.api.draw.service.IDrawGiftService;
import com.tc.draw.demo.api.draw.service.IDrawLotteryService;
import com.tc.draw.demo.api.draw.service.IDrawResultService;
import com.tc.draw.demo.common.utils.StringUtils;
import com.tc.draw.demo.exception.CustomException;
import com.tc.draw.demo.framework.redis.RedisCache;
import com.tc.draw.demo.framework.redis.RedisConstant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DrawLotteryServiceImpl implements IDrawLotteryService {

    private final static Log LOG = LogFactory.getLog(DrawLotteryServiceImpl.class);

    @Autowired
    private DrawThemeMapper drawThemeMapper;

    @Autowired
    private IDrawGiftService drawGiftService;

    @Autowired
    private DrawCustomerMapper drawCustomerMapper;

    @Autowired
    private IDrawResultService drawResultService;

    @Autowired
    private RedisCache redisService;

    @Override
    public DrawLotteryVO getInfo(Long themeId, Long giftId) {
        List<DrawGiftInfoVO> gifts = null;
        if (giftId !=null && giftId > 0) {
            DrawGiftInfoVO giftInfoVO = drawGiftService.selectDrawGiftById(giftId);
            if (giftInfoVO == null) {
                LOG.warn("活动没有可用奖项，themeId=" + themeId);
                return null;
            }
            gifts = Stream.of(giftInfoVO).collect(Collectors.toList());
        } else {
            gifts = drawGiftService.selectGiftByThemeId(themeId);
            if (gifts == null) {
                LOG.warn("活动没有可用奖项，themeId=" + themeId);
                return null;
            }
        }
        List<DrawCustomerInfoVO> customers = drawCustomerMapper.selectCustomerByThemeId(themeId);
        DrawLotteryVO vo = new DrawLotteryVO();
        vo.setGifts(gifts);
        vo.setUsers(customers);
        return vo;
    }

    @Override
    public DrawLotteryVO getResult(Long themeId, Long giftId) {
        DrawThemeInfoVO themeInfoVO = drawThemeMapper.selectDrawThemeById(themeId);
        DrawGiftInfoVO giftInfoVO = drawGiftService.selectDrawGiftById(giftId);
        if (giftInfoVO.getCompleted().intValue() == 1) {
            LOG.warn("活动没有可用奖项，themeId=" + themeId);
            return null;
        }
        List<DrawCustomerInfoVO> luckUsers = new ArrayList<>();
            if(themeInfoVO.getDrawType() == 0){
                List<DrawCustomerInfoVO> customers = drawCustomerMapper.selectNoLotteryCustomerByThemeId( themeId);
                luckUsers = randomCustomers(luckUsers, customers,giftInfoVO.getNum());
            }else {
                List<DrawCustomerInfoVO> customers = drawCustomerMapper.selectNoLotteryCustomerProbability(themeId);
                if(customers == null || customers.size() ==0 ){
                    throw  new CustomException("请添加客户");
                }
                ThreadLocalRandom tlr = ThreadLocalRandom.current();
                boolean isCompleted = false;
                if(giftInfoVO.getNum()>= customers.size()){
                    luckUsers = customers;
                } else {
                    while(customers.size() > 0){
                        for(int j=0;j<customers.size();j++ ){
                            int probability = tlr.nextInt(1, 101);
                            DrawCustomerInfoVO c = customers.get(j);
                            if (probability <= c.getProbability()) {
                                luckUsers.add(c);
                                customers.remove(j);
                                if(luckUsers.size()>= giftInfoVO.getNum()){
                                    isCompleted = true;
                                    break;
                                }
                            }
                        }
                        if (isCompleted) {
                            break;
                        }
                    }
                }
        }
        DrawLotteryVO vo = new DrawLotteryVO();
        List<DrawGiftInfoVO> gifts = new ArrayList<>();
        gifts.add(giftInfoVO);
        vo.setGifts(gifts);
        vo.setUsers(luckUsers);
        redisService.setCacheObject(RedisConstant.DRAW_LOTTERY_RES+"-"+themeId+"-"+giftId, JSONUtil.toJsonStr(vo));
        return vo;
    }

    @Override
    public void updateResult(Long themeId, Long giftId) {
        String json =redisService.getCacheObject(RedisConstant.DRAW_LOTTERY_RES+"-"+themeId+"-"+giftId);
        if(StringUtils.isEmpty(json)){
            LOG.warn("该活动已经更新！themeId="+themeId+",giftId="+giftId);
            return;
        }
        redisService.deleteObject(RedisConstant.DRAW_LOTTERY_RES+"-"+themeId+"-"+giftId);
        DrawLotteryVO vo = JSONUtil.toBean(json,DrawLotteryVO.class);
        updateGift(giftId);
        vo.getUsers().forEach(c->{
            insertResult(themeId, giftId, c.getId());
        });
    }

    private List<DrawCustomerInfoVO>  randomCustomers(List<DrawCustomerInfoVO> luckUsers,List<DrawCustomerInfoVO> customers, long size){
        if(customers == null || customers.size() ==0 ){
            throw  new CustomException("请添加客户");
        }
        if(size> customers.size()){
            size = customers.size();
        }
        ThreadLocalRandom tlr = ThreadLocalRandom.current();
        for (int i = 0; i < size; i++) {
            int index = tlr.nextInt(0, customers.size());
            DrawCustomerInfoVO customerInfoVO = customers.get(index);
            luckUsers.add(customerInfoVO);
            customers.remove(index);
        }
        return luckUsers;
    }

    private void updateGift(long giftId){
        DrawGift gift = new DrawGift();
        gift.setId(giftId);
        gift.setCompleted(1);
        gift.setCompleteTime(System.currentTimeMillis());
        drawGiftService.updateDrawGift(gift);
    }

    private void insertResult(long themeId, long giftId, long customerId) {
        DrawResult result = new DrawResult();
        result.setCustomerId(customerId);
        result.setGiftId(giftId);
        result.setThemeId(themeId);
        drawResultService.insertDrawResult(result);
    }
}
