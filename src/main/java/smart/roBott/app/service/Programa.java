/**
 * @author Vitor Santos Bottazzi
 * Implementation date 15/09/2002	
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

package smart.roBott.app.service;

import smart.roBott.app.coord.Coord_World;
import java.util.*;
import java.text.*;
import java.lang.String;

public class Programa
{
	public String id_prog = "";
    
	public List listaComandos;
  public List PointSelArray; //ArrayList
  public long n_linhas_arqcoord; //long
  public float planoSelZ = 100.0f;
  //Workspace bounds
  public static float infx = 284.0f,
      supx = 737.0f,
      infy = -252.0f,
      supy = 262.0f,
      infz = 0.0f,
      //infz = 155.0f,
      supz = 736.0f;
  
  
  public int num;
	public List listaPontosVarCoord;
  
  public String nome_arquivo_coord;
  public Float[][] coord_table = {{null,null,null}};
  public Coord_World WorkSpaceBounds; // Armazena o Offset(ponto de ref do programa)
  public Coord_World RobotOffset; // Armazena o Offset(ponto de ref do programa)
  public int protocolo;
  public boolean isMesh;
  //public int velocidade;
//  public String lastPointInserted; Para inserir a var mov circular
  
public Programa()
{
this.nome_arquivo_coord = "teste.txt";
this.setId("teste");
this.listaComandos = new ArrayList();
this.listaComandos.clear();
this.PointSelArray = new ArrayList();
this.PointSelArray.clear();
this.n_linhas_arqcoord = 0;
this.num = 0;
this.listaPontosVarCoord = new ArrayList();
this.listaPontosVarCoord.clear();
this.protocolo = 1;
this.isMesh = true;

this.WorkSpaceBounds = new Coord_World((this.supx - this.infx), (this.supy - this.infy), (this.supz - this.infz));
this.RobotOffset = new Coord_World(this.WorkSpaceBounds.Coord_X/2 + this.infx, this.WorkSpaceBounds.Coord_Y/2 + this.infy, 0.0f ); //p.infz-offsetz //-offsetz //-this.infz


//this.velocidade = 1;
}

/**
 * Construtor do Programa, que carrega duas listas, uma para os comandos e outra
 * para separar as vari veis.
 * 
 * @param newId Recebe o nome identificador do programa.
 * @see java.util.ArrayList
 * 
 */
public Programa(String newId)
{
this.nome_arquivo_coord = "teste.txt";
this.setId(newId);
this.listaComandos = new ArrayList();
this.listaComandos.clear();
this.PointSelArray = new ArrayList();
this.PointSelArray.clear();
this.n_linhas_arqcoord = 0;
this.num = 0;
this.listaPontosVarCoord = new ArrayList();
this.listaPontosVarCoord.clear();
this.protocolo = 1;
this.isMesh = true;

this.WorkSpaceBounds = new Coord_World((this.supx - this.infx), (this.supy - this.infy), (this.supz - this.infz));
this.RobotOffset = new Coord_World(this.WorkSpaceBounds.Coord_X/2 + this.infx, this.WorkSpaceBounds.Coord_Y/2 + this.infy, 0.0f ); //p.infz-offsetz //-offsetz //-this.infz
//this.velocidade = 1;
}	

/**
 * Seta o nome identificador do programa.
 * 
 * @param newId Recebe o nome identificador do programa.
 * @return void
 * @see Programa
 * 
 */
protected void setId(String newId)
	{
	this.id_prog = newId.toUpperCase();
	}
	
/**
 * Retorna o nome identificador do programa.
 * 
 * @param NoParameters
 * @return O nome identificador do programa.
 * @see Programa
 */
public String getId()
	{
	return this.id_prog;
	}

/**
 * Retorna o nome identificador do programa.
 * 
 * @param NoParameters
 * @return O nome identificador do programa.
 * @see Programa
 */
public void Update(Float[][] coordTable)
	{
	//atualiza apenas as posi  es alteradas
	}


/**
 * Retorna um iterator para a Lista de Comandos  do Programa.
 * 
 * @param NoParameters
 * @return Retorna um apontador para cada objeto da lista de comandos do 
 * programa.
 * @see java.util.Iterator
 */
public Iterator getItLinhasComando()
	{
	return this.listaComandos.iterator();
	}

/**
 * Retorna um iterator para a lista de vari veis dos comandos do programa.
 * 
 * @param NoParameters
 * @return Retorna um apontador para cada objeto da lista de vari veis dos
 * comandos  do programa.
 * @see java.util.Iterator
 */
public Iterator getItVarCoord()
	{
	return this.listaPontosVarCoord.iterator();
	}

/**
 * Insere um ponto gen rico na lista de comandos, testando se o mesmo j  existe.
 * 
 * @param ponto Recebe um objeto Ponto.
 * @return void
 * @exception Se j  existe o ponto na lista, lan a uma exce  o.
 * @see Ponto
 */
public void InsereComando(Ponto ponto) throws Exception
{
  if (this.listaComandos.contains(ponto))// teste de unicidade no vetor de pontos
      throw new Exception("AVISO!! Este ponto j  existe!");
    else
    {
    this.listaComandos.add(ponto);
    this.listaPontosVarCoord.add(ponto.nome);
    }
/*  if (ponto.movimento == 3)
    this.InserePontosMovC(ponto.nome);
  else
  {
    if (this.listaPontosVarCoord.contains(ponto.nome))// teste de unicidade no vetor de coordenadas de pontos
      throw new Exception("AVISO!! Ponto j  marcado atingido novamente!");
    else
      {this.lastPointInserted = ponto.nome;
      this.listaPontosVarCoord.add(this.lastPointInserted);
       }
  }*/
}

/**
 * Insere as variveis das coordenadas circulares   lista de pontos das vari veis
 * das coordenadas.
 * 
 * @param p1 Recebe string com as vari veis, separadas por v rgulas.
 * @return void
 * @see java.text.SubString
 * 
 
public void InserePontosMovC(String p1) throws Exception
{
if (this.listaPontosVarCoord.contains(p1.substring(0,p1.indexOf(","))))
  {if (!this.listaPontosVarCoord.contains(p1.substring(p1.indexOf(",")+1,p1.length())))
    {this.lastPointInserted = p1.substring(p1.indexOf(",")+1,p1.length());//guarda ponto para MOV Circular
    this.listaPontosVarCoord.add(this.lastPointInserted);//pega a segunda string,depois da virgula
     }
  throw new Exception("AVISO!! Ponto j  marcado atingido novamente!");
  }
  else
    this.listaPontosVarCoord.add(p1.substring(0,p1.indexOf(",")));//pega a primeira string, antes da virgula

if (this.listaPontosVarCoord.contains(p1.substring(p1.indexOf(",")+1,p1.length())))
    throw new Exception("AVISO!! Ponto j  marcado atingido novamente!");
  else
    this.listaPontosVarCoord.add(p1.substring(p1.indexOf(",")+1,p1.length()));//pega a segunda string,depois da virgula
}*/ //N estou utilizando mov circulares no momento

/**
 * Percorre a lista de comandos gen ricos, mostrando o seu conte do.
 * 
 * @param NoParameters
 * @return Retorna a lista de comandos gen ricos.
 * @see java.util.ArrayList
 * 
 */
protected String MostraArray(){
	String acum = "";
	Iterator it = this.getItLinhasComando();
	while(it.hasNext())
	{
	acum += (it.next().toString()+"\n");
	}return acum;
}

  
  /**
 * Retorna uma string com as coordenadas dos pontos de passagem.
 * 
 * @param NoParameters
 * @return Retorna uma string com as coordenadas dos pontos de passagem.
 * @see Programa
 * 
 */
public String coordToString()
{
	String acum = "";
  int i = 0;
  while(this.coord_table[i][0] != null)
  {   
  acum = acum.concat("[" +this.coord_table[i][0] + "," + this.coord_table[i][1] + "," + this.coord_table[i][2] + "]");
  i++;   
  }
  return acum;
}

/**
 * Retorna uma string com o conteudo do programa.
 * 
 * @param NoParameters
 * @return Retorna uma string com o conteudo do programa.
 * @see Programa
 * 
 */
public String toStringSelPlan()
  {
  String acum = "";
  //acum = "Generic Program: " + this.getId() + "\n";
	acum += this.planoSelZ;
	return acum;
  }

public String toString()
  {
  String acum = "";
  acum = "Generic Program: " + this.getId() + "\n";
	acum = this.MostraArray();
	return acum;
  }


}