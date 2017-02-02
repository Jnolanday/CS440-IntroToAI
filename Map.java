package structures;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Map {
	private Point sstart;
	private Point sgoal;
	private Point[] httCenters;
	private Cell[][] map;

	public Map() { // Generates new RANDOM Map w/o input
		map = new Cell[120][160]; // first initialize 120x160 grid

		// initialize all cells as unblocked
		for (int row = 0; row < 120; row++) {
			for (int col = 0; col < 160; col++) {
				map[row][col] = new Cell(row,col,1);
				// System.out.print(map[row][col].toString()+" ");
			}
			// System.out.println();
		}

		// set placement of 8 random htt centers & regions
		 placeHTTs();
		// placement of 4 highways
		placeHighways();
                placeBlocks();
		placeStartGoal();
		display();
                /*System.out.println("Start: "+sstart.toString());
                System.out.println("Goal: "+sgoal.toString());*/
		
		 

	}

	private void placeHTTs() {
		httCenters = new Point[8];
		Random rand = new Random();
		for (int i = 0; i < 8;) {
			int xrand = rand.nextInt(120);
			int yrand = rand.nextInt(160);
			if (map[xrand][yrand].getCellType()==2) {
				continue;
			}
			map[xrand][yrand].setCellType(2);
			httCenters[i] = new Point(xrand, yrand); // inserts point of new htt to list of htt centers
													 
			// System.out.println(httCenters[i].toString());
			for (int r = xrand - 15; r <= xrand + 15; r++) {
				if (r < 0 || r > 119)
					continue;

				for (int c = yrand - 15; c < yrand + 15; c++) {
					if (c < 0 || c > 119)
						continue;
					if (rand.nextBoolean()) {
						map[r][c].setCellType(2);
					}
				}
			}
			i++;
		}
	}

	private void placeHighways() {
		Cell[][] origMap = map;
		int success, reject = 0;
		outerloop: 
		for (success = 0; success < 4;) {
		/*if (reject > 20) {
				success = 0;
				map = origMap;
			}*/
			ArrayList<Point> tempHway = new ArrayList<Point>();
			Random rand = new Random();
			int xstart, ystart;
			Point currPoint, nxtPoint;
			switch (rand.nextInt(4)) {
			case 0:
				xstart = 0;
				ystart = rand.nextInt(158) + 1;
				nxtPoint = new Point(xstart, ystart);
				nxtPoint.translate(1, 0);
				break;
			case 1:
				xstart = 119;
				ystart = rand.nextInt(158) + 1;
				nxtPoint = new Point(xstart, ystart);
				nxtPoint.translate(-1, 0);
				break;
			case 2:
				xstart = rand.nextInt(118) + 1;
				ystart = 0;
				nxtPoint = new Point(xstart, ystart);
				nxtPoint.translate(0, 1);
				break;
			case 3:
				xstart = rand.nextInt(118 + 1);
				ystart = 159;
				nxtPoint = new Point(xstart, ystart);
				nxtPoint.translate(0, -1);
				break;
			default:
				xstart = 0;
				ystart = 0;
				nxtPoint = new Point(xstart, ystart);
				break;
			}
			currPoint = new Point(xstart, ystart);
			tempHway.add(currPoint);
			//System.out.println(tempHway.size()+"\t"+currPoint);
			do{
				if (isHwayPoint(nxtPoint)) {
					for (int i = 0; i < tempHway.size(); i++) {
						Cell c = map[tempHway.get(i).x][tempHway.get(i).y];
						if(c.getCellType()==3){
                                                    map[c.getX()][c.getY()].setCellType(1);
                                                   
                                                }if(c.getCellType()==4){
                                                    map[c.getX()][c.getY()].setCellType(2);
                                                }
					}
					//reject++;
					continue outerloop;
				}
				Point temp = new Point(currPoint);
				currPoint = new Point(nxtPoint);
				tempHway.add(currPoint);
				map[currPoint.x][currPoint.y].setHighway();
				
				if ((tempHway.size() + 1) % 20 == 0) {
					if (rand.nextInt(10) < 6)
						nxtPoint = movStraight(temp, nxtPoint);
					else
						nxtPoint = movPerp(temp, nxtPoint);
				} else {
					nxtPoint = movStraight(temp, nxtPoint);
				}
				
				
				//System.out.println(tempHway.size()+"\t"+currPoint);
			}while (withinBorder(nxtPoint)); 
			if (tempHway.size() < 100) {
				for (int i = 0; i < tempHway.size(); i++) {
					Cell c = map[tempHway.get(i).x][tempHway.get(i).y];
					map[tempHway.get(i).x][tempHway.get(i).y].setHighway();
				}
				continue;
			} else {
				success++;
				System.out.println("Highway " + success + "\tLength: " + tempHway.size());
			}
			
		}
	}
	
	private void placeBlocks(){
		Random rand = new Random();
		for (int i = 0; i < 3840;) {
			int xrand = rand.nextInt(120);
			int yrand = rand.nextInt(160);
			if (map[xrand][yrand].getCellType()>2) {
				continue;
			}
			map[xrand][yrand].setCellType(0);
			i++;
		}
	}
        private void placeStartGoal(){
            sstart = randPoint();
            do{
                sgoal = randPoint();
            }while(sstart.distance(sgoal)<100||sstart.equals(sgoal));
           
        }
        
        public void display(){
            for (int row = 0; row < 120; row++) {
			for (int col = 0; col < 160; col++) {
				System.out.print(map[row][col].cellTypeToStr() + " ");
                                if(sstart.equals(new Point(row,col))){
                                    System.out.print("S ");
                                }else if(sgoal.equals(new Point(row,col))){
                                    System.out.print("G ");
                                }else{
                                    System.out.print(map[row][col].cellTypeToStr() + " ");
                                }
			}
			System.out.println();
		}
        }
        private Point randPoint(){  //generates a random point within allowed regions for start and end points
            Random rand = new Random();
            int x,y, i;
            i = rand.nextInt(4);
            switch(i){
                case 0: //Top 20 rows
                    x = rand.nextInt(120);
                    y = rand.nextInt(20);
                    break;
                case 1: //Bottom 20 rows
                    x = rand.nextInt(120);
                    y = rand.nextInt(20)+140;
                    break;
                case 3: //left most 20
                    x = rand.nextInt(20);
                    y = rand.nextInt(160);
                    break;
                case 4: //right most 20
                    x = rand.nextInt(20)+100;
                    y = rand.nextInt(160);
                    break;
                default:
                    x = 0;
                    y = 0;
                    break;
            }
            return new Point(x,y);
        }   
	private boolean northward(Point curr, Point nxt) {
		if (curr.getX() > nxt.getX() && curr.getY() == nxt.getY())
			return true;
		else
			return false;
	}

	private boolean southward(Point curr, Point nxt) {
		if (curr.getX() < nxt.getX() && curr.getY() == nxt.getY())
			return true;
		else
			return false;
	}

	private boolean eastward(Point curr, Point nxt) {
		if (curr.getX() == nxt.getX() && curr.getY() < nxt.getY())
			return true;
		else
			return false;
	}

	private boolean westward(Point curr, Point nxt) {
		if (curr.getX() == nxt.getX() && curr.getY() > nxt.getY())
			return true;
		else
			return false;
	}

	private Point movStraight(Point curr, Point nxt) {
		if (northward(curr, nxt)) {
			nxt.translate(-1, 0);
		}
		if (southward(curr, nxt)) {
			nxt.translate(1, 0);
		}
		if (eastward(curr, nxt)) {
			nxt.translate(0, 1);
		}
		if (westward(curr, nxt)) {
			nxt.translate(0, -1);
		}
		return nxt;
	}

	private Point movPerp(Point curr, Point nxt) {
		Random rand = new Random();
		if (rand.nextBoolean()) {
			return movLeft(curr, nxt);
		} else
			return movRight(curr, nxt);
	}

	private Point movLeft(Point curr, Point nxt) {
		if (northward(curr, nxt)) {
			nxt.translate(0, 1);
		}
		if (southward(curr, nxt)) {
			nxt.translate(0, -1);
		}
		if (eastward(curr, nxt)) {
			nxt.translate(-1, 0);
		}
		if (westward(curr, nxt)) {
			nxt.translate(1, 0);
		}
		return nxt;
	}

	private Point movRight(Point curr, Point nxt) {
		if (northward(curr, nxt)) {
			nxt.translate(0, -1);
		}
		if (southward(curr, nxt)) {
			nxt.translate(0, 1);
		}
		if (eastward(curr, nxt)) {
			nxt.translate(1, 0);
		}
		if (westward(curr, nxt)) {
			nxt.translate(-1, 0);
		}
		return nxt;
	}

	private boolean isHwayPoint(Point p) {
		return map[(int) p.getX()][(int) p.getY()].getCellType()==3||map[(int) p.getX()][(int) p.getY()].getCellType()==4;
	}

	private boolean withinBorder(Point p) {
		if (p.x >= 0 && p.x < 120 && p.y >= 0 && p.y < 160) {
			return true;
		} else
			return false;
	}

	private boolean atBorder(Point p) {
		if (withinBorder(p) && (p.x == 0 || p.x == 119 || p.y == 0 || p.y == 159)) {
			return true;
		} else
			return false;
	}

}
