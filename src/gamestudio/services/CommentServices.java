package gamestudio.services;

import gamestudio.entity.Comment;

public interface CommentServices {
	
	public void addCommentToDatabase(Comment newComment, String gameName);
	
}
