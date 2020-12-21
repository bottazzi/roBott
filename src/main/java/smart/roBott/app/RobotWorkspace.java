package smart.roBott.app;

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

import javax.swing.*;
import java.awt.*;

/**
 * Created by konstantin.petrukhnov@gmail.com on 2017-01-04.
 */
public class RobotWorkspace extends SimpleApplication {

    private static RobotWorkspace app;
    Node workspace;
    String filepath = System.getProperty("user.dir").concat("/assets/steel_beam_finished_1x1x15.stl");

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
        Mesh m = null;
        try {
            m = StlToMesh.loadStl(filepath);
        } catch (Exception e) {
            e.printStackTrace();
        }

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
