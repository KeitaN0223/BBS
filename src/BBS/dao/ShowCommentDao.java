package BBS.dao;

import static BBS.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import BBS.beans.ShowComment;
import BBS.exception.SQLRuntimeException;

public class ShowCommentDao {

	public List<ShowComment> getUserMessages(Connection connection, int num) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users_comments ");
			sql.append("ORDER BY created_at DESC limit " + num);

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<ShowComment> ret = toUserCommentList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<ShowComment> toUserCommentList(ResultSet rs)
			throws SQLException {

		List<ShowComment> ret = new ArrayList<ShowComment>();
		try {
			while (rs.next()) {
				String text = rs.getString("text");
				Timestamp created_at = rs.getTimestamp("created_at");
				String name = rs.getString("name");
				int post_id = rs.getInt("post_id");
				//String comment = rs.getString("comment");

				ShowComment message = new ShowComment();
				message.setText(text);
				message.setCreated_at(created_at);
				message.setName(name);
				message.setPost_id(post_id);
				//message.setComment(comment);

				ret.add(message);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
}