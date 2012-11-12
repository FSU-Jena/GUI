package edu.fsuj.csb.gui;

import javax.swing.JWindow;

/**
 * @author Stephan Richter
 *
 */
public class Splashscreen extends JWindow implements Runnable {

  private static final long serialVersionUID = -2544848909580594625L;

	public void run() {
		setSize(500, 364);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void stop() {
		dispose();
	}
}