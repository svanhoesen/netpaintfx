/**
 * This is the rectangle shape it extends shape and is used to draw and save
 * rectangles.
 * 
 * @authors Steffan Van Hoesen
 *
 */

package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;



public class Rectangle extends PaintObject<Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4566850474105457416L;


	/**
	 * Creates a new rectangle using Shape by passing shape needed information.
	 * 
	 * @param startX,startY
	 *            Takes in startingX position and startingY position.
	 */
	public Rectangle(Color color, Point initX, Point initY) {
		super(color, initX, initY, null);
	}


	/**
	 * Used to draw an rectangle onto the canvas.
	 * 
	 * @param g2
	 *            Takes in a GraphicContext this is what draws on the canvas.
	 */
	@Override
	public void draw(Graphics g2) {
		g2.setColor(getColor());
		g2.fillRect(Math.min(getIPoint().x, getEPoint().x), Math.min(getIPoint().y, getEPoint().y), getWidth(), getHeight());

	}

}