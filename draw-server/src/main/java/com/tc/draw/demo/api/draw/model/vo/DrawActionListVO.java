package com.tc.draw.demo.api.draw.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import com.tc.draw.demo.framework.aspectj.lang.annotation.Excel;

/**
 * 抽奖行为列表视图对象
 *
 * @author TC
 * @date 2021-03-15
 */
@Data
@ToString
@ApiModel("抽奖行为列表视图对象")
public class DrawActionListVO {

/**
 * 说明：$comment
 * 类型：Long
 */
@Excel(name = "主键ID")
 @ApiModelProperty("主键ID")
private Long id;
/**
 * 说明：主键ID
 * 类型：Long
 */
@Excel(name = "主题Id")
 @ApiModelProperty("主题Id")
private Long themeId;
/**
 * 说明：主题Id
 * 类型：Long
 */
@Excel(name = "主题奖项Id")
 @ApiModelProperty("主题奖项Id")
private Long giftId;
/**
 * 说明：主题奖项Id
 * 类型：Long
 */
@Excel(name = "创建者id")
 @ApiModelProperty("创建者id")
private Long creatorId;
/**
 * 说明：创建者id
 * 类型：Long
 */
@Excel(name = "创建时间")
 @ApiModelProperty("创建时间")
private Long createTime;

}

