package structures;


import javax.swing.JButton; //imports JButton library
import java.awt.GridLayout; //imports GridLayout library
import java.io.IOException;
import structures.Map;
import structures.Cell;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
 
public class Grid extends JPanel{
 
        JPanel panel =new JPanel(); //creates frame
        JButton[][] grid; //names the grid of buttons
        JLabel label = new JLabel();
 
        public Grid(String map_path)throws IOException{ //constructor
                int width = 120;
                int length = 160;
                Map m = new Map(map_path);
                Cell[][] cells = m.getMap();
                panel.setLayout(new GridLayout(width,length)); //set layout
                grid=new JButton[width][length]; //allocate the size of grid
                for(int x=0; x<width; x++){
                        for(int y=0; y<length; y++){
                                String str= cells[x][y].toString();
                                JButton b = new JButton("("+x+","+y+")");
                                b.addActionListener(new ActionListener()
                                {
                                  public void actionPerformed(ActionEvent e)
                                  {
                                   
                                      //str = ;
                                    label.setText(str);
                                  
                                  }
                                });
                                colorButton(b,cells[x][y]);
                                grid[x][y]=b; //creates new button     
                                panel.add(grid[x][y]); //adds button to grid
                        }
                }
               //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               // frame.pack(); //sets appropriate size for frame
               // frame.setVisible(true); //makes frame visible
        }
        public JPanel getPanel(){
            return panel;
        }
        private void colorButton(JButton b, Cell c){
            switch(c.getCellType()){
                case 0:
                    b.setBackground(Color.black);
                    break;
                case 1:
                    b.setBackground(Color.white);
                    break;
                case 2:
                    b.setBackground(Color.darkGray);
                    break;
                case 3:
                    b.setBackground(Color.blue);
                    break;
                case 4:
                    b.setBackground(Color.cyan);
                    break;
                default:
                    b.setBackground(Color.white);
                    break;
            }
        }
        
}
