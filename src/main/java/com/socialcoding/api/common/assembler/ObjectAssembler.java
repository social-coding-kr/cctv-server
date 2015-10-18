package com.socialcoding.api.common.assembler;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Component
public class ObjectAssembler {
    private Map<AssemblerKey, Assembler> assemblers = new HashMap<>();

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void initialize() {
        String[] assemblerNames = applicationContext.getBeanNamesForType(Assembler.class);

        for(String assemblerName : assemblerNames) {
            Assembler assembler = (Assembler) applicationContext.getBean(assemblerName);
            Class<?>[] assemblerClasses = GenericTypeResolver.resolveTypeArguments(assembler.getClass(), Assembler.class);
            AssemblerKey assemblerKey = AssemblerKey.key(assemblerClasses[0], assemblerClasses[1]);
            assemblers.put(assemblerKey, assembler);
            log.info("Assembler {} registered(from class {} to class {})", assemblerName, assemblerClasses[0], assemblerClasses[1]);
        }
    }

    public <F, T> T assemble(F from, Class<T> to) {
        if (from == null) {
            return null;
        }

        AssemblerKey key = AssemblerKey.key(from.getClass(), to);
        if (assemblers.containsKey(key)) {
            return (T) assemblers.get(key).assemble(from);
        } else {
            return DefaultAssembler.assemble(from, to);
        }
    }

    public <F, T> List<T> assemble(List<F> from, Class<T> to) {
        if (from == null) {
            return null;
        }

        AssemblerKey key = AssemblerKey.key(from.getClass(), to);
        if (assemblers.containsKey(key)) {
            return (List<T>) assemblers.get(key).assemble(from);
        } else {
            Type toType = new TypeToken<List<T>>() {}.getType();
            return DefaultAssembler.assemble(from, toType);
        }
    }


    @Data
    private static class AssemblerKey {
        private Class from;
        private Class to;

        private static AssemblerKey key(Class from, Class to) {
            AssemblerKey assemblerKey = new AssemblerKey();
            assemblerKey.setFrom(from);
            assemblerKey.setTo(to);
            return assemblerKey;
        }
    }
}
