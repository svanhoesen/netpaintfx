/**
 * This is the Main GUI for interacting with the the user.
 * 
 * @author Steffan Van Hoesen
 *
 */


package controller_view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Picture;
import model.Line;
import model.Oval;
import model.Rectangle;
import model.PaintObject;
import model.listOfPaint;
import server.Server;

public class NetPaintFXGUI<E> extends JFrame {


	/**
	 * 
	 */
	private static final long serialVersionUID = 2399357383216718140L;
	static final String line = "Line";
	static final String rectangle = "Rectangle";
	static final String oval = "Oval";
	static final String picture = "Picture";

	private JPanel drawingPanel;
	private JScrollPane canvas;
	private JPanel radioPanel;
	private JPanel colorPanel;
	private listOfPaint securedSketches;

	private boolean drawALine, drawARect, drawAOval, drawAPic;

	private Color color;

	/**
	 * Lays out the GUI for NetPaintFX also registers listeners.
	 * 
	 * @param output takes in some output
	 */
public NetPaintFXGUI() {
	this.setTitle("NetPaintFX");
	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	this.setLocation(100, 0);
	setLayout(null);
	setSize(900, 770);
	setVisible(true);

	drawALine = true;
	drawARect = false;
	drawAOval = false;
	drawAPic = false;

	drawingPanel = new DrawingPanel();
	canvas = new JScrollPane(drawingPanel);
	canvas.setSize(900, 400);
	add(canvas);

	radioPanel = new JPanel();
	radioPanel.setSize(800, 40);
	radioPanel.setLocation(0, 400);
	setOptionMenu();
	add(radioPanel);

	//I gave it its own panel because I included more than just the basic color chooser
	colorPanel = new ColorChooser();
	colorPanel.setSize(800, 270);
	colorPanel.setLocation(0, 450);
	add(colorPanel);

}

	public void setOptionMenu() {
		radioPanel.setLayout(new GridLayout(1, 0));
		radioPanel.setBorder(BorderFactory.createEmptyBorder(5, 50, 5, 50));

		// Create the radio buttons.
		JRadioButton lineRadio = new JRadioButton(line);
		lineRadio.setMnemonic(KeyEvent.VK_L);
		lineRadio.setActionCommand(line);
		lineRadio.setSelected(true);

		JRadioButton rectangleRadio = new JRadioButton(rectangle);
		rectangleRadio.setMnemonic(KeyEvent.VK_R);
		rectangleRadio.setActionCommand(rectangle);

		JRadioButton ovalRadio = new JRadioButton(oval);
		ovalRadio.setMnemonic(KeyEvent.VK_O);
		ovalRadio.setActionCommand(oval);

		JRadioButton imageRadio = new JRadioButton(picture);
		imageRadio.setMnemonic(KeyEvent.VK_P);
		imageRadio.setActionCommand(picture);

		// Group the radio buttons.
		ButtonGroup bg = new ButtonGroup();
		bg.add(lineRadio);
		bg.add(rectangleRadio);
		bg.add(ovalRadio);
		bg.add(imageRadio);

		// Register a listener for the radio buttons.
		lineRadio.addItemListener(new lineRadioListener());
		rectangleRadio.addItemListener(new RectRadioListener());
		ovalRadio.addItemListener(new OvalRadioListener());
		imageRadio.addItemListener(new ImageRadioListener());

		// The preferred size is hard-coded to be the width of the
		// widest image and the height of the tallest image.
		// A real program would compute this.
		radioPanel.add(lineRadio);
		radioPanel.add(rectangleRadio);
		radioPanel.add(ovalRadio);
		radioPanel.add(imageRadio);
	}

	private class lineRadioListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				drawALine = true;
			} else
				drawALine = false;
		}
	}

	private class RectRadioListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				drawARect = true;
			} else
				drawARect = false;
		}
	}

	private class OvalRadioListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				drawAOval = true;
			} else
				drawAOval = false;
		}
	}

	private class ImageRadioListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				drawAPic = true;
			} else
				drawAPic = false;
		}
	}

	private class DrawingPanel extends JPanel {


		/**
		 * 
		 */
		private static final long serialVersionUID = -1882191767063800653L;

		/** Monitor and update the two points where drawing
		 * every time the mouse moves from one point to another
		 */ 
		private int oldX, oldY, newX, newY;

		private boolean isDrawing;
		private boolean isDraging;
		private PaintObject<Object> sketchs;
		@SuppressWarnings("rawtypes")
		private Vector<PaintObject> items;

		Socket soc;
		ObjectOutputStream outStream;
		ObjectInputStream inStream;

		@SuppressWarnings("rawtypes")
		public DrawingPanel() {
			setLayout(null);
			setPreferredSize(new Dimension(1500, 1000));
			isDrawing = false;
			isDraging = false;

			this.items = new Vector<PaintObject>();
			securedSketches = new listOfPaint();

			ListenToMouse listener = new ListenToMouse();
			this.addMouseMotionListener(listener);
			this.addMouseListener(listener);

			this.setBackground(Color.white);

			openConnection();

			ServerListener threadListen = new ServerListener();
			threadListen.start();
		}

		// This where all the drawing occurs. Run this by calling repaint()
		@SuppressWarnings("rawtypes")
		// that calls a JPanel method that calls the following paintComponent
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			securedSketches.drawEach(g2);
			for (PaintObject paint : items)
				paint.draw(g2);
		}

		private void openConnection() {
			try {
				soc = new Socket("localHost", Server.PORT_ID);
				outStream = new ObjectOutputStream(soc.getOutputStream());
				inStream = new ObjectInputStream(soc.getInputStream());
				try {
					securedSketches = (listOfPaint) inStream.readObject();
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

		private class ServerListener extends Thread {

			@Override
			public void run() {
				while (true) {
					try {
						securedSketches = (listOfPaint) inStream.readObject();
						System.out.println("get Paint object from the server");
						repaint();
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

		private class ListenToMouse implements MouseListener, MouseMotionListener {

			// Use a mouse click to toggle the drawing
			public void mouseClicked(MouseEvent evt) {
			}

			public void mousePressed(MouseEvent evt) {
				oldX = evt.getX();
				oldY = evt.getY();
				if (!isDrawing) {
					isDraging = true;
					if (drawALine) {
						newX = evt.getX();
						newY = evt.getY();
						sketchs = new Line(color, new Point(oldX, oldY), new Point(newX, newY));
					}
					if (drawARect) {
						newX = evt.getX();
						newY = evt.getY();
						sketchs = new Rectangle(color, new Point(oldX, oldY), new Point(newX, newY));
					}
					if (drawAOval) {
						newX = evt.getX();
						newY = evt.getY();
						sketchs = new Oval(color, new Point(oldX, oldY), new Point(newX, newY));
					}
					if (drawAPic){
						newX = evt.getX();
						newY = evt.getY();
						try {
							sketchs = new Picture(new Point(oldX, oldY), new Point(newX, newY), "./NetPaintFX/images/doge.jpeg" );
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} else {
					sketchs.setEPoint(new Point(evt.getX(), evt.getY()));
					securedSketches.add(sketchs);
					try {
						outStream.writeObject(securedSketches);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			public void mouseReleased(MouseEvent evt) {
				isDraging = false;
				if (oldX == evt.getX() && oldY == evt.getY()) {
					isDrawing = !isDrawing;
				} else {
					securedSketches.add(sketchs);
					try {
						outStream.writeObject(securedSketches);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			public void mouseMoved(MouseEvent evt) {
				if (isDrawing) {
					newX = evt.getX();
					newY = evt.getY();
					sketchs.setEPoint(new Point(newX, newY));
					items.clear();
					items.add(sketchs);
					repaint();
				}
			}

			public void mouseDragged(MouseEvent evt) {
				if (isDraging) {
					newX = evt.getX();
					newY = evt.getY();
					sketchs.setEPoint(new Point(newX, newY));
					items.clear();
					items.add(sketchs);
					repaint();
				}
			}

			public void mouseEntered(MouseEvent evt) {
				newX = evt.getX();
				newY = evt.getY();
			}

			public void mouseExited(MouseEvent evt) {
				newX = evt.getX();
				newY = evt.getY();
			}
		}
	}

	private class ColorChooser extends JPanel implements ChangeListener {

		/**
		 * 
		 */
		private static final long serialVersionUID = 6884344661543290758L;
		private JColorChooser tcc;

		public ColorChooser() {
			setLayout(new BorderLayout());
			tcc = new JColorChooser();
			tcc.getSelectionModel().addChangeListener(this);
			tcc.setBorder(BorderFactory.createTitledBorder("Choose Color"));
			add(tcc, BorderLayout.CENTER);
		}

		@Override
		public void stateChanged(ChangeEvent e) {
			color = tcc.getColor();
		}

	}
}