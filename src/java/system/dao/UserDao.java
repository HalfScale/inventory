package system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import system.bean.User;

public class UserDao {

    private static final String SQL_CREATE_USER = "insert into `user` (first_name, last_name, email, username, password, status) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_GET_USER_BY_ID = "select * from `user` where id = ?";
    private static final String SQL_GET_ALL_USER = "select * from `user`";
    private static final String SQL_UPDATE_USER = "update `user` set first_name = ?, last_name = ?, email = ?, username = ?, password = ?, status = ? where id = ?";
    private static final String SQL_DELETE_USER = "delete from `user` where id = ?";
    
    public static void create(Connection con, User user) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(SQL_CREATE_USER, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getUsername());
            pstmt.setString(5, user.getPassword());
            pstmt.setBoolean(6, user.getStatus());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    public static User getById(Connection con, int id) throws SQLException {
        User user = new User();
        ResultSet rs = null;
        
        try (PreparedStatement pstmt = con.prepareStatement(SQL_GET_USER_BY_ID)) {
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                user.setId(rs.getInt(1));
                user.setFirstName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setUsername(rs.getString(5));
                user.setPassword(rs.getString(6));
                user.setStatus(rs.getBoolean(7));
            }
        }

        return user;
    }

    public static List<User> getAll(Connection con) throws SQLException {
        List<User> users = new ArrayList<>();
        try (PreparedStatement pstmt = con.prepareStatement(SQL_GET_ALL_USER);
                ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                User user = UserDao.getById(con, rs.getInt(1));
				user.setRole(RoleDao.getRoleByUser(con, rs.getInt(1)));
                users.add(user);
            }
        }

        return users;
    }

    public static void update(Connection con, User user) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_USER)) {
            pstmt.setString(1, user.getFirstName());
            pstmt.setString(2, user.getLastName());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getUsername());
            pstmt.setString(5, user.getPassword());
            pstmt.setBoolean(6, user.getStatus());
            pstmt.setInt(7, user.getId());
            pstmt.executeUpdate();
        }
    }

    public static void delete(Connection con, int id) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(SQL_DELETE_USER)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
    
    public static User getByUsernameAndPassword(Connection con, String username, String password) throws SQLException {
        String query = "select * from user where username = ? and password = ?";
        User user = null;
        
        try(PreparedStatement pstmt = con.prepareStatement(query)) {
            
            ResultSet rs = null;
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                user = UserDao.getById(con, rs.getInt(1));
            }
        }
        
        return user;
    }
}
