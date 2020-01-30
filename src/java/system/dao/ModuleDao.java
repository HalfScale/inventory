package system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import system.bean.Module;

public class ModuleDao {
    private static final String SQL_CREATE_MODULE = "insert into `module` (name) VALUES (?)";
    private static final String SQL_GET_MODULE_BY_ID = "select * from `module` where id = ?";
    private static final String SQL_GET_ALL_MODULE = "select * from `module`";
    private static final String SQL_UPDATE_MODULE = "update `module` set col1 = ?, col2 = ? where id = ?";
    private static final String SQL_DELETE_MODULE = "delete from `module` where id = ?";
    
    //<editor-fold defaultstate="collapsed" desc="CRUD methods">
    
    public static void create(Connection con, Module module) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(SQL_CREATE_MODULE, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, module.getName());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                            module.setId(generatedKeys.getInt(1));
                    }
            }
        }
    }

    public static Module getById(Connection con, int id) throws SQLException {
        Module module = new Module();
        ResultSet rs = null;

        try (PreparedStatement pstmt = con.prepareStatement(SQL_GET_MODULE_BY_ID)) {
                pstmt.setInt(1, id);
                rs = pstmt.executeQuery();

                if(rs.next()) {
                    module.setId(rs.getInt(1));
                    module.setName(rs.getString(2));
                }
        }

        return module;
    }

    public static List<Module> getAll(Connection con) throws SQLException {
        List<Module> modules = new ArrayList<>();
        try (PreparedStatement pstmt = con.prepareStatement(SQL_GET_ALL_MODULE);
                ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                        Module module = ModuleDao.getById(con, rs.getInt(1));
                        modules.add(module);
                }
        }

        return modules;
    }

    public static void update(Connection con, Module module) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_MODULE)) {
                pstmt.setString(1, module.getName());
                pstmt.setBoolean(2, module.getStatus());
                pstmt.setInt(3, module.getId());
                pstmt.executeUpdate();
        }
    }

    public static void delete(Connection con, int id) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(SQL_DELETE_MODULE)) {
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
        }
    }
	
    //</editor-fold>
	
	public static List<Module> getModuleByRole(Connection con, int roleId) throws SQLException{
		ResultSet rs = null;
		List<Module> modules = new ArrayList<>();

		String query = "select * from role_module where role_id = ?";

		try (PreparedStatement pstmt = con.prepareStatement(query)) {
			pstmt.setInt(1, roleId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Module module = ModuleDao.getById(con, rs.getInt(3));
				modules.add(module);
			}
		}catch(SQLException e) {
			throw new SQLException(e.getMessage(), e);
		}

		return modules;
	}
}