package BBS.dao;

import static BBS.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BBS.beans.User;
import BBS.exception.NoRowsUpdatedRuntimeException;
import BBS.exception.SQLRuntimeException;

public class UserDao {

 	public User getUser(Connection connection, String account,
			String password) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE account = ?  AND password = ?";

			ps = connection.prepareStatement(sql);
			ps.setString(1, account);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			if (userList.isEmpty() == true) {
				return null;
			} else if (2 <= userList.size()) {
				throw new IllegalStateException("2 <= userList.size()");
			} else {
				return userList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private List<User> toUserList(ResultSet rs) throws SQLException {

		List<User> ret = new ArrayList<User>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String account = rs.getString("account");
				String name = rs.getString("name");
				String password = rs.getString("password");

				User user = new User();
				user.setId(id);
				user.setAccount(account);
				user.setName(name);
				user.setPassword(password);

				ret.add(user);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

	public void insert(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO users ( ");
			sql.append(" account");
			sql.append(", name");
			sql.append(", password");
			sql.append(", branch_id");
			sql.append(", department_id");
			sql.append(", is_stopped");
			sql.append(") VALUES (");
			sql.append(" ?"); // account
			sql.append(", ?"); // name
			sql.append(", ?"); // password
			sql.append(", ?"); // branch_id
			sql.append(", ?"); // department_id
			sql.append(", 0"); // is_stopped
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getAccount());
			ps.setString(2, user.getName());
			ps.setString(3, user.getPassword());
			ps.setInt(4, user.getBranch_id());
			ps.setInt(5, user.getDepartment_id());

			ps.executeUpdate();
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public User getUser(Connection connection, int id) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE id = ?";

			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			if (userList.isEmpty() == true) {
				return null;
			} else if (2 <= userList.size()) {
				throw new IllegalStateException("2 <= userList.size()");
			} else {
				return userList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void update(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE users SET");
			sql.append("  account = ?");
			sql.append(", password = ?");
			sql.append(", name = ?");
			sql.append(", branch_id = ?");
			sql.append(", department_id = ?");

			sql.append(" WHERE");
			sql.append(" id = ?");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getAccount());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setInt(4, user.getBranch_id());
			ps.setInt(5, user.getDepartment_id());

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
