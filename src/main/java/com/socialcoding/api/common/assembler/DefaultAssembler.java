package com.socialcoding.api.common.assembler;

import org.modelmapper.ModelMapper;

import java.lang.reflect.Type;
import java.util.List;

public class DefaultAssembler {
    private static final ModelMapper MAPPER = new ModelMapper();

    public static <F, T> T assemble(F source, Class<T> targetClass) {
        return MAPPER.map(source, targetClass);
    }

    public static <F, T> List<T> assemble(List<F> source, Type type) {
        return MAPPER.map(source, type);
    }
}
