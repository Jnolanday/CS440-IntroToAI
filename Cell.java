public class Cell {
	
	private int myX;
	private int myY;
	private boolean myVisited;
	private int myCellType;
	
	private int[][] myNeighbors = new int[3][3];
	
	// constructor
	public Cell(int x, int y, int cellType){
		
		myX = x;
		myY = y;
		myCellType = cellType;
		
		// sets middle neighbor cell to -1 (represents our current cell)
		myNeighbors[1][1] = -1;
		
		//setNeighbors();
	}
	
	private int getX(){
		
		return myX;
	}
	
	private int getY(){
		
		return myY;
	}
	
	private boolean getVisited(){
		
		return myVisited;
	}
	
	private int getCellType(){
		
		return myCellType;
	}
	
	private int[][] getNeighbors(){
		
		return myNeighbors;
	}
	
	private void setX(int x){
		myX = x;
	}
	
	private void setY(int y){
		myY = y;
	}
	
	private void setVisited(boolean vis){
		myVisited = vis;
	}
	
	private void setCellType(int newCellType){
		
		myCellType = newCellType;
	}
	
	/*
	 TODO: Implement this method using Grid class
	void setNeighbors(){
		
		myNeighbors[0][0] = map.getCell(myX-1, myY-1);
		myNeighbors[0][1] = map.getCell(myX, myY-1);
		myNeighbors[0][2] = map.getCell(myX+1, myY-1);
		myNeighbors[1][0] = map.getCell(myX-1, myY);
		myNeighbors[1][2] = map.getCell(myX+1, myY);
		myNeighbors[2][0] = map.getCell(myX-1, myY+1);
		myNeighbors[2][1] = map.getCell(myX, myY+1);
		myNeighbors[2][2] = map.getCell(myX+1, myY+1);
	}
	
	*/
}
