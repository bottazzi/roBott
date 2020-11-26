/*****************************
**  Projeto final - Ucsal
**	Vitor Santos Bottazzi
**	Implementation date 15/09/2002
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

package smart.roBott.app.lang;
import smart.roBott.app.service.*;
import smart.roBott.app.opt.*;
import smart.roBott.app.protocols.*;
import java.util.*;
import java.io.*;


public class Arquivo_MelfaBasic extends TemplateFileOut
{

Factory fabrica = new Factory();
int num_linha = 1000;
String compara_vel = "";
String nome_ini_movC = "";

public Arquivo_MelfaBasic()
{}



/**
 * Declara  o da variavel de Coordenadas dos pontos ABB.
 *
 * @param Iterator Recebe um iterator para a cole  o de nomes de vari veis de pontos.
 * @return Retorna uma string com a declara  o da variavel de Coordenadas dos pontos ABB.
 * @see String
 */
public String RetornaVarCoordPontos(Iterator it, String coord)
{String s = (String)it.next();
String declarVarPonto = "PD "+ s.substring(s.indexOf("t")+1,s.length())+ "," + coord +",0,0,C";//",Pitch angle,Roll angle,Open//close";
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
public String RetornaLinhaComando(Iterator it)
{
P_Mitsubishi mit = fabrica.getInstanciaPontoMitsubishi((Ponto)it.next());
return mit.toString();
}
/**
 * Incrementa o n mero de linhas. 
 * 
 * 
 */
public int contaLinha()
{
return this.num_linha += 10;  
}

/**
 * Divide o comando em duas linhas. E testa para n o repeti  o de velocodade.
 * 
 * @param 
 * @return void
 * @see BufferedWriter
 */
public void divideComando(BufferedWriter o, String comando) throws Exception
{
if (this.compara_vel.equals(comando.substring(0,comando.indexOf(" "))))
{

o.write(this.contaLinha()+" "+comando.substring(comando.indexOf(" ")+1,comando.length()));
o.newLine();
}
else
{
this.compara_vel = comando.substring(0,comando.indexOf(" "));

o.write(this.contaLinha()+" "+comando.substring(0,comando.indexOf(" ")));
o.newLine();

o.write(this.contaLinha()+" "+comando.substring(comando.indexOf(" ")+1,comando.length()));
o.newLine();
}
}


public void cabecalhoPrograma(BufferedWriter o) throws Exception{}

public void declaraVarsCorpoPrograma(BufferedWriter o, Programa progNovo) throws Exception
{
//*******************************************
int cont = 0;
//Parametros de coordenadas
Iterator itVarCoordPonto = progNovo.getItVarCoord();

  while(cont < progNovo.n_linhas_arqcoord)
  {
  String declarVar;
  declarVar = this.RetornaVarCoordPontos(itVarCoordPonto, (String)((progNovo.coord_table[cont][0].floatValue() + progNovo.RobotOffset.Coord_X) + "," + (progNovo.coord_table[cont][1].floatValue() + progNovo.RobotOffset.Coord_Y) + "," + (progNovo.coord_table[cont][2].floatValue() + progNovo.RobotOffset.Coord_Z)));
  o.write(this.contaLinha() + " " + declarVar);
  o.newLine();
  cont++;
  }
  progNovo.n_linhas_arqcoord = cont;
		//Linhas de Comando
		Iterator itLinhasComando = progNovo.getItLinhasComando();
    
		while(cont > 0)
		{
      String linha = "";
      linha = this.RetornaLinhaComando(itLinhasComando);
      
      this.divideComando(o,linha);
      cont--;
		}



//*******************************************
//Linhas de Comando
/*int cont = 0;
		Iterator itLinhasComando = progNovo.getItLinhasComando();
		while(cont < progNovo.n_linhas_arqcoord)//progNovo.coord_table[cont][0] != null)
		{
      String linha = "";
      linha = this.RetornaLinhaComando(itLinhasComando);
      
      this.divideComando(o,linha);
      cont++;
		}
    progNovo.n_linhas_arqcoord = cont;*/
}

/**
 * Cria um Arquivo Escritor(FILEWRITER) e grava como <nome do arquivo>.prg . 
 * 
 * @param progNovo Recebe o Programa que vai ser escrito no arquivo.
 *        Protocolo Recebe o inteiru que representa o protocolo de trabalho.
 * @return void
 * @see BufferedWriter
 */
public void CriarArquivo(String dir, Programa progNovo) throws Exception{

BufferedWriter out = super.CriarArquivo(dir + progNovo.getId()+".MB4");
		
		this.cabecalhoPrograma(out);
    this.declaraVarsCorpoPrograma(out,progNovo);
    super.fechaPrograma(out);
	}


}