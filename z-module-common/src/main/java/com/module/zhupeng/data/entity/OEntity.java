/**
 * FileName: ColumnEntity
 * Author:   DONGSK
 * Datetime: 2020/3/6 9:56
 * Description: 实体类基础抽象类
 * History:
 * 作者姓名 --修改时间 --版本号--描述
 */
package com.module.zhupeng.data.entity;

import java.io.Serializable;

/**
 * OEntity
 * 实体类基础抽象类
 *
 * @author DONGSK
 * @date 2020/3/6
 * @since 1.0.0
 */
public abstract class OEntity implements Serializable {


    private static final long serialVersionUID = 1L;

    /*** 状态 正常 */
    public static final Integer IS_NOT_DELETE = 0;
    /*** 状态 已删除 */
    public static final Integer IS_DELETED = 1;

    public static final String F_ID = "id";
    public static final String F_SQL_ID = "id";

    /**
     * 状态（0：正常 1：删除）
     */
    public static final String F_DELETE_FLAG = "deleteFlag";
    public static final String F_SQL_DELETE_FLAG = "delete_flag";

    public static final String F_ROW_STATUS = "rowStatus";
    public static final String F_ROW_IN_DB = "rowInDB";

    public static final String F_VERSION = "version";
    public static final String F_HASH_KEY = "hashKey";
    public static final String F_OWN_ORG_OID = "ownOrgOID";
    public static final String F_CREATED_BY = "createdBy";
    public static final String F_CREATED_TIME = "createdTime";
    public static final String F_MODIFIED_BY = "modifiedBy";
    public static final String F_MODIFIED_TIME = "modifiedTime";

    public static final String F_SQL_HASH_KEY = "hash_key";
    public static final String F_SQL_VERSION = "version";
    public static final String F_SQL_OWN_ORG_OID = "own_org_oid";
    public static final String F_SQL_CREATED_BY = "created_by";
    public static final String F_SQL_CREATED_TIME = "created_time";
    public static final String F_SQL_MODIFIED_BY = "modified_by";
    public static final String F_SQL_MODIFIED_TIME = "modified_time";

}
