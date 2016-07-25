package gamestudio.services.servicesjpql;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import gamestudio.entity.entityjpql.GameJPQL;
import gamestudio.entity.entityjpql.PlayerJPQL;
import gamestudio.entity.entityjpql.RatingJPQL;
import gamestudio.services.servicesjpql.usefullservicesjpqlmethods.UsefullServicesJpqlMethods;
import gamestudio.usefullmethods.ReadLine;
import sk.ness.jpa.JpaHelper;

public class RatingJpqlMethods {
	
	public void addRatingJpql(RatingJPQL rating){
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();		
		em.persist(rating);
		JpaHelper.commitTransaction();
	}
	
	public int writeRating() {
		System.out.println("PLease write down your rating.");
		String userRating = new ReadLine().readLine();
		return Integer.parseInt(userRating);
	}
	
	public void ratingToDatabaseJPQL(String gameName, String userName) {
		
		UsefullServicesJpqlMethods method = new UsefullServicesJpqlMethods();
		
		if (method.isGameUniqueJpql(gameName) && method.isUserUniqueJpql(userName)) {
			addRatingJpql(new RatingJPQL(writeRating(), new PlayerJPQL(userName), new GameJPQL(gameName)));
		} else {
			if (method.isUserUniqueJpql(userName) && !method.isGameUniqueJpql(gameName)) {
				addRatingJpql(new RatingJPQL(writeRating(), new PlayerJPQL(userName), method.findGameObjectbyID(gameName)));
			}
			if (!method.isUserUniqueJpql(userName) && method.isGameUniqueJpql(gameName)) {
				addRatingJpql(new RatingJPQL(writeRating(), method.findPlayerObjectbyID(userName), new GameJPQL(gameName)));
			}
			if (!method.isUserUniqueJpql(userName) && !method.isGameUniqueJpql(gameName)) {
				addRatingJpql(new RatingJPQL(writeRating(), method.findPlayerObjectbyID(userName), method.findGameObjectbyID(gameName)));
			}
		}
	}
	
	public boolean isUserNameAndGameNameUnique(String userName,String gameName){
		
		EntityManager em = JpaHelper.getEntityManager();	
		Query query =em.createQuery("Select game_gameID,player_playerID FROM RatingJPQL r Where r.game_gameID=:userName AND r.player_playerID=:gameName");
		query.setParameter("userName", userName);
		query.setParameter("gameName" , gameName);
		
		if(query.getResultList().isEmpty()){
			return true;
		}else{
			return false;
		}
	}
	
}
