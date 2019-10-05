package main;

import java.util.ArrayList;

public abstract class MazeSolver {
	
    private Maze maze;
	
    private boolean isSolved;
    
	public MazeSolver(Maze maze){
		this.maze = maze;
		makeEmpty();
		add(maze.getStart());
		this.isSolved = false;
	}

	public abstract void makeEmpty();

    public abstract boolean isEmpty();

    public abstract void add(Square s);

    public abstract Square next();
	
    public boolean isSolved(){
    	boolean exitTouched = false; //checks if the maze is unsolvable by checking if the exit's neighbors are unexplored
    	ArrayList<Square> neighbors = (ArrayList<Square>) maze.getNeighbors(maze.getExit());
    	for(Square s : neighbors){
    		if(s.getStatus() != '\u0000'){ //the square must have been reached by the program
    			exitTouched = true;
    		}
    	}
    	return this.isSolved || isEmpty() && exitTouched; 
    }
    
    public void step(){
    	if(isEmpty()){
    		return; //if no path was found, the algorithm cannot continue
    	} else{
    		Square next = next();
    		if(next.equals(maze.getExit())){
    			this.isSolved = true;
    			Square prev = next.getPrevious();
    			while(!(prev.equals(maze.getStart()))){ //backtrack from exit to start
    				prev.setStatus('x');
    				prev = prev.getPrevious();
    			}
    			return; //once the exit is found and path traced, the algorithm terminates
    		} else{
    			ArrayList<Square> neighbors = (ArrayList<Square>) maze.getNeighbors(next);
    			for(int i = 0; i < neighbors.size(); i++){
    				if(neighbors.get(i).getType() != Square.WALL){
    					Square temp = neighbors.get(i);
    					if(temp.getStatus() != 'o' && temp.getStatus() != '.' && temp.getPrevious() == null){ 
    						temp.setStatus('o'); //status should be "on worklist" when added
    						temp.setPrevious(next);
    						add(temp);
    					}
    				}
    			}
    			next.setStatus('.'); //once the neighbors have been added, status of original is updated to 'explored'
    		}
    	}
    }
    
    public String getPath(){
    	if(isSolved()){
    		return "Solved.";
    	}
    	if(isEmpty()){ //an empty stack signifies that no path was found
    		return "Unsolvable.";
    	}
    	return "Not yet solved."; //if it is not solved or unsolvable, it must be in the process of solving
    }
    
    public void solve(){
    	while(!isSolved()){
    		step();
    	}
    }
    
}
