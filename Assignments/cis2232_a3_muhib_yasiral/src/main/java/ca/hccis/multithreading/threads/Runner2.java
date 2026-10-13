package ca.hccis.multithreading.threads;

import ca.hccis.multithreading.Controller;

/**
 * Runs the GUI menu in a separate thread.
 *
 * @author Yasir Al Muhib
 * @since 20261011
 */

public class Runner2 implements Runnable {

    @Override
    public void run() {
        Controller.menuGUI();
    }
}
