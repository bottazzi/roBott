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

import java.lang.Byte;
import coord.*;
import java.io.*;
import java.math.*;
import java.util.*;
import otimiza.*;
import servico.*;

public class ReadSTLFile extends TemplateFileIn
{
  public ReadSTLFile()
  {
  
  }


public static long arr2long (byte[] arr, int start)
{
		int i = 0;
		int len = 4;
		int cnt = 0;
		byte[] tmp = new byte[len];
		for (i = start; i < (start + len); i++)
    {
			tmp[cnt] = arr[i];
			cnt++;
		}
		long accum = 0;
		i = 0;
		for ( int shiftBy = 0; shiftBy < 32; shiftBy += 8 )
    {
			accum |= ( (long)( tmp[i] & 0xff ) ) << shiftBy;
			i++;
		}
		return accum;
}


public static float arr2float (byte[] arr, int start)
{
		int i = 0;
		int len = 4;
		int cnt = 0;
		byte[] tmp = new byte[len];
		for (i = start; i < (start + len); i++)
    {
			tmp[cnt] = arr[i];
			cnt++;
		}
		int accum = 0;
		i = 0;
		for ( int shiftBy = 0; shiftBy < 32; shiftBy += 8 )
    {
			accum |= ( (long)( tmp[i] & 0xff ) ) << shiftBy;
			i++;
		}
		return Float.intBitsToFloat(accum);
}

public Programa LeArqBin(Programa prog) throws Exception //, InputStream dis)
{
Coord_World coordWorld;
//Select sel = new Select();
long facetsnumber = 0;
String name = "";
int cnt = 0;
int i=0;
float maxz = 0.0f;
prog.PointSelArray.clear(); //limpa a lista hash para q imprima so o novo conj de pontos
try {
   File file = new File(prog.nome_arquivo_coord);
   InputStream is = new FileInputStream(file);
   DataInputStream dis = new DataInputStream( is );
   long length = file.length();
   if (length > Integer.MAX_VALUE) {
      throw new IOException("File is too large");
   } else {
      byte[] bytes = new byte[(int)length];
      int offset = 0;
      int numRead = 0;
      while (offset < bytes.length && 
         (numRead = is.read(bytes, offset, bytes.length-offset) ) >= 0) {
         offset += numRead;
      }
      if (offset < bytes.length) {
         throw new IOException("Could not completely read file "+file.getName());
      }
      dis.close();
      is.close();

      facetsnumber = arr2long(bytes, 80);
      prog.n_linhas_arqcoord = facetsnumber * 3;
      Float[][] temp_table = new Float[(int)prog.n_linhas_arqcoord][3];// novo array do tamanho do arquivo

      float normalx, normaly, normalz;
      float x = 0.0f;
      float y = 0.0f;
      float z = 0.0f;
      int start = 84;

      for (int ind = 0; ind < facetsnumber; ind++)
      {
        normalx = arr2float(bytes, start);
        start +=4;
        normaly = arr2float(bytes, start);
        start +=4;
        normalx = arr2float(bytes, start);
        start +=4;
        
        for (int icoord = 0; icoord <3; icoord++)
        {
        x = arr2float(bytes, start);
        start +=4;
        y = arr2float(bytes, start);
        start +=4;
        z = arr2float(bytes, start);
        start +=4;
        
      //Referencia do plano superior para a selec  o
//      if (maxz < z) maxz = z;
//      prog.planoSelZ = maxz;
      //
      
       coordWorld = new Coord_World(x,y,z);
   
    prog.PointSelArray.add(coordWorld);

        }
       start += 2; 
      }
   }
}
catch (Exception e)
{
   e.printStackTrace();
}

System.out.println("STL BIN file read!");

//sel.PointsSelection(prog);

return prog; 
}

public Programa LeArqASCII(Programa prog, BufferedReader in) throws Exception
{
  int cont = 0;
  int i=0;
  Float x,y,z;
//  Select sel = new Select();
  String linha = "";
  String delimitador = " "; // Isto pode ser passado como par metro dependendo do tipo do arquivo
   
   linha = in.readLine();
try
{
   while (!linha.trim().equals("endsolid"))//conta a quantidade de vertices para a cria  o do array
   {
   if(linha.trim().equals("endloop")) cont++;
   linha = in.readLine();
   }
   prog.n_linhas_arqcoord = cont * 3; //retorna a quantidade de outerloops X 3 = num coordenadas(n de variaveis necess rias para armazena-las)
   // System.out.println(prog.n_linhas_arqcoord);
   super.FechaArquivoASCII(in);
}
catch(Exception e)
{
System.out.print("!!!!!!!!!!Corrupted File!!!!!!!!!!");
}

   in1 = super.LerArquivoASCII(prog.nome_arquivo_coord);//usei in1 para ler mesmo o arquivo, in para contar as linhas
   linha = in1.readLine().trim();
   //int it = 0;
   Coord_World coordWorld;
   Float minz = new Float(0.0f); //igualar ao primeiro elemento
   Float maxz = new Float(0.0f); //igualar ao primeiro elemento

   prog.PointSelArray.clear(); //limpa a lista hash para q imprima so o novo conj de pontos
   while (!linha.equals("endsolid"))
   {
      
   StringTokenizer strtk = new java.util.StringTokenizer(linha, delimitador);
    
   if(strtk.nextToken().equals("vertex"))
      {
      x = new Float(Float.valueOf(strtk.nextToken()).floatValue());// + prog.offSet.Coord_X);
      y = new Float(Float.valueOf(strtk.nextToken()).floatValue());// + prog.offSet.Coord_Y);
      z = new Float(Float.valueOf(strtk.nextToken()).floatValue());// + prog.offSet.Coord_Z);
      
      //Referencia do plano superior para a selec  o
      //if (minz.floatValue() > z.floatValue()) minz = z;
     // if (maxz.floatValue() < z.floatValue()) maxz = z;
      
      //prog.planoSelZ = maxz.floatValue();
      
      coordWorld = new Coord_World(x.floatValue(),y.floatValue(),z.floatValue());
          prog.PointSelArray.add(coordWorld);   //.put(coordWorld, coordWorld);
      }
      linha = in1.readLine().trim();
      
    }
    super.FechaArquivoASCII(in1);
    System.out.println("STL ASCII file read!");

//sel.PointsSelection(prog);    

return prog; 
}
 
  
public void carregaCoord(BufferedReader in)
{
  
}

public void CabecalhoPrograma(BufferedReader in) throws Exception
{
  
}

public void CarregaListaCoord(Programa prog) throws Exception
{
StringTokenizer strtk;

in = super.LerArquivoASCII(prog.nome_arquivo_coord);
String linha = "";
linha = in.readLine();
linha = in.readLine();

if (linha == null)
{
     this.LeArqBin(prog);
}
else
{
linha = in.readLine();
strtk = new java.util.StringTokenizer(linha, " ");
if (strtk.nextToken().trim().equals("outer"))
    {
    this.LeArqASCII(prog, in);
    super.FechaArquivoASCII(in);
    }
else
    {
    this.LeArqBin(prog);
    }
}

//return prog;
}
  
}