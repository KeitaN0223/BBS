package BBS.service;

import static BBS.utils.CloseableUtil.*;
import static BBS.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import BBS.beans.Category;
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

	public List<Post_comment> getMessage(String startDate, String endDate, String category) {

		Connection connection = null;
		try {
			connection = getConnection();

			Post_commentDao messageDao = new Post_commentDao();
			List<Post_comment> ret = messageDao.getUserMessages(connection, startDate, endDate, category);

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

	public List<Category> getCategory(String category){

		Connection connection = null;
		try{
			connection = getConnection();

			Post_commentDao categoryDao = new Post_commentDao();
			List<Category> ret = categoryDao.getCategories(connection, category);

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

	public void deletePost(int id) {

		Connection connection = null;
		try {
			connection = getConnection();

			PostDao deletePostDao = new PostDao();

			deletePostDao.delete(connection, id);

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
