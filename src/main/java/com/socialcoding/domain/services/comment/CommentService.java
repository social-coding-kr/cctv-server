package com.socialcoding.domain.services.comment;

import com.google.common.collect.Iterables;
import com.socialcoding.domain.models.Comment;
import com.socialcoding.domain.repositories.comment.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.socialcoding.Constants.DEFAULT_COMMENT_PAGE_SIZE;

@Service
public class CommentService {
	private static final int BEFORE_ONE_COMMENT = 1;
	private static final long DEFAULT_LAST_COMMENT_ID = 0L;

	@Autowired
	private CommentRepository commentRepository;

	public List<Comment> getCommentsByCctvIdWithFirstPage(long cctvId) {
		return getCommentsByCctvIdWithFirstPage(cctvId, DEFAULT_COMMENT_PAGE_SIZE);
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
}
