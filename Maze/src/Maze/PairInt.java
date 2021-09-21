/** @name Yi-Chun,Liu */
package Maze;

public class PairInt {
	
	// data field
	private int x;
	private int y;
	
	// constructor
	public PairInt(int x, int y) {
        this.x = x;
        this.y = y;
		
	}

	// getter and setter for x and y
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	@Override
	public boolean equals(Object p) {
        if ( p == null) { 
            return false;
        }
        if(this.getClass() != p.getClass()) {
        	return false;
        }
        PairInt pairInt = (PairInt) p;
        return this.x == pairInt.x && this.y == pairInt.y;
    }
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
	//return a new copy of a PairInt
	public PairInt copy() {
		return new PairInt(x, y);
	}
	
	
}
