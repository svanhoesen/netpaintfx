/**
 * This is the Oval shape it extends shape and is used to draw and save ovals.
 * 
 * @author Steffan Van Hoesen
 * 
 */

package model;

import java.awt.Point;
import javafx.scene.canvas.GraphicsContext;


//import view.NetPaintFXGUI;

import javafx.scene.paint.Color;

public class Oval extends PaintObject<Object> {

	/**
	 * Creates a new Oval using Shape by passing shape needed information.
	 * 
	 * @param initX,initY Takes in initial position of X and Y.
	 */
	public Oval(Color color, Point init, Point end) {
		super(color, init, end, null);

	}

	/**
	 * Used to draw an oval onto the canvas.
	 * 
	 * @param g2 Takes in a GraphicContext this is what draws on the canvas.
	 */
	@Override
	public void draw(GraphicsContext g2) {
		g2.setFill(getColor());
		g2.fillOval(Math.min(getInitX(), getEndX()), Math.min(getInitY(), getEndY()), getWidth(), getHeight());
	}

}