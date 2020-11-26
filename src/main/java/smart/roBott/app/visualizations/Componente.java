/**
 * @author Vitor Santos Bottazzi
 * Implementation date 15/09/2002	
 */
 
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

package smart.roBott.app.visualizations;

// import smart.roBott.app.coord.Coord_World;
import java.util.*;
// import java.text.*;
import java.lang.String;

public class Componente
{
	public String NomeComponente = "";
  public List PointSelArray; //ArrayList
  public long n_linhas_arqcoord; //long
    //Workspace bounds
  public static float infx = 284.0f,
      supx = 737.0f,
      infy = -252.0f,
      supy = 262.0f,
      infz = 0.0f,
      //infz = 155.0f,
      supz = 736.0f;
  
  public Float[][] coord_table = {{null,null,null}};  

public Componente()
{

this.PointSelArray = new ArrayList();
this.PointSelArray.clear();
this.n_linhas_arqcoord = 0;
}

/**
 * Construtor do Componente, que carrega duas listas, uma para os comandos e outra
 * para separar as vari veis.
 * 
 * @param newId Recebe o nome identificador do Componente.
 * @see java.util.ArrayList
 * 
 */
public Componente(String ComponentPath)
{

this.PointSelArray = new ArrayList();
this.PointSelArray.clear();
this.n_linhas_arqcoord = 0;
}	

/**
 * Retorna o nome identificador do Componente.
 * 
 * @param NoParameters
 * @return O nome identificador do Componente.
 * @see Componente
 */
public void Update(Float[][] coordTable)
	{
	//atualiza apenas as posicoes alteradas
	}

 
  /**
 * Retorna uma string com as coordenadas dos pontos de passagem.
 * 
 * @param NoParameters
 * @return Retorna uma string com as coordenadas dos pontos de passagem.
 * @see Componente
 * 
 */
public String coordToString()
{
	String acum = "";
  int i = 0;
  while(this.coord_table[i][0] != null)
  {   
  acum = acum.concat("[" +this.coord_table[i][0] + "," + this.coord_table[i][1] + "," + this.coord_table[i][2] + "]");
  i++;   
  }
  return acum;
}

}