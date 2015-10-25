package com.socialcoding.api.cctv.dto.response;

import com.socialcoding.api.common.dto.AbstractResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CctvDetailResult extends AbstractResponse {
    private CctvDto cctv;
//    private CommentBundleDto comments;
}
