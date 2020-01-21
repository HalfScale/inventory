

package system.bean;

import javax.servlet.http.HttpServletRequest;
import system.util.Util;

/**
 *
 * @author MacMuffin
 */
public class UserRole {
    private int id;
	private User user;
	private Role role;

    public UserRole() {
    }

    public UserRole(HttpServletRequest request) {
        this.id = Integer.parseInt(Util.isBlank(request.getParameter("id"), "-1"));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
