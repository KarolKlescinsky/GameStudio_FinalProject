package gamestudio.services;

import gamestudio.entity.Comment;
import gamestudio.entity.CommentJPA;

public interface CommentServices {
	
	public void addCommentToDatabase(Comment newComment, String gameName);
	
}
