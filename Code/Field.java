package Bamboo;

import java.util.Vector;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JApplet;

public class Field extends JApplet implements MouseListener, KeyListener {

	//fields and variables
	static Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int WINDOWWIDTH = d.width;
	public static final int WINDOWHEIGHT = d.height;
	public static final int DELTABORDER = 50;
	public static final int BOARDWIDTH = WINDOWWIDTH - 2 * DELTABORDER;
	public static final int BOARDHEIGHT = WINDOWHEIGHT - 3 * DELTABORDER;
	static final int DELTA = 5; //used in Spider and Bee
	public static Field f;
	static OurPolygon ourPolygon;
	static Point[] originPoints = new Point[4];
//	public static Vector colorVector = new Vector();
//	public static int colorIndex = 0;

	Creature.Position pos0, pos1, pos2;
	public Node Lines;
	Image imageBuffer;
	Graphics graphicsBuffer;
	Bee bee, bee1, bee2;
	Spider spider;
	Color white, red, blue, green, yellow, black;

	//Construct the applet
	public Field() {
		f = this;
		Lines = new Node(0, 0, 0, 0);
		white = Color.white;
		red = Color.red;
		blue = Color.blue;
		green = Color.green;
		yellow = Color.yellow;
		black = Color.black;
/*		colorVector.addElement(white);
		colorVector.addElement(red);
		colorVector.addElement(blue);
		colorVector.addElement(green);
		colorVector.addElement(yellow);
		colorVector.addElement(black);
*/
		this.originPoints[0] = new Point(100, 100);
		this.originPoints[1] = new Point(750, 100);
		this.originPoints[2] = new Point(750, 550);
//		this.originPoints[3] = new Point(200, 550);
//		this.originPoints[4] = new Point(200, 550);
		this.originPoints[3] = new Point(100, 550);
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.ourPolygon = new OurPolygon(originPoints);
	}

	//Initialize the applet
	public void init() {

		this.setSize(d);
		/*imageBuffer = createImage(WINDOWWIDTH, WINDOWHEIGHT);
		graphicsBuffer = imageBuffer.getGraphics();
		*/
		spider = new Spider(this.originPoints[0].x, this.originPoints[0].y, /*Field.DELTABORDER, Field.DELTABORDER,*/ 3, 4, "the spider", Color.black);
		spider.spiderThread.start();

		bee = new Bee(Field.BOARDWIDTH / 2, Field.BOARDHEIGHT / 5, 7, 50, "A", Color.green.darker().darker());
		bee1 = new Bee(Field.BOARDWIDTH / 4, Field.BOARDHEIGHT / 2, 5, 40, "B", Color.red);
		bee2 = new Bee(Field.BOARDWIDTH / 8, Field.BOARDHEIGHT / 2, 9, 30, "C", Color.blue);

		bee.beeThread.start();
		bee1.beeThread.start();
		bee2.beeThread.start();
	}

	public void destroy(){
		System.exit(0);
	}

	public void paint(Graphics g){
		//super.paintComponents(g);
		g.setColor(black);
		g.drawRect(DELTABORDER-1, DELTABORDER-1, BOARDWIDTH+1, BOARDHEIGHT+1);
		g.drawPolygon(ourPolygon.actualPolygon);
	}

	public void update(Graphics g){
		pos0 =  bee.readPosition();
		pos1 = bee1.readPosition();
		pos2 = bee2.readPosition();

		g.setColor(white);
		g.fillOval(pos0.readOldX(), pos0.readOldY(), bee.readSize(), bee.readSize());
		g.setColor(this.bee.readColor());
		g.fillOval(pos0.readNewX(), pos0.readNewY(), bee.readSize(), bee.readSize());

		g.setColor(white);
		g.fillOval(pos1.readOldX(), pos1.readOldY(), bee1.readSize(), bee1.readSize());
		g.setColor(this.bee1.readColor());
		g.fillOval(pos1.readNewX(), pos1.readNewY(), bee1.readSize(), bee1.readSize());

		g.setColor(white);
		g.fillOval(pos2.readOldX(), pos2.readOldY(), bee2.readSize(), bee2.readSize());
		g.setColor(this.bee2.readColor());
		g.fillOval(pos2.readNewX(), pos2.readNewY(), bee2.readSize(), bee2.readSize());

		g.setColor(white);
		g.fillRect(spider.position.readOldX() - spider.readSize(), spider.position.readOldY() - spider.readSize(), spider.readSize() * 2, spider.readSize() * 2 );
		g.setColor(this.spider.readColor());
		g.fillRect(spider.position.readNewX() - spider.readSize() , spider.position.readNewY() - spider.readSize(), spider.readSize() * 2, spider.readSize() * 2);

		this.paint(g);
	}

	//event handling
	public void mousePressed(MouseEvent e){} //not needed

	public void mouseReleased(MouseEvent e){} //not needed

	public void mouseEntered(MouseEvent e){} //not needed

	public void mouseExited(MouseEvent e){} //not needed

	public void mouseClicked(MouseEvent e){} //not needed

	public void keyTyped(KeyEvent e){} //not needed

	public void keyPressed(KeyEvent e){
		if (spider.isVulnerable == false){
			spider.isVulnerable = true;
		}
//if point already exists --> don't add anymore; following implementation doesn't work yet
		Integer rox = new Integer(spider.position.readOldX());
		Integer npx = new Integer(spider.nextPointX);
		Integer roy = new Integer(spider.position.readOldY());
		Integer npy = new Integer(spider.nextPointY);
		System.out.print("rox: "+ rox);
		System.out.print("  npx: "+ npx);
		System.out.print("  roy: "+ roy);
		System.out.println("  npy: "+ npy);
		if (rox.compareTo(npx) != 0 || roy.compareTo(npy) != 0)
			this.ourPolygon.polygonVector.insertElementAt((new Point(spider.position.readOldX(), spider.position.readOldY())), spider.indexInPolygon);
		this.ourPolygon.vector2Polygon();
		spider.indexInPolygon++;
//just for debugging; can be deleted
		if (spider.position.readOldX() <50 || spider.position.readOldX() >750 || spider.position.readOldY() < 50 || spider.position.readOldY() >550)
			System.out.print('a');
//----------------------------------
	}

	public void keyReleased(KeyEvent e){} //not needed
}