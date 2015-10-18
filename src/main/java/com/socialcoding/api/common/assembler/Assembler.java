package com.socialcoding.api.common.assembler;

import java.util.List;

public interface Assembler<F, T> {
    T assemble(F from);
    List<T> assemble(List<F> from);
}
