package com.tc.draw.demo.api.sys.model.vo.router;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 路由显示信息
 * 
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetaVO
{
    /**
     * 设置该路由在侧边栏和面包屑中展示的名字
     */
    private String title;

    /**
     * 设置该路由的图标，对应路径src/icons/svg
     */
    private String icon;
}
