/**
 * This is the line shape it extends shape and is used to draw and save lines.
 * 
 * @authors Steffan Van Hoesen
 *
 */

package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Line extends PaintObject<Object>{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 9082971076005623150L;


	/**
	 * Makes a new line using Shape by passing shape needed information.
	 * 
	 * @param initX, initY Takes in the initial positions of both X and Y. 	 
	 */
	public Line(Color color, Point initX, Point initY) {
		super(color, initX, initY, null);
		
	}


	/**
	 * Used to draw a line onto the canvas.
	 * 
	 * @param g2 Takes in a GraphicContext this is what draws on the canvas.
	 */
	@Override
	public void draw(Graphics g2) {
		g2.setColor(getColor());
		g2.drawLine(getIPoint().x, getIPoint().y, getEPoint().x, getEPoint().y);
		
	}

	
}