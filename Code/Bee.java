package Bamboo;

import java.awt.Color;

class Bee extends Creature implements Runnable {

	Thread beeThread;
	private boolean toRight;
	private boolean toBottom;

	//constructor
	public Bee( int x, int y, int size, int speed, String name, Color color ) {
		super.position = new Position(x, y, x, y);
		toRight = true;
		toBottom = true;
		beeThread = new Thread( this );
		this.setSize(size);
		this.setSpeed(speed);
		this.setName( name );
		this.setPosition( position );
		this.setColor ( color );
	}

	public void run(){
		while ( true ){
			this.setPosition(calcNewX(this.position));
			this.setPosition(calcNewY(this.position));
			Field.f.update(Field.f.getGraphics());
			try{
				beeThread.sleep(this.readSpeed());
			}
			catch ( InterruptedException e ){
				System.out.println(e);
			}
		}
	}

	Position calcNewX(Position p){
		p.setOldX( p.readNewX());
		if ( this.toRight == true ){
			p.setNewX(p.readOldX() + Field.DELTA);
			if ( p.readNewX() >= Field.BOARDWIDTH + Field.DELTABORDER - this.readSize() ){
				p.setNewX( 2 * Field.BOARDWIDTH + 2 * Field.DELTABORDER - p.readNewX()  - 2 * this.readSize());
				this.toRight = false;
				return p;
			}
		}
		else{
			p.setNewX( p.readOldX() - Field.DELTA);
			if ( p.readNewX() <= Field.DELTABORDER ){
				p.setNewX( 2 * Field.DELTABORDER - p.readNewX());
				this.toRight = true;
				return p;
			}
		}
		return p;
	}

	Position calcNewY(Position p){
		p.setOldY( p.readNewY());
		if ( this.toBottom == true ){
			p.setNewY( p.readOldY() + Field.DELTA);
			if ( p.readNewY() >= Field.BOARDHEIGHT + Field.DELTABORDER - this.readSize() ){
				p.setNewY( 2 * Field.BOARDHEIGHT + 2 * Field.DELTABORDER - p.readNewY() - 2 * this.readSize());
				this.toBottom = false;
				return p;
			}
		}
		else{
			p.setNewY( p.readOldY() - Field.DELTA);
			if ( p.readNewY() <= Field.DELTABORDER ){
				p.setNewY( 2 * Field.DELTABORDER - p.readNewY());
				this.toBottom = true;
				return p;
			}
		}
		return p;
	}
}