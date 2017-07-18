/**
 * This class is a multi-threaded server that allows several clients to connect.
 * The clients are expected to write PaintObjects to this server.
 * The clients are also expected to read the Vector<PaintObject> object
 * when they connect and everytime any Client draws a new PaintObject.
 * 
 * @author Steffan Van Hoesen
 *
 */
/** 
 
 */
package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.listOfPaint;

public class Server implements Runnable {
	public static int PORT_ID = 4000;
	ServerSocket servSock = null;
	volatile boolean isRunning = true;
	private static List<ObjectOutputStream> clients = Collections.synchronizedList(new ArrayList<ObjectOutputStream>());
	private listOfPaint swatches;

	@Override
	public void run() {
		try {
			servSock = new ServerSocket(PORT_ID);
		} catch (IOException e) {
			e.printStackTrace();
		}
		swatches = new listOfPaint();
		System.out.println("Listening");
		while (isRunning) {
			Socket runSoc = null;
			ObjectInputStream clientInStream = null;
			try {
				runSoc = servSock.accept();
			} catch (IOException e) {
				makeClean();
				return;
			}
			try {
				clientInStream = new ObjectInputStream(runSoc.getInputStream());
				ObjectOutputStream clientOutStream = new ObjectOutputStream(runSoc.getOutputStream());
				clients.add(clientOutStream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("Connected-----------");
			new Thread(new ClientHandler(clients, clientInStream)).start();
		}
	}

	public void halt() throws IOException {
		isRunning = false;
		servSock.close();
	}

	public void makeClean() {
		try {
			servSock.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private class ClientHandler implements Runnable {

		ObjectInputStream inStream;
		List<ObjectOutputStream> clients;
		
		ClientHandler(List<ObjectOutputStream> clients, ObjectInputStream inStream) {
			this.clients = clients;
			this.inStream = inStream;
		}

		@Override
		public void run() {
			try {
	
				writePaintListToClient();
				while (true) {
					try {
						swatches = (listOfPaint) inStream.readObject();
						System.out.println("added paint object to the server");
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					writePaintListToClient();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		
		private void writePaintListToClient(){
			for(ObjectOutputStream clientOutStream: clients){
				try {
					clientOutStream.reset();
					clientOutStream.writeObject(swatches);
					System.out.println("write paint object to the clients");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}