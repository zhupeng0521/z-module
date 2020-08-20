package com.module.zhupeng.data.entity;

import javax.persistence.Column;
import javax.persistence.Id;

/**
 * BaseEntityWithId
 * <p>date : 2019-11-05</p>
 *
 * @author DONGSK
 * @since Ver1.0.0.1
 **/
public class BaseEntityWithId extends BaseEntity {

    /**
     * 主键
     */
    @Id
    @Column(name = "rid", unique = true, nullable = false, length = 50)
    private String rid;

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }
}
