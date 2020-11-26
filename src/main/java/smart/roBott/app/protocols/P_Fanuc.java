/*****************************
**  Projeto final - Ucsal
**	Vitor Santos Bottazzi
**	Implementation date 13/04/2003
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

package smart.roBott.app.protocols;

import smart.roBott.app.opt.*;
import smart.roBott.app.service.*;

public class P_Fanuc implements Protopon{

 final String movJ = "Joint";
 final String movL = "Linear";
 final String movC = "Circular";

protected String movimento;
protected String nomePts;
protected String velocidade;
protected String precisao;
protected String ferramenta;

	
public P_Fanuc() {}

/**
 * Construtor do comando da linguagem Karel.
 * 
 * @param NoParameters
 * @return void
 * @see Ponto_Fanuc
 * 
 */
public P_Fanuc(Ponto ponto)
	{
	this.movimento = this.tipoMov(ponto.movimento);
	this.nomePts = ponto.nome;
	this.velocidade = this.EscolheVelocidade(ponto.velocidade);
	this.precisao = this.EscolhePrecisao(ponto.precisao);
	this.ferramenta = "";
	}
	
/**
 * Preenche comando de movimento na linguagem Karel.
 * 
 *@param op Receber um inteiro que vai representar o tipo do movimento.
 * @return Retorna uma string com o movimento na linguagem Karel.
 * @see Ponto_Fanuc
 * 
 */
public String tipoMov(int op){
	switch(op)
	{
	case(1):
	return this.movJ;	
	case(2):
	return this.movL;
	case(3):
	return this.movC;
	default:
	return this.movJ;
	}
}

/**
 * Retorna a velocidade no formato da linguagem Karel.
 * 
 * 
 *@param vel Recebe um inteiro como valor da velocidade do movimento.
 * @return Retorna a velocidade no padr o da linguagem Karel.
 * @see Ponto_Fanuc#toString()
 * 
 */
public String EscolheVelocidade(int vel){
 String velo="";
    velo+=vel;
 return velo;
}

/**
 * Retorna a precis o no formato da linguagem Karel.
 * 
 *@param pre Recebe um inteiro como valor da precis o do movimento.
 * @return Retorna a precis o no padr o da linguagem Karel.
 * @see Ponto_Fanuc#toString()
 * 
 */
public String EscolhePrecisao(int pre){
 String preci="";
 if (pre == 0)
 	preci+="FINE";
 else
 	preci+=pre;
 return preci;
}

/**
 * Retorna uma String com a linha de comando do ponto do 
 * tipo "L P[1] 100mm/s FINE"
 * 
 * @param NoParameters
 * @return Retorna o comando de movimento da linguagem Karel.
 * @see Ponto_Fanuc
 * 
 */
public String toString()
	{
	return "WITH $MOTYPE="+this.movimento +", $TERMTYPE= "+ this.precisao +
  ", $SPEED="+ this.velocidade +" MOVE TO "+ this.nomePts;
	}	


/**
 * Espera por um determinado tempo.
 * 
 * @param tempo Tempo de espera para executar a pr xima instru  o.
 * @return Retorna o comando de espera na linguagem Karel.
 * @see Ponto_Fanuc
 * 
 */
public String TempoEspera(float tempo)
{String str;
str = "WAIT "+tempo;
return str;
}
}