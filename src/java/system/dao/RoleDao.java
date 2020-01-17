package system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import system.bean.Role;

public class RoleDao {
    private static final String SQL_CREATE_ROLE = "insert into `role` (name) VALUES (?)";
    private static final String SQL_GET_ROLE_BY_ID = "select * from `role` where id = ?";
    private static final String SQL_GET_ALL_ROLE = "select * from `role`";
    private static final String SQL_UPDATE_ROLE = "update `role` set name = ? where id = ?";
    private static final String SQL_DELETE_ROLE = "delete from `role` where id = ?";
    
    //<editor-fold defaultstate="collapsed" desc="CRUD methods">
    
    public static void create(Connection con, Role role) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(SQL_CREATE_ROLE, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, role.getName());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                            role.setId(generatedKeys.getInt(1));
                    }
            }
        }
    }

    public static Role getById(Connection con, int id) throws SQLException {
        Role role = new Role();
        ResultSet rs = null;

        try (PreparedStatement pstmt = con.prepareStatement(SQL_GET_ROLE_BY_ID)) {
                pstmt.setInt(1, id);
                rs = pstmt.executeQuery();

                if(rs.next()) {
                    role.setId(rs.getInt(1));
                    role.setName(rs.getString(2));
                }
        }

        return role;
    }

    public static List<Role> getAll(Connection con) throws SQLException {
        List<Role> roles = new ArrayList<>();
        try (PreparedStatement pstmt = con.prepareStatement(SQL_GET_ALL_ROLE);
                ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                        Role role = RoleDao.getById(con, rs.getInt(1));
                        roles.add(role);
                }
        }

        return roles;
    }

    public static void update(Connection con, Role role) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_ROLE)) {
                pstmt.setString(1, role.getName());
                pstmt.setInt(2, role.getId());
                pstmt.executeUpdate();
        }
    }

    public static void delete(Connection con, int id) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(SQL_DELETE_ROLE)) {
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
        }
    }

    //</editor-fold>
}