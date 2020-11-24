/*****************************
**  Projeto Mestrado - UMINHO
**	Vitor Santos Bottazzi
**	Data Cria  o: 20/20/2004
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

import otimiza.*;
import servico.*;

public class P_Sony implements Protopon{

 final String movJ = "DO";
 final String movL = "DO LINE:";
 final String movC = "DO CIRCLE:"; //para o manual fornecido n o foi encontrada refer ncia a mov circular, foi assumido o mov por junta.

protected String movimento;
protected String nomePts;
protected String velocidade;
protected String precisao;
protected String ferramenta;

Factory fabrica = new Factory();
	
public P_Sony() {}

/**
 * Construtor do comando da linguagem Trident.
 * 
 * @param ponto Recebe um ponto gen rico.
 * @return void
 * @see Ponto_Puma
*/
public P_Sony(Ponto ponto)
	{
	this.movimento = this.tipoMov(ponto.movimento);
	this.nomePts = ponto.nome;
	this.velocidade = this.EscolheVelocidade(ponto.velocidade);
	this.precisao = this.EscolhePrecisao(ponto.precisao);
	this.ferramenta = "T";
	}
	
/**
 * Preenche comando de movimento da linguagem Trident.
 * 
 * @param op Receber um inteiro que vai representar o tipo do movimento.
 * @return Retorna uma string com o movimento na linguagem Trident.
 * @see Ponto_Puma
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
 * Retorna a velocidade no formato da linguagem Trident.
 * 
 * @param vel Recebe um inteiro como valor da velocidade do movimento.
 * @return Retorna a velocidade no padr o da linguagem Trident.
 * @see Ponto_Puma#toString()
 * 
 */
public String EscolheVelocidade(int vel){
 String velo="";
if (vel <= 10)
 velo="DO VEL(10)";
else 
 {if (vel > 500)
 	velo="DO VEL(100)";
 else
  velo+="DO VEL(50)";}
 return velo;
}

/**
 * Retorna a precis o no formato da linguagem Trident.
 * 
 * 
 * @param pre Recebe um inteiro como valor da precis o do movimento.
 * @return Retorna a precis o no padr o da linguagem Trident.
 * @see Ponto_Puma#toString()
 * 
 */
public String EscolhePrecisao(int pre){
 String preci="FINE";
/* if (pre <= 0)
 	preci+=",fine";
 else
 {if (pre>200)
 	preci=",z200";
  else
  preci+=",z"+pre;}*/
 return preci;
}

/**
 * Retorna uma String com a linha de comando do ponto do 
 * tipo "MoveL P1,v1000,z100,tool0". 
 * 
 * @param NoParameters
 * @return Retorna o comando de movimento da linguagem Trident.
 * @see Ponto_Puma#toString()
 */
public String toString()
	{
	return this.movimento + " " + this.nomePts; // + this.velocidade + this.precisao +","+ this.ferramenta+";";
	}	

/**
 * Fecha a garra da solda a Ponto.
 * 
 * @param NoParamenters
 * @return Retorna o comando de Fechar Garra de Solda a Ponto na linguagem Trident.
 * @see Ponto_Puma
 * 
 */
public String FechaGarra()
{String str;
return str="CLOSE";
}

/**
 * Abre a garra da solda a Ponto.
 * 
 * @param NoParamenters
 * @return Retorna o comando de Abrir Garra sde Solda a Ponto na linguagem Trident.
 * @see Ponto_Puma
 * 
 */
public String AbreGarra()
{String str;
return str = "OPEN";
}

/**
 * Espera por um determinado tempo.
 * 
 * @param tempo Tempo de espera para executar a pr xima instru  o.
 * @return Retorna o comando de espera na linguagem Trident.
 * @see Ponto_Puma
 * 
 */
public String TempoEspera(float tempo)
{String str;
str = "DO P(X) DLY(" + tempo + ") L1(ON)"; //L1   uma saida
return str;
}

}