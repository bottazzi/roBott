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

import javax.media.j3d.Appearance;
import javax.media.j3d.Geometry;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.Material;
import javax.media.j3d.QuadArray;
import javax.media.j3d.Shape3D;

import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

public class LitQuad extends Shape3D
{
    LitQuad(Point3f A, Point3f B, Point3f C, Point3f D) {
        this.setGeometry(createGeometry(A, B, C, D));
        this.setAppearance(createAppearance());
    }    

  static Geometry createGeometry(Point3f A, Point3f B, Point3f C, Point3f D){

        QuadArray plane = new QuadArray(4, GeometryArray.COORDINATES | GeometryArray.NORMALS);

        plane.setCoordinate(0, A);
        plane.setCoordinate(1, B);
        plane.setCoordinate(2, C);
        plane.setCoordinate(3, D);

        Vector3f a = new Vector3f(A.x - B.x, A.y - B.y, A.z - B.z);
        Vector3f b = new Vector3f(C.x - B.x, C.y - B.y, C.z - B.z);
        Vector3f n = new Vector3f();
        n.cross(b, a);

        n.normalize();

        plane.setNormal(0, n);
        plane.setNormal(1, n);
        plane.setNormal(2, n);
        plane.setNormal(3, n);

        return plane;
    }

static  Appearance createAppearance() {
        Appearance appear = new Appearance();
        Material material = new Material();
        appear.setMaterial(material);

        return appear;
    }

}