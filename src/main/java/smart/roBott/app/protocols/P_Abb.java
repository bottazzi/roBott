/*****************************
**  Projeto final - Ucsal
**	Vitor Santos Bottazzi
**	Implementation date 27/01/2003
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

public class P_Abb implements Protopon{

 final String movJ = "MoveJ";
 final String movL = "MoveL";
 final String movC = "MoveC";

protected String movimento;
protected String nomePts;
protected String velocidade;
protected String precisao;
protected String ferramenta;

Factory fabrica = new Factory();
public String nom = "ABB";
public int nomnum = 1;
public P_Abb() {}

/**
 * Construtor do comando da linguagem Rapid.
 * 
 * @param ponto Recebe um ponto gen rico.
 * @return void
 * @see Ponto_Abb
*/
public P_Abb(Ponto ponto)
	{
	this.movimento = this.tipoMov(ponto.movimento);
	this.nomePts = ponto.nome;
	this.velocidade = this.EscolheVelocidade(ponto.velocidade);
	this.precisao = this.EscolhePrecisao(ponto.precisao); //300);//
	this.ferramenta = "Tool0";//"pen\\wobj := ne";"Dedo";//"Dedo";//
	}
	
/**
 * Preenche comando de movimento da linguagem Rapid.
 * 
 * @param op Receber um inteiro que vai representar o tipo do movimento.
 * @return Retorna uma string com o movimento na linguagem Rapid.
 * @see Ponto_Abb
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
 * Retorna a velocidade no formato da linguagem Rapid.
 * 
 * @param vel Recebe um inteiro como valor da velocidade do movimento.
 * @return Retorna a velocidade no padr o da linguagem Rapid.
 * @see Ponto_Abb#toString()
 * 
 */
public String EscolheVelocidade(int vel){
 String velo="v10";
if (vel <= 100)
 velo=",v10";
else 
 {if (vel <= 500)
 	velo=",v500";
 else
  velo=",v1000";
  }
 return velo;
}

/**
 * Retorna a precis o no formato da linguagem Rapid.
 * 
 * 
 * @param pre Recebe um inteiro como valor da precis o do movimento.
 * @return Retorna a precis o no padr o da linguagem Rapid.
 * @see Ponto_Abb#toString()
 * 
 */
public String EscolhePrecisao(int pre){
 String preci="";
 if (pre <= 0)
 	preci+=",fine";
 else
 {if (pre>200)
 	preci=",z200";
  else
  preci+=",z"+pre;}
 return preci;
}

/**
 * Fecha a garra da solda a Ponto.
 * 
 * @param NoParamenters
 * @return Retorna o comando de Fechar Garra de Solda a Ponto na linguagem Rapid.
 * @see Ponto_Abb
 * 
 */
public String FechaGarra()
{String str = " WaitTime 1;/n PulseDO" + " " + "PLength:=1,close_tip1;";
//str = " WaitTime 1;/n PulseDO\PLength:=1,close_tip1;";
return str;
}

/**
 * Abre a garra da solda a Ponto.
 * 
 * @param NoParamenters
 * @return Retorna o comando de Abrir Garra sde Solda a Ponto na linguagem Rapid.
 * @see Ponto_Abb
 * 
 */
public String AbreGarra()
{String str = " WaitTime 1;/n PulseDO" + " " + "PLength:=1,full_open4;/n WaitTime 1;/n";
//str = " WaitTime 1;/n PulseDO\PLength:=1,full_open4;/n WaitTime 1;/n";
return str;
}

/**
 * Espera por um determinado tempo.
 * 
 * @param tempo Tempo de espera para executar a pr xima instru  o.
 * @return Retorna o comando de espera na linguagem Rapid.
 * @see Ponto_Abb
 * 
 */
public String TempoEspera(float tempo)
{String str;
str = "Wait "+tempo;
return str;
}

/**
 * Retorna uma String com a linha de comando do ponto do 
 * tipo "MoveL P1,v1000,z100,tool0". 
 * 
 * @param NoParameters
 * @return Retorna o comando de movimento da linguagem Rapid.
 * @see Ponto_Abb#toString()
 */
public String toString()
	{
	return this.movimento + " " + this.nomePts + this.velocidade + this.precisao +","+ this.ferramenta+";";
	}	

}