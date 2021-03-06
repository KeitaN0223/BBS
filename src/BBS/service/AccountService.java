package BBS.service;

import static BBS.utils.CloseableUtil.*;
import static BBS.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import BBS.beans.User;
import BBS.dao.UserDao;

public class AccountService {

	public List<User> getAccount() {

		Connection connection = null;
		try {
			connection = getConnection();

			UserDao accountDao = new UserDao();
			List<User> ret = accountDao.getUserAccount(connection, 20);

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

	public void stopUser(User user) {

		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			userDao.isStop(connection, user);

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
