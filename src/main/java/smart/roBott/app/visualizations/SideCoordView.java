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

package smart.roBott.app.visualizations;

import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.geometry.Stripifier;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;

import smart.roBott.app.coord.Coord_World;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.media.j3d.*;

import javax.vecmath.*;

import smart.roBott.app.opt.*;

import smart.roBott.app.service.*;

final public class SideCoordView extends Frame implements KeyListener {

  /**
  	 *
  	 */
  private static final long serialVersionUID = 9078140456246097165L;

  final int
        /**/
        SpintoE  = KeyEvent.VK_RIGHT,
        SpintoW  = KeyEvent.VK_LEFT,
        SpintoN  = KeyEvent.VK_UP,
        SpintoS  = KeyEvent.VK_DOWN,
        SpintoUP = KeyEvent.VK_PAGE_UP,
        SpintoDOWN = KeyEvent.VK_PAGE_DOWN,
        ShifttoE = KeyEvent.VK_RIGHT + KeyEvent.VK_SHIFT,
        ShifttoW = KeyEvent.VK_LEFT  + KeyEvent.VK_SHIFT,
        ShifttoN = KeyEvent.VK_UP  + KeyEvent.VK_SHIFT,
        ShifttoS = KeyEvent.VK_DOWN  + KeyEvent.VK_SHIFT,
        ShifttoUP= KeyEvent.VK_PAGE_UP + KeyEvent.VK_SHIFT,
        ShifttoDOWN = KeyEvent.VK_PAGE_DOWN + KeyEvent.VK_SHIFT,
        ShifttoHome = KeyEvent.VK_ESCAPE,
        UpdateRef = KeyEvent.VK_F5,
        Generate = KeyEvent.VK_F2;
        
private double incx = 0.0d;
private double incy = 0.0d;
private double incz = 0.0d;

private double incgiropx = 0.0d;
private double incgiropy = 0.0d;
private double incgiropz = 0.0d;
static final private float scale = 1000;

 private float offset = 0.0f;
 private float offsetx = 0.0f;
 private float offsety = 0.0f;
 private float offsetz = 0.0f;

//private Canvas3D c;
private Carregador Carga;
private TransformGroup objTrans;

private Transform3D t = new Transform3D();//usado para o posicionamento da pe a
//private Matrix3d m3d = new Matrix3d();
private Matrix4d m3d = new Matrix4d();
private TransformGroup pointSelTrans;
private TransformGroup viewTransform;
private BranchGroup viewBranchGroup;


static private TransformGroup AttachObject(float scale, float xpos, float ypos, float zpos) {
	
	Transform3D t = new Transform3D();
	t.set(scale, new Vector3d(xpos, ypos, zpos));
	TransformGroup objTransPoints = new TransformGroup(t);
	
  Primitive obj = null;
  obj = (Primitive) new Sphere(0.004f);
  
  /**ativa o report de colis o*/
  //obj.setCapability(obj.ENABLE_COLLISION_REPORTING); 

//Cor dos pontos selecionados - Azul Claro
       Appearance appSphere = new Appearance();
       ColoringAttributes colorAttrsplano = new ColoringAttributes();
       colorAttrsplano.setColor(0.5f, 0.5f, 0.5f);//cor plano
       appSphere.setColoringAttributes(colorAttrsplano);
       obj.setAppearance(appSphere);

	// add it to the scene graph.
  objTransPoints.addChild(obj);

	return objTransPoints;
    }
/*********
 * 
 * 
 * MVCMVCMVCMVCMVCMVC
 * 
 */
static private void DetachObject(int n){

//objTrans.removeChild(n);

}

static private void NotifyObject(Programa p)
{
p.Update(p.coord_table);
//mostra novamente as coordenadas no ambiente J3D
}
 
private void setGeometry(Programa p, TransformGroup geomTrans, float offsetx, float offsety, float offsetz)// throws Exception
{
int linha = 0;
float Mz = 0;
float x,y,z = 0.0f;
Coord_World coordWorld;
p.PointSelArray.clear();

        GeometryInfo gi = new GeometryInfo(GeometryInfo.TRIANGLE_ARRAY);//QUAD_ARRAY);//POLYGON_ARRAY);//
        Point3f[] pts = new Point3f[(int)p.n_linhas_arqcoord];
        
        
       Appearance app = new Appearance();
  
                
        // Especifica a apar ncia do material
        Material material = new Material(new Color3f(0.7f,0.1f,0.7f), 
                                         new Color3f(0.0f,0.0f,0.0f), 
                                         new Color3f(0.7f,0.1f,0.7f), 
                                         new Color3f(1.0f,1.0f,1.0f), 60.0f);
                                         
 /*       Material material = new Material(new Color3f(0.0f,0.0f,0.2f), 
                                         new Color3f(0.0f,0.0f,0.2f), 
                                         new Color3f(0.0f,0.0f,0.2f), 
                                         new Color3f(0.0f,0.0f,0.2f), 60.0f);
    */                                     
        app.setMaterial(material);

        Shape3D s3d = new Shape3D();
        s3d.setAppearance (app);

//Aparencia dos obj que representar o os pontos no universo
  while (linha < p.n_linhas_arqcoord)
  {
        //Coordenadas Centralizadas
        x = p.coord_table[linha][0].floatValue() + offsetx;
        y = p.coord_table[linha][1].floatValue() + offsety;
        z = p.coord_table[linha][2].floatValue() + offsetz;
        
        pts[linha] = new Point3f(x/scale, y/scale, z/scale);
               
        //Programa carregado com as coordenadas do mundo real reais
        if (Mz < z) Mz = z;
          p.planoSelZ = Mz + p.RobotOffset.Coord_Z;

        p.coord_table[linha][0] = new Float(x + p.RobotOffset.Coord_X);
        p.coord_table[linha][1] = new Float(y + p.RobotOffset.Coord_Y);
        p.coord_table[linha][2] = new Float(z + p.RobotOffset.Coord_Z);
        
        coordWorld = new Coord_World(x + p.RobotOffset.Coord_X, y + p.RobotOffset.Coord_Y, z + p.RobotOffset.Coord_Z);
        p.PointSelArray.add(coordWorld); 

        linha++;        
    }
       
        gi.setCoordinates(pts);
            

        NormalGenerator ng = new NormalGenerator();
        // Passar 100 como par metro para a fun  o abaixo, faz com que
        // as "dobras" (uni o das faces) fique mais suave do que se fosse  
        // passado um valor mais baixo
        ng.setCreaseAngle( (float) Math.toRadians(100) );
        ng.generateNormals(gi);

        //Une os triangulos em tiras
        Stripifier st = new Stripifier();        
        st.stripify(gi);        
      
        GeometryArray newGeometry = gi.getGeometryArray();
        s3d.setGeometry (newGeometry);
        
        geomTrans.addChild(s3d);
}


 
private void setPointsCloud(Programa p, float offsetx, float offsety, float offsetz)
{
int linha = 0;
float Mz = 0;
float x,y,z = 0.0f;
Coord_World coordWorld;
p.PointSelArray.clear();

//Aparencia dos obj que representar o os pontos no universo
  while (linha < p.n_linhas_arqcoord)
  {
        //Coordenadas Centralizadas
        x = p.coord_table[linha][0].floatValue() + offsetx;
        y = p.coord_table[linha][1].floatValue() + offsety;
        z = p.coord_table[linha][2].floatValue() + offsetz;
                
        //Adiciona Objs com as coord para o centro do mundo para q seja possivel fazer rota  es na pe a
        objTrans.addChild(this.AttachObject( 1.0f , x/scale, y/scale, z/scale));
        
        //Programa carregado com as coordenadas do mundo real reais
        if (Mz < z) Mz = z;
          p.planoSelZ = Mz + p.RobotOffset.Coord_Z;

        p.coord_table[linha][0] = new Float(x + p.RobotOffset.Coord_X);
        p.coord_table[linha][1] = new Float(y + p.RobotOffset.Coord_Y);
        p.coord_table[linha][2] = new Float(z + p.RobotOffset.Coord_Z);
        
        coordWorld = new Coord_World(x + p.RobotOffset.Coord_X, y + p.RobotOffset.Coord_Y, z + p.RobotOffset.Coord_Z);
        p.PointSelArray.add(coordWorld); 

        linha++;
        
    }
   
}


private void createCloud(Programa p)
{
int linha = 0;
float Mz = 0;
float x,y,z = 0.0f;
Coord_World coordWorld;
p.PointSelArray.clear();
   //Aparencia dos obj que representar o os pontos no universo
  while (linha < p.n_linhas_arqcoord)
  {
        //Coordenadas Centralizadas
        x = p.coord_table[linha][0].floatValue();
        y = p.coord_table[linha][1].floatValue();
        z = p.coord_table[linha][2].floatValue();
        
        //Adiciona Objs com as coord para o centro do mundo para q seja possivel fazer rota  es na pe a
        objTrans.addChild(this.AttachObject( 1.0f , x/scale, y/scale, z/scale));
        
        //Programa carregado com as coordenadas do mundo real reais
        if (Mz < z) Mz = z;
          p.planoSelZ = Mz + p.RobotOffset.Coord_Z;

        p.coord_table[linha][0] = new Float(x);
        p.coord_table[linha][1] = new Float(y);
        p.coord_table[linha][2] = new Float(z);
        
        coordWorld = new Coord_World(x, y, z);
        p.PointSelArray.add(coordWorld); 

        linha++;
        
    }
}


private BranchGroup createRobotReference(Programa p)//, Canvas3D c)// throws Exception
    {
  // Create the root of the branch graph
  BranchGroup RobotBody = new BranchGroup();
  // Create a transform group node to scale and position the object.
  Transform3D t1 = new Transform3D();
  t1.set(25.0f , new Vector3d(-(p.RobotOffset.Coord_X )/scale, -(p.RobotOffset.Coord_Y )/scale, -(p.RobotOffset.Coord_Z )/scale)); //recua o rob  em rela  o a area de trabalho usando a pe a como refer ncia
  TransformGroup RobotTrans = new TransformGroup(t1);
	//RobotTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	//RobotTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	//objTrans.setCapability(TransformGroup.ENABLE_PICK_REPORTING);

  RobotBody.addChild(RobotTrans);

p.RobotOffset = new Coord_World(0.0f,0.0f,0.0f);
this.setGeometry(p, RobotTrans, 0.0f,0.0f,0.0f); 

//Inicializa matriz de transform
m3d.setIdentity();
// Have Java 3D perform optimizations on this scene graph.
RobotBody.compile();

return RobotBody;
}


private BranchGroup createWorkAreaReferences(Programa p)//, Canvas3D c) //BranchGroup
{
  // Create the root of the branch graph
  BranchGroup sceneGraphRef = new BranchGroup();
       
  // Create a transform group node to scale and position the object.
  Transform3D t = new Transform3D();
  t.set(1.0f , new Vector3d(-p.RobotOffset.Coord_X/scale, -p.RobotOffset.Coord_Y/scale, -p.RobotOffset.Coord_Z/scale)); //recua o rob  em rela  o a area de trabalho usando a pe a como refer ncia
  TransformGroup objTransGraphRef = new TransformGroup(t);
  
  objTransGraphRef.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	objTransGraphRef.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	objTransGraphRef.setCapability(TransformGroup.ENABLE_PICK_REPORTING);
       
  //Cria a workarea

   Shape3D workarea = new LitQuad(new Point3f(p.infx/scale, p.infy/scale, p.infz/scale),
                                      new Point3f(p.supx/scale, p.infy/scale,  p.infz/scale),
                                      new Point3f(p.supx/scale, p.supy/scale,  p.infz/scale),
                                      new Point3f(p.infx/scale, p.supy/scale,  p.infz/scale));

       Appearance appworkarea = new Appearance();
       ColoringAttributes colorAttrsworkarea = new ColoringAttributes();
       colorAttrsworkarea.setColor(1.0f, 1.0f, 0.0f);//cor workarea
       appworkarea.setColoringAttributes(colorAttrsworkarea);
       workarea.setAppearance(appworkarea);

       objTransGraphRef.addChild(workarea);
       
       
    float dif = 0.02f;
     //Cria o plano para a sele  o dos pontos

     Shape3D selplan = new LitQuad(new Point3f(p.infx/scale+dif, p.infy/scale+dif,  p.infz/scale + 0.001f),
                                   new Point3f(p.supx/scale-dif, p.infy/scale+dif,  p.infz/scale + 0.001f),
                                   new Point3f(p.supx/scale-dif, p.supy/scale-dif,  p.infz/scale + 0.001f),
                                   new Point3f(p.infx/scale+dif, p.supy/scale-dif,  p.infz/scale + 0.001f));

       Appearance appselplan = new Appearance();
       ColoringAttributes colorAttrsplano = new ColoringAttributes();
       colorAttrsplano.setColor(0.0f, 1.0f, 0.0f);//cor plano
       appselplan.setColoringAttributes(colorAttrsplano);
       selplan.setAppearance(appselplan);
      
       objTransGraphRef.addChild(selplan);
                  
   Color3f light1Color = new Color3f(1.0f, 0.0f, 0.2f);

   Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);

   DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
   
   BoundingSphere bounds = new BoundingSphere( new Point3d(0.0f,0.0f,0.0f),100.0 );        
   
   light1.setInfluencingBounds(bounds);

   objTransGraphRef.addChild(light1);

  sceneGraphRef.addChild(objTransGraphRef);
  
  sceneGraphRef.compile();

return sceneGraphRef;

}

static private float PontoMedio(float max, float min)
{float temp = 0.0f;
  if(min<0 && max <0)
    temp = -((min - max)/2 + max);
  else
    temp = -((max - min)/2 + min);
  
  return temp;  
}


private BranchGroup createBody(float scale , Programa p)//, Canvas3D c)// throws Exception
    {
  // Create the root of the branch graph
  BranchGroup Body = new BranchGroup();
  // Create a transform group node to scale and position the object.
  t = new Transform3D();
  t.set(scale, new Vector3d(0.0, 0.0, 0.0));
  objTrans = new TransformGroup(t);
	objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	//objTrans.setCapability(TransformGroup.ENABLE_PICK_REPORTING);

  Body.addChild(objTrans);

    //BoundingSphere bound = new BoundingSphere( new Point3d(0.0f,0.0f,0.0f), 1.0 );    
    //PickTranslateBehavior behavior = new PickTranslateBehavior(objRootBg, c, bounds);
    //objRootBg.addChild(behavior);
    //PickRotateBehavior behavior1 = new PickRotateBehavior(Body, c, bound);
    //Body.addChild(behavior1);

        //inicializa variaveis para centralizar contorno
        float maxx = 0.0f;
        float minx = 0.0f;
        float maxy = 0.0f;
        float miny = 0.0f;
        float maxz = 0.0f;
        float minz = 0.0f;
        if (p.coord_table.length > 0)
        {
        maxx = p.coord_table[0][0].floatValue();
        minx = p.coord_table[0][0].floatValue();
        maxy = p.coord_table[0][1].floatValue();
        miny = p.coord_table[0][1].floatValue();
        maxz = p.coord_table[0][2].floatValue();
        minz = p.coord_table[0][2].floatValue();
        
        int linha = 0;
        for(; linha < p.n_linhas_arqcoord ; linha++)//encontra minimos e maximos nas respec dimensoes
        {         
          if (minx > p.coord_table[linha][0].floatValue()) minx = p.coord_table[linha][0].floatValue();
          if (maxx < p.coord_table[linha][0].floatValue()) maxx = p.coord_table[linha][0].floatValue();
          if (miny > p.coord_table[linha][1].floatValue()) miny = p.coord_table[linha][1].floatValue();
          if (maxy < p.coord_table[linha][1].floatValue()) maxy = p.coord_table[linha][1].floatValue();
          if (minz > p.coord_table[linha][2].floatValue()) minz = p.coord_table[linha][2].floatValue();
          if (maxz < p.coord_table[linha][2].floatValue()) maxz = p.coord_table[linha][2].floatValue();
        } 
                       
        //calcula offset para deslocamento do obj carregado  para o centro do universo        
        offsetx = PontoMedio(maxx, minx);
        offsety = PontoMedio(maxy, miny);
        offsetz = PontoMedio(maxz, minz);        
        //offsetz = -minz;
        
       if (minz < 0)
        {
        offsetz = minz;
        }
        else
        {
        offsetz = -minz; 
        }

if (p.isMesh)
{

p.RobotOffset = new Coord_World((p.supx - p.infx)/2 + p.infx, (p.supy - p.infy)/2 + p.infy, offsetz);
this.setGeometry(p, objTrans, offsetx, offsety, offsetz );  
}
else
{
p.RobotOffset = new Coord_World((p.supx - p.infx)/2 + p.infx, (p.supy - p.infy)/2 + p.infy, 0);// p.planoSelZ);
this.setPointsCloud(p,offsetx, offsety, 0);//offsetx, offsety, offsetz ); 
}


}
/*
    // for the QuadArray that was populated.
    GeometryInfo gi = new GeometryInfo(GeometryInfo.QUAD_ARRAY);
    Appearance app = new Appearance();
    gi.setCoordinates(coordArray);
    gi.setTextureCoordinates(texCoordArray);

    NormalGenerator normalGenerator = new NormalGenerator();
    normalGenerator.generateNormals(gi);

    // wrap the GeometryArray in a Shape3D
    Shape3D shape = new Shape3D(gi.getGeometryArray(), app);

    // add the Shape3D to the parent BranchGroup
    Body.addChild(shape);
*/

// Have Java 3D perform optimizations on this scene graph.
Body.compile();

return Body;
}

/*
public void SetWorkspace(Coord_World coord)
{
this.infx = coord.Coord_X;
this.infy = coord.Coord_Y;
this.infz = coord.Coord_Z;
  
}

public Coord_World GetWorkspace()
{
Coord_World coord = new Coord_World(p.infx,p.infy, p.infz);
return coord;  
}

*/

// public Canvas3D getCanvas()
// {
//   return this.c;
// }


public void NewSolid(Carregador newCarga, boolean isSolid)//Programa newProg
{

//if (viewBranchGroup.numChildren() > 3)
//{
//Node old_Body = viewBranchGroup.getChild(3);
//this.DetachObject(old_Body);
//}

//Timer t = new Timer();
this.Carga = newCarga;
BranchGroup Body = createBody(1.0f ,newCarga.pro_x);//, c); //vem antes das referencia para calculo do centro da pe a e passagem do posicionamento do centro do rob 
viewBranchGroup.addChild(Body);

}

public SideCoordView(Carregador carga)//Programa p)// throws Exception
{

setLayout(new BorderLayout());

GraphicsConfigTemplate3D tmpl = new GraphicsConfigTemplate3D();

GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();

GraphicsDevice device = genv.getDefaultScreenDevice();

GraphicsConfiguration config = device.getBestConfiguration(tmpl);

// c = new Canvas3D(config);
// add("Center", c);
// SimpleUniverse u = new SimpleUniverse(c);     

SimpleUniverse u = new SimpleUniverse();
Locale locale = new Locale(u); 

  //BranchGroup 
  viewBranchGroup = new BranchGroup();

viewBranchGroup.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
viewBranchGroup.setCapability(BranchGroup.ALLOW_DETACH);
viewBranchGroup.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
viewBranchGroup.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);


Timer t1 = new Timer();
  BranchGroup WorkAreaRef = createWorkAreaReferences(carga.Componente);//, c);
t1.print("Tempo a desenhar sceneGraphRef");

Timer t2 = new Timer();
BranchGroup RobotRef = createRobotReference(carga.Componente);// , c);
t2.print("Tempo a desenhar sceneGraphSelRef");
  
  viewBranchGroup.addChild(WorkAreaRef);
  viewBranchGroup.addChild(RobotRef);
  
  View myView = new View();

  ViewingPlatform viewingPlatform = u.getViewingPlatform();
  viewingPlatform.setNominalViewingTransform();
  
        // Adiciona "mouse behaviors"   "viewingPlatform" 
        // (equivale a trocar a posi  o do "observador virtual")
        //OrbitBehavior orbit = new OrbitBehavior(c, OrbitBehavior.REVERSE_ALL);
        //BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        //orbit.setSchedulingBounds(bounds);
        //viewingPlatform.setViewPlatformBehavior(orbit);
        
/*
 * Para usar uma janela independente
 * 
        u.addBranchGraph(viewBranchGroup);
        setSize(500,500);
        setVisible(true);
  */
  
  PhysicalBody physBody = new PhysicalBody(); 
  
  PhysicalEnvironment physEnv = new PhysicalEnvironment();
  
  myView.setPhysicalBody(physBody);
  
  myView.setPhysicalEnvironment(physEnv);
  
//  Color3f light1Color = new Color3f(1.0f, 0.0f, 0.2f);

//  Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);

//  DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);

//  light1.setInfluencingBounds(bounds);

//  viewBranchGroup.addChild(light1);
 
  //viewBranchGroup.compile();
  
  locale.addBranchGraph(viewBranchGroup);

  //c.addKeyListener(this);
}

//KEY EVENTS
public void keyReleased(KeyEvent e){
   // Invoked when a key has been released.
}

public void keyTyped(KeyEvent e){

   //Invoked when a key has been typed.

}

public void keyPressed(KeyEvent e) {

  int keyCode = e.getKeyCode();
  int shift   = e.isShiftDown() ? KeyEvent.VK_SHIFT : 0;
  int i = 0;
  double incrementograus = Math.PI/180;
  float incrementomm = 0.001f;
  Matrix4d AuxRotm3d = new Matrix4d();
  Matrix4d AuxUpm3d = new Matrix4d();
  AuxRotm3d.setIdentity();
  AuxUpm3d.setIdentity();
  
 switch(keyCode + shift)
 {
  case SpintoS:
   AuxRotm3d.rotX(incrementograus);
   m3d.mul(AuxRotm3d);
   t.set(m3d);
   objTrans.setTransform(t);
   System.out.println("Rota  o do eixo X em: " + incgiropx * 180/Math.PI + " ");
   break;
  case SpintoN:
   AuxRotm3d.rotX(-incrementograus);
   m3d.mul(AuxRotm3d);
   t.set(m3d);
   objTrans.setTransform(t);
   System.out.println("Rota  o do eixo X em: " + incgiropx * 180/Math.PI + " ");
   break;
  case SpintoE:
   AuxRotm3d.rotY(incrementograus);
   m3d.mul(AuxRotm3d);
   t.set(m3d);
   objTrans.setTransform(t);
   System.out.println("Rota  o do eixo Y em: " + incgiropy * 180/Math.PI + " ");
   break;
  case SpintoW:
   AuxRotm3d.rotY(-incrementograus);
   m3d.mul(AuxRotm3d);
   t.set(m3d);
   objTrans.setTransform(t);
   System.out.println("Rota  o do eixo Y em: " + incgiropy * 180/Math.PI + " ");
   break;
   case SpintoUP:
   AuxRotm3d.rotZ(incrementograus);
   m3d.mul(AuxRotm3d);
   t.set(m3d);
   objTrans.setTransform(t);
   System.out.println("Rota  o do eixo Z em: " + incgiropz * 180/Math.PI + " ");
   break;
  case SpintoDOWN:
   AuxRotm3d.rotZ(-incrementograus);
   m3d.mul(AuxRotm3d);
   t.set(m3d);
   objTrans.setTransform(t);
   System.out.println("Rota  o do eixo Z em: " + incgiropz * 180/Math.PI + " ");
   break;
  case ShifttoN:
   incy = incy + incrementomm;
   t.get(m3d);
   m3d.m13 = incy;
   t.set(m3d);
   objTrans.setTransform(t);
   System.out.println("Deslocamento em Y: " + incy * 1000 + "mm");
   break;
  case ShifttoS:
   incy = incy - incrementomm;
   t.get(m3d);
   m3d.m13 = incy;
   t.set(m3d);
   objTrans.setTransform(t);
   System.out.println("Deslocamento em Y: " + incy * 1000 + "mm");
   break;
  case ShifttoE:
   incx = incx + incrementomm;
   t.get(m3d);
   m3d.m03 = incx;
   t.set(m3d);
   objTrans.setTransform(t);
   System.out.println("Deslocamento em X: " + incx * 1000 + "mm");
   break;
  case ShifttoW:
   incx = incx - incrementomm;
   t.get(m3d);
   m3d.m03 = incx;
   t.set(m3d);
   objTrans.setTransform(t);
   System.out.println("Deslocamento em X: " + incx * 1000 + "mm");
   break;
   case ShifttoUP:
   incz = incz + incrementomm;
   t.get(m3d);
   m3d.m23 = incz;
   t.set(m3d);
   objTrans.setTransform(t);
   System.out.println("Deslocamento em Z: " + incz * 1000 + "mm");
   break;
  case ShifttoDOWN:
   incz = incz - incrementomm;
   t.get(m3d);
   m3d.m23 = incz;
   t.set(m3d);
   objTrans.setTransform(t);
   System.out.println("Deslocamento em Z: " + incz * 1000 + "mm");
   break;
  case ShifttoHome:
   m3d.setIdentity();
   t.set(m3d);
   objTrans.setTransform(t);
   System.out.println("ESC");
   break;
   case UpdateRef:
   for (i= 0; i < Carga.pro_x.n_linhas_arqcoord; i = i+3)
   {
   //Carga.pro_x.coord_table
   AuxUpm3d = m3d;

   AuxRotm3d.m00 = Carga.pro_x.coord_table[i][0].doubleValue();
   AuxRotm3d.m10 = Carga.pro_x.coord_table[i][1].doubleValue();
   AuxRotm3d.m20 = Carga.pro_x.coord_table[i][2].doubleValue();
   
   AuxRotm3d.m01 = Carga.pro_x.coord_table[i+1][0].doubleValue();
   AuxRotm3d.m11 = Carga.pro_x.coord_table[i+1][1].doubleValue();
   AuxRotm3d.m21 = Carga.pro_x.coord_table[i+1][2].doubleValue();
   
   AuxRotm3d.m01 = Carga.pro_x.coord_table[i+2][0].doubleValue();
   AuxRotm3d.m11 = Carga.pro_x.coord_table[i+2][1].doubleValue();
   AuxRotm3d.m21 = Carga.pro_x.coord_table[i+2][2].doubleValue();
   
   AuxUpm3d.mul(AuxRotm3d);  
   
Carga.pro_x.coord_table[i][0]=   new Float(AuxUpm3d.m00); 
Carga.pro_x.coord_table[i][1]=    new Float(AuxUpm3d.m10);
Carga.pro_x.coord_table[i][2]=    new Float(AuxUpm3d.m20); 
   
Carga.pro_x.coord_table[i+1][0]=    new Float(AuxUpm3d.m01);
Carga.pro_x.coord_table[i+1][1]=    new Float(AuxUpm3d.m11);
Carga.pro_x.coord_table[i+1][2]=    new Float(AuxUpm3d.m21); 
   
Carga.pro_x.coord_table[i+2][0]=    new Float(AuxUpm3d.m01); 
Carga.pro_x.coord_table[i+2][1]=    new Float(AuxUpm3d.m11); 
Carga.pro_x.coord_table[i+2][2]=    new Float(AuxUpm3d.m21);
   
   }
   break;
   case Generate:

   break;
  default:
   break;
 }
}


}