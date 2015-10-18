package com.socialcoding.api.comment.repository;

import com.socialcoding.api.comment.model.Comment;

import java.util.List;

public interface CommentRepositoryCustom {
	List<Comment> findByCctvIdWithFirstPage(long cctvId, int size);
	List<Comment> findByCctvIdWithPagination(long cctvId, long from, int size);
}
