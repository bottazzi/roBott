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

package smart.roBott.app.captur;

import smart.roBott.app.coord.*;
import java.io.*;
import java.util.*;
import smart.roBott.app.opt.*;
import smart.roBott.app.service.*;

public class ReadWorldFile extends TemplateFileIn
{
  public ReadWorldFile()
  {
  
  }

public void CabecalhoPrograma(BufferedReader in) throws Exception
{
  
}

public void CarregaListaCoord(Programa prog) throws Exception
{
  int cont = 0;
  int i=0;
  String linha;
  String delimitador = "\t"; // Isto pode ser passado como par metro dependendo do tipo do arquivo
  Coord_World coordWorld;
  Select sel = new Select();
  Float maxz = new Float(0.0f); //igualar ao primeiro elemento

  in = super.LerArquivoASCII(prog.nome_arquivo_coord);
  
  Factory fabrica = new Factory();
   
   while ((linha = in.readLine()) != null)//conta o tamanho do arquivo
   {cont++;}
   super.FechaArquivoASCII(in);
   prog.n_linhas_arqcoord = cont; //retorna a quantidade de coordenadas(n de variaveis necess rias para armazena-las)

   Float[][] temp_table = new Float[cont][3];// novo array do tamanho do arquivo

   in1 = super.LerArquivoASCII(prog.nome_arquivo_coord);//usei in1 para ler mesmo o arquivo, in para contar as linhas
   
   while ((linha = in1.readLine()) != null)
   {
      StringTokenizer strtk = new java.util.StringTokenizer(linha, delimitador);
                                                                                  //coloca o offset em x de 300
      temp_table[i][0] = new Float(Float.valueOf(strtk.nextToken()).floatValue());// + prog.offSet.Coord_X);//arrumar o arquivo de captura, colocar um enter no fim de cada linha
      temp_table[i][1] = new Float(Float.valueOf(strtk.nextToken()).floatValue());// + prog.offSet.Coord_Y);
      temp_table[i][2] = new Float(Float.valueOf(strtk.nextToken()).floatValue());// + prog.offSet.Coord_Z);
      
//     if (maxz.floatValue() < temp_table[i][2].floatValue()) maxz = temp_table[i][2];
//        prog.planoSelZ = maxz.floatValue();


      coordWorld = new Coord_World(temp_table[i][0].floatValue(),temp_table[i][1].floatValue(),temp_table[i][2].floatValue());
          prog.PointSelArray.add(coordWorld);   //.put(coordWorld, coordWorld);


      i++;      
      }

     super.FechaArquivoASCII(in1);
     prog.coord_table = (Float[][])temp_table.clone(); //faz uma copia da tabela de visualiza  o para as classes de neg cio
     
     sel.PointsSelection(prog);
//    return prog;
}
  
}