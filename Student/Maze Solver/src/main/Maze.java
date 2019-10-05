package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Maze {

	private Square[][] maze;
	
	private Square start, exit;
	
	
	
	public Maze(){
		
	}
	
	public boolean loadMaze(String fileName){
		try {
			Scanner s = new Scanner(new File(fileName));
			int r = s.nextInt();
			int c = s.nextInt();
			this.maze = new Square[r][c];
			for(int i = 0; i < r; i++){
				for(int j = 0; j < c; j++){
					int sq;
					try{ //check if the next token is not an int. throw an error if it is not
					sq = s.nextInt();
					} catch (Exception e){
						return false;
					}
					maze[i][j] = new Square(i,j,sq);
					if(sq == Square.START){
						this.start = maze[i][j];
					} else if(sq == Square.EXIT){
						this.exit = maze[i][j];
					}
				}
			}
			return true; //if the above runs without problems, the file was successfully loaded
		} catch (FileNotFoundException e) {
			return false;
		}
	}
	
	public List<Square> getNeighbors(Square s){
		ArrayList<Square> list = new ArrayList<>();
		int r = s.getRow();
		int c = s.getCol();
		if(r-1>=0) list.add(maze[r-1][c]); //north
		if(r+1<maze.length) list.add(maze[r+1][c]); //south
		if(c-1>=0) list.add(maze[r][c-1]); //west
		if(c+1<maze[r].length) list.add(maze[r][c+1]); //east
		return list;
	}
	
	public Square getStart(){
		return this.start;
	}
	
	public Square getExit(){
		return this.exit;
	}
	
	public void reset(){
		for(int i = 0; i < maze.length; i++){
			for(int j = 0; j < maze[i].length; j++){
				maze[i][j].setStatus('\u0000');//use default char as placeholder
			}
		}
	}
	
	@Override
	public String toString(){
		String result = "";
		for(int i = 0; i < maze.length; i++){
			for(int j = 0; j < maze[i].length; j++){
				result += maze[i][j]+" "; //add a space to distinguish underscores
			}
			result += "\n";
		}
		return result;
	}
	
	
	
}
