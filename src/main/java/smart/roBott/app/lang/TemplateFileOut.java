
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
import java.io.*;

public abstract class TemplateFileOut 
{
BufferedWriter out;

 
 public TemplateFileOut()
  {
  }


public abstract void cabecalhoPrograma(BufferedWriter o) throws Exception;
public abstract void declaraVarsCorpoPrograma(BufferedWriter o,Programa progNovo) throws Exception;


/**
 * Cria um Arquivo Escritor(FILEWRITER) e grava como <nome do arquivo>.prg . 
 * 
 * @param progNovo Recebe o Programa que vai ser escrito no arquivo.
 *        Protocolo Recebe o inteiru que representa o protocolo de trabalho.
 * @return void
 * @see BufferedWriter
 */
public BufferedWriter CriarArquivo(String filepath) throws Exception{
  

 out = new BufferedWriter(new FileWriter(filepath));
		
    System.out.println("Programa criado em --> "+ filepath);
		
   return out;
		
	}

public void fechaPrograma(BufferedWriter o)throws Exception
{
  o.close();
}

}