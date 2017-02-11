/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;


import java.awt.Point;
import static java.lang.Float.MAX_VALUE;
import java.util.ArrayList;
import structures.*;
import java.util.PriorityQueue;
import java.util.function.Predicate;

public class AStar {
     private PriorityQueue<Cell> fringe;
     private ArrayList<Cell> closed;
     private Map map;
     private Cell sStart;
     private Cell sGoal;
     private ArrayList<Cell> succ;
     private ArrayList<Cell> path;
     
     public AStar(Map m,double heuristic){ //HEURISTIC WILL BE A STRING??
         map = m;
         sStart = m.getStart();
         sGoal = m.getGoal();
        for(int r=0;r<120;r++){
            for(int c = 0; c<160;c++){
                map.getMap()[r][c].setG((double)sStart.getLocation().distance(r,c));
                map.getMap()[r][c].setH(heuristic);
                map.getMap()[r][c].setF(map.getMap()[r][c].getH());
            }
        }
        sStart.setG(0);
        sStart.setParent(sStart);
        fringe = new PriorityQueue<>(19200,new FringeComparator());
        fringe.add(sStart);
        closed = new ArrayList<>();
        path = new ArrayList<>();
     }
     
     public int exe(){
         Cell s;
         while(!fringe.isEmpty()){
             s = fringe.poll();
             
             //System.out.println("Entered s to fringe: "+ s.getLocation().toString());
             if(s.equals(sGoal)){
                 
                 return 1;
             }
             closed.add(s);
             
             //System.out.println("Entered s to closed list!");
             setSucc(s);
            //System.out.println("Set successors of s!");
             for (Cell succ1 : succ) {
                 //System.out.println("\t"+succ1.getLocation().toString());
             }
             for (Cell s1 : succ) {
                 //System.out.println("Checking successors of s!");
                 if(!closed.contains(s1)){
                     //System.out.println("\t"+"Successor is not in closed list!");
                     if(!fringe.contains(s1)){
                         //System.out.println("\t"+"Successor is not in fringe!");
                         s1.setG(MAX_VALUE);
                         s1.setParent(null);
                     }
                    //System.out.println("Updating successor of s!");
                    UpdateVertex(s,s1);
                    //path.add(s1);
                    //setPath();
                }
             }
        }
         
         return 0;
     }
     public ArrayList<Cell> getPath(){
         return path;
     }
     private void setPath(){
         Cell goal = fringe.poll();
         Cell parent = goal.getParent();
         path.add(goal);
         while(parent!=null){
             path.add(parent);
             parent = parent.getParent();
         }
     }
     private void UpdateVertex(Cell s,Cell s1){
         if((s.getG()+c(s,s1))<s1.getG()){
             s1.setG(s.getG()+c(s,s1));
             s1.setParent(s);             
             if(fringe.contains(s1)){
                 fringe.remove(s1);
             }
             fringe.add(s1);
                 
         }
     }
     private void setSucc(Cell s){
         int x = s.getX();
         int y = s.getY();
         succ = new ArrayList<>(8);
         //top left
        if(map.withinBorder(new Point(x-1,y-1))){
            succ.add(map.getCell(x-1,y-1));
        }
        //top center
        if(map.withinBorder(new Point(x-1,y))){
            succ.add(map.getCell(x-1,y));
        }
        //top right
        if(map.withinBorder(new Point(x-1,y+1))){
            succ.add(map.getCell(x-1,y+1));
        }
        //center left
        if(map.withinBorder(new Point(x,y-1))){
            succ.add(map.getCell(x,y-1));
        }
        //center right
        if(map.withinBorder(new Point(x,y+1))){
            succ.add(map.getCell(x,y+1));
        }
        //botton left
        if(map.withinBorder(new Point(x+1,y-1))){
            succ.add(map.getCell(x+1,y-1));
        }
        //bottom center
        if(map.withinBorder(new Point(x+1,y))){
            succ.add(map.getCell(x+1,y));
        }
        //bottom right
        if(map.withinBorder(new Point(x+1,y+1))){
            succ.add(map.getCell(x+1,y+1));
        }
       
         succ.removeIf(isBlocked());
     }
     private static Predicate<Cell> isBlocked(){
         return c -> c.getCellType()==0;
     }
     private double c(Cell s, Cell s1){
         int sType = s.getCellType();
         int s1Type = s1.getCellType();
         double cost=0;
         switch (sType) {
             case 1:
                 if(dir(s,s1)==1){
                     switch(s1Type){
                         case 1:case 3:
                             cost = 1;
                             break;
                         case 2:case 4:
                             cost = 1.5;
                             break;
                         default:
                             cost = 1;
                             break;
                     }
                 }else{
                     switch(s1Type){
                         case 1:case 3:
                             cost = Math.sqrt(2);
                             break;
                         case 2:case 4:
                             cost = (Math.sqrt(2)+Math.sqrt(8))/2;
                             break;
                         default:
                             cost = Math.sqrt(2);
                             break;
                     }
                     
                 }   break;
             case 2:
                 if(dir(s,s1)==1){
                     switch(s1Type){
                         case 1:case 3:
                             cost = 1.5;
                             break;
                         case 2:case 4:
                             cost = 2;
                             break;
                         default:
                             cost = 1.5;
                             break;
                     }
                 }else{
                     switch(s1Type){
                         case 1:case 3:
                             cost = (Math.sqrt(2)+Math.sqrt(8))/2;
                             break;
                         case 2:case 4:
                             cost = Math.sqrt(8);
                             break;
                         default:
                             cost = (Math.sqrt(2)+Math.sqrt(8))/2;
                             break;
                     }
                     
                 }   break;
             case 3:
                 if(dir(s,s1)==1){
                     switch(s1Type){
                         case 1:
                             cost = 1;
                             break;
                         case 2:
                             cost = 1.5;
                             break;
                         case 3:
                             cost = 0.25;
                             break;
                         case 4:
                             cost = 0.375;
                             break;
                         default:
                             cost = 1;
                             break;
                     }
                 }else{
                     switch(s1Type){
                         case 1:case 3:
                             cost = Math.sqrt(2);
                             break;
                         case 2:case 4:
                             cost = (Math.sqrt(2)+Math.sqrt(8))/2;
                             break;
                         default:
                             cost = Math.sqrt(2);
                             break;
                     }
                     
                 }   break;
             case 4:
                 if(dir(s,s1)==1){
                     switch(s1Type){
                         case 1:
                             cost = 1.5;
                             break;
                         case 2:
                             cost = 2;
                             break;
                         case 3:
                             cost = 0.375;
                             break;
                         case 4:
                             cost = 0.5;
                             break;
                         default:
                             cost = 1.5;
                             break;
                     }
                 }else{
                     switch(s1Type){
                         case 1:case 3:
                             cost = (Math.sqrt(2)+Math.sqrt(8))/2;
                             break;
                         case 2:case 4:
                             cost = Math.sqrt(8);
                             break;
                         default:
                             cost = (Math.sqrt(2)+Math.sqrt(8))/2;
                             break;
                     }
                     
                 }   break;
             default:
                 break;
         }
        return cost;
     }
     private int dir(Cell s,Cell s1){
         int x = s.getX();
         int y = s.getY();
         int x1 = s1.getX();
         int y1 = s1.getY();
         if(x==x1||y==y1){ //if horizontal or vertical
             return 1;
         }else return 0;    //if diagonal
         
     }
}
