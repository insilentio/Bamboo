import java.awt.*;

/**
 *	MyPolygon is a abstract base class of all polygon types in our application. Every type of polygon
 *	has a name, set of vertices and its own color.
 */

public abstract class MyPolygon {
	/**
	 *	Name of the polygon
	 */
	protected String	name		= null;
	
	/**
	 *	array of vertices of the polygon
	 */
	protected Point		vertices[]	= null;
	
	/**
	 *	color of the polygon
	 */
	protected Color		color		= null;
	
	
	/**
	 *	constructor
	 *	@param name name of the polygon
	 *	@param vertices set of vertices of the polygon
	 *	@param color color of the polygon
	 */
	public MyPolygon(String name, Point vertices[], Color color) {
		this.name = name;
		this.vertices = vertices;
		this.color = color;
	}
	
	/**
	 *	get the name of the polygon
	 *	@return the name of the polygon in a string
	 */
	public String getName() {
		return name;
	}
	
	/**
	 *	get all the vertices of the polygon in an array
	 *	@return the array of vertices
	 */
	public Point[] getPoints() {
		return vertices;
	}
	
	/**
	 *	get the color of the polygon
	 *	@return the color of the polygon
	 */
	public Color getColor() {
		return color;
	}
	
	/**
	 *	To test whether the point provided is inside the polygon or not
	 *	@param p point to be tested
	 *	@return true of the point is inside the polygon; false otherwise
	 */
	abstract public boolean inside(Point p);
	
	/**
	 *	To calculate the area of the polygon
	 *	@return the area of the polygon
	 */
	abstract public int area();
}