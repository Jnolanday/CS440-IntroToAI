package assignment1;

import java.io.IOException;

public class Test {

	
	static Map map;
	
	public static void main(String[] args) throws IOException {
		
		
		
		map = new Map("C:\\Users\\allen\\workspace\\CS440\\testmap1.txt");
		Cell start = map.getStart();
		Cell end = map.getGoal();
		
		
		AStar driver = new AStar(map, 0);
		driver.exe();
		
		System.out.println();
	}

}
