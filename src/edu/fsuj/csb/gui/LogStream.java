package edu.fsuj.csb.gui;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 
 */

/**
 * extends output stream to write to a StatusPanel instance
 * @author Stephan Richter
 *
 */
public class LogStream extends OutputStream {

	
	/**
	 * the status panel, in which the logs shall be displayed
	 */
	private StatusPanel sp;

	/**
	 * creates a new LogStream instance and assigns it to a StatusPanel
	 * @param statusPanel the StatusPanel instance, in which the logs shall be displayed
	 * @throws FileNotFoundException defined by OutputStreem, will not be thrown
	 */
	public LogStream(StatusPanel statusPanel) throws FileNotFoundException {
		this.sp=statusPanel;
  }

	/* (non-Javadoc)
	 * @see java.io.OutputStream#write(int)
	 */
	@Override
  public void write(int arg0) throws IOException {
		//sp.log("LogStream.write(int agr0)");
		sp.log(""+arg0);
  }
	
	/* (non-Javadoc)
	 * @see java.io.OutputStream#write(byte[], int, int)
	 */
	public void write(byte[] b, int off, int len){
		//sp.log("LogStream.write(b,off,len)");
		StringBuffer sb=new StringBuffer();
		for (int i=0; i<len; i++){
			sb.append((char)b[off+i]);
		}
		sp.log(sb.toString());
	}
	
	/* (non-Javadoc)
	 * @see java.io.OutputStream#write(byte[])
	 */
	public void write(byte[] b){
		//sp.log("LogStream.write(byte[]b)");
		sp.log(b.toString());
	}
	
	/* (non-Javadoc)
	 * @see java.io.OutputStream#flush()
	 */
	public void flush(){}
	/* (non-Javadoc)
	 * @see java.io.OutputStream#close()
	 */
	public void close(){
		sp.clear();
	}
}
