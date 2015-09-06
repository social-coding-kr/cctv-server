package com.socialcoding.domain.repositories.comment;

import com.socialcoding.domain.models.Comment;

import java.util.List;

public interface CommentRepositoryCustom {
	List<Comment> findByCctvIdWithFirstPage(long cctvId, int size);
	List<Comment> findByCctvIdWithPagination(long cctvId, long from, int size);
}
