/**
 * This is the Oval shape it extends shape and is used to draw and save ovals.
 * 
 * @author Steffan Van Hoesen
 * 
 */

package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Oval extends PaintObject<Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8460448092103932159L;

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
	public void draw(Graphics g2) {
		g2.setColor(getColor());
		g2.fillOval(Math.min(getIPoint().x, getEPoint().x), Math.min(getIPoint().y, getEPoint().y), getWidth(), getHeight());
	}

}