package Bamboo;

import java.awt.Color;

public class Creature{

	//fields
	private int size;
	private int speed;
	private String name;
	private Color color;
	Position position;

	//member class
	class Position{

		//fields
		private int OldX, OldY, NewX, NewY;

		//constructor of member class
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
	//end of member class

	//constructor
	public Creature() {}

	//instance methods
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
}