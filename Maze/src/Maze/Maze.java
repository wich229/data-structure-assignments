/** @name Yi-Chun,Liu */
package Maze;

import java.util.Stack;
import java.util.ArrayList;

/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
        return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    public boolean findMazePath(int x, int y) {
        // COMPLETE HERE FOR PROBLEM 1
    	//if the current cell being analyzed falls outside the grid;
    	if((x<0 || y<0) || x>=maze.getNCols()|| y>=maze.getNRows()) {
    		return false;
    	}
    	//If the current cell being analyzed does not have NON_BACKGROUND;
    	else if( maze.getColor(x,y).equals(NON_BACKGROUND)==false) {
    		return false;
    	}
    	//If the current cell being analyzed is the exit cell;
    	else if( x == maze.getNCols()-1 && y == maze.getNRows()-1 ){
    		maze.recolor(x, y, PATH);
    		return true;
    	}
    	// the current cell may be assumed to be part of the path and hence
    	else {
    		//be painted color PATH
    		maze.recolor(x, y, PATH);
    		//Each of the four neighboring cells must be explored to see if they are part of a path
    		if(findMazePath(x+1,y) || findMazePath(x-1,y) || findMazePath(x,y+1) || findMazePath(x,y-1)) {
    			return true;
    		}
    		/* 	the cell is not part of a path: it is a dead end.
				Hence it must be marked so that it is not visited again. For that it must be
				colored to color TEMPORARY.
    		 */
    		else {
    			maze.recolor(x, y, TEMPORARY);
    			return false;
    		}	
    	}
    }

    // ADD METHOD FOR PROBLEM 2 HERE
	/**
	 * find all possible paths
	 * @param x The x-coordinate of current point
	 * @param y The y-coordinate of current point
	 */
    
    /** Reference from the assignment 
     * (x,y) are the coordinates currently being visited
     * result - is the list of successful paths recorded up to now
     * trace - is the trace of the current path being explored
     */
    
    public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x,int y){
    	ArrayList < ArrayList < PairInt > > result = new ArrayList < >();
    	Stack < PairInt > trace = new Stack < >();
    	findMazePathStackBased(0,0,result,trace);
    	return result;
    }
    
    public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {
    	// if the cell no possible path through
    	if (x < 0 || y < 0 || x >= maze.getNCols() || y >= maze.getNRows()
           || (maze.getColor(x,y).equals(NON_BACKGROUND)==false)) {
        	System.out.println(" no possible path through ");
           return;
        }
    	// if the exit has been reached, the contents of the stack should be copied into a list and placed in result.
        else if ( x == maze.getNCols()-1 && y == maze.getNRows()-1 ) {
        	PairInt exitCell = new PairInt(x,y);
        	trace.push(exitCell);
        	ArrayList<PairInt> curPath = new ArrayList<>(); 
        	curPath.addAll(trace);
            result.add(curPath);
            trace.pop();
            maze.recolor(x, y, NON_BACKGROUND);
            return;
        }
        else {
        	/* else,recursive call, after returning from visiting the neighbors, should remove
        	the mark (by coloring the cell with the NON_BACKGROUND color) so that this cell may be
        	visited again after backtracking.
        	*/
        	maze.recolor(x, y, PATH);
        	PairInt curCell = new PairInt(x,y);
        	trace.push(curCell);
            findMazePathStackBased(x + 1, y, result, trace);
            findMazePathStackBased(x - 1, y, result, trace);
            findMazePathStackBased(x, y + 1, result, trace);
            findMazePathStackBased(x, y - 1, result, trace);
            trace.pop();
    		maze.recolor(x, y, NON_BACKGROUND);
            return;
        }
    }
    
    // ADD METHOD FOR PROBLEM 3 HERE
    //returns the shortest path in the list of paths. 
    public ArrayList<PairInt> findMazePathMin(int x, int y){
    	ArrayList < ArrayList < PairInt > > allResult = findAllMazePaths(x, y); // All path
    	int min = allResult.get(0).size();
    	int index = 0;
    	
    	for (int i = 0; i < allResult.size(); i++) { 
	    	if( allResult.get(i).size()> min ) {
	    		min = allResult.get(i).size();
	    		index = i;
	    	}
        }
    	return allResult.get(index);
    }
 
    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/
