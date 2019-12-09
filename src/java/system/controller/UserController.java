/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import system.bean.User;

/**
 *
 * @author MacMuffin
 */
public class UserController {
	
//	private static

    public UserController() {
    }
    
    public static User getUser(int id) {
        return new User();
    }
    
    public static User getUser(Connection con, String username, String password) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet result = null;
        User user = null;
        
        String query = "select * from user where username = ? and password = ?";
        
        stmt = con.prepareStatement(query);
        stmt.setString(1, username);
        stmt.setString(2, password);
        
        result = stmt.executeQuery();
        
        if (result.next()) {
            user = new User();
            user.setId(result.getInt("id"));
            user.setFirstName(result.getString("first_name"));
            user.setLastName(result.getString("last_name"));
            user.setEmail(result.getString("email"));
            user.setUsername(result.getString("username"));
            user.setPassword(result.getString("password"));
        }
        
        return user;
    }
}
