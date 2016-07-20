package gamestudio.services.servicesjpql;

import javax.persistence.EntityManager;

import gamestudio.entity.entityjpql.CommentJPQL;
import sk.ness.jpa.JpaHelper;

public class CommentJpqlMethods {
	
	public void addCommentJpql(CommentJPQL comment){
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();		
		System.out.println(comment.getPlayer() + " "+comment.getGame() +""+ comment.getComment());
		em.persist(comment);
		JpaHelper.commitTransaction();
	}
}
