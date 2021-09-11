/*
	A basic polgon drawing applet
	by Dr Jeffrey J. Gosper
	
	This applet draws a series of spots and lines on a canvas
	in response to mouse click. The points can be mved around interactively
	and the resultant polygon that arises from joining the points is also drawn 
	and a number of tests and calculations are performed on it. These include:
	1) Whether it is a simple polygon of not;
	2) Whether it is convex;
	3) Its area.
	
	The aplet employs two new classes to achieve this action. The iPoint (integr point) 
	and polygon class These are detailed in the comments inside the classes themselves.
	Also a canvas is extended to draw the polygon and points. 
	
 */

import java.awt.*;
import java.applet.*;
import java.util.*;     // Required for the vector

public class polygon extends Applet
{
	polygonClass poly1;
	iPoint currentPoint;    //Used to store a current point selected by a right mouse press
	                        
	public void init()
	{
	    poly1 = new polygonClass();    // Create a new (empty) polygon
	    
		//{{INIT_CONTROLS
		setLayout(null);
		setSize(380,413);
		setFont(new Font("Dialog", Font.PLAIN, 12));
		label1 = new java.awt.Label("Polygon - Draw and Test",Label.CENTER);
		label1.setBounds(6,6,384,30);
		label1.setFont(new Font("Dialog", Font.PLAIN, 22));
		add(label1);
		label2 = new java.awt.Label("by Dr Jeffrey J. Gosper",Label.CENTER);
		label2.setBounds(6,30,372,30);
		label2.setFont(new Font("Dialog", Font.ITALIC, 16));
		label2.setForeground(new Color(255));
		add(label2);
		panel1 = new java.awt.Panel();
		panel1.setLayout(null);
		panel1.setBounds(6,120,262,262);
		panel1.setBackground(new Color(0));
		add(panel1);
		label4 = new java.awt.Label("Cursor at:");
		label4.setBounds(270,126,56,24);
		add(label4);
		label5 = new java.awt.Label("(0 , 0)");
		label5.setBounds(288,150,78,24);
		add(label5);
		buttonClear = new java.awt.Button();
		buttonClear.setLabel("Clear");
		buttonClear.setBounds(270,240,102,24);
		buttonClear.setBackground(new Color(12632256));
		add(buttonClear);
		label3 = new java.awt.Label("To add a node press the left mouse button");
		label3.setBounds(6,66,264,18);
		label3.setForeground(new Color(4210752));
		add(label3);
		label6 = new java.awt.Label("To move a point use a right-mouse button drag");
		label6.setBounds(6,84,264,18);
		label6.setForeground(new Color(4210752));
		add(label6);
		checkCoords = new java.awt.Checkbox("Coords Visible");
		checkCoords.setBounds(270,180,108,20);
		add(checkCoords);
		label7 = new java.awt.Label("Note: New points are inserted in the red edge.");
		label7.setBounds(6,102,264,18);
		label7.setForeground(new Color(4210752));
		add(label7);
		checkNumbersVisible = new java.awt.Checkbox("Point # Visible");
		checkNumbersVisible.setBounds(270,204,108,20);
		add(checkNumbersVisible);
		//}}
		
		// Add my own canvas
		canvas1 = new DrawVectorCanvas();
		canvas1.setBounds(6,6,250,250);
		canvas1.setBackground(new Color(16777215));
		panel1.add(canvas1);

		//{{REGISTER_LISTENERS
		SymMouseMotion aSymMouseMotion = new SymMouseMotion();
		canvas1.addMouseMotionListener(aSymMouseMotion);
		SymMouse aSymMouse = new SymMouse();
		canvas1.addMouseListener(aSymMouse);
		SymAction lSymAction = new SymAction();
		buttonClear.addActionListener(lSymAction);
		canvas1.addMouseMotionListener(aSymMouseMotion);
		SymItem lSymItem = new SymItem();
		checkCoords.addItemListener(lSymItem);
		checkNumbersVisible.addItemListener(lSymItem);
		//}}
	}

	//{{DECLARE_CONTROLS
	java.awt.Label label1;
	java.awt.Label label2;
	java.awt.Panel panel1;
	java.awt.Label label4;
	java.awt.Label label5;
	java.awt.Button buttonClear;
	java.awt.Label label3;
	java.awt.Label label6;
	java.awt.Checkbox checkCoords;
	java.awt.Label label7;
	java.awt.Checkbox checkNumbersVisible;
	//}}
	DrawVectorCanvas canvas1;

    class DrawVectorCanvas extends Canvas   
    {
        public void paint(Graphics g)
        {
            poly1.draw(g);
        }
    }

	class SymMouseMotion extends java.awt.event.MouseMotionAdapter
	{
		public void mouseDragged(java.awt.event.MouseEvent event)
		{
			Object object = event.getSource();
			if (object == canvas1)
				canvas1_MouseDragged(event);
		}

		public void mouseMoved(java.awt.event.MouseEvent event)
		{
			Object object = event.getSource();
			if (object == canvas1)
				canvas1_MouseMoved(event);
		}
	}

	void canvas1_MouseMoved(java.awt.event.MouseEvent event)
	{
		//{{CONNECTION
		// Get the cursor and display its position
		// The canvas is 250 wide and high
		// The position is relative to the centre of the canvas and with up as positive Y
		int newx = event.getX()-125;
		int newy = -1*(event.getY()-125);
		label5.setText("(" + newx + "," + newy + ")");
		//}}
	}

	class SymAction implements java.awt.event.ActionListener
	{
		public void actionPerformed(java.awt.event.ActionEvent event)
		{
			Object object = event.getSource();
			if (object == buttonClear)
				buttonClear_ActionPerformed(event);
		}
	}

	void buttonClear_ActionPerformed(java.awt.event.ActionEvent event)
	{
		// to do: code goes here.
			 
		//{{CONNECTION
        poly1.clearPolygon();
        canvas1.repaint();
        //}}
	}


	void canvas1_MouseDragged(java.awt.event.MouseEvent event)
	{
		// to do: code goes here.
			 
		//{{CONNECTION
		// Get the cursor and display its position
		int newx = event.getX()-125;
		int newy = -1*(event.getY()-125);
		label5.setText("(" + newx + "," + newy + ")");
		// Find whether a point is within +/-5 of existing point
		if (currentPoint!=null) 
		{
		    if (event.getModifiers()!=java.awt.event.MouseEvent.BUTTON1_MASK)
		    {
    		    int dist = currentPoint.get_DistSQR(event.getX(), event.getY());
		        newx = event.getX()-125;
		        newy = -1*(event.getY()-125);
                poly1.moveiPointAbs(currentPoint,newx, newy);
                //poly1.updateStatus();   //As a points position has chnaged we need to update the polygons properties
                canvas1.repaint();
		    }
		}
		//}}
	}

	class SymMouse extends java.awt.event.MouseAdapter
	{
		public void mousePressed(java.awt.event.MouseEvent event)
		{
			Object object = event.getSource();
			if (object == canvas1)
				canvas1_MousePressed(event);
		}
	}

	void canvas1_MousePressed(java.awt.event.MouseEvent event)
	{
		// to do: code goes here.
			 
		//{{CONNECTION
		int newx = event.getX()-125;
		int newy = -1*(event.getY()-125);
		if (event.getModifiers()==java.awt.event.MouseEvent.BUTTON1_MASK)
		{
    		iPoint p = new iPoint(newx, newy);
    		poly1.addPoint(p);
            canvas1.repaint();
            currentPoint=null;  // Required so that a left-mouse click followed by a drag does not do anything
		}
		else
			currentPoint = poly1.getClosest(newx, newy);
		//}}
	}

	class SymItem implements java.awt.event.ItemListener
	{
		public void itemStateChanged(java.awt.event.ItemEvent event)
		{
			Object object = event.getSource();
			if (object == checkCoords)
				checkCoords_ItemStateChanged(event);
			if (object == checkNumbersVisible)
				checkNumbersVisible_ItemStateChanged(event);
		}
	}

	void checkCoords_ItemStateChanged(java.awt.event.ItemEvent event)
	{
		// to do: code goes here.
			 
		//{{CONNECTION
		// Update and Repaint the Canvas
		poly1.setCoordsVisible(checkCoords.getState());
		canvas1.repaint();
		//}}
	}
	void checkNumbersVisible_ItemStateChanged(java.awt.event.ItemEvent event)
	{
		// to do: code goes here.
			 
		//{{CONNECTION
		// Update and Repaint the Canvas
		poly1.setNumbersVisible(checkNumbersVisible.getState());
		canvas1.repaint();
		//}}
	}


}

class iPoint
{
    /*
	iPoint class
	    Description:
	    A simple class that holds the coordinates of a point, as an integer) on a 2D space
	    All the data is protected and only methods which do not effect the state of a point are public.
	    This prevents a point being created or moved within going through the polygon class. This is necessary
	    as the polygon class needs to keep track as to whether it is a simple polgon, etc.
	    Constructors:
	    This class contains overloaded constructors such that points can 
	    be created using default values or from coordinates, or even another point.
	    
	    Attributes:
	    A point only holds an X and Y integer value. 
	    
	    Methods:
	    Apart from simple get and set functions for its attributes
	    methods are provided to move the spot around in both 
	    an absolute and relative sence. A method is also available to provide the distance
	    of the square of the point from a provided point.
    */

    // coordinates of the point
    protected int x;
    protected int y;
    // Constructor to create a point from its coordinates
    protected iPoint(int xVal, int yVal)
    {
        x = xVal;
        y = yVal;
    }
    // Constructor to create a point from another point
    //  i.e. copy constructor in C++ speak
    protected iPoint(iPoint point)
    {
        x = point.getX();
        y = point.getY();
    }
    protected iPoint()         //  Construct a new point from an existing point
    {
        x = 0;
        y = 0;
    }
    public int getX(){      // Return X coordinate
        return x;}
    public int getY(){      // Return X coordinate
        return y;}
    protected void setX(int xnew){ // Set X coordinate
        x = xnew;}
    protected void setY(int ynew){ // Set Y coordinate
        y = ynew;}
    // Move the point - absolute
    protected void moveiPointAbs(int xnew, int ynew)   
    {
        x = xnew;
        y = ynew;
    }
    // Move the point - relative
    protected void moveiPointRel(int xdelta, int ydelta)
    {
        x += xdelta;
        y += ydelta;
    }
    // Return square of distance (is an integer)
    public int get_DistSQR(int xpos, int ypos)
    {
        int dist1_sqr = xpos-x;
        dist1_sqr *= dist1_sqr;     //sqr the number
        int dist2_sqr = ypos-y;
        dist2_sqr *= dist2_sqr;     //sqr the number
        return (dist1_sqr+dist2_sqr);
    }
}

class polygonClass
{
    /*
	Polygon class
	    Description:
        Essentially a vector of points with methods but also 'knows' whether it is a valid polygon or not.
        The first point also appears at the end of the vector so that the poygon is closed
        
	    Constructors:
	    A default construct sets up the object with no points
	    
	    Methods:
        NB: All methods are listed in alphabetical order
        addPoint - add points to the vector and determines whether the polygon is valid
        AreaTriangle2 - Calculates twice the area of a triangle defined by the three points provided to it as parameters
                        NB: Twice the area is always an integer. Therefore efficient integer opertaions only need to be performed
        calcArea2 - Calculates and returns the area of twice the area of the polygon (this ensures integer opertions are valid)
        checkIsConvex - Checks whether the polygon is convex and stores the result in the boolean variable isConvex
        checkIsValid - Checks whether the polygon is simple (not 'stray' intersceting edges) and stores the result in the boolean variable isValid
        clearPolygon - Remove all points from the polygon
        doIntersectProper - Test whether two lines (defined by the points a to b, and c to d) intersect properly (i.e. lines cross completely). 
                            (from 'Computational Geometry in C', J. O'Rourke, code 1.6)
        doIntersect - Test whether lines (defined by the points a to b, and c to d) intersect either properly or improperly 
                        (i.e. lines cross completely or just touch at one point.) 

        draw     - Draws the polygon, on the graphics object provided as a parameter, following the sequence of points along the vector 
                   and cycles back to the first point. Spots represent the nodes.
                   Prints point coordinates and numbers if require
        getArea2 - Returns the area of the polygon          
        getClosest - Finds the point closest to a provided coordinate pair
        getSize - returns the size of the vector holding the polygon (recall the first point is held twice)
        isBetween - determines whether a point (c) is between to other points (a,b)
        isColinear - determines whether three points are colinear
        isLeft - detrmines whether a third point is strictly left of an line defined by the first two points
        isLeftOn - detrmines whether a third point is is on or left of an line defined by the first two points
        isValidPolygon - returns whether the polygon is a simple polygon
        setCoordsVisible - sets the coodinate visibility flag to the provided boolean
        setNumbersVisible - sets the point label numbers visibility flag to the provided boolean
        updateStatus - Updates the variables holding the area*2, convexity booelan and simple polygon flag
    */
    Vector vect; // This holds the points of the polygon. Note the first point will be stored twise. Once at the beginning and once at the end.
    boolean isValid;        // Holds whether the polygon is a valid simple polygon
    boolean isConvex;       // Holdswhether the polygon is fully convex
    boolean coordsVisible;  // Holds whether the coordinates should be printed next to the points
    boolean numbersVisible;  // Holds whether the number of the point should be printed next to the points
    int area2;               // Holds the area of the polygon
    
    public polygonClass()    //Create an empty vector
    {
        vect = new Vector();
        isValid = false;
        isConvex = false;
    }

    public void addPoint(iPoint point)
    { 
        // Reset the final point to the new point and add a point 
        // then test the validity of polygon
        if (vect.size()>0)
        {
            vect.setElementAt(point,vect.size()-1);   // Change the last point to current
            vect.addElement(vect.elementAt(0));     // Add first point as last again
        }
        else    // No points exist so we need to create two for the start and end
        {
            vect.addElement(point);     // Add point as start
            vect.addElement(point);     // and as the end
        }
        updateStatus();     // As the polygon has changed the properties of the polygon (area2, convexity, validity) need updating
    }

    protected int AreaTriangle2(iPoint a, iPoint b, iPoint c)
    {
        // returns twice the signed area of the triangle,m positive is ccw from a to b to c
        return  a.getX()*b.getY()-a.getY()*b.getX() +
                a.getY()*c.getX()-a.getX()*c.getY() +
                b.getX()*c.getY()-c.getX()*b.getY(); 
    }
    
    protected void calcArea2()
    {
        if (vect.size()<3) 
        {
            area2=0;
            return;
        }
        int sum = 0;  // Partial sum
        // Recall the last point is stroed twice threfore only need to evalated up to n-2 rather than n-1 as on p. 26
        // of Rourke's book.
        for (int i = 0; i<vect.size()-2; i++)
            sum += AreaTriangle2((iPoint)vect.elementAt(0), (iPoint)vect.elementAt(i),(iPoint)vect.elementAt(i+1));
        area2=sum;
    }
    
    protected void checkIsConvex()
    {
        // If there exists a right turn the polygon is not convex
        for (int i = 0; i < vect.size()-2; i++)
            if (isLeftOn((iPoint)vect.elementAt(i),(iPoint)vect.elementAt(i+1),(iPoint)vect.elementAt(i+2))==false)
            {    
                isConvex=false;
                return;
            }
        // Finally test the corner around the first point.        
            if (isLeftOn((iPoint)vect.elementAt(vect.size()-2),(iPoint)vect.elementAt(0),(iPoint)vect.elementAt(1))==false)   
            {
                isConvex=false;
                return;
            }
        isConvex=true;
    }

    public void checkIsValid()
    {
        // Determine whether the polygon is valid
        // First there must be at least three points (i.e. four coordinates as the first is stored twise)
        if (vect.size()<4)
            isValid = false;
        else
        {
            // There can be no intersecting lines.
            // Assume no lines cross and then test for any crosses (below)
            // The check needs to be made for each pair of non-connected lines
            // So start with the first line and check the lines from points 2-to-3, 3-to-4, ... (n-2)-to-(n-1)
            // However as the first point is also stored as the end and there is a need for a test to catch this loop back
            //  as the first and last lines must touch as they have a common point
            //  This could be handled by treating the first or last as a special case but the test below ensures there is no problem.            
            isValid=true;
            for (int i=0; i<vect.size()-1;i++)
                for (int j=i+2; j<vect.size()-1;j++)
                  if (!((i==0)&&(j==vect.size()-2)))
                    if ((doIntersect((iPoint)vect.elementAt(i),(iPoint)vect.elementAt(i+1),(iPoint)vect.elementAt(j),(iPoint)vect.elementAt(j+1)))==true)
                    {
                        isValid = false;
                        return;
                    }
        }
    }
    
    public void clearPolygon()    //Remove all points
    {
        vect.removeAllElements();
        isValid = false;
    }

    protected boolean doIntersectProper(iPoint a, iPoint b, iPoint c, iPoint d)
    {
        // Test whether lines intersect properly (i.e. lines cross completely). 
        // (from 'Computational Geometry in C', J. O'Rourke, ode 1.6)
        if ((isColinear(a,b,c))||(isColinear(a,b,d))||(isColinear(c,d,a))||(isColinear(c,d,b)))
            return false;
        else
            return (isLeft(a,b,c)^isLeft(a,b,d)) && (isLeft(c,d,a)^isLeft(c,d,b));
    }
            
    protected boolean doIntersect(iPoint a, iPoint b, iPoint c, iPoint d)
    {
        // Test whether lines intersect properly or improperly (
        // i.e. lines cross completely or just touch at one point). 
        // (from 'Computational Geometry in C', J. O'Rourke, ode 1.6)
        if ((doIntersectProper(a,b,c,d)))
            return true;
        else if (    isBetween(a,b,c)
                  || isBetween(a,b,d)
                  || isBetween(c,d,a)
                  || isBetween(c,d,b)
                )
                return true;
        else    return false;
    }

    public void draw(Graphics g)
    {
        if (vect.size()==0)
        {
            g.drawString("Please draw a polygon", 5, 25);
            return;
        }
        else
        {
            //draw all points as spots
            for(int i=0; i<vect.size()-1;i++) // No beed to draw last/last point twice
            {
                iPoint p = (iPoint)vect.elementAt(i);
                int newx = p.getX()+125;
                int newy = 125-p.getY();
                g.fillOval(newx-3,newy-3,5,5);  
                //g.fillOval(p.getX()-3,p.getY()-3,5,5);
            }
            // Draw the last spot as red
            g.setColor(Color.red);
            iPoint p = (iPoint)vect.elementAt(vect.size()-1);
            int newx = p.getX()+125;
            int newy = 125-p.getY();
            g.fillOval(newx-3,newy-3,5,5);   
            g.setColor(Color.black);
            // Draw the existing points in the vector
            for(int i = 0; i < vect.size()-2; i++)
            {
                iPoint p1=(iPoint)(vect.elementAt(i));
                iPoint p2 =(iPoint)(vect.elementAt(i+1));
                int newx1 = p1.getX()+125;
                int newy1 = 125-p1.getY();
                int newx2 = p2.getX()+125;
                int newy2 = 125-p2.getY();
                g.drawLine(newx1,newy1,newx2,newy2);
            }
            // The last line and point are to appear in red hence require special code
            g.setColor(Color.red);
            iPoint p1=(iPoint)(vect.elementAt(vect.size()-2));
            iPoint p2 =(iPoint)(vect.elementAt(vect.size()-1));
            int newx1 = p1.getX()+125;
            int newy1 = 125-p1.getY();
            int newx2 = p2.getX()+125;
            int newy2 = 125-p2.getY();
            g.drawLine(newx1,newy1,newx2,newy2);
            g.setColor(Color.black);
        
            // Determine whether points coordinates should be drawn and do so if required
            if (coordsVisible)
                for (int i=0; i<vect.size(); i++)
                {
                    p = (iPoint)vect.elementAt(i);
                    newx1 = p.getX()+125;
                    newy1=125-p.getY();
                    g.drawString("(" + p.getX() + "," + p.getY() + ")", newx1+5,newy1); 
                }
            // Determine whether point numbers should be drawn and do so if required
            if (numbersVisible)
            {
                for (int i=0; i<vect.size()-1; i++)
                {
                    p = (iPoint)vect.elementAt(i);
                    newx1 = p.getX()+125;
                    newy1=125-p.getY();
                    // Offset x by 5 to right and Y 12 down so they don't overlap the point or the coordinates
                    g.drawString(""+i, newx1+5,newy1+12); 
                }
            }
        }
        if (isValid) 
        {
            g.drawString("2*Area = " + this.getArea2(), 5,30);
            g.drawString("Simple Polygon", 5,15);
            if (isConvex)
                g.drawString("(Convex)",100,15);
            else
                g.drawString("(Non-Convex)",100,15);
        }
        else
        {
            g.drawString("2*Area = " + this.getArea2() + " invalid", 5,30);
            g.drawString("None Simple Polygon", 5,15);
        }
    }    
    
    public int getArea2()
    {
        return area2;
    }
    
    public iPoint getClosest(int x, int y)
    {
        if (vect.size()<2) return null;
        iPoint point = (iPoint)vect.elementAt(0);
        int dist1_sqr = point.getX()-x;
        dist1_sqr *= dist1_sqr;     //sqr the number
        int dist2_sqr = point.getY()-y;
        dist2_sqr *= dist2_sqr;     //sqr the number
        int dist_sqr = dist1_sqr + dist2_sqr;
        for (int i=1; i<vect.size(); i++)
        {
            iPoint tempPoint = (iPoint)vect.elementAt(i);
            dist1_sqr = tempPoint.getX()-x;
            dist1_sqr *= dist1_sqr;     //sqr the number
            dist2_sqr = tempPoint.getY()-y;
            dist2_sqr *= dist2_sqr;     //sqr the number
            if ((dist1_sqr+dist2_sqr)<dist_sqr)
            {
                dist_sqr = dist1_sqr+dist2_sqr;
                point = (iPoint)vect.elementAt(i);
            }
        }
        return point;
    }
    
    public int getSize()
    {
        return vect.size();
    }
    
    protected boolean isBetween(iPoint a, iPoint b, iPoint c)
    {
        if (isColinear(a,b,c)==false)
            return false;
        // If line a-b not vertical check betweenness on x else on y
        if (a.getX() != b.getX())
            return ((a.getX()<=c.getX())&&(c.getX()<=b.getX())) || ((a.getX()>=c.getX())&&(c.getX()>=b.getX()));
        else
            return ((a.getY()<=c.getY())&&(c.getY()<=b.getY())) || ((a.getY()>=c.getY())&&(c.getY()>=b.getY()));
    }
    
    protected boolean isColinear(iPoint p1, iPoint p2, iPoint p3)
    {
        if (AreaTriangle2(p1,p2,p3)==0)
            return true;
        else
            return false;
    }
 
    protected boolean isLeft(iPoint p1, iPoint p2, iPoint p3)
    {
        if (AreaTriangle2(p1,p2,p3)>0)
            return true;
        else
            return false;
    }
    
    protected boolean isLeftOn(iPoint p1, iPoint p2, iPoint p3)
    {
        if (AreaTriangle2(p1,p2,p3)>=0)
            return true;
        else
            return false;
    }

    public boolean isValidPolygon()
    {
        return isValid;
    }
    
    public void moveiPointAbs(iPoint point, int newx, int newy)
    {
        point.moveiPointAbs(newx, newy);
        updateStatus();   //As a points position has chnaged we need to update the polygons properties
    }
    
    public void setCoordsVisible(boolean bool)
    {
        coordsVisible = bool;
    }
    public void setNumbersVisible(boolean bool)
    {
        numbersVisible = bool;
    }
    public void updateStatus()
    {
        checkIsValid();     // Check whether the polygon is still simple
        checkIsConvex();    // Check whether its still a valid convex poygon
        calcArea2();        // Recalculate its (area*2)
    }
}
