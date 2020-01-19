package system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import system.bean.RoleModule;

public class RoleModuleDao {
    private static final String SQL_CREATE_ROLE_MODULE = "insert into `role_module` (role_id, module_id) VALUES (?, ?)";
    private static final String SQL_GET_ROLE_MODULE_BY_ID = "select * from `role_module` where id = ?";
    private static final String SQL_GET_ALL_ROLE_MODULE = "select * from `role_module`";
    private static final String SQL_UPDATE_ROLE_MODULE = "update `role_module` set col1 = ?, col2 = ? where id = ?";
    private static final String SQL_DELETE_ROLE_MODULE = "delete from `role_module` where id = ?";
    
    //<editor-fold defaultstate="collapsed" desc="CRUD methods">
    
    public static void create(Connection con, RoleModule roleModule) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(SQL_CREATE_ROLE_MODULE, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, roleModule.getRoleId());
            pstmt.setInt(2, roleModule.getModuleId());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                            roleModule.setId(generatedKeys.getInt(1));
                    }
            }
        }
    }

    public static RoleModule getById(Connection con, int id) throws SQLException {
        RoleModule roleModule = new RoleModule();
        ResultSet rs = null;

        try (PreparedStatement pstmt = con.prepareStatement(SQL_GET_ROLE_MODULE_BY_ID)) {
                pstmt.setInt(1, id);
                rs = pstmt.executeQuery();

                if(rs.next()) {
                    roleModule.setId(rs.getInt(1));
                    roleModule.setName(rs.getString(2));
                }
        }

        return roleModule;
    }

    public static List<RoleModule> getAll(Connection con) throws SQLException {
        List<RoleModule> roleModules = new ArrayList<>();
        try (PreparedStatement pstmt = con.prepareStatement(SQL_GET_ALL_ROLE_MODULE);
                ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                        RoleModule roleModule = RoleModuleDao.getById(con, rs.getInt(1));
                        roleModules.add(roleModule);
                }
        }

        return roleModules;
    }

    public static void update(Connection con, RoleModule roleModule) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_ROLE_MODULE)) {
                pstmt.setString(1, roleModule.getName());
                pstmt.setBoolean(2, roleModule.getStatus());
                pstmt.setInt(3, roleModule.getId());
                pstmt.executeUpdate();
        }
    }

    public static void delete(Connection con, int id) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(SQL_DELETE_ROLE_MODULE)) {
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
        }
    }

    //</editor-fold>
}