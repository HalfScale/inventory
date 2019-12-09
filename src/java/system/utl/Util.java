/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.utl;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author MacMuffin
 */
public class Util {
    
    public static String getRootRelativePath(HttpServletRequest request) {
        System.out.println("request.getServletPath() " + request.getServletPath());
        int depth = StringUtils.countMatches(request.getServletPath(), '/');
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < depth; i++) {
            sb.append("../");
        }
        return sb.toString();
    }
}