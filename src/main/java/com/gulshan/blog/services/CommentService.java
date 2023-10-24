package com.gulshan.blog.services;

import com.gulshan.blog.payloads.CommentDto;

public interface CommentService {

	CommentDto createComment(CommentDto commentDto , Integer postId);
	
	void deleteComment(Integer commentId);
}
