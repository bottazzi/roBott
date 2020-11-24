/**
 * @author Vitor Santos Bottazzi
 * Data inicio: 21/08/2005	
 */

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

package coord;

import java.io.BufferedReader;
import otimiza.*;
import persist.*;
import servico.*;
import captur.*;

public class T_Coordinates 
{
final String TXT = "txt";
//final String DXF = "dxf";
final String STL = "stl";
final String OBJ = "obj";
//int C_type = this.TXT;
Factory fabrica = new Factory();

/**
 * Seta a coordenada de trabalho escolhido.
 * 
 * @param prot Recebe o inteiro que representa o protocolo de trabalho.
 * @return void
 * @see CarregaProtocolo
 * 
 */
public T_Coordinates()
  {

  }

/*public void T_Coordinates(String caminho)
  {
  //this.nomeArquivo = caminho;
  this.C_type = 0;
  }*/

/**
 * Retorna o objeto com o protocolo de comandos do fabricante. 
 * 
 * @param prot Recebe o inteiro que representa o protocolo de trabalho.
 * @return Retorna o objeto com o protocolo de comandos do fabricante. 
 * @see protocolo.Protopon
 * 
 */
public Object RetornaLinhaDeclaracaoCoord(int coord)
{
Object cod = null;
/*switch(coord)
  {case this.TXT:*/
    Coord_World wrld = fabrica.getInstanciaCoordWorld();
    cod=wrld;
    //break;
  /*case this.Joint:
    Coord_Joint jnt = fabrica.getInstanciaCoordJoint();
    cod=jnt;
    break;
  case this.MITSUBISHI:
    Ponto_Mitsubishi mit = fabrica.getInstanciaPontoMitsubishi();
    cod=mit;
    break;
  case this.PUMA:
    Ponto_Puma pum = fabrica.getInstanciaPontoPuma();
    cod=pum;
    break;*/
  /*default:
    return null;
  }*/
return cod;
}

/**
 * Retorna o objeto com o arquivo utilizado pelo fabricante. 
 * 
 * @param prot Recebe o inteiro que representa o protocolo de trabalho.
 * @return Retorna o objeto com arquivo de trabalho do fabricante. 
 * @see protocolo.Protopon
 * 
 */
public void LeArquivoProprietarioCoord(Programa prog) throws Exception
{

String s = prog.nome_arquivo_coord.substring(prog.nome_arquivo_coord.indexOf(".")+1,prog.nome_arquivo_coord.length());


if (s.equals(this.TXT) || s.equals(this.TXT.toUpperCase()))
{
  ReadWorldFile rwf = fabrica.getInstanciaLeitorArquivoWorld();
    //prog =
    rwf.CarregaListaCoord(prog);
}
/*
if (s.equals(this.DXF)|| s.equals(this.DXF.toUpperCase()))//carrega arquivos tabulados txt
 {
 
  ReadDXFile dxf = fabrica.getInstanciaLeitorArquivoDXF();
    prog = dxf.CarregaListaCoord(prog);
 }*/
if (s.equals(this.STL) || s.equals(this.STL.toUpperCase()))//carrega arquivos stl
 {
 
  ReadSTLFile stl = fabrica.getInstanciaLeitorArquivoSTL();
    //prog = 
    stl.CarregaListaCoord(prog);
 }
//return prog;
}
}