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

package interf;
import comunicacao.FTP;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Frame1_FTPConfiguration extends JPanel 
{
  private Border border = BorderFactory.createEtchedBorder();
  private GridBagLayout layoutMain = new GridBagLayout();
  private JTextField jTextField1 = new JTextField();
  private JTextArea jTextArea1 = new JTextArea();
  private JTextArea jTextArea2 = new JTextArea();
  private JTextField jTextField2 = new JTextField();

  FTP ftp = new FTP();
  private JTextField jTextField3 = new JTextField();
  private JTextField jTextField4 = new JTextField();
  private JTextArea jTextArea3 = new JTextArea();
  private JTextArea jTextArea4 = new JTextArea();
  
  //public String host 	   = "192.168.125.1";
  //public int port     = 21;
  
  public Frame1_FTPConfiguration()
  {
  
  
    try
    {
      jbInit();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

  }

  private void jbInit() throws Exception
  {
    this.setLayout(layoutMain);
    this.setBorder(border);
    jTextField1.setText("jTextField1");
    jTextField1.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jTextField1_actionPerformed(e);
        }
      });
    jTextArea1.setText("Controller FTP Address");
    jTextArea2.setText("Port");
    jTextField2.setText("jTextField2");
    jTextField2.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jTextField2_actionPerformed(e);
        }
      });
    jTextField3.setText("jTextField3");
    jTextField4.setText("jTextField4");
    jTextArea3.setText("User");
    jTextArea4.setText("Password");
    this.add(jTextArea2, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    this.add(jTextField2, new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 18, 0));
    this.add(jTextField1, new GridBagConstraints(1, 0, 1, 1, 1.0, 1.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 115, 0));
    this.add(jTextArea1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    this.add(jTextField3, new GridBagConstraints(1, 2, 1, 1, 1.0, 1.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 82, 0));
    this.add(jTextField4, new GridBagConstraints(1, 3, 1, 1, 1.0, 1.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 81, 0));
    this.add(jTextArea3, new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
    this.add(jTextArea4, new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
   }  

  private void jTextField1_actionPerformed(ActionEvent e)
  {
  ftp.host = jTextField1.getText();
  System.out.print(ftp.host);
  }

  private void jTextField2_actionPerformed(ActionEvent e)
  {
  ftp.port = jTextField2.getText().hashCode();
  System.out.print(ftp.port);
  }
}