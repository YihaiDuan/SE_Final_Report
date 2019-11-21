package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.DBConnection;
/**
 * bookœÍ«È µÃÂ
 * @author Monly_P
 *
 */
public class BookDao {
	
	public List<String> query() throws SQLException{
		List<String> isbnList = new ArrayList<>();
		Connection conn = DBConnection.getConnection();
		String sql = "select id,isbn from books";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet result = pstmt.executeQuery();
		while(result.next()){
			int id = result.getInt("id");
			isbnList.add(id-1,result.getString("isbn"));
		}
		DBConnection.closeConnection(result, pstmt, conn);
		return isbnList;
	}
	
	public void update(int id,String tags,double score) throws SQLException{
		Connection conn = DBConnection.getConnection();
		String sql = "update books set tags=?,score=? where id=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, tags);
		pstmt.setDouble(2, score);
		pstmt.setInt(3, id);
		pstmt.executeUpdate();
		DBConnection.closeConnection(null, pstmt, conn);
	}
	
	public List<String> queryTags() throws SQLException{
		List<String> tagsList = new ArrayList<>();
		Connection conn = DBConnection.getConnection();
		String sql = "select id,tags from books";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet result = pstmt.executeQuery();
		while(result.next()){
			int id = result.getInt("id");
			tagsList.add(id-1,result.getString("tags"));
		}
		DBConnection.closeConnection(result, pstmt, conn);
		return tagsList;
	}

}
