/**
 * This is the GUI for interacting with the server file.
 * 
 * @author Steffan Van Hoesen
 *
 */


package controller_view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;

import server.Server;

public class ServerView extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2489366069123566034L;
	private JButton connectServerButton;
	private JButton terminateServerButton;
	private Server server;
	private Thread thread;

	public ServerView() {
		setSize(400, 200);
		setLayout(new GridLayout(1, 2, 15, 15));

		connectServerButton = new JButton("Connect");
		add(connectServerButton);

		terminateServerButton = new JButton("Terminate");
		terminateServerButton.setEnabled(false);
		add(terminateServerButton);
		
		registerListener();
	}

	private void registerListener() {
		connectServerButton.addActionListener(new StartServerListener());
		terminateServerButton.addActionListener(new TerminateServerListener());
	}

	private class StartServerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			server = new Server();
			thread = new Thread(server);
			thread.start();
			connectServerButton.setEnabled(false);
			terminateServerButton.setEnabled(true);
		}
	}

	private class TerminateServerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			connectServerButton.setEnabled(true);
			terminateServerButton.setEnabled(false);
			try {
				server.halt();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			thread.interrupt();
		}
	}
}
