package gamestudio.jpa;

import javax.persistence.EntityManager;


import gamestudio.entity.CommentJPA;
import sk.ness.jpa.JpaHelper;

public class CommentServiceJpa {

	public void addCommentToDatabase(CommentJPA comment ) {
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();		
		em.persist(comment);
		JpaHelper.commitTransaction();
		
	}	
}
