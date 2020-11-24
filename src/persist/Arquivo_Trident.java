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

package persist;
import servico.*;
import otimiza.*;
import protocolo.*;
import java.util.*;
import java.io.*;


public class Arquivo_Trident extends TemplateFileOut
{

Factory fabrica = new Factory();

public Arquivo_Trident()
{}



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
P_Puma pum = fabrica.getInstanciaPontoPuma((Ponto)it.next());
return pum.toString();
}

public void cabecalhoPrograma(BufferedWriter o) throws Exception{}



public void declaraVarsCorpoPrograma(BufferedWriter o, Programa progNovo) throws Exception
{
//Linhas de Comando
  int cont = 0;
		Iterator itLinhasComando = progNovo.getItLinhasComando();
//   Iterator itCoordsPontos_linhasComando = progNovo.getItCoordPosicionamento();/*controla a escrita das linhas de comando*/
   /* 
		while(itLinhasComando.hasNext() && itCoordsPontos_linhasComando.hasNext())
		{
      String linha = "";
      linha = this.RetornaLinhaComando(itLinhasComando) + itCoordsPontos_linhasComando.next();// exception java.util.AbstractList$Itr@2
      o.write(linha);
      o.newLine();
		}*/
while(cont < progNovo.n_linhas_arqcoord)//progNovo.coord_table[cont][0] != null)
		{
      String linha = "";
      linha = this.RetornaLinhaComando(itLinhasComando) + "[" + progNovo.coord_table[cont][0] + "," + progNovo.coord_table[cont][1] + "," + progNovo.coord_table[cont][2] + "]";
      cont++;
      o.write(linha);
      o.newLine();
		}
    progNovo.n_linhas_arqcoord = cont;



}


public void fechaPrograma(BufferedWriter o) throws Exception
{
//Fecha arquivo
o.close();
}


/**
 * Cria um Arquivo Escritor(FILEWRITER) e grava como <nome do arquivo>.prg . 
 * 
 * @param progNovo Recebe o Programa que vai ser escrito no arquivo.
 *        Protocolo Recebe o inteiro que representa o protocolo de trabalho.
 * @return void
 * @see BufferedWriter
 */
public void CriarArquivo(String dir, Programa progNovo) throws Exception{

BufferedWriter out = super.CriarArquivo(dir + progNovo.getId()+".PUMA");
		
		this.cabecalhoPrograma(out);
    this.declaraVarsCorpoPrograma(out,progNovo);
    super.fechaPrograma(out);
	}
  
}