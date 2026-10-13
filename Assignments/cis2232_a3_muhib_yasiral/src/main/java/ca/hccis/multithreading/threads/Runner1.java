package ca.hccis.multithreading.threads;

import ca.hccis.multithreading.Controller;

/**
 * Runs the console menu in a separate thread.
 *
 * @author yam
 * @since 20261011
 */

public class Runner1 extends Thread {

    @Override
    public void run() {
        Controller.menuConsole();
    }
}
