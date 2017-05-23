package BBS.service;

import static BBS.utils.CloseableUtil.*;
import static BBS.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import BBS.beans.Comment;
import BBS.beans.ShowComment;
import BBS.dao.CommentDao;
import BBS.dao.ShowCommentDao;

public class CommentService {

	public void register(Comment comment) {

		Connection connection = null;
		try {
			connection = getConnection();

			CommentDao commentDao = new CommentDao();
			commentDao.insert(connection, comment);

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

	public List<ShowComment> getMessage() {

		Connection connection = null;
		try {
			connection = getConnection();

			ShowCommentDao messageDao = new ShowCommentDao();
			List<ShowComment> ret = messageDao.getUserMessages(connection);

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
	public void deleteComment(int id) {

		Connection connection = null;
		try {
			connection = getConnection();

			CommentDao deleteCommentDao = new CommentDao();

			deleteCommentDao.delete(connection, id);

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
}
