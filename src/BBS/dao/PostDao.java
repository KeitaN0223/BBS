package BBS.dao;

import static BBS.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import BBS.beans.Post;
import BBS.exception.SQLRuntimeException;

public class PostDao {

	public void insert(Connection connection, Post post) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO posts ( ");
			sql.append("id");
			sql.append(", user_id");
			sql.append(", subject");
			sql.append(", text");
			sql.append(", category");
			sql.append(", created_at");
			sql.append(") VALUES (");
			sql.append("null"); // id
			sql.append(", ?"); // user_id
			sql.append(", ?"); // subject
			sql.append(", ?"); // text
			sql.append(", ?"); // category
			sql.append(", CURRENT_TIMESTAMP"); // created_at
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, post.getUser_id());
			ps.setString(2, post.getSubject());
			ps.setString(3, post.getText());
			ps.setString(4, post.getCategory());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

}
