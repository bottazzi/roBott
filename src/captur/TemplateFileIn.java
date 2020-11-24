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

package captur;
//import com.sun.j3d.loaders.Loader;
//import com.sun.j3d.loaders.Scene;
import servico.*;
import java.io.*;

public abstract class TemplateFileIn 
{
BufferedReader in, in1;
DataInputStream inbin;

  public TemplateFileIn()
  {
  }

public abstract void CabecalhoPrograma(BufferedReader i) throws Exception;

public abstract void CarregaListaCoord(Programa prog) throws Exception; //, Float[][] show_float

/**
 * Cria um Buffer Leitor de Arquivo(FILEREADER) . 
 * 
 * @param progNovo Recebe o caminho do arquivo de coordenadas que vai ser lido.
 *        Protocolo Recebe o inteiro que representa o tipo do arquivo que vai ser lido.
 * @return void
 * @see BufferedReader
 */
final BufferedReader LerArquivoASCII(String filepath) throws Exception
{
 in = new BufferedReader(new FileReader(filepath));
 return in;
}

static final void FechaArquivoASCII(BufferedReader i)throws Exception
{
  i.close();
}

}