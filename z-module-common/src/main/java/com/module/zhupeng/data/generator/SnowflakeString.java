package com.module.zhupeng.data.generator;

import com.module.zhupeng.util.SnowflakeIdUtil;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

/**
 * SnowflakeLong
 * <p>date : 2019-11-12</p>
 *
 * @author DONGSK
 * @since Ver1.0.0.1
 **/
public class SnowflakeString implements IdentifierGenerator {

    public static final String NAME = "SnowflakeString";
    public static final String TYPE = "com.module.zhupeng.data.generator.SnowflakeString";
    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return String.valueOf(SnowflakeIdUtil.nextId());
    }
}
