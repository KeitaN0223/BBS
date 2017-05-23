package BBS.dao;

import static BBS.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import BBS.beans.Comment;
import BBS.exception.NoRowsUpdatedRuntimeException;
import BBS.exception.SQLRuntimeException;

public class CommentDao {

	public void insert(Connection connection, Comment comment) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO comments ( ");
			sql.append("id");
			sql.append(", user_id");
			sql.append(", post_id");
			sql.append(", text");
			sql.append(", created_at");
			sql.append(") VALUES (");
			sql.append("null"); // id
			sql.append(", ?"); // user_id
			sql.append(", ?"); // post_id
			sql.append(", ?"); // text
			sql.append(", CURRENT_TIMESTAMP"); // created_at
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, comment.getUser_id());
			ps.setInt(2, comment.getPost_id());
			ps.setString(3, comment.getText());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	public void delete(Connection connection, int id) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM comments ");

			sql.append(" WHERE");
			sql.append(" id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setInt(1, id);

			int count = ps.executeUpdate();
			if (count == 0) {
				throw new NoRowsUpdatedRuntimeException();
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

}
