/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

import java.util.Comparator;

/**
 *
 * @author Jarisha
 */
public class FringeComparator implements Comparator<Cell>
{
    @Override
    public int compare(Cell s, Cell s1)
    {
        // Assume neither string is null. Real code should
        // probably be more robust
        // You could also just return x.length() - y.length(),
        // which would be more efficient.
        
        return (int)(s.getF()-s1.getF());
    }
}
