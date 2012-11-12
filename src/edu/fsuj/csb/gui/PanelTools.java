package edu.fsuj.csb.gui;
import java.awt.Component;
import java.awt.Toolkit;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JFileChooser;


/**
 * a colection of static methods for usage with panels and other GUI elements
 * @author Stephan Richter
 */
public class PanelTools {
	private static String lastSelectedFile;

	/**
	 * get the width of the primary screen
	 * @return a width in pixels
	 */
	public static int screenWidth() {
		return Toolkit.getDefaultToolkit().getScreenSize().width;
  }

	/**
	 * get the height of the primary screen
	 * @return a height in pixels
	 */
	public static int screenHeight() {
		return Toolkit.getDefaultToolkit().getScreenSize().height;
	}
	
	/**
	 * Zeigt einen Datei-Öffnen-Dialog mit Titel <i>title</i>, Startverzeichnis <i>defDir</i> und Dateityp <i>fileType</i> an *
	 */
	public static URL showSelectFileDialog(String title, String fileName, GenericFileFilter fileType, Component owner) {
		if (fileName == null) fileName = lastSelectedFile;
		URL result = null;
		JFileChooser FileDialog = new JFileChooser();
		FileDialog.setDialogTitle(title);
		FileDialog.setFileFilter(fileType);
		if (fileName!=null)	FileDialog.setSelectedFile(new File(fileName));
		int returnVal = FileDialog.showOpenDialog(owner);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				result = new URL("file:" + FileDialog.getSelectedFile().getPath());
				lastSelectedFile = FileDialog.getSelectedFile().getPath();
			} catch (MalformedURLException e) {
				System.out.println("Die angegebene Zeichenkette ist keine gültige URL!");
			}
		}
		return result;
	}
}
