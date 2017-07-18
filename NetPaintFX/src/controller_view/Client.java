/**
 * A JPanel GUI for Netpaint that has all paint objects drawn on it. This file
 * also represents the controller as it controls how paint objects are drawn and
 * sends new paint objects to the server. All Client objects also listen to the
 * server to read the Vector of PaintObjects and repaint every time any client
 * adds a new one.
 * 
 * @author Steffan Van Hoesen
 * 
 */
package controller_view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;

import model.PaintObject;
import model.listOfPaint;
import server.Server;
import controller_view.NetPaintFXGUI;

public class Client implements Runnable, Observer{

	listOfPaint paints;
	Socket soc;
	ObjectOutputStream outStream;
	ObjectInputStream inStream;
	
	public Client(){
		paints = new listOfPaint();
		new NetPaintFXGUI<Object>();
	}
	
	/**
	 * openConnection
	 * 
	 * opens connection to server
	 */
	private void makeConnection(){
		try {
			soc = new Socket("localHost", Server.PORT_ID);
			outStream = new ObjectOutputStream(soc.getOutputStream());
			inStream = new ObjectInputStream(soc.getInputStream());
			try {
				inStream.readObject();

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		makeConnection();
		
		ServerListener listenthread= new ServerListener();
		listenthread.start();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void update(Observable obs, Object obj) {
		PaintObject paintStuff = (PaintObject) obj;
		try {
			outStream.writeObject(paintStuff);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private class ServerListener extends Thread{
		
		@Override
		public void run(){
			while(true){
				try {
					inStream.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}