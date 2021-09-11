package Bamboo;

import java.awt.*;
import java.applet.*;
import java.awt.event.*;

public class Field extends Applet implements MouseListener, KeyListener {
  public static final int WINDOWWIDTH = 800;
  public static final int WINDOWHEIGHT = 600;
  public static final int DELTABORDER = 50;
  public static final int DELTA = 1;
  public static final int BOARDHEIGHT = WINDOWHEIGHT - 2 * DELTABORDER;
  public static final int BOARDWIDTH = WINDOWWIDTH - 2 * DELTABORDER;
  public static Field f;

  Position pos0, pos1, pos2;

  public Node Lines;

  Image imageBuffer;
  Graphics graphicsBuffer;

  int firstX = 0;
  int firstY = 0;

  Spider spider, spider1, spider2;
  Fly fly;
  Color white, red, blue, green, yellow, black;
  boolean isFirst = true;

  //Construct the applet
  public Field() {
    f = this;
    Lines = new Node(0, 0, 0, 0);
    white  =  Color.white;
    red  =    Color.red;
    blue  =   Color.blue;
    green =   Color.green;
    yellow =  Color.yellow;
    black =   Color.black;
    this.addKeyListener(this);
    this.addMouseListener(this);
  }

  //Initialize the applet
  public void init() {

    this.setSize(new Dimension(WINDOWWIDTH, WINDOWHEIGHT));
    /*imageBuffer = createImage(WINDOWWIDTH, WINDOWHEIGHT);
    graphicsBuffer = imageBuffer.getGraphics();
    */
    fly = new Fly(Field.DELTABORDER, Field.DELTABORDER, 3, 15, "the fly", Color.black);
    fly.flyThread.start();

    spider = new Spider(Field.BOARDWIDTH / 2, Field.BOARDHEIGHT / 5, 7, 10, "A", Color.green);
    spider1 = new Spider(Field.BOARDWIDTH / 4, Field.BOARDHEIGHT / 2, 5, 20, "B", Color.red);
    spider2 = new Spider(Field.BOARDWIDTH / 8, Field.BOARDHEIGHT / 2, 9, 5, "C", Color.blue);

    spider.spiderThread.start();
    spider1.spiderThread.start();
    spider2.spiderThread.start();
  }

  public void paint(Graphics g){
    g.drawRect( DELTABORDER-1, DELTABORDER-1, BOARDWIDTH+1, BOARDHEIGHT+1 );
  }

  public void update(Graphics g){
    pos0 =  spider.readPosition();
    pos1 = spider1.readPosition();
    pos2 = spider2.readPosition();

    g.setColor(white);
    g.fillOval(pos0.readOldX(), pos0.readOldY(), spider.readSize(), spider.readSize());
    g.setColor(this.spider.readColor());
    g.fillOval(pos0.readNewX(), pos0.readNewY(), spider.readSize(), spider.readSize());

    g.setColor(white);
    g.fillOval(pos1.readOldX(), pos1.readOldY(), spider1.readSize(), spider1.readSize());
    g.setColor(this.spider1.readColor());
    g.fillOval(pos1.readNewX(), pos1.readNewY(), spider1.readSize(), spider1.readSize());

    g.setColor(white);
    g.fillOval(pos2.readOldX(), pos2.readOldY(), spider2.readSize(), spider2.readSize());
    g.setColor(this.spider2.readColor());
    g.fillOval(pos2.readNewX(), pos2.readNewY(), spider2.readSize(), spider2.readSize());

    g.setColor(white);
    g.fillRect(fly.position.readOldX() - fly.readSize(), fly.position.readOldY() - fly.readSize(), fly.readSize() * 2, fly.readSize() * 2 );
    g.setColor(this.fly.readColor());
    g.fillRect(fly.position.readNewX() - fly.readSize() , fly.position.readNewY() - fly.readSize(), fly.readSize() * 2, fly.readSize() * 2);

    g.setColor(black);
    g.drawRect( DELTABORDER-1, DELTABORDER-1, BOARDWIDTH+1, BOARDHEIGHT+1 );
  }

  public void mousePressed(MouseEvent e){}

  public void mouseReleased(MouseEvent e){}

  public void mouseEntered(MouseEvent e){}

  public void mouseExited(MouseEvent e){}

  public void mouseClicked(MouseEvent e){
    if (isFirst == true){
      firstX = fly.position.readNewX();
      firstY = fly.position.readNewY();
      isFirst = false;
    }
    else{
      Field.f.Lines.add(new Node(fly.position.readNewX(), fly.position.readNewY(), firstX, firstY));
      System.out.println("Linie " + " " +  fly.position.readNewX() + "/" +  fly.position.readNewY() + ", " +  firstX + "/" + firstY + " hinzugfuegt");
      this.isFirst = true;
    }
  }

   public void keyTyped(KeyEvent e){
    if (isFirst == true){
      firstX = fly.position.readNewX();
      firstY = fly.position.readNewY();
      isFirst = false;
    }
    else{
      Field.f.Lines.add(new Node(fly.position.readNewX(), fly.position.readNewY(), firstX, firstY));
      System.out.println("Linie " + " " +  fly.position.readNewX() + "/" +  fly.position.readNewY() + ", " +  firstX + "/" + firstY + " hinzugfuegt");
      this.isFirst = true;
    }
  }


   public void keyPressed(KeyEvent e){
    if (isFirst == true){
      firstX = fly.position.readNewX();
      firstY = fly.position.readNewY();
      isFirst = false;
    }
    else{
      Field.f.Lines.add(new Node(fly.position.readNewX(), fly.position.readNewY(), firstX, firstY));
      System.out.println("Linie " + " " +  fly.position.readNewX() + "/" +  fly.position.readNewY() + ", " +  firstX + "/" + firstY + " hinzugfuegt");
      this.isFirst = true;
    }
  }


   public void keyReleased(KeyEvent e){
    if (isFirst == true){
      firstX = fly.position.readNewX();
      firstY = fly.position.readNewY();
      isFirst = false;
    }
    else{
      Field.f.Lines.add(new Node(fly.position.readNewX(), fly.position.readNewY(), firstX, firstY));
      System.out.println("Linie " + " " +  fly.position.readNewX() + "/" +  fly.position.readNewY() + ", " +  firstX + "/" + firstY + " hinzugfuegt");
      this.isFirst = true;
    }
  }

}


class Position{

  private int OldX, OldY, NewX, NewY;

  public Position(int ox, int oy, int nx, int ny){
    OldX = ox;
    OldY = oy;
    NewX = nx;
    NewY = ny;
  }

  void setOldX ( int ox ){
    this.OldX = ox;
  }

  int readOldX (){
    return this.OldX;
  }

  void setOldY ( int oy ){
    this.OldY = oy;
  }

  int readOldY (){
    return this.OldY;
  }

  void setNewX ( int nx ){
    this.NewX = nx;
  }

  int readNewX (){
    return this.NewX;
  }

  void setNewY ( int ny ){
    this.NewY = ny;
  }

  int readNewY (){
    return this.NewY;

  }
}
