package edu.fsuj.csb.gui;

/**
 * describes a class for logging actions
 * @author Stephan Richter
 *
 */
public interface LogListener {
	/**
	 * adds an entry to the log
	 * @param message the message to append to the log
	 */
	public void log(String message);
}
