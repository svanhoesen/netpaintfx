/**
 * This is the initial GUI. It calls up the server and client GUIs in one interface window.
 * 
 * @author Steffan Van Hoesen
 *
 */


package controller_view;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GUIStart extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4732660994100674367L;

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new GUIStart();
			}
		});
	}

	public GUIStart() {
		setSize(300, 300);
		setVisible(true);
		setLayout(new GridLayout(4, 1, 15, 15));
		setLocationRelativeTo(null);

		add(new JLabel("Server Controller"));
		add(new ServerView());
		add(new JLabel("Client Start"));
		add(new ClientView());
	}
}
