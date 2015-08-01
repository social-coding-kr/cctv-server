package com.socialcoding.domain.repositories;

import com.socialcoding.domain.models.Comment;
import com.socialcoding.domain.models.QComment;
import org.springframework.data.jpa.repository.support.QueryDslRepositorySupport;

import java.util.List;

public class CommentRepositoryImpl extends QueryDslRepositorySupport implements CommentRepositoryCustom {
	private QComment comment = QComment.comment;

	public CommentRepositoryImpl() {
		super(Comment.class);
	}

	@Override
	public List<Comment> findByCctvId(long cctvId) {
		return from(comment).where(comment.cctv.cctvId.eq(cctvId)).list(comment);
	}
}
