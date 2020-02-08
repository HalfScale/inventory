package system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//import system.bean.UserAvatar;

public class UserAvatarDao {
    private static final String SQL_CREATE_USER_AVATAR = "insert into `user_avatar` (col1, col2) VALUES (?, ?)";
    private static final String SQL_GET_USER_AVATAR_BY_ID = "select * from `user_avatar` where id = ?";
    private static final String SQL_GET_ALL_USER_AVATAR = "select * from `user_avatar`";
    private static final String SQL_UPDATE_USER_AVATAR = "update `user_avatar` set col1 = ?, col2 = ? where id = ?";
    private static final String SQL_DELETE_USER_AVATAR = "delete from `user_avatar` where id = ?";
    
    //<editor-fold defaultstate="collapsed" desc="CRUD methods">
    
    public static void create(Connection con, UserAvatarDao userAvatar) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(SQL_CREATE_USER_AVATAR, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, userAvatar.getName());
            pstmt.setBoolean(2, userAvatar.getStatus());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                            userAvatar.setId(generatedKeys.getInt(1));
                    }
            }
        }
    }

    public static UserAvatarDao getById(Connection con, int id) throws SQLException {
        UserAvatarDao userAvatar = new UserAvatarDao();
        ResultSet rs = null;

        try (PreparedStatement pstmt = con.prepareStatement(SQL_GET_USER_AVATAR_BY_ID)) {
                pstmt.setInt(1, id);
                rs = pstmt.executeQuery()

                if(rs.next()) {
                    userAvatar.setId(rs.getInt(1));
                    userAvatar.setName(rs.getString(2));
                }
        }

        return userAvatar;
    }

    public static List<UserAvatarDao> getAll(Connection con) throws SQLException {
        List<UserAvatarDao> userAvatars = new ArrayList<>();
        try (PreparedStatement pstmt = con.prepareStatement(SQL_GET_ALL_USER_AVATAR);
                ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                        UserAvatarDao userAvatar = UserAvatarDao.getById(con, rs.getInt(1));
                        userAvatars.add(userAvatar);
                }
        }

        return userAvatars;
    }

    public static void update(Connection con, UserAvatarDao userAvatar) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_USER_AVATAR)) {
                pstmt.setString(1, userAvatar.getName());
                pstmt.setBoolean(2, userAvatar.getStatus());
                pstmt.setInt(3, userAvatar.getId());
                pstmt.executeUpdate();
        }
    }

    public static void delete(Connection con, int id) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(SQL_DELETE_USER_AVATAR)) {
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
        }
    }

    //</editor-fold>
	
	public static ResultSet getAvatar(Connection con, int id) throws SQLException {
		String sql = "select * from `user_avatar` where id = ?";
		ResultSet rs = null;

		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();
		}
		
		return rs;
	}
}