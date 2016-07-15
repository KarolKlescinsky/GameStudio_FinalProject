package gamestudio.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import gamestudio.entity.Comment;

public class CommentServicesMethods implements CommentServices{

    public static final String URL = "jdbc:oracle:thin:@//localhost:1521/XE";
    public static final String USER = "gamestudiouser";
    public static final String PASSWORD = "gamestudiouser";
    public static final String QUERY = "insert into User_Comments (COMMENTID,GAMEID,USERID,USER_COMMENT) values (ids.nextval,?,?,?)";
    
    
	public void addComment(Comment newComment, String gameName) {
	    	
	    	
			try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

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



}
