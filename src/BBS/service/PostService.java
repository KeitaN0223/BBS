package BBS.service;

import java.sql.Connection;
import java.util.List;

import BBS.beans.Post;
import BBS.dao.PostDao;

public class PostService {

	public void register(Post message) {

		Connection connection = null;
		try {
			connection = getConnection();

			PostDao messageDao = new PostDao();
			messageDao.insert(connection, message);

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

	public List<UserPost> getMessage() {

		Connection connection = null;
		try {
			connection = getConnection();

			UserPostDao messageDao = new UserPostDao();
			List<UserPost> ret = messageDao.getUserMessages(connection, LIMIT_NUM);

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
