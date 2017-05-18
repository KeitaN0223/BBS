package BBS.dao;

import static BBS.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BBS.beans.Department;
import BBS.exception.SQLRuntimeException;

public class DepartmentDao {

	public List<Department> getDepartments(Connection connection) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM departments";

			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			List<Department> departmentList = toDepartmentList(rs);

			return departmentList;

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	private List<Department> toDepartmentList(ResultSet rs) throws SQLException {

		List<Department> ret = new ArrayList<Department>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");

				Department department = new Department();

				department.setId(id);
				department.setName(name);

				ret.add(department);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
}