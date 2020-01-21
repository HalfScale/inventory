

package system.bean;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import system.util.Util;

/**
 *
 * @author Muffin
 */
public class Role {
    private int id;
    private String name;
	private List<Module> modules;

    public Role() {
    }

    public Role(HttpServletRequest request) {
        this.id = Integer.parseInt(Util.isBlank(request.getParameter("id"), "-1"));
        this.name = Util.isBlank(request.getParameter("name"), "");
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

	public List<Module> getModules() {
		return modules;
	}

	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
    
}
