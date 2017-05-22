package BBS.dao;

import static BBS.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import BBS.beans.Category;
import BBS.beans.Post_comment;
import BBS.exception.SQLRuntimeException;

public class Post_commentDao {

	public List<Post_comment> getUserMessages(Connection connection, String startDate, String endDate, String category) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users_posts ");
			sql.append("WHERE created_at >= ? AND created_at < date_add(?, interval 1 day) ");

			if(category != null){
				sql.append("AND category = ?");
			}

			sql.append("ORDER BY created_at DESC ");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, startDate);
			ps.setString(2, endDate);

			if(category != null){
				ps.setString(3, category);
			}

			ResultSet rs = ps.executeQuery();
			List<Post_comment> ret = toUserMessageList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<Post_comment> toUserMessageList(ResultSet rs)
			throws SQLException {

		List<Post_comment> ret = new ArrayList<Post_comment>();
		try {
			while (rs.next()) {
				String subject = rs.getString("subject");
				String text = rs.getString("text");
				Timestamp created_at = rs.getTimestamp("created_at");
				String name = rs.getString("name");
				int post_id = rs.getInt("post_id");
				String category = rs.getString("category");
				//String comment = rs.getString("comment");

				Post_comment message = new Post_comment();
				message.setSubject(subject);
				message.setText(text);
				message.setCreated_at(created_at);
				message.setName(name);
				message.setPost_id(post_id);
				message.setCategory(category);
				//message.setComment(comment);

				ret.add(message);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public List<Category> getCategories(Connection connection, String category){

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT distinct category FROM posts ");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<Category> ret = toCategoryList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<Category> toCategoryList(ResultSet rs)
			throws SQLException {

		List<Category> ret = new ArrayList<Category>();
		try {
			while (rs.next()) {
				String category = rs.getString("category");

				Category categoryList = new Category();
				categoryList.setCategory(category);
				//message.setComment(comment);

				ret.add(categoryList);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
}