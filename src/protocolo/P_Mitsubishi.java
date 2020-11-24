/*****************************
**  Projeto final - Ucsal
**	Vitor Santos Bottazzi
**	Data inicio: 27/01/2003
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

public class P_Mitsubishi implements Protopon{

 final String movJ = "DW";
 final String movL = "MO";//"DS";
 final String movC = "MVR";

protected String movimento;
protected String nomePts;
protected String velocidade;
protected String precisao;
protected String ferramenta;

Factory fabrica = new Factory();
	
public P_Mitsubishi()
{}

/**
 * Construtor do comando da linguagem MelfaBasic.
 * 
 * @param ponto Recebe um ponto gen rico.
 * @return void
 * @see Ponto_Mitsubishi
*/
public P_Mitsubishi(Ponto ponto)
	{
	this.movimento = this.tipoMov(ponto.movimento);
	this.nomePts = ponto.nome.substring(ponto.nome.indexOf("t")+1,ponto.nome.length() );
	this.velocidade = this.EscolheVelocidade(ponto.velocidade);
	this.precisao = this.EscolhePrecisao(ponto.precisao);
	this.ferramenta = "";
	}
	
/**
 * Preenche comando de movimento da linguagem MelfaBasic.
 * 
 * @param op Receber um inteiro que vai representar o tipo do movimento.
 * @return Retorna uma string com o movimento na linguagem MelfaBasic.
 * @see Ponto_Mitsubishi
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
	return this.movL;
	}
}

/**
 * Retorna a velocidade no formato da linguagem MelfaBasic.
 * 
 * @param vel Recebe um inteiro como valor da velocidade do movimento.
 * @return Retorna a velocidade no padr o da linguagem MelfaBasic.
 * @see Ponto_Mitsubishi#toString()
 * 
 */
public String EscolheVelocidade(int vel){
 String velo="";
 vel = vel/10;
if (vel <= 10)
 velo="OVRD 10";
else 
 {if (vel > 100)
 	velo="OVRD 100";
 else
  velo+="OVRD "+vel;}
 return velo;
}

/**
 * Retorna a precis o no formato da linguagem MelfaBasic.
 * 
 * 
 * @param pre Recebe um inteiro como valor da precis o do movimento.
 * @return Retorna a precis o no padr o da linguagem MelfaBasic.
 * @see Ponto_Mitsubishi#toString()
 * 
 */
public String EscolhePrecisao(int pre){
 String preci="";
 if (pre <= 0)
 	preci+="";
 else
 {if (pre>200)
 	preci=",-200";
  else
  preci+=",-"+pre;}
 return preci;
}

/**
 * Retorna uma String com a linha de comando do ponto do 
 * tipo "PD nomeponto,x,y,z,pitchangle, rollangle, Open/Close"
 *      "MO nomeponto". 
 * 
 * @param NoParameters
 * @return Retorna o comando de movimento da linguagem MelfaBasic.
 * @see Ponto_Mitsubishi#toString()
 */
public String toString()
	{
	return  this.velocidade+" "+ this.movimento + " " + this.nomePts + this.precisao;
	}	

/**
 * Fecha a garra da solda a Ponto.
 * 
 * @param NoParamenters
 * @return Retorna o comando de Fechar Garra na linguagem MelfaBasic.
 * @see Ponto_Mitsubishi
 * 
 */
public String FechaGarra()
{String str;
str = "HCLOSE 1";
return str;
}

/**
 * Abre a garra da solda a Ponto.
 * 
 * @param NoParamenters
 * @return Retorna o comando de Abrir Garra na linguagem MelfaBasic.
 * @see Ponto_Mitsubishi
 * 
 */
public String AbreGarra()
{String str;
str = "HOPEN 1";
return str;
}

/**
 * Espera por um determinado tempo.
 * 
 * @param tempo Tempo de espera para executar a pr xima instru  o.
 * @return Retorna o comando de espera na linguagem MelfaBasic.
 * @see Ponto_Mitsubishi
 * 
 */
public String TempoEspera(float tempo)
{String str;
str = "DLY "+tempo;
return str;
}

}