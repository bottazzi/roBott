/**
 * @author Vitor Santos Bottazzi
 * Data de implementa  o: 15/09/2002	
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

package coord;

public class Coord_Joint //extends T_Coordinates
{
	public float J1;	
	public float J2;
	public float J3;
  public float J4;	
	public float J5;
	public float J6;
	
/**
 * M todo construtor de um ponto cartesiano.
 * 
 * @param NoParameters
 * */
public Coord_Joint()
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
public Coord_Joint(float J1,float J2,float J3,float J4,float J5,float J6)
	{
	this.J1 = J1;
	this.J2 = J2;
  this.J3 = J3;
  this.J4 = J4;
  this.J5 = J5;
  this.J6 = J6;
	}
	
/**
 * Seta o valor da J1.
 * 
 * @param newJ1 Valor que representa o valor da coordenada J1.
 * @return void
 * 
 */
public void setJ1(float newJ1)
	{
	this.J1 = newJ1;
	}

/**
 * Seta o valor da J2.
 * 
 * @param newJ1 Valor que representa o valor da coordenada J2.
 * @return void
 * 
 */
public void setJ2(float newJ2)
	{
	this.J2 = newJ2;
	}

/**
 * Seta o valor da J3.
 * 
 * @param newJ1 Valor que representa o valor da coordenada J3.
 * @return void
 * 
 */
public void setJ3(float newJ3)
	{
	this.J3 = newJ3;
	}
  
/**
 * Seta o valor da J4.
 * 
 * @param newJ1 Valor que representa o valor da coordenada J4.
 * @return void
 * 
 */
public void setJ4(float newJ4)
	{
	this.J4 = newJ4;
	}
  
/**
 * Seta o valor da J5.
 * 
 * @param newJ1 Valor que representa o valor da coordenada J5.
 * @return void
 * 
 */
public void setJ5(float newJ5)
	{
	this.J5 = newJ5;
	}
  
/**
 * Seta o valor da J6.
 * 
 * @param newJ1 Valor que representa o valor da coordenada J6.
 * @return void
 * 
 */
public void setJ6(float newJ6)
	{
	this.J6 = newJ6;
	}
  
/**
 * Retorna o valor da coordenada J1.
 * 
 * @param NoParameters
 * @return Retorna o valor da coordenada J1.
 */	
public float getJ1()
	{
	return this.J1;
	}

/**
 * Retorna o valor da coordenada J2.
 * 
 * @param NoParameters
 * @return Retorna o valor da coordenada J2.
 */	
public float getJ2()
	{
	return this.J2;
	}
  
/**
 * Retorna o valor da coordenada J3.
 * 
 * @param NoParameters
 * @return Retorna o valor da coordenada J3.
 */	
public float getJ3()
	{
	return this.J3;
	}
  
/**
 * Retorna o valor da coordenada J4.
 * 
 * @param NoParameters
 * @return Retorna o valor da coordenada J4.
 */	
public float getJ4()
	{
	return this.J4;
	}
  
/**
 * Retorna o valor da coordenada J5.
 * 
 * @param NoParameters
 * @return Retorna o valor da coordenada J5.
 */	
public float getJ5()
	{
	return this.J5;
	}
/**
 * Retorna o valor da coordenada J6.
 * 
 * @param NoParameters
 * @return Retorna o valor da coordenada J6.
 */	
public float getJ6()
	{
	return this.J6;
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
	return 	"Coordenadas cartesiana = "+"[" + this.getJ1() + "," + this.getJ2() + "," + this.getJ3() + "," + this.getJ4() + "," + this.getJ5() + "," + this.getJ6() + "]";
	}

}