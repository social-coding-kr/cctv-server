package com.socialcoding.api.base.assembler;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class DefaultAssembler {
    private static final ModelMapper MAPPER = new ModelMapper();

    public static <F, T> T assemble(F source, Class<T> targetClass) {
        return MAPPER.map(source, targetClass);
    }

    public static <F, T> List<T> assemble(List<F> source, Class<T> targetClass) {
        List<T> target = new ArrayList<>();
        for(F f : source) {
            T t = MAPPER.map(f, targetClass);
            target.add(t);
        }
        return target;
    }
}
