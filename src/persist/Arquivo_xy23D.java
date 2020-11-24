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

package persist;

import java.io.*;
import java.util.*;
import otimiza.*;
import servico.*;

public class World extends TemplateFileIn
{
  public World()
  {
  
  }
  
public void cabecalhoPrograma(BufferedReader in) throws Exception
{
  
}



public void CarregaListaCoord(String filename, Programa progNovo) throws Exception //,Programa progNovo)
{
  BufferedReader in = super.LerArquivo(filename);
  Factory fabrica = new Factory();
  Coordenadas coord = fabrica.getInstanciaCoordenadas();
      
  String str = new String(in.readLine()); 
  String x,y,z;
      while (str != null){
      
      x = str.substring(0,str.indexOf(" "));
      y = str.substring(str.indexOf(" "),str.indexOf(" "));
      z = str.substring(str.indexOf(" "),str.length());
      //progNovo.listaPontosVarCoord.add(coord.setCoordenadas((float)x,(float)y,(float)z));
       System.out.println("x = " + x);
       System.out.println("y = " + y);
       System.out.println("z = " + z);
            
      str = new String(in.readLine());
      }
      in.close();
}

public void fechaPrograma(BufferedReader in)throws Exception
{
  
}
  
}