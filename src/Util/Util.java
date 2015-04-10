package Util;

import Object.Fish;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {

    public static void sleep(int time) {
        try {
            java.lang.Thread.sleep(time);
        } catch (InterruptedException ex) {
            Logger.getLogger(Fish.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
