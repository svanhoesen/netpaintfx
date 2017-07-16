/**
 * This is the rectangle shape it extends shape and is used to draw and save
 * rectangles.
 * 
 * @authors Steffan Van Hoesen
 *
 */

package model;

import java.awt.Point;
import javafx.scene.canvas.GraphicsContext;


//import view.NetPaintFXGUI;

import javafx.scene.paint.Color;


public class Rectangle extends PaintObject<Object> {

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
	public void draw(GraphicsContext g2) {
		g2.setFill(getColor());
		g2.fillRect(Math.min(getInitX(), getEndX()), Math.min(getInitY(), getEndY()), getWidth(), getHeight());

	}

}