package com.socialcoding.api.base.assembler;

import java.util.List;

public interface Assembler<F, T> {
    T assemble(F from);
    List<T> assemble(List<F> from);
}
