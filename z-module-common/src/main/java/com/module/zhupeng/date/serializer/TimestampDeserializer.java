package com.module.zhupeng.date.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.sql.Timestamp;

/**
 * TimestampDeserializer
 * <p>date : 2019-11-07</p>
 *
 * @author DONGSK
 * @since Ver1.0.0.1
 **/
public class TimestampDeserializer extends JsonDeserializer<Timestamp> {
    @Override
    public Timestamp deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return Timestamp.valueOf(p.getValueAsString());
    }
}
