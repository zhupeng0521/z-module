package com.module.zhupeng.data.entity;


import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

/**
 * BaseEntity
 * <p>date : 2019-11-05</p>
 *
 * @author DONGSK
 * @since Ver1.0.0.1
 **/
@Deprecated
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    /**
     * 用于锁
     */
    @Column(name = "hash_key", length = 40)
    private String hashKey;

    /**
     * 用于锁
     */
    @Column(name = "version")
    private Integer version = 0;

    /**
     * 公司别，当前用户的公司别
     */
    @Column(name = "company", length = 40)
    private String company;

    /**
     * 创建人
     */
    @Column(name = "creator", updatable = false)
    private String creator;

    /**
     * 创建时间
     */
    @Column(name = "create_date", updatable = false)
    private Date createDate;
    /**
     * 修改者
     */
    @Column(name = "modifier", insertable = false)
    private String modifier;
    /**
     * 修改时间
     */
    @Column(name = "modify_date", insertable = false)
    private Date modifyDate;

    public String getHashKey() {
        return hashKey;
    }

    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
