package gamestudio.services.servicesjpql;

import javax.persistence.EntityManager;


import gamestudio.entity.entityjpql.CommentJPQL;
import gamestudio.entity.entityjpql.GameJPQL;
import gamestudio.entity.entityjpql.PlayerJPQL;
import gamestudio.services.servicesjpql.usefullservicesjpqlmethods.UsefullServicesJpqlMethods;
import gamestudio.usefullmethods.ReadLine;
import sk.ness.jpa.JpaHelper;

public class CommentJpqlMethods {

	public void addCommentJpql(CommentJPQL comment) {
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		em.persist(comment);
		JpaHelper.commitTransaction();
	}

	public String writeComment() {
		System.out.println("PLease write down your comment.");
		String userComment = new ReadLine().readLine();
		return userComment;
	}

	public void commentToDatabaseJPQL(String gameName, String userName) {

		UsefullServicesJpqlMethods method = new UsefullServicesJpqlMethods();

		if (method.isGameUniqueJpql(gameName) && method.isUserUniqueJpql(userName)) {
			addCommentJpql(new CommentJPQL(writeComment(), new PlayerJPQL(userName), new GameJPQL(gameName)));
		} else {
			if (method.isUserUniqueJpql(userName) && !method.isGameUniqueJpql(gameName)) {
				addCommentJpql(
						new CommentJPQL(writeComment(), new PlayerJPQL(userName), method.findGameObjectbyID(gameName)));
			}
			if (!method.isUserUniqueJpql(userName) && method.isGameUniqueJpql(gameName)) {
				addCommentJpql(
						new CommentJPQL(writeComment(), method.findPlayerObjectbyID(userName), new GameJPQL(gameName)));
			}
			if (!method.isUserUniqueJpql(userName) && !method.isGameUniqueJpql(gameName)) {
				addCommentJpql(new CommentJPQL(writeComment(), method.findPlayerObjectbyID(userName),
						method.findGameObjectbyID(gameName)));
			}
		}
	}

}
