package Bamboo;

import java.awt.Image;

public class Node {

  public Node(int x, int y, int x2, int y2) {
    this.setX(x);
    this.setY(y);
    this.setX2(x2);
    this.setY2(y2);
    this.setName(name);
  }

  void setName(String n){
    this.name = n;
  }

  String getName(){
    return this.name;
  }

  void setLeft(Node k){
    this.left = k;
  }

  void setRight(Node k){
    this.right = k;
  }

  Node getLeft(){
    return this.left;
  }

  Node getRight(){
    return this.right;
  }

  boolean hasMoreLeft(){
    return this.left != null;
  }

  boolean hasMoreRight(){
    return this.right != null;
  }

  void setX(int xPos){
    this.x = xPos;
  }

  int getX(){
    return this.x;
  }

  void setY(int yPos){
    this.y = yPos;
  }

  int getY(){
    return this.y;
  }

  void setX2(int x2Pos){
    this.x2 = x2Pos;
  }

  int getX2(){
    return this.x2;
  }

  void setY2(int y2Pos){
    this.y2 = y2Pos;
  }

  int getY2(){
    return this.y2;
  }

  void add(Node k){
    this.countAdd++;
    if (this.countAdd % 2 == 1){
      if (k.getX() <= this.getX()){
        if (this.hasMoreLeft() == false){
          this.setLeft(k);
        }
        else{
          this.getLeft().add(k);
        }
      }
      else{
        if (this.hasMoreRight() == false){
          this.setRight(k);
        }
        else{
          this.getRight().add(k);
        }
      }
    }
    else{
      if (k.getY() <= this.getY()){
        if (this.hasMoreLeft() == false){
          this.setLeft(k);
        }
        else{
          this.getLeft().add(k);
        }
      }
      else{
        if (this.hasMoreRight() == false){
          this.setRight(k);
        }
        else{
          this.getRight().add(k);
        }
      }
    }
    this.countAdd = 0;
  }

  Node getNode(int i, int j){

    int actualNodeX = this.getX();
    int actualNodeY = this.getY();

    if (actualNodeX == i && actualNodeY == j){
      this.countGet = 0;
      return this;
    }
    countGet++;
    if(this.countGet % 2 == 1){
      if (i > actualNodeX){
        if (this.getRight() != null)
          thisNode = this.getRight().getNode(i, j);
        else{
          System.out.println("get: No existing Node " + i + ", " + j);
          return null;
        }
      }
      else{
        if (this.getLeft() != null)
          thisNode = this.getLeft().getNode(i, j);
        else{
          System.out.println("get: No existing Node " + i + ", " + j);
          return null;
        }
      }
    }
    else{
      if (j > actualNodeY){
        if (this.getRight() != null)
          thisNode = this.getRight().getNode(i, j);
        else{
          System.out.println("get: No existing Node " + i + ", " + j);
          return null;
        }
      }
      else{
        if (this.getLeft() != null)
          thisNode = this.getLeft().getNode(i, j);
        else{
          System.out.println("get: No existing Node " + i + ", " + j);
          return null;
        }
      }
    }
    this.countGet = 0;
    return thisNode;
  }

  /*Node changeImage(int i, int j, Image setImg){
    int actualNodeX = this.getX();
    int actualNodeY = this.getY();

    if (actualNodeX == i && actualNodeY == j){
      this.setImage(setImg);
      this.countChange = 0;
      return this;
    }

    countChange++;
    if(this.countChange % 2 == 1){
      if (i > actualNodeX){
        if (this.getRight() != null)
          thisNode = this.getRight().changeImage(i, j, img);
        else{
          System.out.println("change: No existing Node " + i + ", " + j);
          return null;
        }
      }
      else{
        if (this.getLeft() != null)
          thisNode = this.getLeft().changeImage(i, j, img);
        else{
          System.out.println("change: No existing Node " + i + ", " + j);
          return null;
        }
      }
    }
    else{
      if (j > actualNodeY){
        if (this.getRight() != null)
          thisNode = this.getRight().changeImage(i, j, img);
        else{
          System.out.println("change: No existing Node " + i + ", " + j);
          return null;
        }
      }
      else{
        if (this.getLeft() != null)
          thisNode = this.getLeft().changeImage(i, j, img);
        else{
          System.out.println("change: No existing Node " + i + ", " + j);
          return null;
        }
      }
    }

    thisNode.setImage(setImg);
    this.countChange = 0;
    return thisNode;
  }
  */


  private Node left;
  private Node right;
  private String name;
  private int x;
  private int y;
  private int x2;
  private int y2;
  private Image img;
  static Node thisNode;
  static int countAdd = 0;
  static int countGet = 0;
  static int countChange = 0;
}


