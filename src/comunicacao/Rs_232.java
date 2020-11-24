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

package cominucacao;



public class Rs_232 
{
int BitPorSeg = 9600;
int BitdeDados = 7;
String Paridade = "Even";//=Par odd=impar
int StopBit = 2;
String HwControl = "None";

String end = "0A 0D";
//System.load("example.dll");

  public Rs_232()
  {
  }
}