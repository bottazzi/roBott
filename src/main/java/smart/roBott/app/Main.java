package smart.roBott.app;

import com.jme3.app.SimpleApplication;

import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Cylinder;
import com.jme3.scene.Mesh;

import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.util.BufferUtils;
import com.jme3.light.AmbientLight;
import com.jme3.light.PointLight;

import java.awt.*;

/**
 * Created by konstantin.petrukhnov@gmail.com on 2017-01-04.
 */
public class Main extends SimpleApplication {

    private Main() {

    }

    public static void main(String[] args){
        final Main app = new Main();


        app.setPauseOnLostFocus(false);
        app.showSettings = true;
        app.start();
    }


    public void simpleInitApp() {

        try {

        Mesh m = StlImporter.loadStl(System.getProperty("user.dir").concat("/assets/steel_beam_finished_1x1x15.stl"));

        Geometry geom = new Geometry("Steel_beam_finished_1x1x15", m);
        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.Blue);
        geom.setMaterial(mat);
        geom.setLocalTranslation(0.0f,0.0f,0.0f);
        geom.rotate(0, 0.2f, 0);
        rootNode.attachChild(geom);

        PointLight  pl = new PointLight();
        pl.setColor(ColorRGBA.Magenta);
        pl.setRadius(400000f);
            rootNode.addLight(pl);

            AmbientLight al = new AmbientLight();
            al.setColor(ColorRGBA.White.mult(1.3f));
            rootNode.addLight(al);


        //orbit.addLight(pl);
//        DifuseLight sun = new DifuseLight();
//        sun.setDirection(new Vector3f(1,0,-2).normalizeLocal());
//        sun.setColor(ColorRGBA.White);
        //rootNode.addLight(sun);

        }catch (Exception e){
            System.out.println(e);
        }

//
//        Geometry geom = new Geometry("Steel_beam_finished_1x1x15", m);
//        Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
//        mat.setTexture("DiffuseMap", assetManager.loadTexture("Textures/Terrain/Pond/Pond.jpg"));
//        mat.setColor("Diffuse",ColorRGBA.White);
//        mat.setColor("Specular",ColorRGBA.White);
//        mat.setFloat("Shininess", 64f);  // [0,128]
//        geom.setMaterial(mat);
////            geom.setLocalTranslation(-1,2,-2); // Move it a bit
//        geom.rotate(1.6f, 0, 0);
//
//        rootNode.attachChild(geom);
//
//
//        /** Must add a light to make the lit object visible! */
//        DirectionalLight sun = new DirectionalLight();
//        sun.setDirection(new Vector3f(1,0,-2).normalizeLocal());
//        sun.setColor(ColorRGBA.White);
//        rootNode.addLight(sun);

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