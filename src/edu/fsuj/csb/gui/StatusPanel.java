package edu.fsuj.csb.gui;
import java.awt.Component;
import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;


/**
 * implements a log listener, which writes it's log messages to a visual panel
 * @author Stephan Richter
 *
 */
public class StatusPanel extends VerticalPanel implements LogListener {
	private static final long serialVersionUID = -7044888618098845235L;
	private JTextArea logs; // the text area, in which the messages shall be displayed
	private StringBuffer log; // buffers the log messages. useful for two purposes: fast appending of text and editing of previous post messages
	private static final Dimension initialSize=new Dimension(600, 160);
	private JScrollPane scp;
	/**
	 * creates a new StatusPanel instance
	 */
	public StatusPanel(){
		super();
		scp=new JScrollPane(logs=new JTextArea());
		scp.setPreferredSize(initialSize);
		add(scp);
		scale();
		LogStream ls;
    try {
	    ls = new LogStream(this);
			System.setOut(new PrintStream(ls));
			log=new StringBuffer();
    } catch (FileNotFoundException e) {
	    e.printStackTrace();
    }
	}	
	
	/* (non-Javadoc)
	 * @see LogListener#log(java.lang.String)
	 */
	public void log(String message){
		Component p=getParent();
		if (p instanceof JTabbedPane) ((JTabbedPane) p).setSelectedComponent(this);
		if (isWhitespace(message)){
			int l=message.length();
			int i=log.lastIndexOf("\n");
			log=log.delete(i-l, log.length());
		} else log.append(message);
		logs.setText(log.toString());
		if (log.length()>3) logs.setCaretPosition(log.length()-3);
  }

	private boolean isWhitespace(String message) {
		for (int i=0; i<message.length(); i++) if (message.charAt(i)!=' ') return false;
	  return true;
  }

	/**
	 * removes all the text from the panel
	 */
	public void clear() {
		log=new StringBuffer();
		logs.setText(log.toString());
  }

	public void setWidth(int w) {
		Dimension d = scp.getPreferredSize();
		d.width=w-10;
		scp.setPreferredSize(d);
		scale();
  }
}
