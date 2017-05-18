package BBS.service;

import static BBS.utils.CloseableUtil.*;
import static BBS.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import BBS.beans.Branch;
import BBS.dao.BranchDao;

public class BranchService {


	public List<Branch> getBranches() {

		Connection connection = null;
		try {
			connection = getConnection();

			BranchDao branchDao = new BranchDao();
			List<Branch> ret = branchDao.getBranches(connection);

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
