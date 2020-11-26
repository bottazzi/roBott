/****************************************************
**  Projeto final - Ucsal 
**	Vitor Santos Bottazzi
**	Implementation date 15/09/2002	
****************************************************/
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

package smart.roBott.app.service;


public class Ponto
{
	
public int movimento;
public String nome = "a";
public int velocidade;
public int precisao;

/**
 * M todo construtor de um ponto gen rico.
 *
 * @param NoParameters 
 */
protected Ponto()
{}

public String getNewNome()
{
System.out.print(nome.valueOf(this.nome.hashCode()+1));
  return nome.valueOf(this.nome.hashCode()+1);
}

public Ponto(String newNome, int newVelocidade, int newPrecisao)
	{
	this.movimento = 2; //Linear
	this.nome = newNome;
	this.velocidade =  newVelocidade;
	this.precisao = newPrecisao; // Fine
	}


/**
 * Sobrecarrega o m todo construtor de um ponto gen rico.
 * <p>
 * Inst ncia um comando gen rico para que no futuro, apartir do protocolo 
 * escolhido seja gerado o c digo de programa  o.
 * 
 * @param newMovimento Inteiro que representa o tipo do movimento.<p>
 * @param newNome String que representa o ponto de passsagem do movimento.<p>
 * @param newVelocidade Valor Inteiro da velocidade.<p>
 * @param newPrecisao Valor Inteiro da precisao.
 * @see Ponto
 * 
 */
public Ponto(int newMovimento,String newNome,int newVelocidade,int newPrecisao)
	{
	this.movimento = newMovimento;
	this.nome = newNome;
	this.velocidade = newVelocidade;
	this.precisao = newPrecisao;
	}

/**
 * Retorna uma String com a linha de comando do ponto gen rico.
 *
 * @param NoParameters
 * @return Retorna a representa ao gen rica de um comando.
 * @see Ponto#Ponto(int newMovimento,String newNome,int newVelocidade,int newPrecisao)
 * 
 */
public String toString()
	{
	return this.movimento + " " + this.nome + " " + this.velocidade + " " + this.precisao;
	}	
}