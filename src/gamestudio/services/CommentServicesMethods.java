package gamestudio.services;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import gamestudio.database.DatabaseConnectionStats;
import gamestudio.entity.Comment;
import gamestudio.usefullmethods.ReadLine;

public class CommentServicesMethods implements CommentServices{


    public static final String QUERY = "insert into User_Comments (COMMENTID,GAMEID,USERID,USER_COMMENT) values (ids.nextval,?,?,?)";
    
    
	public void addCommentToDatabase(Comment newComment, String gameName) {
	    			
			try (Connection con = DriverManager.getConnection(DatabaseConnectionStats.getUrl(), DatabaseConnectionStats.getUser(), DatabaseConnectionStats.getPassword());

					PreparedStatement stmt = con.prepareStatement(QUERY)) {
					stmt.setInt(1, newComment.getGame_id());
					stmt.setInt(2, newComment.getUser_id());
					stmt.setString(3, newComment.getUser_comment());

					
					ResultSet rs = stmt.executeQuery();

					rs.close();
					stmt.close();
					con.close();

				} catch (SQLException e) {
					e.printStackTrace();
				}
	}
	
	public void writeComment(String gameName) {
		System.out.println("Do you want to write a comment? Y or N");
		String userInput = new ReadLine().readLine().toUpperCase();
		if (userInput.equals("Y")) {
			Comment newComment = new Comment(0, 0, null);
			newComment.setGame_id(findGameID(gameName));
			newComment.setUser_id(findUserID());
			System.out.println("Please write down your comment.");
			newComment.setUser_comment(new ReadLine().readLine());
			new CommentServicesMethods().addCommentToDatabase(newComment, gameName);
		}
	}
	
	private int findUserID() {

		String findGameID = "SELECT Userid FROM User_names WHERE User_name = ?";
		String insertNewUser = "insert into User_names (Userid, User_name) values (ids.nextval, ?)";
		String userName = System.getProperty("user.name");
		int userID = 0;

		try (Connection con = DriverManager.getConnection(DatabaseConnectionStats.getUrl(), DatabaseConnectionStats.getUser(), DatabaseConnectionStats.getPassword());

				PreparedStatement stmt = con.prepareStatement(findGameID)) {
			stmt.setString(1, userName);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				userID = rs.getInt(1);
			}

			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (userID == 0) {
			try (Connection con = DriverManager.getConnection(DatabaseConnectionStats.getUrl(), DatabaseConnectionStats.getUser(), DatabaseConnectionStats.getPassword());

					PreparedStatement stmt2 = con.prepareStatement(insertNewUser)) {
				stmt2.setString(1, userName);
				stmt2.executeUpdate();

				stmt2.close();
				con.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		try (Connection con = DriverManager.getConnection(DatabaseConnectionStats.getUrl(), DatabaseConnectionStats.getUser(), DatabaseConnectionStats.getPassword());

				PreparedStatement stmt = con.prepareStatement(findGameID)) {
			stmt.setString(1, userName);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				userID = rs.getInt(1);
			}

			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userID;
	}

	private int findGameID(String gameName) {

		String findGameID = "SELECT Gameid FROM Game WHERE Game_name = ?";
		int gameID = 0;

		try (Connection con = DriverManager.getConnection(DatabaseConnectionStats.getUrl(), DatabaseConnectionStats.getUser(), DatabaseConnectionStats.getPassword());

				PreparedStatement stmt = con.prepareStatement(findGameID)) {
			stmt.setString(1, gameName);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				gameID = rs.getInt(1);
			}

			rs.close();
			stmt.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return gameID;

	}




	



}
