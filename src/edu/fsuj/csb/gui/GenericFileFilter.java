package edu.fsuj.csb.gui;
import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * implements an all purpose file filter
 * @author Stephan Richter
 *
 */
public class GenericFileFilter extends FileFilter{
  String [] ending=null;
  String desc;
  
  /**
   * create new file filter for files of certain types
   * @param description the descrption of the file filter
   * @param type a list of files that shall be displayed in the form <b>extensions</b>, where extensions is of the form <b>extension</b>{;<b>extensions</b>}<br/>extension is one of <b>*.<i>string</i></b>, <b>.<i>string</i></b> or <b><i>string</i></b>
   */
  public GenericFileFilter(String description,String type){
    super();
    desc=description;
    if (type==null){
      ending=null;
    } else {
      ending = type.split(";");
      for (int i=0; i<ending.length; i++){
       	if (ending[i].startsWith("*")) ending[i]=ending[i].substring(1);
      	if (ending[i].startsWith(".")) ending[i]=ending[i].substring(1);
      	ending[i]=ending[i].toLowerCase();
      }
    }
  }
  
  /* (non-Javadoc)
   * @see javax.swing.filechooser.FileFilter#accept(java.io.File)
   */
  public boolean accept(File f){
    if ((f.isDirectory())||(ending==null)) return true;
    for (int i=0; i<ending.length; i++){
      if (f.getName().toLowerCase().endsWith(ending[i])) return true;
    }
    return false;
  }
  
  /* (non-Javadoc)
   * @see javax.swing.filechooser.FileFilter#getDescription()
   */
  public String getDescription(){
    return desc;
  }
}
