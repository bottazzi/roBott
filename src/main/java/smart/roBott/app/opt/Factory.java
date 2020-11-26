/*****************************
**  Projeto Mestrado - UMINHO
**	Vitor Santos Bottazzi
**	Implementation date: 20/20/2004
*****************************/

/*****************************
**  Projeto final - Ucsal
**	Vitor Santos Bottazzi
**	Implementation date: 05/11/2002
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

package smart.roBott.app.opt;
import smart.roBott.app.lang.*;
import smart.roBott.app.protocols.*;
import smart.roBott.app.service.*;
import smart.roBott.app.coord.*;
import smart.roBott.app.captur.*;

final public class Factory {

Ponto ponto;
Programa prog;

CarregaProtocolo carregapro;
T_Coordinates templcoord;

P_Abb abb;
P_Fanuc fanuc;
P_Mitsubishi mit;
P_Puma pum;
P_Sony sny;


Arquivo_Rapid arq_Abb;	
Arquivo_Karel arq_K;
Arquivo_MelfaBasic arq_M;
Arquivo_Trident arq_P;

Coord_World coord_w;
Coord_Joint coord_j;

ReadWorldFile rw;
//ReadDXFile dxf;
ReadSTLFile stl;

public Factory(){}



/**
 * Instancia um ponto gen rico.
 * 
 * @param newMovimento  Inteiro que representa o tipo do movimento
 *        newNome       String que representa o ponto de passsagem do movimento
 *        newVelocidade Valor Inteiro da velocidade
 *        newPrecisao   Valor Inteiro da precisao
 * @return void
 * @see Class#Ponto
 * 
 */
public Ponto getInstanciaPonto(String newNome ,int newVelocidade, int newPrecisao){
         return ponto == null ? new Ponto(newNome, newVelocidade, newPrecisao): ponto;
    }

public Coord_World getInstanciaCoordWorld(float newX,float newY,float newZ)
{
return new Coord_World(newX,newY,newZ);
}
/*
public Coord_Joint getInstanciaCoordJoint()
{
return new Coord_Joint();
}*/

/**
 * Instancia um objeto que conhece os comandos da linguagem Rapid.
 * 
 * @param NoParameters
 * @return Retorna um objeto que conhece os comandos da linguagem Rapid.
 * @see Class#Ponto_Abb
*/
public P_Abb getInstanciaPontoAbb(){
         return abb == null ? abb = new P_Abb() : abb;
    }

/**
 * Instancia um objeto que conhece os comandos da linguagem Karel.
 * 
 * @param NoParameters
 * @return Retorna um objeto que conhece os comandos da linguagem Karel.
 * @see Class#Ponto_Fanuc
*/
public P_Fanuc getInstanciaPontoFanuc(){
         return fanuc == null ? fanuc = new P_Fanuc() : fanuc;
    }

/**
 * Instancia um objeto que conhece os comandos da linguagem MelfaBasic.
 * 
 * @param NoParameters
 * @return Retorna um objeto que conhece os comandos da linguagem MelfaBasic.
 * @see Class#Ponto_Fanuc
*/
public P_Mitsubishi getInstanciaPontoMitsubishi(){
         return mit == null ? mit = new P_Mitsubishi() : mit;
    }


/**
 * Instancia um objeto que conhece os comandos da linguagem Trident.
 * 
 * @param NoParameters
 * @return Retorna um objeto que conhece os comandos da linguagem Trident.
 * @see Class#Ponto_Fanuc
*/
public P_Puma getInstanciaPontoPuma(){
         return pum == null ? pum = new P_Puma() : pum;
    }

/**
 * Instancia um objeto que conhece os comandos da linguagem SRX(Sony).
 * 
 * @param ponto Recebe um ponto gen rico.
 * @return Retorna um objeto que conhece os comandos da linguagem SRX.
 * @see Class#P_Sony
*/
public P_Sony getInstanciaPontoSony(){
         return sny == null ? sny = new P_Sony() : sny;
}
/**
 * Instancia um objeto que conhece os comandos da linguagem Rapid.
 * 
 * @param ponto Recebe um ponto gen rico.
 * @return Retorna um objeto que conhece os comandos da linguagem Rapid.
 * @see Class#Ponto_Abb
*/
public P_Abb getInstanciaPontoAbb(Ponto p){
         return new P_Abb(p);
    }
    
/**
 * Instancia um objeto que conhece os comandos da linguagem Karel.
 * 
 * @param ponto Recebe um ponto gen rico.
 * @return Retorna um objeto que conhece os comandos da linguagem Karel.
 * @see Class#Ponto_Fanuc
*/
public P_Fanuc getInstanciaPontoFanuc(Ponto p){
         return new P_Fanuc(p);
}

/**
 * Instancia um objeto que conhece os comandos da linguagem MelfaBasic.
 * 
 * @param ponto Recebe um ponto gen rico.
 * @return Retorna um objeto que conhece os comandos da linguagem MelfaBasic.
 * @see Class#Ponto_Mitsubishi
*/
public P_Mitsubishi getInstanciaPontoMitsubishi(Ponto p){
         return new P_Mitsubishi(p);
}

/**
 * Instancia um objeto que conhece os comandos da linguagem Trident.
 * 
 * @param ponto Recebe um ponto gen rico.
 * @return Retorna um objeto que conhece os comandos da linguagem Trident.
 * @see Class#P_Puma
*/
public P_Puma getInstanciaPontoPuma(Ponto p){
         return new P_Puma(p);
}


/**
 * Instancia um objeto que conhece os comandos da linguagem SRX(Sony).
 * 
 * @param ponto Recebe um ponto gen rico.
 * @return Retorna um objeto que conhece os comandos da linguagem SRX.
 * @see Class#P_Sony
*/
public P_Sony getInstanciaPontoSony(Ponto p){
         return new P_Sony(p);
}

/**
 * Instancia um objeto programa.
 * 
 * @param nome Recebe o nome identificador do programa.
 * @return Retorna um objeto programa.
 * @see Class#Programa
 * 
 */ 
public Programa getInstanciaPrograma()
{
	return prog == null ? prog = new Programa() : prog;
}

/**
 * Instancia um objeto programa.
 * 
 * @param nome Recebe o nome identificador do programa.
 * @return Retorna um objeto programa.
 * @see Class#Programa
 * 
 */ 
static public Programa getInstanciaNovoPrograma()
{
	return new Programa();
}

/**
 * Instancia um objeto de Armazenamento para a linguagem Rapid.
 * 
 * @param NoParameters
 * @return Retorna um objeto Armazenamento.
 * @see persist.Arquivo_Rapid
 * 
 */
public Arquivo_Rapid getInstanciaArquivo_Rapid(){
         return arq_Abb == null ? arq_Abb = new Arquivo_Rapid() : arq_Abb;
}	
/**
 * Instancia um objeto de Armazenamento para a linguagem Karel.
 * 
 * @param NoParameters
 * @return Retorna um objeto Armazenamento.
 * @see persist.Arquivo_Karel
 * 
 */
public Arquivo_Karel getInstanciaArquivo_Karel(){
         return arq_K == null ? arq_K = new Arquivo_Karel() : arq_K;
}	

/**
 * Instancia um objeto de Armazenamento para a linguagem MelfaBasicIV.
 * 
 * @param NoParameters
 * @return Retorna um objeto Armazenamento.
 * @see persist.Arquivo_MelfaBasic
 * 
 */
public Arquivo_MelfaBasic getInstanciaArquivo_MelfaBasic(){
         return arq_M == null ? arq_M = new Arquivo_MelfaBasic() : arq_M;
}	 

/**
 * Instancia um objeto de Armazenamento para a linguagem Trident.
 * 
 * @param NoParameters
 * @return Retorna um objeto Armazenamento.
 * @see persist.Arquivo_Trident
 * 
 */
public Arquivo_Trident getInstanciaArquivo_Trident(){
         return arq_P == null ? arq_P = new Arquivo_Trident() : arq_P;
}	 



/**
 * Instancia um objeto de carga de protocolo de trabalho.
 * 
 * @param protocolo Recebe o protocolo corrente de trabalho.
 * @return Retorna um protocolo para rob s.
 * @see protocolo.CarregaProtocolo
 * 
 */
public CarregaProtocolo getInstanciaCarregaProtocolo(int protocolo){
         return carregapro == null? carregapro = new CarregaProtocolo(protocolo) : carregapro;
}
    
/**
 * Instancia um objeto de carga de coordenada de trabalho.
 * 
 * @param protocolo Recebe o protocolo corrente de trabalho.
 * @return Retorna um protocolo para rob s.
 * @see protocolo.CarregaProtocolo
 * 
 */
public T_Coordinates getInstanciaTCoordinates(){
return templcoord == null? templcoord = new T_Coordinates() : templcoord;
}

public ReadWorldFile getInstanciaLeitorArquivoWorld()
{
return rw == null ? rw = new ReadWorldFile() : rw;
}

/*public ReadDXFile getInstanciaLeitorArquivoDXF()
{
return dxf == null ? dxf = new ReadDXFile() : dxf;
}*/

public ReadSTLFile getInstanciaLeitorArquivoSTL()
{
return stl == null ? stl = new ReadSTLFile() : stl;
}


public Coord_World getInstanciaCoordWorld()
{
return coord_w = new Coord_World();
}

public Coord_Joint getInstanciaCoordJoint()
{
return coord_j = new Coord_Joint();
}


}