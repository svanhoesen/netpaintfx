/**
 * This is the line shape it extends shape and is used to draw and save lines.
 * 
 * @authors Steffan Van Hoesen
 *
 */

package model;

import java.awt.Point;
import javafx.scene.canvas.GraphicsContext;


//import view.NetPaintFXGUI;

import javafx.scene.paint.Color;

public class Line extends PaintObject<Object>{
	

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
	public void draw(GraphicsContext g2) {
		g2.setStroke(getColor());
		g2.strokeLine(getInitX(),getInitY(), getEndX(),getEndY());
		
	}

	
}