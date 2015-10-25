package com.socialcoding.api.base.assembler;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultAssembler {
    private static final ModelMapper MAPPER = new ModelMapper();

    public static <F, T> T assemble(F source, Class<T> targetClass) {
        return MAPPER.map(source, targetClass);
    }

    public static <F, T> List<T> assemble(List<F> source, Class<T> targetClass) {
		return source.stream().map(f -> DefaultAssembler.assemble(f, targetClass)).collect(Collectors.toList());
    }
}
