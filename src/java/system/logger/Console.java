/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.logger;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author MacMuffin
 */
public class Console {

    private final static Logger logger = LogManager.getLogger(Console.class);

    public static void log(String... message) {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtils.join(message, " "));
        logger.debug(sb);
    }
}
