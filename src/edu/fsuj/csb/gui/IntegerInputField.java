package edu.fsuj.csb.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;



/**
 * Erzeugt einen Eingabebereich für Ganzzahlen mit zwei Buttons zum Einstellen daneben
 * @author Stephan Richter
 *
 */
public class IntegerInputField extends HorizontalPanel implements ActionListener, KeyListener{

  private static final long serialVersionUID = -2088663258362363480L;
	private JButton increaseBtn,decreaseBtn; // die Knöpfe, mit denen der Wert schittweise verändert werden kann
	private JTextField countField; // das Textfeld, in welchem alternativ ein Wert eingegeben werden kann
	private int minimum=0; // der Minimale Wert, der angenommen werden darf
	private Vector<ActionListener> listeners; // die Liste der Objekte, die über Veränderungen des Wertes informiert werden sollen
	private JLabel label; // die Beschriftung neben dem Feld
	
	/**
	 * erzeugt ein neues Eingabefeld
	 * @param text der neben dem Eingabefeld anzuzeigende Text
	 */
	public IntegerInputField(String text){
		super(); // neues, leeres Panel erzeugen
		listeners=new Vector<ActionListener>(); // Liste der überwachenden Objekte erzeugen
		increaseBtn=new JButton("+"); // Knopf zum Erhöhen des Wertes erzeugen
		increaseBtn.addActionListener(this); // Knopf zum erhöhen des Wertes überwachen
		countField=new JTextField(5); // Eingabefeld für Anzeige/Veränderung des Wertes erzeugen
		countField.setText("1"); // Eingabefeld initialisieren
		countField.setHorizontalAlignment(JTextField.RIGHT); // Ausrichtung des Textes im Eingabebereich ändern: für Zahlen erscheint eine rechtsbündige Ausrichtung angemessen
		countField.addKeyListener(this); // Eingabefeld zur Überwachung registrieren
		decreaseBtn=new JButton("-"); // Knopf zum Verringern des Wertes erzeugen
		decreaseBtn.addActionListener(this); // Knopf zur Überwachung registrieren
		add(label = new JLabel(text)); // Beschriftung erzeugen und hinzufügen
		add(increaseBtn); // Knopf zum Panel hinzufügen
		add(countField); // Eingabefeld zum Panel hinzufügen
		add(decreaseBtn); // Knopf zum Panel hinzufügen
		skalieren();
	}

	/**
	 * erzeugt ein neues Eingabefeld mit vorgegebenem Minimum
	 * @param text der Text, der neben dem Feld angezeigt werden soll
	 * @param minimum dieser Wert wird als Minimum für die Eingabe verwendet; mit diesem Wert wird das Eingabefeld initialisiert
	 */
	public IntegerInputField(String text,int minimum){
		this(minimum,text); // Erzeugen des Eingabefeldes und setzen des anfänglichen Wertes
		this.minimum=minimum; // speichern des Minimums
	}
	
	/**
	 * erzeugt ein neues Eingabefeld mit vorgegebenem Anfangswert
	 * @param wert der Wert, den das Eingabefeld anfangs anzeigen soll
	 * @param text der Text, der neben dem Feld angezeigt werden soll
	 */
	public IntegerInputField(int wert,String text){
		this(text); // Erzeugen des Eingabefelde
		countField.setText(wert+""); // setzen des Ausgangswertes		
	}
	
	/**
	 * fügt ein Objekt zur Liste der Objekte hinzu, die über Veränderungen des Wertes informiert werden sollen
	 * @param l das Listener-Objekt
	 */
	public void addActionListener(ActionListener l){
		listeners.add(l);
	}
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		Object sender=arg0.getSource();		
		if (sender==increaseBtn) countField.setText(String.valueOf(1+Integer.parseInt(countField.getText())));
		if (sender==decreaseBtn) countField.setText(String.valueOf(Math.max(minimum, Integer.parseInt(countField.getText())-1)));
		if (sender==this || sender instanceof JButton) {
			arg0.setSource(this);
			for (int i=0; i<listeners.size();i++) listeners.get(i).actionPerformed(arg0);
		}
	}
	
	/**
	 * liefert den Wert zurück, der im Feld angezeigt wird
	 * @return den Wert der im Feld angezeigt wird
	 */
	public int wert(){
		return Integer.parseInt(countField.getText());
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	public void keyPressed(KeyEvent arg0) {	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	public void keyReleased(KeyEvent arg0) {
		actionPerformed(new ActionEvent(this, 0, "ValueChanged"));		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	public void keyTyped(KeyEvent arg0) {	}
	
	/**
	 * legt den im Eingabefeld angezeigten Wert fest
	 * @param wert der Wert, der im Eingabefeld angezeigt werden soll
	 */
	public void setzewert(int wert){
		countField.setText(""+wert);
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#setForeground(java.awt.Color)
	 */
	public void setForeground(Color c){
		if (label!=null) label.setBackground(c);
	}
	
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#setToolTipText(java.lang.String)
	 */
	public void setToolTipText(String text){
		increaseBtn.setToolTipText(text);
		decreaseBtn.setToolTipText(text);
		countField.setToolTipText(text);
	}
}
