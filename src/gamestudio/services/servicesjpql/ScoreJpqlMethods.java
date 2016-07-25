package gamestudio.services.servicesjpql;

import javax.persistence.EntityManager;

import gamestudio.entity.entityjpql.GameJPQL;
import gamestudio.entity.entityjpql.PlayerJPQL;
import gamestudio.entity.entityjpql.ScoreJPQL;
import gamestudio.services.servicesjpql.usefullservicesjpqlmethods.UsefullServicesJpqlMethods;
import sk.ness.jpa.JpaHelper;

public class ScoreJpqlMethods {

	public void addScoreJpql(ScoreJPQL score) {
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		em.persist(score);
		JpaHelper.commitTransaction();
	}

	public void scoreToDatabaseJPQL(int userScore, String gameName, String userName) {
		
		UsefullServicesJpqlMethods method = new UsefullServicesJpqlMethods();

		if (method.isGameUniqueJpql(gameName) && method.isUserUniqueJpql(userName)) {
			addScoreJpql(new ScoreJPQL(userScore, new PlayerJPQL(userName), new GameJPQL(gameName)));
		} else {
			if (method.isUserUniqueJpql(userName) && !method.isGameUniqueJpql(gameName)) {
				addScoreJpql(new ScoreJPQL(userScore, new PlayerJPQL(userName), method.findGameObjectbyID(gameName)));
			}
			if (!method.isUserUniqueJpql(userName) && method.isGameUniqueJpql(gameName)) {
				addScoreJpql(new ScoreJPQL(userScore, method.findPlayerObjectbyID(userName), new GameJPQL(gameName)));
			}
			if (!method.isUserUniqueJpql(userName) && !method.isGameUniqueJpql(gameName)) {
				addScoreJpql(new ScoreJPQL(userScore, method.findPlayerObjectbyID(userName), method.findGameObjectbyID(gameName)));
			}
		}
	}

}
