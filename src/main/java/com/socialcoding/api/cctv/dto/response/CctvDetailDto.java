package com.socialcoding.api.cctv.dto.response;

import com.socialcoding.api.comment.dto.response.CommentBundleDto;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CctvDetailDto extends AbstractResponse {
    private CctvDto cctv;
    private CommentBundleDto comments;
}
