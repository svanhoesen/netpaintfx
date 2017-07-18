/**
 * This is the GUI for interacting with the client file.
 * 
 * @author Steffan Van Hoesen
 *
 */

package controller_view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ClientView extends JPanel{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2712025623779137491L;
	private JButton painterButton;

	public ClientView() {
		setSize(400, 200);
		setVisible(true);

		setLayout(new GridLayout(1, 1, 10, 10));

		painterButton = new JButton("Call new sketchpad");
		add(painterButton);

		registerListener();
	}

	public void registerListener() {
		painterButton.addActionListener(new ProgrammerListener());
	}

	private class ProgrammerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			javax.swing.SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					new NetPaintFXGUI<Object>();
				}
			});
		}
	}
}
