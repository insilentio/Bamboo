package Bamboo;

import java.awt.*;
import java.util.Vector;

/**
 * Überschrift:   Bamboo
 * Beschreibung:  Your description
 * Copyright:     Copyright (c) 1999
 * Organisation:  privat
 * @author Simon Zürcher
 * @version
 */

public class OurPolygon /*extends Polygon*/ {

	static Vector polygonVector = new Vector();
//	static Vector polygonHolder = new Vector(1);
	static Polygon actualPolygon = new Polygon();

	public OurPolygon(Point[] points) {
		for (int i = 0; i < points.length; i++)
			this.polygonVector.addElement(points[i]);
/*		this.polygonVector.addElement(points[1]);
		this.polygonVector.addElement(points[2]);
		this.polygonVector.addElement(points[3]);
*/		this.vector2Polygon();
	}

	void vector2Polygon(){
		Polygon tempPolygon = new Polygon();
		this.actualPolygon = tempPolygon;
		//this.actualPolygon = new Polygon();
		int i = 0;
		Point p;

		while (polygonVector.size() > i)
		{
			p = (Point)(polygonVector.elementAt(i));
			this.actualPolygon.addPoint(p.x, p.y);//point2IntX(point), point2IntY(point));
			i++;
		}

		//this.polygonHolder.removeAllElements();
		//this.polygonHolder.addElement(actualPolygon);
	}
/*
	int point2IntX(Point p){
		return p.x;
	}

	int point2IntY(Point p){
		return p.y;
	}
*/
	Point getPoint(int i){
		int x,y;
		x = this.actualPolygon.xpoints[i];
		y = this.actualPolygon.ypoints[i];
		//x = ((Polygon)(polygonHolder.elementAt(0))).xpoints[i];
		//y = ((Polygon)(polygonHolder.elementAt(0))).ypoints[i];
		return new Point(x, y);
	}

	int getLength(){
		return this.actualPolygon.npoints;
		//return ((Polygon)(polygonHolder.elementAt(0))).npoints;
	}

}