/**
 * @author Vitor Santos Bottazzi
 * Implementation date: 15/09/2002	
 *  
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

package smart.roBott.app.coord;

final public class Coord_World //extends T_Coordinates
{
	public float Coord_X;	
	public float Coord_Y;
	public float Coord_Z;
	
/**
 * M todo construtor de um ponto cartesiano.
 * 
 * @param NoParameters
 * */
public Coord_World()
{}

/**
 * M todo Construtor de um ponto cartesiano com coordenadas recebidas por 
 * par metro.
 * 
 * @param newCoord_X Valor do eixo cartesiano X.
 * @param newCoord_Y Valor do eixo cartesiano Y.
 * @param newCoord_Z Valor do eixo cartesiano Z.
 * @see Cartesiano
 * 
 * */	
public Coord_World(float newCoord_X,float newCoord_Y,float newCoord_Z)
	{
	this.Coord_X = newCoord_X;
	this.Coord_Y = newCoord_Y;
	this.Coord_Z = newCoord_Z;
	}
	
/**
 * Seta o valor da coordenada X.
 * 
 * @param newCoord_X Valor que representa o valor da coordenada X.
 * @return void
 * 
 */
public void setCoord_X(float newCoord_X)
	{
	this.Coord_X = newCoord_X;
	}
	
/**
 * Seta o valor da coordenada Y.
 * 
 * @param newCoord_Y Valor que representa o valor da coordenada Y.
 * @return void
 * 
 */
public void setCoord_Y(float newCoord_Y)
	{
	this.Coord_Y = newCoord_Y;
	}
	
/**
 * Seta o valor da coordenada Z.
 * 
 * @param newCoord_Z Valor que representa o valor da coordenada Z.
 * @return void
 * 
 */
 public void setCoord_Z(float newCoord_Z)
	{
	this.Coord_Z = newCoord_Z;
	}
	
/**
 * Retorna o valor da coordenada X.
 * 
 * @param NoParameters
 * @return Retorna o valor da coordenada X.
 */	
public Float getCoord_X()
	{
	return new Float(this.Coord_X);
	}

/**
 * Retorna o valor da coordenada Y.
 * 
 * @param NoParameters
 * @return Retorna o valor da coordenada Y.
 */
public Float getCoord_Y()
	{
	return new Float(this.Coord_Y);
	}

/**
 * Retorna o valor da coordenada Z.
 * 
 * @param NoParameters
 * @return Retorna o valor da coordenada Z.
 */
public Float getCoord_Z()
	{
	return new Float(this.Coord_Z);
	}	

/**
 * Retorna uma mensagem com as coordenadas cartesianas do ponto.
 * 
 * @param NoParameters
 * @return Retorna uma String com as coordenadas cartesianas do ponto 
 * do tipo [x,y,z].
 * */
public String toString()
	{
	return 	"[" + this.getCoord_X() + "," + this.getCoord_Y() + "," + this.getCoord_Z() + "]";
	}

}