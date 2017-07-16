/**
 * This is the Image it extends shape and is used to draw and save Images.
 * 
 * @author Steffan Van Hoesen
 *
 */

package model;

import javafx.scene.image.Image;
import java.awt.Point;
import java.io.IOException;

import javafx.scene.canvas.GraphicsContext;


public class Picture extends PaintObject<Object> {


	/**
	 * Creates a new Image by passing PaintObject needed information and
	 * loading in the image.
	 * 
	 * @param initX,initY Takes in initial position of X and Y.
	 * @throws IOException 
	 */
	public Picture(Point initX, Point initY, String s) {
		super(null, initX, initY, s);
		setPicture("file:./NetPaintFX/images/" + s);
	}

	/**
	 * Used to draw a image onto the canvas.
	 * 
	 * @param g2
	 *            Takes in a GraphicContext this is what draws on the canvas.
	 */
	@Override
	public void draw(GraphicsContext g2) {
		Image pic = new Image(getPicture());
		g2.drawImage(pic, (double)Math.min(getInitX(), getEndX()), (double)Math.min(getInitY(), getEndY()), (double)getWidth(), (double)getHeight());

	}




}