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

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.Serializable;

public abstract class PaintObject<E> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3239400053352091185L;
	private Point iPoint;
	private Point ePoint;
	private Color color;
	private String paintObjectLocation;

	/**
	 * Creates a new shape with all the needed information about that shape.
	 * 
	 * @param color, startLoc, endLoc, s
	 *            Takes in the positions of initial and end points.
	 */
	public PaintObject(Color color, Point startLoc, Point endLoc, String s) {

		setColor(color);
		setIPoint(startLoc);
		setEPoint(endLoc);
	}

	/*
	 * Setters
	 */
	/**
	 * Sets the initial point.
	 * 
	 * @param point
	 *            Takes in the new point.
	 */
	public void setIPoint(Point p) {

		this.iPoint = p;
	}

	/**
	 * Sets the end point.
	 * 
	 * @param point
	 *            Takes in the new point.
	 */
	public void setEPoint(Point p) {
		this.ePoint = p;
	}

	/**
	 * Sets the requested shapes color.
	 * 
	 * @param newColor
	 *            Takes in the new color.
	 */
	public void setColor(Color newColor) {
		this.color = newColor;
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

	/*
	 * Getters
	 */
	/**
	 * Returns the initial point.
	 * 
	 * @return Sends back the initial point.
	 */
	public Point getIPoint() {
		return iPoint;
	}

	/**
	 * Returns the end point.
	 * 
	 * @return Sends back the end point.
	 */
	public Point getEPoint() {
		return ePoint;
	}

	/**
	 * Returns the desired painted object's color.
	 * 
	 * @return color Sends back a color.
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Returns the height of the shape.
	 * 
	 * @return height Sends back the height.
	 */
	public int getHeight() {
		return Math.abs(getEPoint().y - getIPoint().y);
	}

	/**
	 * Returns the width of the shape.
	 * 
	 * @return width Sends back the width.
	 */
	public int getWidth() {
		return Math.abs(getEPoint().x - getIPoint().x);
	}

	/**
	 * Returns the width of the shape.
	 * 
	 * @return imageLocation Sends back the imageLocation.
	 */
	public String getPicture() {
		return paintObjectLocation;
	}

	abstract public void draw(Graphics gc);

}