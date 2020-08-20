package com.module.zhupeng.data.entity;


import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.util.Date;

/**
 * BaseVEntity
 * <p>date : 2019-11-05</p>
 *
 * @author DONGSK
 * @since Ver1.0.0.1
 **/
@MappedSuperclass
public abstract class BaseOEntity extends OEntity {

    private static final long serialVersionUID = 111L;

    /**
     * 用于锁
     */
    @Column(name = OEntity.F_SQL_HASH_KEY, length = 40)
    private String hashKey;

    /**
     * 用于锁
     */
    @Version
    @Column(name = OEntity.F_SQL_VERSION)
    private Integer version = 0;

    /**
     * 公司别，当前用户的公司别
     */
    @Column(name = OEntity.F_SQL_OWN_ORG_OID, length = 40)
    private String ownOrgOID;

    /**
     * 创建人
     */
    @Column(name = OEntity.F_SQL_CREATED_BY, updatable = false)
    private String createdBy;

    /**
     * 创建时间
     */
    @Column(name = OEntity.F_SQL_CREATED_TIME, updatable = false)
    private Date createdTime;
    /**
     * 修改者
     */
    @Column(name = OEntity.F_SQL_MODIFIED_BY, insertable = false)
    private String modifiedBy;
    /**
     * 修改时间
     */
    @Column(name = OEntity.F_SQL_MODIFIED_TIME, insertable = false)
    private Date modifiedTime;

    /**
     * 逻辑删除标识
     */
    @Column(name = OEntity.F_SQL_DELETE_FLAG)
    private Integer deleteFlag = BaseOEntity.IS_NOT_DELETE;

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

    public String getOwnOrgOID() {
        return ownOrgOID;
    }

    public void setOwnOrgOID(String ownOrgOID) {
        this.ownOrgOID = ownOrgOID;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteLogic) {
        this.deleteFlag = deleteLogic;
    }
}
