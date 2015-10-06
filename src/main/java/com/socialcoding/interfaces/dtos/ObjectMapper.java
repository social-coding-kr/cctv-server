package com.socialcoding.interfaces.dtos;

import com.socialcoding.domain.models.Cctv;
import com.socialcoding.domain.models.Comment;
import com.socialcoding.interfaces.dtos.Request.CctvRegistrationDto;
import com.socialcoding.interfaces.dtos.Request.CommentWriteDto;
import org.modelmapper.ModelMapper;

import java.lang.reflect.Type;
import java.util.List;

public class ObjectMapper {
    private static final ModelMapper MAPPER = new ModelMapper();

    public static <T, V> V map(T source, Class<V> targetClass) {
        return MAPPER.map(source, targetClass);
    }

    public static <T, V> List<V> map(List<T> source, Type type) {
        return MAPPER.map(source, type);
    }

    public static Comment map(CommentWriteDto commentDto, Cctv cctv) {
        Comment comment = new Comment();
        comment.setUserId(commentDto.getUserId());
        comment.setContents(commentDto.getContents());
        comment.setCctv(cctv);
        return comment;
    }

    public static Cctv map(CctvRegistrationDto dto) {
        Cctv cctv = new Cctv();
        cctv.setLatitude(dto.getLatitude());
        cctv.setLongitude(dto.getLongitude());
        cctv.setPurpose(dto.getPurpose());
        cctv.setCreatedBy(dto.getUserId());
        cctv.setModifiedBy(dto.getUserId());
        return cctv;
    }
}
