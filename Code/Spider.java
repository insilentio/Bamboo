package Bamboo;

import java.awt.*;
import java.util.Vector;
import java.applet.*;

class Spider extends Creature implements Runnable {

	//fields
	boolean isVulnerable = false;
	Thread spiderThread;
	boolean isFirst = true;
	int firstX = 0;
	int firstY = 0;
	int indexInPolygon = 1;
	int passedPointX = Field.ourPolygon.getPoint(indexInPolygon - 1).x;
	int passedPointY = Field.ourPolygon.getPoint(indexInPolygon - 1).y;
	int nextPointX = Field.ourPolygon.getPoint(indexInPolygon).x;
	int nextPointY = Field.ourPolygon.getPoint(indexInPolygon).y;
	String direction = "East";

	//constructor
	public Spider( int x, int y, int size, int speed, String name, Color color) {
		super.position = new Position(x, y, x, y);
		this.setSize( size );
		this.setSpeed( speed );
		this.setName( name );
		this.setPosition( position );
		this.setColor ( color );
		spiderThread = new Thread( this );
	}

	public void run(){
		while ( true ){
			this.setPosition(this.calcNewPos(this.position));
			//this.setPosition(this.calcNewY(this.position));
			Field.f.update(Field.f.getGraphics());
			try{
				spiderThread.sleep(this.readSpeed());
			}
			catch ( InterruptedException e ){
				System.out.println(e);
			}
		}
	}

	Position calcNewPos(Position p){
		p.setOldX(p.readNewX());
		p.setOldY(p.readNewY());

		if ( this.direction == "North" ){
			p.setNewY(p.readOldY()+1);
		}

		if (this.direction == "East" ){
			p.setNewX(p.readOldX()+1);
		}

		if ( this.direction == "South" ){
			p.setNewY(p.readOldY()-1);
		}

		if (this.direction == "West" ){
			p.setNewX(p.readOldX()-1);
		}

		if ( p.readNewX() == this.nextPointX && p.readNewY() == this.nextPointY ){
			if ( indexInPolygon < (Field.ourPolygon.getLength() - 1)){
				this.indexInPolygon++;
			}
			else{
				this.indexInPolygon = 0;
			}

			this.passedPointX = this.nextPointX;
			this.passedPointY = this.nextPointY;
			this.nextPointX = Field.ourPolygon.getPoint(indexInPolygon).x;
			this.nextPointY = Field.ourPolygon.getPoint(indexInPolygon).y;

			if ( this.passedPointX < this.nextPointX )
				this.direction = "East";
			if ( this.passedPointX > this.nextPointX )
				this.direction = "West";
			if ( this.passedPointY < this.nextPointY )
				this.direction = "North";
			if ( this.passedPointY > this.nextPointY )
				this.direction = "South";
		}
		return p;
	}

	/*if ( p.readNewY() == Field.DELTABORDER){
		p.setNewX(p.readNewX() + Field.DELTA);
		if ( p.readNewX() > Field.BOARDWIDTH + Field.DELTABORDER ){
		p.setNewX(Field.BOARDWIDTH);
		}
	}

	if ( p.readNewY() == Field.BOARDHEIGHT + Field.DELTABORDER){
		p.setNewX(p.readNewX() - Field.DELTA);
		if ( p.readNewX() < Field.DELTABORDER ){
		p.setNewX(Field.BOARDWIDTH);
		}
	}
	return p;
	}*/

	/*Position calcNewY(Position p){
	p.setOldY(p.readNewY());
	if ( p.readNewX() == Field.DELTABORDER){
		p.setNewY(p.readNewY() - Field.DELTA);
		if ( p.readNewY() > Field.BOARDHEIGHT + Field.DELTABORDER ){
		p.setNewY(Field.BOARDHEIGHT);
		}
	}

	if ( p.readNewX() == Field.BOARDWIDTH + Field.DELTABORDER){
		p.setNewY(p.readNewY() + Field.DELTA);
		if ( p.readNewY() < Field.DELTABORDER ){
		p.setNewY(Field.BOARDHEIGHT);
		}
	}
	return p;
	}*/
}