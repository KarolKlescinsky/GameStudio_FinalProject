package gamestudio.jpa;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import gamestudio.entity.RatingJPA;
import sk.ness.jpa.JpaHelper;

public class RatingServiceJpa {

	public void addScoreToDatabaseWhichCanExistAlready(RatingJPA rating, String userName ) {
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();		
		em.persist(rating);
		JpaHelper.commitTransaction();
	}	
	
	public void deleteExistingScore(RatingJPA rating, String userName){
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();	
		Query query =em.createQuery("DELETE r FROM RatingJPA r Where r.user_name=:userName");
		query.setParameter("userName", userName);
		query.executeUpdate();
		JpaHelper.commitTransaction();
	}
	
	public void addUniqueScoreToDatabase(RatingJPA rating,String userName,String gameName){
		if(isUserNameAndGameNameUnique(userName, gameName)){
			addScoreToDatabaseWhichCanExistAlready(rating, userName);
		}else{
			deleteExistingScore(rating, userName);
			addScoreToDatabaseWhichCanExistAlready(rating, userName);
		}
	}
	
	public boolean isUserNameAndGameNameUnique(String userName,String gameName){
		
		EntityManager em = JpaHelper.getEntityManager();	
		Query query =em.createQuery("Select user_name FROM RatingJPA r Where r.user_name=:userName AND r.game_name=:gameName");
		query.setParameter("userName", userName);
		query.setParameter("gameName" , gameName);
		
		if(query.getResultList().isEmpty()){
			return true;
		}else{
			return false;
		}
	}
	
	public void averageRating(String gameName){
		EntityManager em = JpaHelper.getEntityManager();	
		Query query =em.createQuery("Select AVG(cast(r.user_rating as double)) FROM RatingJPA r Where r.game_name=:gameName");
		query.setParameter("gameName" , gameName);
		System.out.println(query.getResultList());
	}
	
	public void countOfRatings(String gameName){
		EntityManager em = JpaHelper.getEntityManager();	
		Query query =em.createQuery("Select Count(r.user_rating) FROM RatingJPA r Where r.game_name=:gameName");
		query.setParameter("gameName" , gameName);
		System.out.println(query.getResultList());
	}
}
