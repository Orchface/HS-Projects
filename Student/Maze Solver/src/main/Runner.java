package main;

public class Runner {
	public static void main(String[] args){
		Maze m = new Maze();
		m.loadMaze("resources/maze-2");
		System.out.println(m); //test toString
	}
}
