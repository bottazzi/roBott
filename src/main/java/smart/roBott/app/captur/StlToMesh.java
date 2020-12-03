package smart.roBott.app.captur;

import com.jme3.math.Vector3f;
import com.jme3.scene.Mesh;
import com.jme3.scene.VertexBuffer.Type;
import com.jme3.util.BufferUtils;
import net.sf.javaml.core.kdtree.KDTree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
* Thanks kantengri, extracted from https://hub.jmonkeyengine.org/t/stl-import/26131 as StlImporter.java
* */

public class StlToMesh {

    static Pattern FACET_PAT = Pattern.compile("\\s*facet\\s+normal\\s+(.*?)\\s+(.*?)\\s+(.*)");
    static Pattern VERTEX_PAT = Pattern.compile("\\s*vertex\\s+(.*?)\\s+(.*?)\\s+(.*)");

    public static Mesh loadStl(String file) throws Exception {


        List<Vector3f> points = new ArrayList<>();
        List<Vector3f> normals = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                Matcher facetMat = FACET_PAT.matcher(line);
                if (facetMat.find()) {
                    Vector3f norm = new Vector3f(Float.parseFloat(facetMat.group(1)), Float.parseFloat(facetMat.group(2)), Float.parseFloat(facetMat.group(3)) );
                    if (!Vector3f.isValidVector(norm) || norm.length() == 0) {
                        continue;
                    }
                    normals.add(norm);

                    int i = 0;
                    while ((line = br.readLine()) != null) {
                        line = line.trim();
                        Matcher vertexMat = VERTEX_PAT.matcher(line);
                        if (vertexMat.find()) {
                            points.add(new Vector3f(
                                    Float.parseFloat(vertexMat.group(1)),
                                    Float.parseFloat(vertexMat.group(2)),
                                    Float.parseFloat(vertexMat.group(3))
                            ));
                            i++;
                        } else if (i > 0) {
                            assert i == 3;
                            break;
                        }
                    }
                }
            }
        }

        assert points.size() % 3 == 0;
        assert points.size() / 3 == normals.size();

        List<Vector3f> pointsRefined = new ArrayList<>();
        int indexesSize = 0;
        int[] indexes = new int[1000 * 1024];

        KDTree kd = new KDTree(3);
        double[] key = new double[3];
        for (int i=0; i < points.size(); i++) {
            if (i < 1) {
                Vector3f pt = points.get(i);
                key[0] = pt.x;
                key[1] = pt.y;
                key[2] = pt.z;
                kd.insert(key, pointsRefined.size());
                indexes[indexesSize++] = pointsRefined.size();
                pointsRefined.add(pt);
            } else {
                Vector3f pt = points.get(i);
                key[0] = pt.x;
                key[1] = pt.y;
                key[2] = pt.z;
                int inx = (Integer) kd.nearest(key);
                Vector3f ptInx = pointsRefined.get(inx);
                if (pt.distanceSquared(ptInx) > 0.00001f) {
                    kd.insert(key, pointsRefined.size());
                    indexes[indexesSize++] = pointsRefined.size();
                    pointsRefined.add(pt);
                } else {
                    indexes[indexesSize++] = inx;
                }
            }
        }

        assert indexesSize == points.size();

        Map<Integer, List<Integer>> v2trMap = new HashMap<>();
        for (int i=0; i<indexesSize; i++) {
            List<Integer> triangles = v2trMap.get(indexes[i]);
            if (triangles == null) {
                triangles = new ArrayList<>();
                v2trMap.put(indexes[i], triangles);
            }
            triangles.add(i/3);
        }
        Vector3f[] vertexNormals = new Vector3f[pointsRefined.size()];
        for (int i=0; i < pointsRefined.size(); i++) {
            List<Integer> triangles = v2trMap.get(i);
            Vector3f avg = new Vector3f();
            for (Integer tinx : triangles) {
                Vector3f normal = normals.get(tinx);
                avg.addLocal(normal);
            }
            avg.divideLocal(triangles.size());
            vertexNormals[i] = avg;
        }

        float[] uv = new float[vertexNormals.length * 2];
        // define texcoords
        int uvInx = 0;
        for (Vector3f norm : vertexNormals) {

            assert -1f <= norm.x && norm.x <= 1f;
            assert -1f <= norm.y && norm.y <= 1f;
            assert -1f <= norm.z && norm.z <= 1f;

            double u = .5d + Math.atan2(norm.z, norm.x)/(Math.PI * 2d);
            double v = .5d - Math.asin(norm.y)/Math.PI;

            assert 0d <= u && u <= 1d;
            assert 0d <= v && v <= 1d;
            uv[uvInx++] = (float) u;
            uv[uvInx++] = (float) v;
        }

        Mesh mesh = new Mesh();
        indexes = Arrays.copyOfRange(indexes, 0, indexesSize);
        assert indexes.length == indexesSize;

        mesh.setBuffer(Type.Position, 3, BufferUtils.createFloatBuffer(pointsRefined.toArray(new Vector3f[]{})));
        mesh.setBuffer(Type.Index, 3, BufferUtils.createIntBuffer(indexes));
        mesh.setBuffer(Type.Normal, 3, BufferUtils.createFloatBuffer(vertexNormals));
        mesh.setBuffer(Type.TexCoord, 2, BufferUtils.createFloatBuffer(uv));
        mesh.updateBound();
        assert mesh.getVertexCount() == pointsRefined.size();
        assert mesh.getTriangleCount() == indexesSize / 3;
        assert indexesSize % 3 == 0;

        return mesh;
    }

}
