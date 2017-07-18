/**
 * This is for the GUI for keeping track of the paint Objects.
 * 
 * @author Steffan Van Hoesen
 *
 */

package model;


import java.awt.Graphics;
import java.io.Serializable;
import java.util.Vector;

public class listOfPaint implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 880186880161789052L;
	@SuppressWarnings("rawtypes")
	private Vector<PaintObject> securedSketches;
	
	@SuppressWarnings("rawtypes")
	public listOfPaint (){
		securedSketches = new Vector<PaintObject>();
	}
	
	public void add(@SuppressWarnings("rawtypes") PaintObject sketchs){
		securedSketches.add(sketchs);
	}
	
	@SuppressWarnings("rawtypes")
	public void drawEach(Graphics g){
		for(PaintObject sketchs: securedSketches)
			sketchs.draw(g);
}
	
}
