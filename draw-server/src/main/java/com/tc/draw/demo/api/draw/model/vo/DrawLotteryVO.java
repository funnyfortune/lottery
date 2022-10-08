package com.tc.draw.demo.api.draw.model.vo;

import lombok.Data;
import lombok.ToString;

import java.util.List;
@Data
@ToString
public class DrawLotteryVO {

    private List<DrawGiftInfoVO> gifts;

    private List<DrawCustomerInfoVO> users;

}
