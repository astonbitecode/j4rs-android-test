package com.example.j4rstest.json;

import com.example.j4rstest.api.dto.TestDto;
import org.astonbitecode.j4rs.api.services.json.Codec;
import org.astonbitecode.j4rs.api.services.json.exceptions.JsonCodecException;

public class TestDtoJsonCodec implements Codec {

    @Override
    public <T> T decode(String s, String s1) throws JsonCodecException {
        return (T) new TestDto(3);
    }

    @Override
    public String encode(Object o) throws JsonCodecException {
        return "{\"number\":3}";
    }

    @Override
    public Object[] decodeArrayContents(String s) throws JsonCodecException {
        return new Object[0];
    }
}
