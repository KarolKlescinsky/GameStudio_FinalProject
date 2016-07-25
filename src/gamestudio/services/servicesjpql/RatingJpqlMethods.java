package gamestudio.services.servicesjpql;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import gamestudio.entity.RatingJPA;
import gamestudio.entity.entityjpql.GameJPQL;
import gamestudio.entity.entityjpql.PlayerJPQL;
import gamestudio.entity.entityjpql.RatingJPQL;
import gamestudio.services.servicesjpql.usefullservicesjpqlmethods.UsefullServicesJpqlMethods;
import gamestudio.usefullmethods.ReadLine;
import sk.ness.jpa.JpaHelper;

public class RatingJpqlMethods {

	public void addRatingJpql(RatingJPQL rating) {
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
				addRatingJpql(
						new RatingJPQL(writeRating(), new PlayerJPQL(userName), method.findGameObjectbyID(gameName)));
			}
			if (!method.isUserUniqueJpql(userName) && method.isGameUniqueJpql(gameName)) {
				addRatingJpql(
						new RatingJPQL(writeRating(), method.findPlayerObjectbyID(userName), new GameJPQL(gameName)));
			}
			if (!method.isUserUniqueJpql(userName) && !method.isGameUniqueJpql(gameName)) {
				addRatingJpql(new RatingJPQL(writeRating(), method.findPlayerObjectbyID(userName),
						method.findGameObjectbyID(gameName)));
			}
		}
	}

	public int getRatingId(String userName, String gameName) {

		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("Select r.ratingID FROM RatingJPQL r Where r.game=:gameID AND r.player=:userID");
		query.setParameter("userID", new UsefullServicesJpqlMethods().findPlayerObjectbyID(userName));
		query.setParameter("gameID", new UsefullServicesJpqlMethods().findGameObjectbyID(gameName));

		System.out.println(new UsefullServicesJpqlMethods().findPlayerObjectbyID(userName));
		System.out.println(new UsefullServicesJpqlMethods().findGameObjectbyID(gameName));

		if (query.getResultList().isEmpty()) {
			return 0;
		} else {
			return (int) query.getResultList().get(0);
		}
	}

	public void deleteExistingRating(String userName, String gameName) {
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("DELETE FROM RatingJPQL r Where r.ratingID=:ratingID ");
		query.setParameter("ratingID", getRatingId(userName, gameName));
		query.executeUpdate();
		JpaHelper.commitTransaction();
	}

	public void addUniqueScoreToDatabase(String userName, String gameName) {
		if (new UsefullServicesJpqlMethods().isGameUniqueJpql(gameName)
				&& new UsefullServicesJpqlMethods().isUserUniqueJpql(userName)) {
			ratingToDatabaseJPQL(gameName, userName);
		} else {
			deleteExistingRating(userName, gameName);
			ratingToDatabaseJPQL(gameName, userName);
		}
	}

	public void averageRating(String gameName){
	 EntityManager em = JpaHelper.getEntityManager();
	 Query query =em.createQuery("Select AVG(cast(r.user_rating as double)) FROM RatingJPQL r Where r.game_name=:gameName");
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
