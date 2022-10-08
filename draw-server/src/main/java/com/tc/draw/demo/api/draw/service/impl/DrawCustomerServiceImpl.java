package com.tc.draw.demo.api.draw.service.impl;

import java.util.List;

import com.tc.draw.demo.common.exception.ImportDataException;
import com.tc.draw.demo.common.utils.StringUtils;
import com.tc.draw.demo.framework.web.model.Res;
import com.tc.draw.demo.api.draw.mapper.DrawCustomerMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tc.draw.demo.api.draw.model.domain.DrawCustomer;
import com.tc.draw.demo.api.draw.model.vo.DrawCustomerInfoVO;
import com.tc.draw.demo.api.draw.model.vo.DrawCustomerListVO;
import com.tc.draw.demo.api.draw.model.dto.DrawCustomerListDTO;
import com.tc.draw.demo.api.draw.service.IDrawCustomerService;

import com.tc.draw.demo.common.utils.SecurityUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * 抽奖客户Service业务层处理
 *
 * @author TC
 * @date 2021-03-14
 */
@Service
public class DrawCustomerServiceImpl implements IDrawCustomerService {
    @Autowired
    private DrawCustomerMapper drawCustomerMapper;

    private final  static Log LOG = LogFactory.getLog(DrawCustomerServiceImpl.class);

    /**
     * 查询抽奖客户
     *
     * @param id 抽奖客户ID
     * @return 抽奖客户
     */
    @Override
    public DrawCustomerInfoVO selectDrawCustomerById(Long id) {
        return drawCustomerMapper.selectDrawCustomerById(id);
    }

    /**
     * 查询抽奖客户列表
     *
     * @param drawCustomer 抽奖客户
     * @return 抽奖客户
     */
    @Override
    public List<DrawCustomerListVO> selectDrawCustomerList(DrawCustomerListDTO drawCustomer) {
        return drawCustomerMapper.selectDrawCustomerList(drawCustomer);
    }

    /**
     * 新增抽奖客户
     *
     * @param drawCustomer 抽奖客户
     * @return 结果
     */
    @Override
    public int insertDrawCustomer(DrawCustomer drawCustomer) {
        drawCustomer.setIsDelete(0);
        drawCustomer.setCreatorId(SecurityUtils.getUserId());
        drawCustomer.setOperatorId(drawCustomer.getCreatorId());
        drawCustomer.setCreateTime(System.currentTimeMillis());
        drawCustomer.setUpdateTime(drawCustomer.getCreateTime());
        return drawCustomerMapper.insert(drawCustomer);
    }

    /**
     * 修改抽奖客户
     *
     * @param drawCustomer 抽奖客户
     * @return 结果
     */
    @Override
    public int updateDrawCustomer(DrawCustomer drawCustomer) {
        drawCustomer.setUpdateTime(System.currentTimeMillis());
        drawCustomer.setOperatorId(SecurityUtils.getUserId());
        return drawCustomerMapper.updateById(drawCustomer);
    }

    /**
     * 批量删除抽奖客户
     *
     * @param ids 需要删除的抽奖客户ID
     * @return 结果
     */
    @Override
    public int deleteDrawCustomerByIds(List<Long> ids) {
        return drawCustomerMapper.deleteByIds(ids, System.currentTimeMillis(), SecurityUtils.getUserId());
    }

    @Override
    public boolean checkValueUnique(String mobile,long themeId,  long id) {
        Long dbId = drawCustomerMapper.selectIdByMobile(mobile,themeId);
        if(dbId == null || dbId.longValue() == id){
            return true;
        }
        return false;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Res<String, Void> importData(List<DrawCustomerListVO> list) {
        if (list == null || list.isEmpty()) {
            return Res.ok("导入失败！没有可导入数据");
        }
        int size = list.size();
        //设置回滚点
        Object savePoint = TransactionAspectSupport.currentTransactionStatus().createSavepoint();
        for (int i = 0; i < size; i++) {
            try {
                DrawCustomerListVO p = list.get(i);
                int row = i + 2;
                String errMsg = checkEmpty(row, p);
                if (errMsg == null) {
                    errMsg = "导入失败！第" + row + "行数据;";
                } else {
                    throw new ImportDataException(errMsg);
                }
                if(!checkValueUnique(p.getMobile(),p.getThemeId(),0L)){
                    return Res.ok(errMsg+"该手机号已存在，不能重复新增");
                }
                DrawCustomer customer = new DrawCustomer();
                customer.setName(p.getName());
                customer.setMobile(p.getMobile());
                customer.setProbability(0.0D);
                customer.setStatus("0");
                customer.setRemark(p.getRemark());
                customer.setCompany(p.getCompany());
                customer.setThemeId(p.getThemeId());
                insertDrawCustomer(customer);
            } catch (Exception e) {
                LOG.error(SecurityUtils.getUsername() + "导入数据异常", e);
                //回滚到savePoint
                TransactionAspectSupport.currentTransactionStatus().rollbackToSavepoint(savePoint);
                if(e instanceof ImportDataException){
                    return Res.ok(e.getMessage());
                } else {
                    return Res.ok("导入数据异常！请稍后再试");
                }
            }
        }
        LOG.info(SecurityUtils.getUsername() + "共导入数据" + size + "条");
        return Res.ok("导入成功！共导入数据" + size + "条");
    }

    private String checkEmpty(int row, DrawCustomerListVO p) {
        String errMsg = "导入失败！第" + row + "行数据;";
        if (p.getThemeId() ==null || p.getThemeId() == 0L) {
            return errMsg + "主题为空";
        }
        if (StringUtils.isEmpty(p.getCompany())) {
            return errMsg + "公司为空";
        }
        if (StringUtils.isEmpty(p.getName())) {
            return errMsg + "名称为空";
        }
        if (StringUtils.isEmpty(p.getMobile())) {
            return errMsg + "手机不能为空";
        }
        return null;
    }
}
