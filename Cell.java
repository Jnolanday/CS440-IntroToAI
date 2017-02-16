package assignment1;

import java.awt.Point;
import static java.lang.Integer.MAX_VALUE;
//import structures.Map;

public class Cell {
	
	private int myX;
	private int myY;
	private int myCellType;
            /*Cell Types:
                0 - blocked
                1 - unblocked
                2- htt
                3- unblocked highway
                4- htt highway
            */
	private double f,g,h;
        private Cell parent;
    private int cellID;
	
	
	// constructor
	public Cell(int x, int y, int cellType){        
		
		myX = x;
		myY = y;
		myCellType = cellType;
		
                f = -1;
                h = -1;
                g = MAX_VALUE;
                parent = null;
		
	}
        public double getF(){
            return f;
        }
        public double getG(){
            return g;
        }
        public double getH(){
            return h;
        }
        
        public Cell getParent(){
            return parent;
        }
        
        public void setF(double i){
            f = g+i;
        }
        public void setG(double i){
            g = i;
        }
        public void setH(double i){
            h = i;
        }
        public void setParent(Cell c){
            parent = c;
        }
	
	public int getX(){
		
		return myX;
	}
	
	public int getY(){
		
		return myY;
	}
        
        public void setX(int x){
		myX = x;
	}
	
	public void setY(int y){
		myY = y;
	}
        public Point getLocation(){
            return new Point(myX,myY);
        }
	
        public void setLocation(Point p){
            myX = (int)p.getX();
            myY = (int)p.getY();
        }
	
	public int getCellType(){
		
		return myCellType;
	}
	
	public void setCellType(int newCellType){
		
		myCellType = newCellType;
	}
        public void setCellType(char type){
            switch(type){
                case '0': myCellType = 0;
                          break;
                case '1': myCellType = 1;
                          break;
                case '2': myCellType = 2;
                          break;  
                case 'a': myCellType = 3;
                          break;
                case 'b': myCellType = 4;
                          break;
                default : myCellType = 1;
                          break;
            }    
        }
        
        public void setCellID(int newID)
        {
        	cellID = newID;
        }
        
        public int getCellID()
        {
        	return cellID;
        }
	
        public void setHighway(){
            if(myCellType==1){
                this.setCellType(3);
            }else if(myCellType==2){
                this.setCellType(4);
            }
        }
        public void removeHighway(){
            if(myCellType==3){
                this.setCellType(1);
            }else if(myCellType==4){
                this.setCellType(2);
            }
        }
        public String cellTypeToStr(){
            switch(myCellType){
                case 0: return "\u001B[47m"+"b"+"\u001B[0m";
                case 1: return "1";
                case 2: return "2";
                case 3: return "a";
                case 4: return "b";
                default: return "1";
            }
        }
        public String toString(){
            return "<html>x: "+myX
                    + "<br>y: "+myY
                    + "<br>h: "+h
                    + "<br>f "+f;
        }
        
        
	
	/*public void setNeighbors(Map map){
		
		myNeighbors[0][0] = map.getCell(myX-1, myY-1);
		myNeighbors[0][1] = map.getCell(myX, myY-1);
		myNeighbors[0][2] = map.getCell(myX+1, myY-1);
		myNeighbors[1][0] = map.getCell(myX-1, myY);
		myNeighbors[1][2] = map.getCell(myX+1, myY);
		myNeighbors[2][0] = map.getCell(myX-1, myY+1);
		myNeighbors[2][1] = map.getCell(myX, myY+1);
		myNeighbors[2][2] = map.getCell(myX+1, myY+1);
	}*/
	
}
