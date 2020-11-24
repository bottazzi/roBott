/*****************************
**  Projeto final - Ucsal
**	Vitor Santos Bottazzi
**	Data inicio: 15/09/2002
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

package persist;
import servico.*;
import otimiza.*;
import protocolo.*;
import java.util.*;
import java.io.*;


public class Arquivo_Rapid extends TemplateFileOut
{
Factory fabrica = new Factory();
String tipo = "PERS";//"CONST";
String offset = "";

public Arquivo_Rapid()
{

}
/**
 * Declara  o da variavel de Coordenadas dos pontos ABB.
 *
 * @param Iterator Recebe um iterator para a cole  o de nomes de vari veis de pontos.
 * @return Retorna uma string com a declara  o da variavel de Coordenadas dos pontos ABB.
 * @see String
 */
public String RetornaVarCoordPontos(Iterator itVar, String coord)
{                                                                                             //[0.027248,-0.011139,-0.999567,-0.000304],[-1,0,0,0]
String declarVarPonto = ""+ tipo +" robtarget "+(String)itVar.next().toString()+":=["+coord+",[0.003789,0.027131,-0.999609,-0.005718],[0,0,-1,0],[9E+09,9E+09,9E+09,9E+09,9E+09,9E+09]];";//",[0.00496385,0.212595,0.976688,-0.0293119],[-1,0,-1,0],[9E+09,9E+09,9E+09,9E+09,9E+09,9E+09]];";
return declarVarPonto;
		}

/**
 * Retorna string com linha de comando do fabricante.
 * 
 * @param
 * @return
 * @see
 * 
 */
public String RetornaLinhaComando(Iterator it_linha)//, Iterator it_coord)
{
P_Abb abb = fabrica.getInstanciaPontoAbb((Ponto)it_linha.next());
return abb.toString();
}


public void cabecalhoPrograma(BufferedWriter o) throws Exception{
o.write("%%%");//INICIO CABE ALHO
o.newLine();
o.write("  VERSION:1");
o.newLine();
o.write("  LANGUAGE:ENGLISH");
o.newLine();
o.write("%%%");
o.newLine();
o.newLine();//FIM CABE ALHO
}

public void declaraVarsCorpoPrograma(BufferedWriter o, Programa progNovo) throws Exception
{
int cont = 0;
o.write("MODULE "+ progNovo.getId());
o.newLine();
		
//Parametros de coordenadas e quadrantes
Iterator itVarCoordPonto = progNovo.getItVarCoord();

  while(cont < progNovo.n_linhas_arqcoord)
  {
  String declarVar;
  //declarVar = this.RetornaVarCoordPontos(itVarCoordPonto, "[" + (progNovo.coord_table[cont][0].floatValue() + progNovo.WorkSpaceOffset.Coord_X) + "," + (progNovo.coord_table[cont][1].floatValue() + progNovo.WorkSpaceOffset.Coord_Y) + "," + (progNovo.coord_table[cont][2].floatValue() + progNovo.WorkSpaceOffset.Coord_Z) + "]");
declarVar = this.RetornaVarCoordPontos(itVarCoordPonto, "[" + (progNovo.coord_table[cont][0].floatValue()) + "," + (progNovo.coord_table[cont][1].floatValue() ) + "," + (progNovo.coord_table[cont][2].floatValue()) + "]");
  o.write(declarVar);
  o.newLine();
  cont++;
  }
  progNovo.n_linhas_arqcoord = cont;
  //Principal
		o.write("  PROC main()");
		o.newLine();
   
   //********************************************
    o.write("    ConfL \\Off;");
		o.newLine();
   //********************************************
		
		//Linhas de Comando
		Iterator itLinhasComando = progNovo.getItLinhasComando();
    //Iterator itPonto0 = itLinhasComando;
    String linha = "";
    String linhaPonto0 = "";
		while(cont > 0)
		{
      linha = this.RetornaLinhaComando(itLinhasComando);//, itCoordsPontos_linhasComando); exception java.util.AbstractList$Itr@2
      if (cont == progNovo.n_linhas_arqcoord) 
      {
      o.write("    "+linha);
      o.newLine();
      linhaPonto0 = linha;      }
      else
      {
      o.write("    "+linha);
      o.newLine();
      }

      cont--;
		}

    o.write("    " + linhaPonto0);
    o.newLine();

    //********************************************
    o.write("    ConfL \\On;");
		o.newLine();
   //********************************************

}


public void fechaPrograma(BufferedWriter o) throws Exception
{
o.write("  ENDPROC");
o.newLine();
o.write("ENDMODULE");
o.newLine();
//Fecha arquivo
super.fechaPrograma(o);
}

/**
 * Cria um Arquivo Escritor(FILEWRITER) e grava como <nome do arquivo>.prg . 
 * 
 * @param progNovo Recebe o Programa que vai ser escrito no arquivo.
 *        Protocolo Recebe o inteiru que representa o protocolo de trabalho.
 * @return void
 * @see BufferedWriter
 */
public void CriarArquivo(String dir, Programa progNovo) throws Exception
    {
		BufferedWriter out = super.CriarArquivo(dir + progNovo.getId()+".PRG");
		
		this.cabecalhoPrograma(out);
    this.declaraVarsCorpoPrograma(out, progNovo);
    this.fechaPrograma(out);
		}

/**
 * Retorna uma String com a linha de coordenadas do ponto do 
 * tipo "[x,y,z]". 
 * 
 * @param NoParameters
 * @return Retorna as coordenadas xyz da linguagem Rapid.
 * @see 
 
public String CoordToString()
	{
	return null;//"["+ +","+ +","+ +"]";
	}*/	

}
