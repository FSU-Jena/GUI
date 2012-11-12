package edu.fsuj.csb.gui;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import edu.fsuj.csb.gui.VerticalPanel;

public class SelectionDialog extends JDialog {	

  private static final long serialVersionUID = -6049241633290600425L;
	private Plopp plopp=new Plopp();
	
  private class Plopp implements ActionListener {
  	private String choice=null;
		@Override
    public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
	    if (source instanceof JButton) {
	      JButton bt = (JButton) source;
	      choice=bt.getText();
	      setVisible(false);
      }
    }
		
		public String getChoice(){
			return choice;
		}
  	
  }
  
  @SuppressWarnings({ "rawtypes", "unchecked" })
  private SelectionDialog(String title, String caption, Collection choices) {
  	super();
  	this.setTitle(title);
		VerticalPanel vp=new VerticalPanel();
		vp.add(new JLabel(caption));
		for (Iterator<Object> it = choices.iterator(); it.hasNext();){
			Object o=it.next();
			JButton bt=new JButton(o.toString());
			bt.addActionListener(plopp);
			vp.add(bt);
		}
		vp.skalieren();
		add(vp);
		pack();
		setModalityType(Dialog.DEFAULT_MODALITY_TYPE);
		setVisible(true);
	}
  
	@SuppressWarnings("rawtypes")
  public static String selectOutOf(String title,String caption,Collection choices) {
		SelectionDialog sd=new SelectionDialog(title,caption,choices);
		String result=sd.getChoice();
		sd.dispose();
		return result;
  }

	private String getChoice() {
	  return plopp.getChoice();
  }	
}
