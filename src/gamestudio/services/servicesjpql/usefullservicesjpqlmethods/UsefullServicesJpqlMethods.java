package gamestudio.services.servicesjpql.usefullservicesjpqlmethods;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import gamestudio.entity.entityjpql.GameJPQL;
import gamestudio.entity.entityjpql.PlayerJPQL;
import sk.ness.jpa.JpaHelper;

public class UsefullServicesJpqlMethods {
	
	public boolean isUserUniqueJpql(String userName) {

		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("SELECT playerName FROM PlayerJPQL p WHERE p.playerName =:userName");
		query.setParameter("userName", userName);

		if (query.getResultList().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isGameUniqueJpql(String gameName) {

		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("SELECT gameName FROM GameJPQL g WHERE g.gameName =:gameName");
		query.setParameter("gameName", gameName);

		if (query.getResultList().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public int getGameIdOfUniqueGame(String gameName) {

		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("SELECT gameID FROM GameJPQL g WHERE g.gameName =:gameName");
		query.setParameter("gameName", gameName);
		
		int ident = (int) query.getResultList().get(0);
		return ident;
	}

	public int getPlayerIdOfUniquePlayer(String userName) {

		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("SELECT playerID FROM PlayerJPQL p WHERE p.playerName =:userName");
		query.setParameter("userName", userName);

		int ident = (int) query.getResultList().get(0);
		return ident;

	}

	public PlayerJPQL findPlayerObjectbyID(String userName) {
		EntityManager em = JpaHelper.getEntityManager();
		return em.find(PlayerJPQL.class, getPlayerIdOfUniquePlayer(userName));
	}

	public GameJPQL findGameObjectbyID(String gameName) {
		EntityManager em = JpaHelper.getEntityManager();
		return em.find(GameJPQL.class, getGameIdOfUniqueGame(gameName));
	}

}
