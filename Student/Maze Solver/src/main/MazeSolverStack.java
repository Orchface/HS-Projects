package main;

import java.util.Stack;

public class MazeSolverStack extends MazeSolver{

	private Stack<Square> stack;
	
	public MazeSolverStack(Maze maze) {
		super(maze);
	}

	@Override
	public void makeEmpty() { 
		this.stack = new Stack<Square>();
	}

	@Override
	public boolean isEmpty() {
		return this.stack.empty();
	}

	@Override
	public void add(Square s) {
		this.stack.push(s);
	}

	@Override
	public Square next() {        //remove the element from the stack because 
		return this.stack.pop();  //it will never be placed on the stack again
	}

}
