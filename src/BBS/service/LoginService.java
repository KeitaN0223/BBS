package BBS.service;

import static BBS.utils.CloseableUtil.*;
import static BBS.utils.DBUtil.*;

import java.sql.Connection;

import BBS.beans.User;
import BBS.dao.UserDao;
import BBS.utils.CipherUtil;
public class LoginService {

	public User login(String account, String password) {

		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			String encPassword = CipherUtil.encrypt(password);
			User user = userDao.getUser(connection, account, encPassword);

			commit(connection);

			return user;
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
