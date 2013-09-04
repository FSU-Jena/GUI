package edu.fsuj.csb.gui;
import java.awt.Component;
import java.awt.Dimension;


/**
 * ein Abkömmling von JPanel, der eine automatische Anordnung der Unterlemente nebeneinander ermöglicht
 * @author Stephan Richter
 *
 */
public class HorizontalPanel extends ScalablePanel {

  private static final long serialVersionUID = -3763921236213613770L;
		
	public HorizontalPanel(String title) {
		super(title);
  }

	public HorizontalPanel() {
		super();
  }

	/**
	 * skaliert das gesamte Panel so, dass alle hinzugefügten Komponenten sichtbar bleiben
	 */
	public void scale(){
		Component[] components = getComponents();
		int width=0;
		int maxHeight=0;
		for (Component c:components){
			if (c instanceof ScalablePanel) {
				ScalablePanel scalablePanel = (ScalablePanel) c;
				scalablePanel.scale();	      
      }
			width+=space;
			c.setSize(c.getPreferredSize());
			c.setLocation(width, top);
			if (c.getHeight()>maxHeight) maxHeight=c.getHeight();
			width+=c.getWidth();
		}
		
		setPreferredSize(new Dimension(width+5, maxHeight+space+space));
		setSize(getPreferredSize());
	}	
}
