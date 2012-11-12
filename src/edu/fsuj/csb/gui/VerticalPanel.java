package edu.fsuj.csb.gui;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;


public class VerticalPanel extends JPanel {
	/**
	 * ein Abkömmling von JPanel, der eine automatische Anordnung der Unterlemente untereinander ermöglicht
	 * @author Stephan Richter
	 *
	 */
  private static final long serialVersionUID = -3284460780727609981L;
	private static int versatz=5;
	private int breite=0;
	private int höhe=versatz;
	
	
	/**
	 * erzeugt ein neues, leeres Panel
	 */
	public VerticalPanel(){
		super();
		init();
	}

	/**
	 * erzeugt ein neues, leeres Panel mit Beschriftung
	 * @param string die Beschriftung, die im Rahmen um das Panel erscheinen soll
	 */
	public VerticalPanel(String string) {
		super();
		init();
		höhe+=15;
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(),string)); // Rahmen um Feld Erzeugen
	}

	/**
	 * schaltet das Java-eigene automatische Layout ab
	 */
	private void init() {
		this.setLayout(null);
	}
	
	/**
	 * fügt eine grafische Komponente zum Panel hinzu. die Komponente wird unterder zuletzt hinzugefügten angeordnet
	 * @param c die zuzufügende Komponente
	 */
	public void add(JComponent c){
		c.setSize(c.getPreferredSize());
		c.setLocation(versatz, höhe);
		breite=Math.max(breite, c.getWidth());
		höhe+=c.getHeight();
		super.add(c);
	}
	
	/**
	 * skaliert das gesamte Panel so, dass alle hinzugefügten Komponenten sichtbar bleiben
	 */
	public void skalieren(){
		setPreferredSize(new Dimension(breite+versatz+versatz,höhe+versatz));
	}
	
	/**
	 * verringert die Höhe des Panels um eine definierte Anzahl von Pixeln
	 * @param h die Zahl der Pixel, um die die Höhe verringert werden soll
	 */
	public void reduceHeight(int h){
		höhe-=h;
	}
}
