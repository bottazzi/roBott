/*****************************
**  Projeto Mestrado - UMINHO
**	Vitor Santos Bottazzi
**	Data Altera  o: 20/20/2004
*****************************/

/*****************************
**  Projeto final - Ucsal
**	Vitor Santos Bottazzi
**	Data inicio: 05/11/2002
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
import servico.*;
import otimiza.*;
import persist.*;

public class CarregaProtocolo 
{
final int ABB = 1;
final int FANUC = 2;
final int MITSUBISHI = 3;
final int PUMA = 4;
final int SONY = 5;
int tipoProt;

Factory fabrica = new Factory();

/**
 * Seta o protocolo de trabalho.
 * 
 * @param prot Recebe o inteiro que representa o protocolo de trabalho.
 * @return void
 * @see CarregaProtocolo
 * 
 */
public CarregaProtocolo(int prot)
  {
  this.tipoProt = prot;
  }


/**
 * Retorna o objeto com o protocolo de comandos do fabricante. 
 * 
 * @param prot Recebe o inteiro que representa o protocolo de trabalho.
 * @return Retorna o objeto com o protocolo de comandos do fabricante. 
 * @see protocolo.Protopon
 * 
 
public Object RetornaLinhaComando(int prot)
{Object cod = null;
switch(prot)
  {case this.ABB:
    P_Abb abb = fabrica.getInstanciaPontoAbb();
    cod=abb;
    break;
  case this.FANUC:
    P_Fanuc fan = fabrica.getInstanciaPontoFanuc();
    cod=fan;
    break;
  case this.MITSUBISHI:
    P_Mitsubishi mit = fabrica.getInstanciaPontoMitsubishi();
    cod=mit;
    break;
  case this.PUMA:
    P_Puma pum = fabrica.getInstanciaPontoPuma();
    cod=pum;
    break;
    case this.SONY:
    P_Sony sny = fabrica.getInstanciaPontoSony();
    cod=sny;
    break;
  default:
    cod = null;
  }
return cod;
}*/

/**
 * Retorna o objeto com o arquivo utilizado pelo fabricante. 
 * 
 * @param prot Recebe o inteiro que representa o protocolo de trabalho.
 * @return Retorna o objeto com arquivo de trabalho do fabricante. 
 * @see protocolo.Protopon
 * 
 */
public Object RetornaArquivoProprietarioFab(Programa prog, String dir) throws Exception
{
switch(prog.protocolo)
  {case ABB:
    Arquivo_Rapid rap = fabrica.getInstanciaArquivo_Rapid();
    rap.CriarArquivo(dir, prog);
    return rap;

  case FANUC:
    Arquivo_Karel krl = fabrica.getInstanciaArquivo_Karel();
    krl.CriarArquivo(dir, prog);
    return krl;

  case MITSUBISHI:
    Arquivo_MelfaBasic mlf = fabrica.getInstanciaArquivo_MelfaBasic();
    mlf.CriarArquivo(dir, prog);
    return mlf;

 case PUMA:
    Arquivo_Trident tri = fabrica.getInstanciaArquivo_Trident();
    tri.CriarArquivo(dir, prog);
    return tri;

  default:
    return null;

  }

}

}