package BBS.service;

import static BBS.utils.CloseableUtil.*;
import static BBS.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import BBS.beans.Post;
import BBS.beans.Post_comment;
import BBS.dao.PostDao;
import BBS.dao.Post_commentDao;

public class PostService {

	public void register(Post post) {

		Connection connection = null;
		try {
			connection = getConnection();

			PostDao postDao = new PostDao();
			postDao.insert(connection, post);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	private static final int LIMIT_NUM = 1000;

	public List<Post_comment> getMessage() {

		Connection connection = null;
		try {
			connection = getConnection();

			Post_commentDao messageDao = new Post_commentDao();
			List<Post_comment> ret = messageDao.getUserMessages(connection, LIMIT_NUM);

			commit(connection);

			return ret;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
}
