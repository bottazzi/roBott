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


// TODO: Check current networking protocol for robot integration and Add a newer ftp feature to load the programs directly into the robots

package smart.roBott.app.comms;

import java.io.*;
//import sun.net.ftp.FtpClient;

public class FTP 
{
	public String 		   host 	= "192.168.125.1";//isso pode mudar de cordo com o fab
    public int             port     = 21;//isso pode mudar de cordo com o fab
    String 				   user 	= "abb"; //isso pode mudar de cordo com o fab
    String 				   password = "abb"; //isso pode mudar de cordo com o fab
//    FtpClient ftpc;
 public FTP()
 {
   
 }
 
 public void FtpConnect() throws Exception
 {
    try {
      System.out.println("Connecting to host " + host);
//      ftpc = new FtpClient(host);
//      ftpc.login(user, password);
//      System.out.println("User " + user + " login OK");
//      System.out.println(ftpc.welcomeMsg);
//      System.out.println("C" + ftpc.pwd());
//      this.ftpc.binary();
      System.out.println("Success.");
    } catch (Exception ex) {
      System.out.println("Error: " + ex.toString());
    }
  }

public void FtpDisconnect() throws Exception
 {
 try
 {
//  ftpc.closeServer();
 System.out.println("Successfull Disconnected.");
 }catch (Exception ex) {
      System.out.println("Error: " + ex.toString());
 }
 
 }

public void putFile(String Diretorio, String nomeProg) throws Exception{
    
    byte[] buffer = new byte[1024];
    try {
      File f = new File(nomeProg);
      int size = (int) f.length();

//      FileInputStream in = new FileInputStream(Diretorio + nomeProg);//Diretorio + nomeProg);
//      OutputStream out = ftpc.put(nomeProg);

      int counter = 0;
      // while (true) {
      //   int bytes = in.read(buffer);
      //   if (bytes < 0)
      //     break;
      //   out.write(buffer, 0, bytes);
      //   counter += bytes;
      //   System.out.println(counter + "bytes transferred.");
      // }
      // System.out.println("File successfull sent.");
      // out.close();
      // in.close();
    } catch (Exception ex) {
      System.out.println("Error: " + ex.toString());
    }
  }
 
}