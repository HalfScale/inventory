

package system.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import system.logger.Console;
import system.util.Util;

/**
 *
 * @author MacMuffin
 */
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
	private Role role;
    private boolean status;
	
    public User() {
    }

    public User(HttpServletRequest request) {
        this.id = Integer.parseInt(Util.isBlank(request.getParameter("id"), "-1"));
        this.firstName = request.getParameter("firstName");
        this.lastName = request.getParameter("lastName");
        this.email = request.getParameter("email");
        this.username = request.getParameter("username");
        this.password = request.getParameter("password");
        this.status = request.getParameter("status").equals("1");
    }
	
	public boolean hasModuleAccess(int module) {
		Console.log("Target module", String.valueOf(module));
		String sql = "select * from `user` u inner join `user_role` ur on u.id = ur.role_id "
				+ "inner join `role_module` rm "
				+ "on ur.role_id = rm.role_id where rm.module_id = ? "
				+ "and rm.role_id = ur.role_id";
		
		ResultSet rs = null;
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, module);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				Console.log("this user has an access");
				return true;
			}
		
		}catch(SQLException ex) {
			ex.printStackTrace();
			Console.log(ex.getMessage());
		}
		
		Console.log("this user does not have an access");
		return false;
	}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
    
}
