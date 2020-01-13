/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 *
 * @author MacMuffin
 */
public class Console {
    private final static Logger logger = LogManager.getLogger(Console.class);
    
    public static void log(String message) {
        logger.debug(message);
    }
}
