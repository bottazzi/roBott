package smart.roBott.app;

import com.jme3.light.DirectionalLight;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import smart.roBott.app.captur.StlToMesh;
import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Mesh;
import com.jme3.light.AmbientLight;
import com.jme3.light.PointLight;
import com.jme3.scene.shape.Box;

import javax.swing.*;
import java.awt.*;

/**
 * Created by konstantin.petrukhnov@gmail.com on 2017-01-04.
 */
public class RobotWorkspace extends SimpleApplication {

    private static RobotWorkspace app;
    Node workspace;
    String robotfilepath = System.getProperty("user.dir").concat("/assets/Models/bracao.stl");
    String partfilepath = System.getProperty("user.dir").concat("/assets/Models/steel_beam_finished_1x1x15.stl");

    private RobotWorkspace() {
        workspace = new Node();


    }

    public static void main(String[] args){

        app = new RobotWorkspace();

        Frame1 frame = new Frame1(app);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        frame.setLocation(d.width/10, d.height / 10);
        frame.setVisible(true);


        app.setPauseOnLostFocus(false);
        app.showSettings = true;
        app.setShowSettings(false);
        app.start();

    }

    @Override
    public void simpleInitApp() {

        // Camera settings
        flyCam.setMoveSpeed(30);

        // Lighting
        AmbientLight ambient = new AmbientLight();
        ambient.setColor(ColorRGBA.White.mult(0.4f));
        rootNode.addLight(ambient);

        PointLight lamp = new PointLight();
        lamp.setPosition(new Vector3f(0, 1, 0));
        lamp.setColor(ColorRGBA.Yellow);
        System.out.println(lamp.getRadius());
        rootNode.addLight(lamp);

        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(1, 0.0f, -2));
        sun.setColor(ColorRGBA.White);
        rootNode.addLight(sun);

//        // Workspace
//        Spatial workspace = assetManager.loadModel("assets/Scenes/RoBott_Workcell.j3o");
//        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//        mat.setColor("Color", ColorRGBA.Green);
//        workspace.setMaterial(mat);
//
//        rootNode.attachChild(workspace);

//        // Table
//        Box b = new Box(2, 1, 1);
//        Geometry geom = new Geometry("Box", b);
//        Material tableMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//        tableMat.setColor("Color", ColorRGBA.Blue);
//        geom.setMaterial(tableMat);
//        geom.move(0.0f, 1.0f, 0.0f);
//
//        rootNode.attachChild(geom);

//        // Articulated Arm Reference
//        Mesh mArm = null;
//        try {
//            mArm = StlToMesh.loadStl(partfilepath);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        Geometry cobot = new Geometry("Articulated Arm Reference", mArm);
//        Material beamMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//        beamMat.setColor("Color", ColorRGBA.Red);
//        cobot.setMaterial(beamMat);
//        cobot.move(0.0f, 2.0f, -150.0f);
//
//        rootNode.attachChild(cobot);


        // Part Reference
        Mesh mPart = null;
        try {
            mPart = StlToMesh.loadStl(partfilepath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Geometry part = new Geometry("Articulated Arm Reference", mPart);
        Material partMat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        partMat.setColor("Color", ColorRGBA.Gray);
        part.setMaterial(partMat);
        part.move(0.0f, 2.0f, -100.0f);

        rootNode.attachChild(part);

    }

    public void load(String newFilePath) {
        try {

        Mesh m = StlToMesh.loadStl(newFilePath);

        Geometry geom = new Geometry("Steel_beam_finished_1x1x15", m);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        geom.setMaterial(mat);

        geom.move(0, -20.0f, -500.0f);
        geom.rotate(0, 0.2f, 0);

        rootNode.attachChild(geom);

        PointLight  pl = new PointLight();
        pl.setColor(ColorRGBA.Magenta);
        pl.setRadius(400000f);
        rootNode.addLight(pl);

        AmbientLight al = new AmbientLight();
        al.setColor(ColorRGBA.White.mult(1.3f));
        rootNode.addLight(al);

        }catch (Exception e){
            System.out.println(e);
        }

    }

        @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
}
