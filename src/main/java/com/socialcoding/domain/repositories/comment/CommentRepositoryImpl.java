package com.socialcoding.domain.repositories.comment;

import com.socialcoding.domain.models.Comment;
import com.socialcoding.domain.models.QComment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import java.util.List;

public class CommentRepositoryImpl extends QueryDslRepositorySupport implements CommentRepositoryCustom {
	private QComment comment = QComment.comment;

	public CommentRepositoryImpl() {
		super(Comment.class);
	}

	@Override
	public List<Comment> findByCctvIdWithFirstPage(long cctvId, int size) {
		return from(comment).where(comment.cctv.cctvId.eq(cctvId))
				.orderBy(comment.commentId.desc()).limit(size).list(comment);
	}

	@Override
	public List<Comment> findByCctvIdWithPagination(long cctvId, long from, int size) {
		return from(comment).where(comment.cctv.cctvId.eq(cctvId)).where(comment.commentId.loe(from))
				.orderBy(comment.commentId.desc()).limit(size).list(comment);
	}
}
