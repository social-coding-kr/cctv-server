package com.socialcoding.api.comment.service;

import com.google.common.collect.Iterables;
import com.socialcoding.api.comment.model.Comment;
import com.socialcoding.api.comment.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(value = "transactionManager", readOnly = true)
public class CommentService {
	private static final int BEFORE_ONE_COMMENT = 1;
	private static final long DEFAULT_LAST_COMMENT_ID = 0L;

	@Autowired
	private CommentRepository commentRepository;

	public List<Comment> getCommentsByCctvIdWithFirstPage(long cctvId) {
		return getCommentsByCctvIdWithFirstPage(cctvId, 5);
	}

	public List<Comment> getCommentsByCctvIdWithFirstPage(long cctvId, int size) {
		return commentRepository.findByCctvIdWithFirstPage(cctvId, size);
	}

	public List<Comment> getCommentsByCctvIdWithPagination(long cctvId, Long from, Integer size) {
		if (from == null) {
			return getCommentsByCctvIdWithFirstPage(cctvId, size);
		}
		return commentRepository.findByCctvIdWithPagination(cctvId, from, size);
	}

	public long getNextRequestCommentId(List<Comment> comments) {
		Comment lastComment = Iterables.getLast(comments, null);
		return lastComment != null ? lastComment.getCommentId() - BEFORE_ONE_COMMENT : DEFAULT_LAST_COMMENT_ID;
	}

	@Transactional(value = "transactionManager", readOnly = false)
	public Comment writeComment(Comment comment) {
		return commentRepository.save(comment);
	}

	@Transactional(value = "transactionManager", readOnly = false)
	public void deleteComment(long cctvId) {
		commentRepository.delete(cctvId);
	}
}
