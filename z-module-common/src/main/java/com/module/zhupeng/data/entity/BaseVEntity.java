/**
 * FileName: ColumnEntity
 * Author:   DONGSK
 * Datetime: 2020/3/6 9:56
 * Description: 实体类基础抽象类
 * History:
 * 作者姓名 --修改时间 --版本号--描述
 */
package com.module.zhupeng.data.entity;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * VEntity
 * 实体类基础抽象类
 *
 * @author DONGSK
 * @date 2020/3/6
 * @since 1.0.0
 */
@MappedSuperclass
public abstract class BaseVEntity extends BaseOEntity {

    private static final long serialVersionUID = 11111L;

    /**
     * 数据状态
     * 0=未修改，1=新增，2=修改，3=删除
     */
    @Transient
    private int rowStatus = 0;

    /**
     * 數據狀態
     * true = 數據庫中已存儲，false未存儲
     */
    @Transient
    private boolean rowInDB = true;

    public int getRowStatus() {
        return rowStatus;
    }

    public void setRowStatus(int rowStatus) {
        this.rowStatus = rowStatus;
    }

    public boolean isRowInDB() {
        return rowInDB;
    }

    public void setRowInDB(boolean rowInDB) {
        this.rowInDB = rowInDB;
    }

}
