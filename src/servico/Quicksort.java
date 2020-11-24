/**
 *     
    roBott - Copyright (C) 2002  Vitor Santos Bottazzi

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *    
 */

package servico;
import java.util.Comparator;
import java.util.Random;

public class Quicksort {


    public static final Random RND = new Random();      
    public static Comparator cmp = new AppComparator();
    
    private void swap(Object[][] array, int i, int j, int byXZY) {
        //Object tmp = array[i][byXZY];
        //array[i][byXZY] = array[j][byXZY];
        //array[j][byXZY] = tmp;
      Object FloatAux;  
      FloatAux = array[i][0];
      array[i][0] = array[j][0];
      array[j][0] = FloatAux;
                            
      FloatAux = array[i][1];
      array[i][1] = array[j][1];
      array[j][1] = FloatAux;
                            
      FloatAux = array[i][2];
      array[i][2] = array[j][2];
      array[j][2] = FloatAux;     
    }
    private int partition(Object[][] array, int begin, int end, Comparator cmp, int byXZY) {
        int index = begin + RND.nextInt(end - begin + 1);
        Object pivot = array[index][byXZY];
        swap(array, index, end, byXZY);                
        for (int i = index = begin; i < end; ++ i) {
            if (cmp.compare(array[i][byXZY], pivot) <= 0) {
                swap(array, index++, i, byXZY);
        }   }
        swap(array, index, end, byXZY);        
        return (index);
    }
    private void qsort(Object[][] array, int begin, int end, Comparator cmp, int byXZY) {
        if (end > begin) {
            int index = partition(array, begin, end, cmp, byXZY);
            qsort(array, begin, index - 1, cmp, byXZY);
            qsort(array, index + 1,  end,  cmp, byXZY);
    }   }
    public void sort(Object[][] array,  int byXZY, int inirange,int endrange)
    {
    
    qsort(array, inirange, endrange, cmp, byXZY);
    
    }
}