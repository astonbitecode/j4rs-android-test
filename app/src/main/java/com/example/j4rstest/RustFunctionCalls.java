package com.example.j4rstest;

import com.example.j4rstest.api.dto.TestDto;
import org.astonbitecode.j4rs.api.Instance;
import org.astonbitecode.j4rs.api.java2rust.Java2RustUtils;

public class RustFunctionCalls {

    private static native Instance<Integer> addintegers(Instance<Integer> i1, Instance<Integer> i2);
    private static native Instance<Long> addlongs(Instance<Long> i1, Instance<Long> i2);
    private static native Instance<String> fnstring(Instance<String> s);
    private static native Instance<TestDto> fncustomobject(Instance<TestDto> dto);

    public RustFunctionCalls() {
        System.loadLibrary("j4rstest");
    }

    public Integer addInRust(Integer i1, Integer i2) {
        Instance<Integer> instance = addintegers(
                Java2RustUtils.createInstance(i1),
                Java2RustUtils.createInstance(i2));
        return Java2RustUtils.getObjectCasted(instance);
    }

    public Long addInRust(Long i1, Long i2) {
        Instance<Long> instance = addlongs(
                Java2RustUtils.createInstance(i1),
                Java2RustUtils.createInstance(i2));
        return Java2RustUtils.getObjectCasted(instance);
    }

    public String strings(String string) {
        Instance<String> i = fnstring(Java2RustUtils.createInstance(string));
        return Java2RustUtils.getObjectCasted(i);
    }

    public TestDto doCallWithCustomObject(TestDto testDto) {
        Instance instance = fncustomobject(Java2RustUtils.createInstance(testDto));
        return Java2RustUtils.getObjectCasted(instance);
    }
}
