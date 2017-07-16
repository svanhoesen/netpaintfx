/**
 * PaintObject
 * 
 * A PaintObject is an abstract class defining a paintObject that can be drawn with: a color,
 * two points, and a string (only if it is a picture).
 * 
 * @author Steffan Van Hoesen
 *
 */

package model;


import java.awt.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;



public abstract class PaintObject<E> extends quiz.Quiz {

//	private Point iPoint;
//	private Point ePoint;
	private int initX;
	private int initY;
	private int endX;
	private int endY;
	private Paint color;
	private String paintObjectLocation;

	/**
	 * Creates a new shape with all the needed information about that shape.
	 * 
	 * @param initX,
	 *            initY Takes in the initial positions for X and Y.
	 */
	public PaintObject(Color color, Point startLoc, Point endLoc, String s ) {
		
		setColor(color);
		setInitX(startLoc.x);
		setInitY(startLoc.y);
		setEndX(endLoc.x);
		setEndY(endLoc.y);

	}
	

/*
 * For later development of the project
 * 
 * public void setIPoint(Point p){
 
	this.iPoint = p;
}
public void setEPoint(Point p){
	this.ePoint = p;
}
public Point getIPoint(){
	return iPoint;
}
public Point getEPoint(){
	return ePoint;
}
*/
	/*
	 * Getters
	 */

	/**
	 * Returns the desired painted object's color.
	 * 
	 * @return color Sends back a color.
	 */
	public Paint getColor() {
		return color;
	}

	/**
	 * Returns the initial X point of the painted object.
	 * 
	 * @return initX Sends back the initial X position.
	 */
	public int getInitX() {
		return initX;
	}

	/**
	 * Returns the initial Y point of the painted object.
	 * 
	 * @return initY Sends back the initial Y position.
	 */
	public int getInitY() {
		return initY;
	}

	/**
	 * Returns the ending X point of the shape.
	 * 
	 * @return endX Returns the EndingX point.
	 */
	public int getEndX() {
		return endX;
	}

	/**
	 * Sets the ending Y point of the shape.
	 * 
	 * @return endY Sends back the endingY point.
	 */
	public int getEndY() {
		return endY;
	}

	/**
	 * Returns the height of the shape.
	 * 
	 * @return height Sends back the height.
	 */
	public int getHeight() {
		return Math.abs(getEndY() - getInitY());
	}

	/**
	 * Returns the width of the shape.
	 * 
	 * @return width Sends back the width.
	 */
	public int getWidth() {
		return Math.abs(getEndX() - getInitX());
	}

	/**
	 * Returns the width of the shape.
	 * 
	 * @return imageLocation Sends back the imageLocation.
	 */
	public String getPicture() {
		return paintObjectLocation;
	}

	/*
	 * Setters
	 */

	/**
	 * Sets the requested shapes color.
	 * 
	 * @param newColor
	 *            Takes in the new color.
	 */
	public void setColor(Paint newColor) {
		color = newColor;
	}

	/**
	 * Sets the initial X point of the paintObject.
	 * 
	 * @param newInitX
	 *            Takes in the new initX.
	 */
	public void setInitX(int newInitX) {
		this.initX = newInitX;
	}

	/**
	 * Sets the starting Y point of the paintObject.
	 * 
	 * @param newInitY
	 *            Takes in the new initY.
	 */
	public void setInitY(int newInitY) {
		this.initY = newInitY;
	}

	/**
	 * Sets the ending X point of the shape.
	 * 
	 * @param newEndX
	 *            Takes in the new EndX.
	 */
	public void setEndX(int newEndX) {
		this.endX = newEndX;
	}

	/**
	 * Sets the ending Y point of the shape.
	 * 
	 * @param newEndY
	 *            Takes in the new EndY.
	 */
	public void setEndY(int newEndY) {
		this.endY = newEndY;
	}

	/**
	 * Sets the height of the shape.
	 * 
	 * @param newHeight
	 *            Takes in the new height.
	 */
	public void setHeight(int newHeight) {
	}

	/**
	 * Sets the width of the shape.
	 * 
	 * @param newWidth
	 *            Takes in the new width.
	 */
	public void setWidth(int newWidth) {
	}

	/**
	 * Sets the image.
	 * 
	 * @param image
	 *            Takes in the new image.
	 */
	public void setPicture(String img) {
		this.paintObjectLocation = img;
	}

	abstract public void draw(GraphicsContext gc);

}