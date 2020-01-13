

package system.bean;

import javax.servlet.http.HttpServletRequest;
import system.util.Util;

/**
 *
 * @author MacMuffin
 */
public class TestBean {
    private int id;

    public TestBean() {
    }

    public TestBean(HttpServletRequest request) {
        this.id = Integer.parseInt(Util.isBlank(request.getParameter("id"), "-1"));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
