package com.socialcoding.domain.services.comment;

import com.socialcoding.domain.models.Comment;
import com.socialcoding.domain.repositories.comment.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
	@Autowired
	private CommentRepository commentRepository;

	public List<Comment> getCommentsByCctvId(long cctvId) {
		return commentRepository.findByCctvId(cctvId);
	}
}
