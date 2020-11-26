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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;


/*
import edu.uci.ics.jung.graph.impl.DirectedSparseGraph;
import edu.uci.ics.jung.graph.impl.DirectedSparseVertex;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.Vertex;
import edu.uci.ics.jung.algorithms.shortestpath.UnweightedShortestPath;
*/
final public class Select 
{

public final float infx = 284.0f,
                   supx = 737.0f,
                   infy = -252.0f,
                   supy = 262.0f,
                   infz = 155.0f,
                   supz = 736.0f;
   

public ArrayList selectedCoords = new ArrayList();
public static Quicksort esort = new Quicksort();
  public Select()
  {
  }


public void swap(Float a, Float b)
{
Float temp;

temp = a;
a = b;
b = temp;
}


public boolean findmin(float a,float b)
{
	if(a>b)
		return true;
	else
		return false;
}

public boolean findMinDist(float a,float b)
{
	if(a>b)
		return true;
	else
		return false;
}



public double DistanciaAbsolutaEntre1Dim(float num1, float num2)
{float temp = 0.0f;
/* if (num1>0 && num2>0)
  temp = num1 - num2;
  else
    if (num1>0 && num2<0)
      temp = num1 - num2;
      else
        if (num1<0 && num2>0)
          temp = num2 - num1;
           else
             if (num1 > num2)
               temp = - (num2 - num1); 
                 else
                  temp = - (num1 - num2);     
                  
                  */
// if (num1 >= num2)
  temp = num1 - num2;
// else   
//  temp = num2 - num1;
  
  
  return Math.abs(temp);
}

public Double DistanciaEntre2Pontos(float x1, float y1, float z1, float x2, float y2, float z2)
{
double D = 0.0f;
double dxq,dyq,dzq;
 
 dxq = Math.pow(DistanciaAbsolutaEntre1Dim(x1, x2) ,2);
 dyq = Math.pow(DistanciaAbsolutaEntre1Dim(y1, y2) ,2);
 dzq = Math.pow(DistanciaAbsolutaEntre1Dim(z1, z2) ,2);
 D = Math.pow((dxq + dyq + dzq),0.5);
    
  return new Double(D);
}

/*public int countZ(Programa p, int index, float pattern)
{int count =0;
  while(p.coord_table[index][2].floatValue() == pattern)
    count++;
  
return count; 
}*/


public void EliminaCoincidentes(Programa prog)
{
Float[][] temp_table = new Float[(int)prog.n_linhas_arqcoord][3];// novo array do tamanho do arquivo
//int index = 0;
int posicao = 1;

temp_table[0][0] = prog.coord_table[0][0];
temp_table[0][1] = prog.coord_table[0][1];
temp_table[0][2] = prog.coord_table[0][2];

  for (int j1=1; j1 < prog.n_linhas_arqcoord; j1++)
  {
   for(int index = 0; index < posicao ; index++) 
    {
     if(prog.coord_table[j1][2].floatValue() != temp_table[index][1].floatValue() || prog.coord_table[j1][1].floatValue() != prog.coord_table[index][1].floatValue() || prog.coord_table[j1][2].floatValue() != prog.coord_table[index][2].floatValue())
      {      
      //Coord_World coord = new Coord_World(prog.coord_table[j1][0].floatValue() , prog.coord_table[j1][1].floatValue(), prog.coord_table[j1][2].floatValue());
      temp_table[posicao][0] = prog.coord_table[j1][0];
      temp_table[posicao][1] = prog.coord_table[j1][1];
      temp_table[posicao][2] = prog.coord_table[j1][2];
      posicao++;
      }
    }
  }
}
/*
public void OrdenaPorDistancia(Programa prog, int ini, int fim)
{
Float FloatAux;
int ele = 0;
int i, inirange, endrange; 
Double DoubleAux;
Double[] distanceArray = new Double[(int)prog.n_linhas_arqcoord];
//Float[][] AuxArray = new Float[(int)prog.n_linhas_arqcoord][3];

 for (int k1 = ini; k1 < fim; k1++)
 {
 distanceArray[k1] = new Double(0.0d);
  for (int j1 = k1; j1 <= fim; j1++)
   {
    distanceArray[j1] = this.DistanciaEntre2Pontos(prog.coord_table[k1][0].floatValue(), prog.coord_table[k1][1].floatValue(), prog.coord_table[k1][2].floatValue(), prog.coord_table[j1][0].floatValue(), prog.coord_table[j1][1].floatValue(), prog.coord_table[j1][2].floatValue());    
   } 
 
  for (int k2 = k1+1; k2 < fim; k2++)
  {
  for (int l2 = k2+1; l2 <= fim; l2++)
  {
   if(distanceArray[k2].floatValue() > distanceArray[l2].floatValue())
   {ele = l2;}
  }
  if (k1 > 0)
  {
    DoubleAux = distanceArray[ele];
    distanceArray[ele] = distanceArray[k2];
    distanceArray[k2] = DoubleAux;
        
    FloatAux = prog.coord_table[ele][0];
    prog.coord_table[ele][0] = prog.coord_table[k2][0];
    prog.coord_table[k2][0] = FloatAux;

    FloatAux = prog.coord_table[ele][1];
    prog.coord_table[ele][1] = prog.coord_table[k2][1];
    prog.coord_table[k2][1] = FloatAux;
                           
    FloatAux = prog.coord_table[ele][2];
    prog.coord_table[ele][2] = prog.coord_table[k2][2];
    prog.coord_table[k2][2] = FloatAux;
  }
  }
}  

}
*/


public void OrdenaPorDistancia(Programa prog, int ini, int fim)
{
Float FloatAux;
Double DoubleAux;
Double[] distanceArray = new Double[(int)prog.n_linhas_arqcoord];
while (ini < fim)
{
 for (int k1 = ini; k1 < fim; k1++)
 {
 distanceArray[k1] = new Double(0.0d);
  for (int j1 = k1+1; j1 <= fim; j1++)
   {
    //distanceArray[k1] = new Double(0.0d);
    distanceArray[j1] = this.DistanciaEntre2Pontos(prog.coord_table[k1][0].floatValue(), prog.coord_table[k1][1].floatValue(), prog.coord_table[k1][2].floatValue(), prog.coord_table[j1][0].floatValue(), prog.coord_table[j1][1].floatValue(), prog.coord_table[j1][2].floatValue());
   } 
  for (int k2 = k1; k2 < fim; k2++)
  {
  for (int l2 = k2+1; l2 <= fim; l2++)
   {
   if(findmin(distanceArray[k2].floatValue() ,distanceArray[l2].floatValue()))
   {
    DoubleAux = distanceArray[l2];
    distanceArray[l2] = distanceArray[k2];
    distanceArray[k2] = DoubleAux;
        
    FloatAux = prog.coord_table[k2][0];
    prog.coord_table[k2][0] = prog.coord_table[l2][0];
    prog.coord_table[l2][0] = FloatAux;

    FloatAux = prog.coord_table[k2][1];
    prog.coord_table[k2][1] = prog.coord_table[l2][1];
    prog.coord_table[l2][1] = FloatAux;
                           
    FloatAux = prog.coord_table[k2][2];
    prog.coord_table[k2][2] = prog.coord_table[l2][2];
    prog.coord_table[l2][2] = FloatAux;
   }
  }
 } 
}
ini++;  
}  

}


public void SelecionaPlanoZ(Iterator it, Programa p)
{
int i=0;
Coord_World coordWorld;
selectedCoords.clear();
  while (i<10000 &&  it.hasNext())//10000   o num max de pontos para n exceder a memoria disp
  {
   //limite area de trabalho
   coordWorld = (Coord_World)it.next();
   
   if (coordWorld.Coord_Z == p.planoSelZ)
   {
   selectedCoords.add(coordWorld);
   i++;
   }
  }
   p.n_linhas_arqcoord = i;
   
   p.PointSelArray = selectedCoords;
   
   this.HashSetToArray(p);
   
   this.OrdenaPorDistancia(p,0, (int)p.n_linhas_arqcoord-1 );
}


public void OrdenaPorValor(Programa prog, int dim, int ini, int fim)
{
Float FloatAux;
 for (int i1 = ini; i1 < fim; i1++)
  {
    for (int j1 = i1+1; j1 <= fim; j1++)
    {
     if(findmin(prog.coord_table[i1][dim].floatValue() ,prog.coord_table[j1][dim].floatValue()))
     {
      FloatAux = prog.coord_table[i1][0];
      prog.coord_table[i1][0] = prog.coord_table[j1][0];
      prog.coord_table[j1][0] = FloatAux;
                            
      FloatAux = prog.coord_table[i1][1];
      prog.coord_table[i1][1] = prog.coord_table[j1][1];
      prog.coord_table[j1][1] = FloatAux;
                            
      FloatAux = prog.coord_table[i1][2];
      prog.coord_table[i1][2] = prog.coord_table[j1][2];
      prog.coord_table[j1][2] = FloatAux;
     }
    }
  } 
}

public void HashSetToArray(Programa prog)
{
Float[][] temp_table = new Float[(int)prog.n_linhas_arqcoord][3];// novo array do tamanho do arquivo
Coord_World coordWorld;
int indice;

Iterator itset = prog.PointSelArray.iterator();
indice = 0;
  while (indice<(int)prog.n_linhas_arqcoord && itset.hasNext())
   {
   coordWorld = (Coord_World)itset.next();
   
      temp_table[indice][0] = coordWorld.getCoord_X();
      temp_table[indice][1] = coordWorld.getCoord_Y();
      temp_table[indice][2] = coordWorld.getCoord_Z();
      indice++;
   }
  prog.coord_table = (Float[][])temp_table.clone(); //faz uma copia da tabela de visualiza  o para as classes de neg cio  
}


public void PointsSelection(Programa prog)
{
int i, inirange, endrange;

this.HashSetToArray(prog);

esort.sort(prog.coord_table, 2, 0, (int)prog.n_linhas_arqcoord-1);
//this.OrdenaPorValor(prog,2, 0, (int)prog.n_linhas_arqcoord-1);// ordena por Z = 2

//Ordena por y os ranges onde z   igual
i = inirange = endrange = 0;
while (i < (int)prog.n_linhas_arqcoord-1) //n o precisa comparar o ultimo pq o ultimo ja ser  o maior
{
  if ((prog.coord_table[i][2].floatValue() != prog.coord_table[i+1][2].floatValue()) || (i == (int)prog.n_linhas_arqcoord - 2))
  {
   if (i == (int)prog.n_linhas_arqcoord - 2)
    endrange = i+1;
  else
    endrange = i; 
  esort.sort(prog.coord_table,1, inirange, endrange);
  //this.OrdenaPorValor(prog,1, inirange, endrange);// ordena por y = 1
   inirange = endrange+1;
  }
  i++;
}

//Ordena por x os ranges onde z   igual
i = inirange = endrange = 0;
while (i < (int)prog.n_linhas_arqcoord - 1) //n o precisa comparar o ultimo pq o ultimo ja ser  o maior
{
  if (prog.coord_table[i][1].floatValue() != prog.coord_table[i+1][1].floatValue() || prog.coord_table[i][2].floatValue() != prog.coord_table[i+1][2].floatValue() || (i == (int)prog.n_linhas_arqcoord - 2))
  {
  if (i == (int)prog.n_linhas_arqcoord - 2)
    endrange = i+1;
  else
    endrange = i;
   esort.sort(prog.coord_table,0, inirange, endrange);
   //this.OrdenaPorValor(prog,0, inirange, endrange);// ordena por x = 0
   inirange = endrange+1;
  }
  i++;
}

//Zerar as coord iguais
i--; //para iniciar da penultima linha e comparar com a ultima
while (i > -1)
{
  if (prog.coord_table[i][0].floatValue() == prog.coord_table[i+1][0].floatValue() && prog.coord_table[i][1].floatValue() == prog.coord_table[i+1][1].floatValue() && prog.coord_table[i][2].floatValue() == prog.coord_table[i+1][2].floatValue())
  {
  prog.coord_table[i+1][0] = null;
  }
  i--;
}

i = 0;
prog.PointSelArray.clear();
while (i < (int)prog.n_linhas_arqcoord)
{
  if (prog.coord_table[i][0] != null)
  {
    prog.PointSelArray.add(new Coord_World(prog.coord_table[i][0].floatValue(),prog.coord_table[i][1].floatValue(),prog.coord_table[i][2].floatValue()));
  }
  i++;
}

prog.n_linhas_arqcoord = prog.PointSelArray.size();
this.HashSetToArray(prog);
//prog.PointSelArray.clear();
}



/*
public void PointsSelection(Programa prog)
{
int i, inirange, endrange;

this.HashSetToArray(prog);

this.OrdenaPorValor(prog,2, 0, (int)prog.n_linhas_arqcoord-1);// ordena por Z = 2

//Ordena por y os ranges onde z   igual
i = inirange = endrange = 0;
while (i < (int)prog.n_linhas_arqcoord-1) //n o precisa comparar o ultimo pq o ultimo ja ser  o maior
{
  if ((prog.coord_table[i][2].floatValue() != prog.coord_table[i+1][2].floatValue()) || (i == (int)prog.n_linhas_arqcoord - 2))
  {
   if (i == (int)prog.n_linhas_arqcoord - 2)
    endrange = i+1;
  else
    endrange = i; 
  this.OrdenaPorValor(prog,1, inirange, endrange);// ordena por y = 1
   inirange = endrange+1;
  }
  i++;
}

//Ordena por x os ranges onde z   igual
i = inirange = endrange = 0;
while (i < (int)prog.n_linhas_arqcoord - 1) //n o precisa comparar o ultimo pq o ultimo ja ser  o maior
{
  if (prog.coord_table[i][1].floatValue() != prog.coord_table[i+1][1].floatValue() || prog.coord_table[i][2].floatValue() != prog.coord_table[i+1][2].floatValue() || (i == (int)prog.n_linhas_arqcoord - 2))
  {
  if (i == (int)prog.n_linhas_arqcoord - 2)
    endrange = i+1;
  else
    endrange = i;
   this.OrdenaPorValor(prog,0, inirange, endrange);// ordena por x = 0
   inirange = endrange+1;
  }
  i++;
}

//Zerar as coord iguais
i--; //para iniciar da penultima linha e comparar com a ultima
while (i > -1)
{
  if (prog.coord_table[i][0].floatValue() == prog.coord_table[i+1][0].floatValue() && prog.coord_table[i][1].floatValue() == prog.coord_table[i+1][1].floatValue() && prog.coord_table[i][2].floatValue() == prog.coord_table[i+1][2].floatValue())
  {
  prog.coord_table[i+1][0] = null;
  }
  i--;
}

i = 0;
prog.PointSelArray.clear();
while (i < (int)prog.n_linhas_arqcoord)
{
  if (prog.coord_table[i][0] != null)
  {
    prog.PointSelArray.add(new Coord_World(prog.coord_table[i][0].floatValue(),prog.coord_table[i][1].floatValue(),prog.coord_table[i][2].floatValue()));
  }
  i++;
}

prog.n_linhas_arqcoord = prog.PointSelArray.size();
this.HashSetToArray(prog);
//prog.PointSelArray.clear();
}
*/

public void SelecionaPontosMaioresQ(Iterator it,Programa p, float threshold)
  {int i=0;
  Coord_World coordWorld;
  selectedCoords.clear();   
    while (i<10000 && it.hasNext())//10000   o num max de pontos para n exceder a memoria disp
   {
   
   coordWorld = (Coord_World)it.next();
   if (coordWorld.Coord_Z > threshold)
      selectedCoords.add(coordWorld);
      i++;
   }
   p.n_linhas_arqcoord = i;

p.PointSelArray = selectedCoords;
  }

public void SelecionaPontosPlanoZ(Iterator it,Programa p, float z)
  {int i=0;
  Coord_World coordWorld;
  selectedCoords.clear();
    while (i<10000 && it.hasNext())//10000   o num max de pontos para n exceder a memoria disp
   {
   
   coordWorld = (Coord_World)it.next();
   if (coordWorld.Coord_Z == z)
      selectedCoords.add(coordWorld);
      i++;
   }
   p.n_linhas_arqcoord = i;
  
   p.PointSelArray = selectedCoords;
   
   this.HashSetToArray(p);
   
   this.OrdenaPorDistancia(p,0, (int)p.n_linhas_arqcoord-1 );
  }

  
public void SelecionaPontosValidosAreaTrabalho(Iterator it,Programa p)
  {int i=0;
  Coord_World coordWorld;
  selectedCoords.clear();
    while (i<18000 && it.hasNext())//3000   o num max de pontos para n exceder a memoria disp
   {
   //limite area de trabalho
   coordWorld = (Coord_World)it.next();
   if(coordWorld.getCoord_X().floatValue() > p.infx && coordWorld.getCoord_X().floatValue() < p.supx)
   {if(coordWorld.getCoord_Y().floatValue() > p.infy && coordWorld.getCoord_Y().floatValue() < p.supy )
   {if(coordWorld.getCoord_Z().floatValue() > p.infz && coordWorld.getCoord_Z().floatValue() < p.supz)
   {
   p.PointSelArray.add(coordWorld); i++;
   }}}
   }
   p.n_linhas_arqcoord = i;

   //p.PointSelArray.finalize(); 
  
   p.PointSelArray = selectedCoords;

  }
 
  public ArrayList ReduzNumeroPontosmaiores20Graus(Iterator it,Programa p)
  {int i=0;
  double rad = 0.0d;
  double angulo = 0.0d;
  Coord_World coordWorld,coordWorld1;
  selectedCoords.clear();
  coordWorld = (Coord_World)it.next();
    while (i<10000 && it.hasNext())//10000   o num max de pontos para n exceder a memoria disp
   {
  
   coordWorld1 = (Coord_World)it.next();
   rad = Math.asin((DistanciaAbsolutaEntre1Dim(coordWorld1.getCoord_Y().floatValue(),coordWorld.getCoord_Y().floatValue()))/
   (Math.pow(( Math.pow(DistanciaAbsolutaEntre1Dim(coordWorld1.getCoord_X().floatValue(),coordWorld.getCoord_X().floatValue()),2) + Math.pow(DistanciaAbsolutaEntre1Dim(coordWorld1.getCoord_X().floatValue(),coordWorld.getCoord_X().floatValue()),2 )),0.5)));
   //System.out.println("Numero de radianos entre os pontos: " + rad);
   angulo = rad * 180/Math.PI;
   //System.out.println("Numero de graus entre os pontos: " + angulo);   
   if(angulo < 20.0d)//20   o angulo de separa  o dos pontos
   {
    selectedCoords.add(coordWorld); i++;
    coordWorld = coordWorld1;
   }
   
   }
   p.n_linhas_arqcoord = i;
   System.out.println("Numero de pontos inseridos: " + i);
   return selectedCoords;
  }

/*
public void InserePontos(float newX, float newY, float newZ)
  {
  selectedCoords.add(new Coord_World(newX,newY,newZ));
  }
  */
  
}