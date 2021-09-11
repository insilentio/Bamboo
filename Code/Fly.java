package Bamboo;

import java.awt.Color;
import java.awt.*;

class Fly extends Creature implements Runnable{

  public Fly( int x, int y, int size, int speed, String name, Color color) {
    this.setSize( size );
    this.setSpeed( speed );
    this.setName( name );
    this.setPosition( position );
    this.setColor ( color );
    flyThread = new Thread( this );
  }

  public void run(){
    while ( true ){
      this.setPosition(this.calcNewX(this.position));
      this.setPosition(this.calcNewY(this.position));
      System.out.println("lasdfjk");
      Field.f.update(Field.f.getGraphics());
      try{
        flyThread.sleep(this.readSpeed());
      }
      catch ( InterruptedException e ){
        System.out.println(e);
      }
    }
  }

   Position calcNewX(Position p){
    p.setOldX(p.readNewX());
    if ( p.readNewY() == Field.DELTABORDER){
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
  }

  Position calcNewY(Position p){
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
  }

  public Position position = new Position(Field.DELTABORDER, Field.DELTABORDER, Field.DELTABORDER, Field.DELTABORDER);
  Thread flyThread;
}

