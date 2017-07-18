/**
 * This is the Image it extends shape and is used to draw and save Images.
 * 
 * @author Steffan Van Hoesen
 *
 */

package model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class Picture extends PaintObject<Object> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1285179179933640203L;
	private ImageIcon img;

	/**
	 * Creates a new Image by passing PaintObject needed information and
	 * loading in the image.
	 * 
	 * @param initX,initY Takes in initial position of X and Y.
	 * @throws IOException 
	 */
	public Picture(Point initX, Point initY, String s) throws IOException {
		super(null, initX, initY, s);
		//setPicture("File:../NetPaintFX/images/" + s);
		 img = new ImageIcon(ImageIO.read(new File(s)));
	}

	/**
	 * Used to draw a image onto the canvas.
	 * 
	 * @param g2
	 *            Takes in a GraphicContext this is what draws on the canvas.
	 */
	@Override
	public void draw(Graphics g2) {
		g2.setColor(getColor());
		Image imgPaint = img.getImage();
		g2.drawImage(imgPaint,Math.min(getIPoint().x, getEPoint().x), Math.min(getIPoint().y, getEPoint().y), getWidth(), getHeight(), null);

	}




}