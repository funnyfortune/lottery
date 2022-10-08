package com.tc.draw.demo.framework.web.controller;

import cn.hutool.core.date.DateUtil;
import com.tc.draw.demo.common.utils.StringUtils;
import com.tc.draw.demo.common.utils.sql.SqlUtil;
import com.tc.draw.demo.framework.web.model.page.PageDomain;
import com.tc.draw.demo.framework.web.model.page.PageVO;
import com.tc.draw.demo.framework.web.model.page.TableSupport;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;

/**
 * web层通用数据处理
 * 
 *
 */
public class BaseController
{

    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder)
    {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport()
        {
            @Override
            public void setAsText(String text)
            {
                setValue(DateUtil.parseDate(text));
            }
        });
    }

    /**
     * 设置请求分页数据
     */
    protected void startPage()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize))
        {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }

    /**
     * 响应请求分页数据
     */
	protected <T> PageVO<T> getDataTable(List<T> list)
    {
        return new PageVO<>(new PageInfo<T>(list).getTotal(), list);
    }
}
