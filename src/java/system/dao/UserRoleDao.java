package system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import system.bean.UserRole;

public class UserRoleDao {
    private static final String SQL_CREATE_USER_ROLE = "insert into `user_role` (role_id, user_id) VALUES (?, ?)";
    private static final String SQL_GET_USER_ROLE_BY_ID = "select * from `user_role` where id = ?";
    private static final String SQL_GET_ALL_USER_ROLE = "select * from `user_role`";
    private static final String SQL_UPDATE_USER_ROLE = "update `user_role` set role_id = ? where user_id = ?";
    private static final String SQL_DELETE_USER_ROLE = "delete from `user_role` where id = ?";
    
    //<editor-fold defaultstate="collapsed" desc="CRUD methods">
    
    public static void create(Connection con, UserRole userRole) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(SQL_CREATE_USER_ROLE, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, userRole.getRole().getId());
            pstmt.setInt(2, userRole.getUser().getId());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                            userRole.setId(generatedKeys.getInt(1));
                    }
            }
        }
    }

    public static UserRole getById(Connection con, int id) throws SQLException {
        UserRole userRole = new UserRole();
        ResultSet rs = null;

        try (PreparedStatement pstmt = con.prepareStatement(SQL_GET_USER_ROLE_BY_ID)) {
                pstmt.setInt(1, id);
                rs = pstmt.executeQuery()

                if(rs.next()) {
                    userRole.setId(rs.getInt(1));
                    userRole.setName(rs.getString(2));
                }
        }

        return userRole;
    }

    public static List<UserRole> getAll(Connection con) throws SQLException {
        List<UserRole> userRoles = new ArrayList<>();
        try (PreparedStatement pstmt = con.prepareStatement(SQL_GET_ALL_USER_ROLE);
                ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                        UserRole userRole = UserRoleDao.getById(con, rs.getInt(1));
                        userRoles.add(userRole);
                }
        }

        return userRoles;
    }

    public static void update(Connection con, UserRole userRole) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_USER_ROLE)) {
                pstmt.setInt(1, userRole.getRole().getId());
                pstmt.setInt(2, userRole.getUser().getId());
                pstmt.executeUpdate();
        }
    }

    public static void delete(Connection con, int id) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(SQL_DELETE_USER_ROLE)) {
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
        }
    }

    //</editor-fold>
}