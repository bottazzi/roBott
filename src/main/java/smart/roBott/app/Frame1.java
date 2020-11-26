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

package smart.roBott.app;

import smart.roBott.app.comms.FTP;
import smart.roBott.app.opt.*;
import smart.roBott.app.service.*;
import smart.roBott.app.visualizations.*;

import smart.roBott.app.coord.Coord_World;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import java.util.Iterator;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Node;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import java.util.List;
//import java.text.DecimalFormat;

final public class Frame1 extends JFrame 
{
  private JButton buttonHelp = new JButton();
  private JButton buttonClose = new JButton();
  private JButton buttonOpen = new JButton();
  private JToolBar toolBar = new JToolBar();
  private JLabel statusBar = new JLabel();
  private JMenuItem menuHelpAbout = new JMenuItem();
  private JMenu menuHelp = new JMenu();
  private JMenuItem menuFileExit = new JMenuItem();
  private JMenu menuFile = new JMenu();
  private JMenu menuFTP = new JMenu();
  private JMenuItem menuFTPConfiguration = new JMenuItem();
  private JMenuBar menuBar = new JMenuBar();
  private JPanel panelCenter = new JPanel();
  private BorderLayout layoutMain = new BorderLayout();
  private JLabel jLabel2 = new JLabel();
  private JRadioButton Media = new JRadioButton();
  private JRadioButton Baixa = new JRadioButton();
  private JRadioButton Alta = new JRadioButton();
  private JButton BotaoGerarPrograma = new JButton();
  private JRadioButton Proto_Rapid = new JRadioButton();
  private JRadioButton Proto_Karel = new JRadioButton();
  private JRadioButton Proto_MelfaBasic = new JRadioButton();
  private JRadioButton Proto_Trident = new JRadioButton();
  private JButton load = new JButton();

  private Button newPointSelectionRef = new Button();
  private JTextField jTextField1 = new JTextField();
  private JLabel jLabel1 = new JLabel();
  
//WORKAREA
  private JTextField jTextField2 = new JTextField();
  private JTextField jTextField3 = new JTextField();
  private JTextField jTextField4 = new JTextField();
  private JTextField jTextField5 = new JTextField();
  private JTextField jTextField6 = new JTextField();
  private JTextField jTextField7 = new JTextField();
  private JLabel jLabel3 = new JLabel();
  private JLabel jLabel4 = new JLabel();
  private JLabel jLabel5 = new JLabel();
  private JLabel jLabel6 = new JLabel();
  private JLabel jLabel7 = new JLabel();
  private JLabel jLabel8 = new JLabel();
  private JLabel jLabel9 = new JLabel();
  private JLabel jLabel10 = new JLabel();

//Programa p;//ponteiro para o programa generico que vai ser utilizado

Carregador carrega = new Carregador();

Select sel = new Select();

SideCoordView sv;

private JPanel telaWorld = new JPanel();    

final int P_Rapid = 1;
final int P_Karel = 2;
final int P_MelfaBasic = 3;
final int P_Trident = 4;
int Protocolo = this.P_Rapid;

final int M_JUNTA = 1;
final int M_LINEAR = 2;
final int M_CIRCULAR = 3;
int movimento = this.M_JUNTA;

//String nome;

final int V_BAI = 100;
final int V_MED = 500;
final int V_ALT = 1000;
int velocidade = this.V_BAI;


final int P_FINE = 0;
final int P_10= 10;
final int P_50 = 50;
final int P_100 = 100;
final int P_Max = 200;
int precisao = this.P_FINE;


//private double mx,my;

private JButton copy = new JButton();

/**
 * 
 * 
 * @param
 * @return
 * @see
 * 
 */
  public void init()
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
/**
 * D carga no ponto, rotulo dos pontos
 * 
 * @param
 * @return
 * @see
 * 
 */

public void CarregadorPontos(Carregador carga) throws Exception
{
carga.CarregadorPonto(this.velocidade, this.precisao);
}


/**
 * Principal
 * 
 * @param
 * @return
 * @see
 **/

  public static void main(String[] args)
  {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = frame.getSize();
    frame.setLocation((d.width - frameSize.width) / 2, (d.height - frameSize.height) / 2);
    frame.setVisible(true);
    
  }

/***************************************
 * Retorna o protocolo do fabricante.
 * 
 * @return O inteiro que representa o protocolo de trabalho do rob.
 * @see
 * 
 */
public int getProtocolo()
{
	return this.Protocolo;
}

public int getVel()
{
	return this.velocidade;
}

public int getPrecision()
{
	return this.precisao;
}

//Movimento
/**
 * 
 * 
 * @param
 * @return
 * @see
 * 
 */
  private void Junta_actionPerformed(ActionEvent e)
  {this.movimento = this.M_JUNTA;
  }

/**
 * 
 * 
 * @param
 * @return
 * @see
 * 
 */   
  private void Linear_actionPerformed(ActionEvent e)
  {this.movimento = this.M_LINEAR;
  }

/**
 * 
 * 
 * @param
 * @return
 * @see
 * 
 */
  private void Circular_actionPerformed(ActionEvent e)
  {this.movimento = this.M_CIRCULAR;
  }
  
  
//Velocidade  
/**
 * 
 * 
 * @param
 * @return
 * @see
 * 
 */
  private void Baixa_actionPerformed(ActionEvent e)
  {this.velocidade = this.V_BAI;
  this.Baixa.setSelected(true);
  this.Media.setSelected(false);
  this.Alta.setSelected(false);
  }

/**
 * 
 * 
 * @param
 * @return
 * @see
 * 
 */
  private void Media_actionPerformed(ActionEvent e)
  {this.velocidade = this.V_MED;
  this.Baixa.setSelected(false);
  this.Media.setSelected(true);
  this.Alta.setSelected(false);
  }

/**
 * 
 * 
 * @param
 * @return
 * @see
 * 
 */
  private void Alta_actionPerformed(ActionEvent e)
  {this.velocidade = this.V_ALT;
  this.Baixa.setSelected(false);
  this.Media.setSelected(false);
  this.Alta.setSelected(true);
  }


//Preciso
/**
 * 
 * 
 * @param
 * @return
 * @see
 * 
 */
  private void Fine_actionPerformed(ActionEvent e)
  {this.precisao = this.P_FINE;
  P10.setSelected(false);
  P50.setSelected(false);
  P100.setSelected(false);
  P200.setSelected(false);
  Fine.setSelected(true);
  }

/**
 * 
 * 
 * @param
 * @return
 * @see
 * 
 */
  private void P10_actionPerformed(ActionEvent e)
  {this.precisao = this.P_10;
  Fine.setSelected(false);  
  P50.setSelected(false);
  P100.setSelected(false);
  P200.setSelected(false);
  P10.setSelected(true);
  }

/**
 * 
 * 
 * @param
 * @return
 * @see
 * 
 */ 
  private void P50_actionPerformed(ActionEvent e)
  {this.precisao = this.P_50;
  Fine.setSelected(false);
  P10.setSelected(false);
  P100.setSelected(false);
  P200.setSelected(false);
  P50.setSelected(true);
  }

/**
 * 
 * 
 * @param
 * @return
 * @see
 * 
 */
  private void P100_actionPerformed(ActionEvent e)
  {this.precisao = this.P_100;  
  Fine.setSelected(false);
  P10.setSelected(false);
  P50.setSelected(false);
  P200.setSelected(false);
  P100.setSelected(true);
  }

/**
 * 
 * 
 * @param
 * @return
 * @see
 * 
 */
  private void P200_actionPerformed(ActionEvent e)
  {this.precisao = this.P_Max;
  Fine.setSelected(false);
  P10.setSelected(false);
  P50.setSelected(false);
  P100.setSelected(false);
  P200.setSelected(true);
  }

//Protocolos
/**
 * 
 * 
 * @param
 * @return
 * @see
 * 
 */
  private void Proto_Rapid_actionPerformed(ActionEvent e)
  {this.Protocolo = this.P_Rapid;
  this.Proto_Rapid.setSelected(true);
  this.Proto_Karel.setSelected(false);
  this.Proto_MelfaBasic.setSelected(false);
  this.Proto_Trident.setSelected(false);
  }

/**
 * 
 * 
 * @param
 * @return
 * @see
 * 
 */
  private void Proto_Karel_actionPerformed(ActionEvent e)
  {this.Protocolo = this.P_Karel;
  this.Proto_Rapid.setSelected(false);
  this.Proto_Karel.setSelected(true);
  this.Proto_MelfaBasic.setSelected(false);
  this.Proto_Trident.setSelected(false);
  }

/**
 * 
 * 
 * @param
 * @return
 * @see
 * 
 */
  private void Proto_MelfaBasic_actionPerformed(ActionEvent e)
  {this.Protocolo = this.P_MelfaBasic;
  this.Proto_Rapid.setSelected(false);
  this.Proto_Karel.setSelected(false);
  this.Proto_MelfaBasic.setSelected(true);
  this.Proto_Trident.setSelected(false);
  }

/**
 * 
 * 
 * @param
 * @return
 * @see
 * 
 */
  private void Proto_Trident_actionPerformed(ActionEvent e)
  {this.Protocolo = this.P_Trident;
  this.Proto_Rapid.setSelected(false);
  this.Proto_Karel.setSelected(false);
  this.Proto_MelfaBasic.setSelected(false);
  this.Proto_Trident.setSelected(true);
  }

  
  public Frame1()
  {
  
    try
    {
      jbInit();
      
  this.carrega.pro_x = this.carrega.fabrica.getInstanciaPrograma();
  this.carrega.CarregadorTipoNomePrograma(this.carrega.pro_x, this.getProtocolo(), this.getVel(), this.getPrecision());//Inicializa Programa com nome e tipo do servo
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

  }

  private void jbInit() throws Exception
  {
  
    this.setJMenuBar(menuBar);
    this.getContentPane().setLayout(layoutMain);
    panelCenter.setLayout(null);
    BotaoGerarPrograma.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          BotaoGerarPrograma_actionPerformed(e);
        }
      });
    copy.setText("Send to Controller");
    copy.setBounds(new Rectangle(10, 390, 210, 40));
    copy.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          copy_actionPerformed(e);
        }
      });
    jButton1.setText("Resize Workspace");
    jButton1.setBounds(new Rectangle(20, 635, 185, 30));
    jButton1.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jButton1_actionPerformed(e);
        }
      });
    Fine.setText("Fine");
    Fine.setBounds(new Rectangle(0, 85, 45, 15));
    Fine.setSelected(true);
    Fine.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          Fine_actionPerformed(e);
        }
      });
    P10.setText("-10");
    P10.setBounds(new Rectangle(45, 85, 45, 15));
    P10.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          P10_actionPerformed(e);
        }
      });
    P50.setText("-50");
    P50.setBounds(new Rectangle(90, 85, 45, 15));
    P50.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          P50_actionPerformed(e);
        }
      });
    P100.setText("-100");
    P100.setBounds(new Rectangle(135, 85, 50, 15));
    P100.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          P100_actionPerformed(e);
        }
      });
    P200.setText("-200");
    P200.setBounds(new Rectangle(185, 85, 50, 15));
    P200.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          P200_actionPerformed(e);
        }
      });
    jLabel11.setText("Precision:");
    jLabel11.setBounds(new Rectangle(10, 60, 85, 15));
    scrollPane.setBounds(new Rectangle(805, 20, 210, 645));
    scrollPane.setBackground(SystemColor.activeCaptionBorder);
    scrollPane.setForeground(Color.red);
    jTable.setBounds(new Rectangle(440, 130, 155, 225));
    jTable.setBackground(Color.red);
    jTable.setForeground(Color.white);
    jTable.setFont(new Font("Arial", 1, 12));
    jTable.setAlignmentX((float)5.0);
    jTable.setAlignmentY((float)1.0);
    jTable.setModel(new DefaultTableModel(null, new String[] {"X", "Y", "Z"}));
    jTable.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
    jTextField2.setBounds(new Rectangle(100, 455, 70, 25));
    jTextField3.setBounds(new Rectangle(100, 480, 70, 25));

    jTextField4.setBounds(new Rectangle(100, 510, 70, 25));

    jTextField5.setBounds(new Rectangle(100, 555, 70, 25));

    jTextField6.setBounds(new Rectangle(100, 580, 70, 25));

    jTextField7.setBounds(new Rectangle(100, 605, 70, 25));

    jLabel3.setText("X");
    jLabel3.setBounds(new Rectangle(50, 465, 34, 14));
    jLabel4.setText("Y");
    jLabel4.setBounds(new Rectangle(50, 485, 34, 14));
    jLabel5.setText("Z");
    jLabel5.setBounds(new Rectangle(50, 505, 34, 14));
    jLabel6.setText("Length");
    jLabel6.setBounds(new Rectangle(50, 560, 35, 15));
    jLabel7.setText("Width");
    jLabel7.setBounds(new Rectangle(50, 585, 34, 14));
    jLabel8.setText("Height");
    jLabel8.setBounds(new Rectangle(50, 615, 34, 14));
    jLabel9.setText("Workspace Offset");
    jLabel9.setBounds(new Rectangle(35, 435, 105, 15));
    jLabel10.setText("Workspace Size");
    jLabel10.setBounds(new Rectangle(35, 535, 105, 15));
    newPointSelectionRef.setLabel("Select Points");
    newPointSelectionRef.setBounds(new Rectangle(10, 170, 140, 35));
    newPointSelectionRef.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          newPointSelectionRef_actionPerformed(e);
        }
      });
    jTextField1.setBounds(new Rectangle(160, 185, 60, 20));
    jTextField1.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jTextField1_actionPerformed(e);
        }
      });
    jLabel1.setText("ZSelection");
    jLabel1.setBounds(new Rectangle(165, 165, 60, 20));

    telaWorld.setBounds(new Rectangle(235, 20, 565, 645));
    telaWorld.setBackground(Color.black);
    telaWorld.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));

    scrollPane.getViewport().add(jTable, null);
    this.getContentPane().add(scrollPane, null);
    this.getContentPane().add(jLabel11, null);
    this.getContentPane().add(P200, null);
    this.getContentPane().add(P100, null);
    this.getContentPane().add(P50, null);
    this.getContentPane().add(P10, null);
    this.getContentPane().add(Fine, null);
    this.getContentPane().add(jButton1, null);
    this.getContentPane().add(jLabel10, null);
    this.getContentPane().add(jLabel9, null);
    this.getContentPane().add(jLabel8, null);
    this.getContentPane().add(jLabel7, null);
    this.getContentPane().add(jLabel6, null);
    this.getContentPane().add(jLabel5, null);
    this.getContentPane().add(jLabel4, null);
    this.getContentPane().add(jLabel3, null);
    this.getContentPane().add(jTextField7, null);
    this.getContentPane().add(jTextField6, null);
    this.getContentPane().add(jTextField5, null);
    this.getContentPane().add(jTextField4, null);
    this.getContentPane().add(jTextField3, null);
    this.getContentPane().add(jTextField2, null);
    this.getContentPane().add(jLabel1, null);
    this.getContentPane().add(jTextField1, null);
    this.getContentPane().add(newPointSelectionRef, null);
    this.getContentPane().add(telaWorld, null);
    this.getContentPane().add(copy, null);
    this.getContentPane().add(load, null);
 //   this.getContentPane().add(scrollPane, null);
    load.setText("Load Coordinates");
    load.setBounds(new Rectangle(10, 120, 210, 35));
    load.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          load_actionPerformed(e);
        }
      });

    this.setSize(new Dimension(400, 300));
    this.setTitle("RoBott Trajectory Generator - OLP Tool");
    menuFile.setText("File");
    menuFileExit.setText("Exit");
    menuFileExit.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent ae)
        {
          fileExit_ActionPerformed(ae);
        }
      });
    menuFTP.setText("FTP");
    menuFTPConfiguration.setText("Configuration");
    menuFTPConfiguration.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent ae)
        {
          FTPConfiguration_ActionPerformed(ae);
        }
      });
    menuHelp.setText("Help");
    menuHelpAbout.setText("About");
    menuHelpAbout.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent ae)
        {
          helpAbout_ActionPerformed(ae);
        }
      });
    statusBar.setText("");
    buttonOpen.setToolTipText("Open File");
    buttonClose.setToolTipText("Close File");
    buttonHelp.setToolTipText("About");
    menuFile.add(menuFileExit);
    menuBar.add(menuFile);
    menuFTP.add(menuFTPConfiguration);
    menuBar.add(menuFTP);
    menuHelp.add(menuHelpAbout);
    menuBar.add(menuHelp);
    toolBar.add(buttonOpen);
    toolBar.add(buttonClose);
    toolBar.add(buttonHelp);
    
    this.getContentPane().add(statusBar, BorderLayout.SOUTH);
    this.getContentPane().add(toolBar, BorderLayout.NORTH);
    this.getContentPane().add(panelCenter, BorderLayout.CENTER);
    this.getContentPane().add(Proto_Trident, null);
    this.getContentPane().add(Proto_MelfaBasic, null);
    this.getContentPane().add(Proto_Karel, null);
    this.getContentPane().add(Proto_Rapid, null);
    this.getContentPane().add(BotaoGerarPrograma, null);
    this.getContentPane().add(Alta, null);
    this.getContentPane().add(jLabel2, null);
    this.getContentPane().add(Baixa, null);
    this.getContentPane().add(Media, null);
    this.getContentPane().setLayout(null);
    this.setSize(new Dimension(1024, 728));
    Alta.setText("High");
    Alta.setBounds(new Rectangle(170, 35, 60, 15));
    Alta.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          Alta_actionPerformed(e);
        }
      });
    Media.setText("Medium");
    Media.setBounds(new Rectangle(90, 35, 70, 15));
    Media.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          Media_actionPerformed(e);
        }
      });
    Baixa.setText("Low");
    Baixa.setBounds(new Rectangle(15, 35, 65, 15));
    Baixa.setSelected(true);
    Baixa.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          Baixa_actionPerformed(e);
        }
      });
    jLabel2.setText("Velocity:");
    jLabel2.setBounds(new Rectangle(10, 10, 105, 15));
    jLabel2.setToolTipText("Escolha a velocidade do movimento:");
    BotaoGerarPrograma.setText("Generate Code");
    BotaoGerarPrograma.setBounds(new Rectangle(10, 325, 210, 40));
    Proto_Rapid.setText("Rapid (ABB)");
    Proto_Rapid.setBounds(new Rectangle(15, 210, 91, 24));
    Proto_Rapid.setSelected(true);
    Proto_Rapid.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          Proto_Rapid_actionPerformed(e);
        }
      });
    Proto_Karel.setText("Karel (Fanuc)");
    Proto_Karel.setBounds(new Rectangle(15, 235, 91, 24));
    Proto_Karel.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          Proto_Karel_actionPerformed(e);
        }
      });
    Proto_MelfaBasic.setText("Melfa-Basic IV (Mitsubishi)");
    Proto_MelfaBasic.setBounds(new Rectangle(15, 260, 155, 25));
    Proto_MelfaBasic.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          Proto_MelfaBasic_actionPerformed(e);
        }
      });
      
   Proto_Trident.setText("Trident (PUMA)");
   Proto_Trident.setBounds(new Rectangle(15, 285, 150, 25));     
   Proto_Trident.addActionListener(new ActionListener()
     {
        public void actionPerformed(ActionEvent e)
        {
          Proto_Trident_actionPerformed(e);
        }
      }); 

 InitRobot();
 
}


public void InitRobot() throws Exception
{
    this.carrega.Componente = this.carrega.getPrograma();

    this.carrega.CarregaArquivoCoordEntrada(System.getProperty("user.dir").concat("\\assets\\bracao.stl"),  this.carrega.Componente);
    
 //   this.carrega.ComponentsList.add(component);

    sel.HashSetToArray( this.carrega.Componente);//Programa(this.carrega.ComponentsList.get(0)));

    showWA(this.carrega);

    this.jTextField2.setText(String.valueOf( this.carrega.Componente.infx)+ " mm");
    this.jTextField3.setText(String.valueOf( this.carrega.Componente.infy)+ " mm");
    this.jTextField4.setText(String.valueOf( this.carrega.Componente.infz)+ " mm");
    this.jTextField5.setText(String.valueOf( this.carrega.Componente.supx)+ " mm");
    this.jTextField6.setText(String.valueOf( this.carrega.Componente.supy)+ " mm");
    this.jTextField7.setText(String.valueOf( this.carrega.Componente.supz)+ " mm");

}

void fileExit_ActionPerformed(ActionEvent e)
{
  System.exit(0);
}

void FTPConfiguration_ActionPerformed(ActionEvent e)
{
Frame1_FTPConfiguration ftpconf = new Frame1_FTPConfiguration();
JOptionPane.showMessageDialog(this, ftpconf , "FTP Configuration", JOptionPane.OK_CANCEL_OPTION);
}

void helpAbout_ActionPerformed(ActionEvent e)
{
JOptionPane.showMessageDialog(this, new Frame1_AboutBoxPanel1(), "About", JOptionPane.PLAIN_MESSAGE);
}

private void jTextField1_actionPerformed(ActionEvent e)
{

}    

private void showWA(Carregador carga) throws Exception
  {
   telaWorld.removeAll();//limpa a tela
    
  //  sv = new SideCoordView(carga);//cria a tela
   
  //  sv.setBounds(telaWorld.getBounds());//tamanho da tela
  //  sv.setVisible(true); //apresenta
   
  //  telaWorld.add("Center",sv.getCanvas()); //adiciona
  //  sv.dispose();
}





private void ShowWAndPoints(Carregador carga)//Programa newP)
{
 
  sv.NewSolid(carga, false);
    
  sv.setBounds(telaWorld.getBounds());//tamanho da tela
  sv.setVisible(true); //apresenta
   
  // telaWorld.add("Center",sv.getCanvas()); //adiciona
  // sv.dispose();
}

private void load_actionPerformed(ActionEvent e)
{
String filename = "";
      
      FileDialog f = new FileDialog(this, "Load", FileDialog.LOAD);
      f.setDirectory(System.getProperty("user.dir").concat("\\assets"));
      f.setVisible(true);// Display the dialog and block.
      
      //filename = f.getFile();    // Get the user's response
      
      if (!(f.getFile() == null) )//.equals(filename))
      {
      try {
      filename = f.getDirectory() + f.getFile();
      
      this.carrega.pro_x = this.carrega.getPrograma();//ponteiro para o obj programa para visualizao das coord
           
      this.carrega.CarregaArquivoCoordEntrada(filename, this.carrega.pro_x);    
      
    //Workspace bounds
    String aux = null;

    this.jTextField2.setText(aux.valueOf(this.carrega.pro_x.infx)+ " mm");
    this.jTextField3.setText(aux.valueOf(this.carrega.pro_x.infy)+ " mm");
    this.jTextField4.setText(aux.valueOf(this.carrega.pro_x.infz)+ " mm");
    this.jTextField5.setText(aux.valueOf(this.carrega.pro_x.supx)+ " mm");
    this.jTextField6.setText(aux.valueOf(this.carrega.pro_x.supy)+ " mm");
    this.jTextField7.setText(aux.valueOf(this.carrega.pro_x.supz)+ " mm");
  
    sel.HashSetToArray(this.carrega.pro_x);//trocado pelo de cima
      
    this.carrega.pro_x.isMesh = true;

    this.ShowWAndPoints(this.carrega); // Mostra coordenadas em J3D
      
    this.jTable.setModel(new DefaultTableModel(this.carrega.pro_x.coord_table,new String [] {"X", "Y", "Z"}));
      
    this.carrega.CarregadorPonto(this.getVel(), this.getPrecision()); // gera os comandos lineares na ling generica para as coord carregadas setando a vel */
      
    this.jTextField1.setText(this.carrega.pro_x.toStringSelPlan());
   }
   catch (Exception excep)
  { 
   excep.printStackTrace();
  }
  }//If filename 
}

FTP ftp = new FTP();
  private JButton jButton1 = new JButton();
  private JRadioButton Fine = new JRadioButton();
  private JRadioButton P10 = new JRadioButton();
  private JRadioButton P50 = new JRadioButton();
  private JRadioButton P100 = new JRadioButton();
  private JRadioButton P200 = new JRadioButton();
  private JLabel jLabel11 = new JLabel();
  public JScrollPane scrollPane = new JScrollPane();
  public JTable jTable = new JTable();
 
private void copy_actionPerformed(ActionEvent e)
{ 
  
 FileDialog f = new FileDialog(this, "Copy to the Controller", FileDialog.LOAD);
 f.setVisible(true);                         // Display the dialog and block.
 
try{
 ftp.FtpConnect();
 ftp.putFile(f.getDirectory(), f.getFile());
 ftp.FtpDisconnect();
  }
  catch(Exception exc)
	{
    exc.printStackTrace();
  }
}
 
private void newPointSelectionRef_actionPerformed(ActionEvent e) //throws Exception
{  
 if (!this.carrega.pro_x.PointSelArray.isEmpty())
  {         
    //Workspace bounds
    String aux = null;

    this.jTextField2.setText(aux.valueOf(this.carrega.pro_x.infx)+ " mm");
    this.jTextField3.setText(aux.valueOf(this.carrega.pro_x.infy)+ " mm");
    this.jTextField4.setText(aux.valueOf(this.carrega.pro_x.infz)+ " mm");
    this.jTextField5.setText(aux.valueOf(this.carrega.pro_x.supx)+ " mm");
    this.jTextField6.setText(aux.valueOf(this.carrega.pro_x.supy)+ " mm");
    this.jTextField7.setText(aux.valueOf(this.carrega.pro_x.supz)+ " mm");

    //ZSelection
    this.carrega.pro_x.planoSelZ = (new Float(this.jTextField1.getText())).floatValue();
    
    
    System.out.print("Numero de pontos:" + this.carrega.pro_x.n_linhas_arqcoord);
    Timer t = new Timer();
    
    this.carrega.pro_x.isMesh = false;
    sel.PointsSelection(this.carrega.pro_x);
    t.print("Tempo de eliminao dos pontos coincidentes!");
  
    Iterator it = this.carrega.pro_x.PointSelArray.iterator();
    //sel.HashSetToArray(this.carrega.pro_x);
    
    Timer t1 = new Timer();
    sel.SelecionaPlanoZ(it, this.carrega.pro_x);
    t1.print("Tempo de seleco do plano Z!");
    
    this.jTable.setModel(new DefaultTableModel(this.carrega.pro_x.coord_table, new String [] {"X", "Y", "Z"}));
    
    this.ShowWAndPoints(this.carrega); // Mostra coordenadas em J3D
    
    
  }
}

private void jButton1_actionPerformed(ActionEvent e)// throws Exception
{
String aux = null;
  //Workspace bounds
    this.carrega.pro_x.infx = (new Float(this.jTextField2.getText().substring(0,this.jTextField2.getText().indexOf(" ")))).floatValue();
    this.carrega.pro_x.infy = (new Float(this.jTextField3.getText().substring(0,this.jTextField3.getText().indexOf(" ")))).floatValue();
    this.carrega.pro_x.infz = (new Float(this.jTextField4.getText().substring(0,this.jTextField4.getText().indexOf(" ")))).floatValue();
    this.carrega.pro_x.supx = (new Float(this.jTextField5.getText().substring(0,this.jTextField5.getText().indexOf(" ")))).floatValue();
    this.carrega.pro_x.supy = (new Float(this.jTextField6.getText().substring(0,this.jTextField6.getText().indexOf(" ")))).floatValue();
    this.carrega.pro_x.supz = (new Float(this.jTextField7.getText().substring(0,this.jTextField7.getText().indexOf(" ")))).floatValue(); 
    
    this.carrega.pro_x.RobotOffset.Coord_X = (this.carrega.pro_x.supx - this.carrega.pro_x.infx)/2 + this.carrega.pro_x.infx;
    this.carrega.pro_x.RobotOffset.Coord_Y = (this.carrega.pro_x.supy - this.carrega.pro_x.infy)/2 + this.carrega.pro_x.infy;
    //this.carrega.pro_x.RobotOffset.Coord_Z = this.carrega.pro_x.infz;
    
    //Actualize ZSelection
    this.jTextField1.setText(aux.valueOf(new Float(this.carrega.pro_x.planoSelZ)));
    //this.carrega.pro_x.isSolid = true;
    
    //this.showCoord(this.carrega.pro_x);
    this.ShowWAndPoints(this.carrega); // Mostra coordenadas em J3D
    
    this.jTable.setModel(new DefaultTableModel(this.carrega.pro_x.coord_table, new String [] {"X", "Y", "Z"}));
}

/**
 * 
 * 
 * @param
 * @return
 * @see
 * 
 */
private void BotaoGerarPrograma_actionPerformed(ActionEvent e)
  {
   FileDialog f = new FileDialog(this, "Save", FileDialog.SAVE);
   f.setDirectory("C:\\Testes\\Prog\\");
   f.setVisible(true);// Display the dialog and block.
    
  try
  {
  this.carrega.CarregadorTipoNomePrograma(this.carrega.pro_x ,this.getProtocolo(), this.getVel(), this.getPrecision());
	this.carrega.CarregadorArquivo(f.getDirectory(), f.getFile());
 	}
	catch(Exception exc)
		{
    exc.printStackTrace();
    }
}


}