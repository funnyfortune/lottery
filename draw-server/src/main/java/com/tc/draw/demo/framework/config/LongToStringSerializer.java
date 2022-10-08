package com.tc.draw.demo.framework.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * 处理前端js超长long字段
 * @author Administrator
 */
@JacksonStdImpl
public class LongToStringSerializer extends StdSerializer<Long> {

    public static final LongToStringSerializer instance = new LongToStringSerializer();

    public LongToStringSerializer() {
        super(Long.class);
    }


    @Override
    public void serialize(Long value, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        String vstring = value+"";
        //处理前端js超长long字段
        if(vstring.length()<=16){
            gen.writeNumber(value);
            return;
        }
        gen.writeString(vstring);
    }

}
