package com.socialcoding.domain.repositories;

import com.socialcoding.domain.models.Comment;

import java.util.List;

public interface CommentRepositoryCustom {
	List<Comment> findByCctvId(long cctvId);
}
