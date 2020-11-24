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


public class Arquivo_Karel extends TemplateFileOut
{
private Map diretorio = new HashMap();
Factory fabrica = new Factory();
String nome;


public Arquivo_Karel()
{}

/**
 * Armazena numa cole  o HashMap(),com nome do programa como indice, e as cole  es de ArrayList().
 *  
 * @param Programa Recebe o objeto programa que vai ser armazenado.
 * @return void
 * @see HashMap
 *      ArrayList
 */
protected void ArmazenarProg(Programa newProg) throws Exception
{
	if (diretorio.containsKey(newProg.getId())){
		throw new Exception("Programa j  existe!");
	}
	else{
		diretorio.put(newProg.getId(),newProg.listaComandos);
	}
}	

/**
 * Declara  o da variavel de Coordenadas dos pontos ABB.
 *
 * @param Iterator Recebe um iterator para a cole  o de nomes de vari veis de pontos.
 * @return Retorna uma string com a declara  o da variavel de Coordenadas dos pontos ABB.
 * @see String
 */
public String RetornaVarCoordPontos(Iterator itVar, String coord)
{
String declarVarPonto = "  "+(String)itVar.next()+": POS(" +coord + ",0,0,0,'');";
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
P_Fanuc abb = fabrica.getInstanciaPontoFanuc((Ponto)it.next());
return abb.toString();
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
o.write(comando.substring(0,comando.indexOf(" ")));
o.newLine();
o.write("   "+comando.substring(comando.indexOf(" ")+1,comando.length()));
o.newLine();
}


public void cabecalhoPrograma(BufferedWriter o) throws Exception
{
		o.write("PROGRAM "+ this.nome);//INICIO DO PROGRAMA
    o.newLine();
    o.write("-- ! LANGUAGE KAREL 2");//INICIO CABE ALHO
		o.newLine();
		o.write("-- ! MEMORY 8192");
		o.newLine();
		o.write("-- ! ROBOT IRB1400");
		o.newLine();
		o.write("-- TEACHPOINT DECLARATIONS");
    o.newLine();//FIM CABE ALHO

}

public void declaraVarsCorpoPrograma(BufferedWriter o, Programa progNovo) throws Exception
{
int cont = 0;
o.write("VAR");
o.newLine();
o.newLine();
		
		//Parametros de coordenadas e quadrantes
		Iterator itVarCoordPonto = progNovo.getItVarCoord();
    //Iterator itCoordsPontos_declaraVar = progNovo.getItCoordPosicionamento(); /*controla a escrita das variaveis*/
/*
		while(itVarCoordPonto.hasNext() && itCoordsPontos_declaraVar.hasNext())
		{
      String declarVar;
      declarVar = this.RetornaVarCoordPontos(itVarCoordPonto, itCoordsPontos_declaraVar);
      o.write(declarVar);
      o.newLine();
      cont++;
		}
		o.newLine();*/
		
  while(cont < progNovo.n_linhas_arqcoord)//progNovo.coord_table[cont][0] != null)
  {
  String declarVar;
  declarVar = this.RetornaVarCoordPontos(itVarCoordPonto, "[" + progNovo.coord_table[cont][0] + "," + progNovo.coord_table[cont][1] + "," + progNovo.coord_table[cont][2] + "]");
  o.write(declarVar);
  o.newLine();
  cont++;
	}
  progNovo.n_linhas_arqcoord = cont;
  o.newLine();
  
    
    
    
		//Principal
	  o.write("BEGIN");
		o.newLine();
		
		//Linhas de Comando
		Iterator itLinhasComando = progNovo.getItLinhasComando();
    
		while(cont > 0)
		{
      String linha = "";
      linha = this.RetornaLinhaComando(itLinhasComando);// exception java.util.AbstractList$Itr@2
      this.divideComando(o,linha);
      cont--;
		}
}


public void fechaPrograma(BufferedWriter o) throws Exception
{
o.write("END " + this.nome);
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
		BufferedWriter out = super.CriarArquivo(dir + progNovo.getId()+".KRL");
		this.nome = progNovo.getId();
		this.cabecalhoPrograma(out);
    this.declaraVarsCorpoPrograma(out,progNovo);
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
  Iterator it = prog
	return +","+ +","+ +"]";
	} */

}