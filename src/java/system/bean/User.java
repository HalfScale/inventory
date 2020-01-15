

package system.bean;

import javax.servlet.http.HttpServletRequest;
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
    
}
