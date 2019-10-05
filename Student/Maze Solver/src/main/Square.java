package main;

public class Square {

	public static final int SPACE = 0;
	public static final int WALL = 1;
	public static final int START = 2;
	public static final int EXIT = 3;
	
	private int row, col, type;
	
	private char status = '\u0000'; // '\u0000' is a placeholder for "untouched"
	
	private Square previous;
	
	public Square(int row, int col, int type){
		this.row = row;
		this.col = col;
		this.type = type;
		this.previous = null;
	}
	
	public void setPrevious(Square s){
		this.previous = s;
	}
	
	public Square getPrevious(){
		return this.previous;
	}
	
	@Override
	public String toString(){
		  switch(this.type){
          case SPACE:
        	  if(this.status != '\u0000'){ 
        		  return this.status+"";
        	  }
              return "_";
          case WALL:
              return "#";
          case START:
              return "S";
          case EXIT:
              return "E";
          default:
        	  return "";
		  }
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof Square){ //coordinates match AND statuses match
			return ((Square) obj).getRow() == this.row && ((Square) obj).getCol() == this.col && ((Square) obj).getStatus() == this.status;
		}
		return false;
	}
	
	public int getRow(){
		return this.row;
	}
	
	public int getCol(){
		return this.col;
	}
	
	public void setStatus(char c){
		this.status = c;
	}
	
	public int getType(){
		return this.type;
	}
	
	public char getStatus(){
		return this.status;
	}
	
	
	
}
