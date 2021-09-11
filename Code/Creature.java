package Bamboo;

import java.awt.Color;

public class Creature {
  public Creature() {}

  void setSpeed(int s){
    this.speed = s;
  }

  void setSize(int s){
    this.size = s;
  }

  void setName(String n){
    this.name = n;
  }

  void setPosition(Position p){
    this.position = p;
  }

  void setColor ( Color c ){
    this.color = c;
  }

  int readSpeed(){
    return this.speed;
  }

  int readSize(){
    return this.size;
  }

  String readName(){
    return this.name;
  }

  Position readPosition(){
    return this.position;
  }

  Color readColor(){
    return this.color;
  }

  private int size;
  private int speed;
  private String name;
  private Color color;
  Position position;
}