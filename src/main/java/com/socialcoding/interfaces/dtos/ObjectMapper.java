package com.socialcoding.interfaces.dtos;

import com.socialcoding.domain.models.Reliability;
import com.socialcoding.interfaces.dtos.Request.ReliabilitySelectDto;
import org.modelmapper.ModelMapper;

import java.lang.reflect.Type;
import java.util.List;

public class ObjectMapper {
    private static final ModelMapper MAPPER = new ModelMapper();

    public static <T, V> V map(T souce, Class<V> targetClass) {
        return MAPPER.map(souce, targetClass);
    }

    public static <T, V> List<V> map(List<T> source, Type type) {
        return MAPPER.map(source, type);
    }

    public static Reliability map(ReliabilitySelectDto reliabilitySelectDto) {
        Reliability reliability = new Reliability();
        reliability.setUserId(reliabilitySelectDto.getUserId());
        reliability.setReliable(reliabilitySelectDto.getReliable());
        return reliability;
    }
}
