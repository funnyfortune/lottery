package com.tc.draw.demo.framework.aspectj.lang.enums;

/**
 * 业务操作类型
 * 
 *
 */
public enum BusinessType
{
    /**
     * 其它
     */
    OTHER(0),

    /**
     * 新增
     */
    INSERT(1),

    /**
     * 修改
     */
    UPDATE(2),

    /**
     * 删除
     */
    DELETE(3),

    /**
     * 推送
     */
    PUSH(4),

    /**
     * 授权
     */
    GRANT(5),

    /**
     * 导出
     */
    EXPORT(6),

    /**
     * 导入
     */
    IMPORT(7),

    /**
     * 强退
     */
    FORCE(8),

    /**
     * 生成代码
     */
    GENCODE(9),
    
    /**
     * 清空数据
     */
    CLEAN(10),
    
    
    /**
     * 转变
     */
    CHANGE(11),
    
    /**
     * 
     * 审核
     */
    AUDIT(12),

    /**
     * 拒绝
     */
    REJECT(13),

    /**
     * 退回
     */
    FALLBACK(14),



    /**
     * 转派
     */
    ALLOCAtION(15),

    ACCEPT(16),
    SEND(17),
    INSTALL(19),
    REFUND(20),
    EXCHANGE(21),
    //冻结
    FREZZ(22);



    int value;

    BusinessType(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
