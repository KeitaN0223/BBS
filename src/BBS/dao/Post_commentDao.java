package BBS.dao;

import static BBS.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import BBS.beans.Post_comment;
import BBS.exception.SQLRuntimeException;

public class Post_commentDao {

	public List<Post_comment> getUserMessages(Connection connection, int num) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM Posts_comments ");
			//sql.append("ORDER BY insert_date DESC limit " + num);

			ps = connection.prepareStatement(sql.toString());

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
				String post_text = rs.getString("post_text");
				Timestamp post_created_at = rs.getTimestamp("post_created_at");
				String post_name = rs.getString("post_name");
				String comment = rs.getString("comment");

				Post_comment message = new Post_comment();
				message.setSubject(subject);
				message.setPost_text(post_text);
				message.setPost_created_at(post_created_at);
				message.setPost_name(post_name);
				message.setComment(comment);

				ret.add(message);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
}