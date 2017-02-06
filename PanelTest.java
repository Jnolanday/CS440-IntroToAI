/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apps;

import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import structures.Grid;

/**
 *
 * @author Jarisha
 */
public class PanelTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        JPanel panel = new Grid("C:\\Users\\Jarisha\\workspace\\CS440\\testMap1.txt").getPanel();
        JFrame frame = new JFrame();
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame.pack(); //sets appropriate size for frame
               frame.setVisible(true); //makes frame visible
    }
    
}
