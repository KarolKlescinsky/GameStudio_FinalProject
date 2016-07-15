package gamestudio.services;

import gamestudio.entity.Comment;

public interface CommentServices {
	
	public void addComment(Comment newComment, String gameName);
	
}
