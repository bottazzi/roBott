/*****************************
**  Projeto Mestrado - UMINHO
**	Vitor Santos Bottazzi
**	Data Altera  o: 20/20/2004
*****************************/

/*****************************
**  Projeto final - Ucsal
**	Vitor Santos Bottazzi
**	Data inicio: 05/11/2002
*****************************/

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

package protocolo;

public interface Protopon 
{
/**
 * M todo que define comando de movimento.
 * 
 * @param op Receber um inteiro que vai representar o tipo do movimento.
 * @return Retorna uma string atrav s das vari veis static definidas na classe.
 * @see Ponto_Abb
 * 
 */
public String tipoMov(int op);
/**
 * M todo que define a velocidade.
 * 
 * @param vel Recebe um inteiro como valor da velocidade do movimento.
 * @return Retorna a velocidade.
 * @see Ponto
 * 
 */
public String EscolheVelocidade(int vel);
/**
 * M todo que define a precis o. 
 * 
 * @param pre Recebe um inteiro como valor da precis o do movimento.
 * @return Retorna a precis o.
 * @see Ponto
 * 
 */
public String EscolhePrecisao(int pre);
/**
 * Retorna uma String com a linha de comando do ponto.
 * 
 * @param NoParameters
 * @return Retorna o comando de movimento.
 * @see Ponto
 */
public String toString();
}