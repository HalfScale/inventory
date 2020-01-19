

package system.bean;

import javax.servlet.http.HttpServletRequest;
import system.util.Util;

/**
 *
 * @author MacMuffin
 */
public class Module {
    private int id;
	private String name;

    public Module() {
    }

    public Module(HttpServletRequest request) {
        this.id = Integer.parseInt(Util.isBlank(request.getParameter("id"), "-1"));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
