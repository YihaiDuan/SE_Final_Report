package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import common.DBConnection;

public class TagsDao {

	public void insert(int bookId,String similar) throws SQLException{
		Connection conn = DBConnection.getConnection();
		String sql = "insert into booksimilar(bookId,similar) values(?,?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, bookId);
		pstmt.setString(2,similar);
		pstmt.executeUpdate();
		DBConnection.closeConnection(null, pstmt, conn);
	}
	
}
