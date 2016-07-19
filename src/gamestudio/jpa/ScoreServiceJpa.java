package gamestudio.jpa;

import javax.persistence.EntityManager;

import gamestudio.entity.ScoreJPA;
import sk.ness.jpa.JpaHelper;

public class ScoreServiceJpa {

	public void addScoreToDatabase(ScoreJPA score ) {
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();		
		em.persist(score);
		JpaHelper.commitTransaction();
	}	
}
