package com.socialcoding.api.base.assembler;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractAssembler<F, T> implements Assembler<F, T> {
    @Override
    public T assemble(F from) {
        if (from == null) {
            return null;
        }

        return doAssemble(from);
    }

    @Override
    public List<T> assemble(List<F> from) {
        if (from == null) {
            return null;
        }

        return from.stream().map(this::assemble).collect(Collectors.toList());
    }

    protected abstract T doAssemble(F from);
}
