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

package otimiza;

import coord.*;
import javax.swing.JTextArea;
import servico.*;
import persist.*;
import protocolo.*;

import java.util.*;
//import servico.ConvexHull3DContour;

public class Carregador{

//int protocolo;
//int velocidade;
public Factory fabrica = new Factory();
public Programa pro_x;

public Programa Componente;

//public List ComponentsList = new ArrayList();
//public ConvexHull3DContour hulls;

/**
 * Construtor da classe Carregador.
 *
 * @param NoParameters
 * @return void
 * @see Class#Carregador
 * 
 */
public void Carregador()
{
this.pro_x = null;
}

public Programa getPrograma()
{
  return fabrica.getInstanciaNovoPrograma(); //Novo Programa para gerar codigo para outro arquivo de coord
}

/**
 * Instancia novo programa pelo nome e seta fabricante.
 *
 * @param prot Recebe o inteiro que representa o protocolo que vai ser utilizado para criar o progama.
 *        nome Recebe o nome do programa.
 * @return void
 * @see Class#Carregador
 * 
 */
public void CarregadorTipoNomePrograma(Programa prog, int prot, int vel, int prec){
Ponto p;
//foi isso q fazia o prog zerar o nr de linhas para escrita das variaveis e corpo
this.pro_x = prog;

this.pro_x.protocolo = prot; //vai ser passado para o carregaProtoco

Iterator it = this.pro_x.getItLinhasComando();
while(it.hasNext())
{
  p = (Ponto)it.next();
  p.velocidade = vel;//Atualiza a velocidade para a gera  o do programa do rob 
  p.precisao = prec;
}
}

/**
 * Instancia novo ponto e insere na cole  o.
 * 
 * @param movimento  Inteiro que representa o tipo do movimento.
 *        nomePonto  String que representa o ponto de passsagem do movimento.
 *        velocidade Valor Inteiro da velocidade.
 *        precisao   Valor Inteiro da precisao.
 * @return void
 * @Exception  Lan a uma exce ao caso j  exista o ponto inserido.
 * @see Class#Programa
 * 
 */  
public void CarregadorPonto( int vel, int precisao) throws Exception{
String n = null;
long cont = this.pro_x.n_linhas_arqcoord;
  while(cont > 0)
  {
  Ponto ponto = fabrica.getInstanciaPonto("Point" + n.valueOf(this.pro_x.num++), vel, precisao);
  //System.out.println(ponto.toString());
  this.pro_x.InsereComando(ponto);
  cont--;
  }
}

/**
 * Instancia a classe Armazenamento e chama seu m todo para a cria  o de um arquivo.
 * 
 * @param NoParameters
 * @return void
 * @throws @Exception  Lan a uma exce ao caso ocorra algum erro na cria  o do arquivo.
 * @see Class#Armazenamento
 * 
 */
public void CarregadorNomePrograma(String caminho)
{
this.pro_x.id_prog = caminho;
}	

/**
 * Instancia a classe Armazenamento e chama seu m todo para a cria  o de um arquivo.
 * 
 * @param NoParameters
 * @return void
 * @throws @Exception  Lan a uma exce ao caso ocorra algum erro na cria  o do arquivo.
 * @see Class#Armazenamento
 * 
 */
public void CarregadorArquivo(String dir, String nome_prog) throws Exception
{
if (nome_prog != null)
{

CarregaProtocolo carrpro = fabrica.getInstanciaCarregaProtocolo(this.pro_x.protocolo);
this.pro_x.id_prog = nome_prog;
carrpro.RetornaArquivoProprietarioFab(this.pro_x, dir);

}
}	

/**
 * Recebe o nome do arquivo de entrada das coordenadas.
 * 
 * @param NoParameters
 * @return void
 * @throws @Exception  Lan a uma exce ao caso ocorra algum erro na passagem do arquivo de entrada.
 * @see Class#T_Coordinates
 * 
 */
public void CarregaArquivoCoordEntrada(String caminho, Programa p) throws Exception //, Coord_World newOffSet)
{
//this.pro_x.nome_arquivo_coord = caminho; //vai ser preciso usar algum m todo para identificar o tipo do arquivo
p.nome_arquivo_coord = caminho; //vai ser preciso usar algum m todo para identificar o tipo do arquivo
T_Coordinates tCoord = fabrica.getInstanciaTCoordinates();
//return 
tCoord.LeArquivoProprietarioCoord(p);
}
/**
 * Chama o m todo toString da classe Programa.
 * 
 * @param NoParameters
 * @return Retorna atrav s do toString do programa gen rico, as linhas de comando carregadas.
 * @see Class#Programa
 * 
 */
public String toString()
{
return this.pro_x.toString();
}

}