/*
 *  This file is part of Empty3.
 *
 *     Empty3 is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Empty3 is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Empty3.  If not, see <https://www.gnu.org/licenses/>. 2
 */

package one.empty3.library;

import java.awt.*;

/**
 * Created by manue on 06-11-19.
 */
public class ArcBall2 {
    private final ZBufferImpl zBuffer;
    private Point3D pointCenter;
    private double radius;
    private Point3D currentPosition;
    private Camera currentCamera;
    private double lastX, lastY;
    private Representable representable;

    /**
     *
     * @param ray origin: camera.eye ; extrem : vector dir
     * @return t :: orig+t*v
     */
    public Point3D  intersect(Axe ray)
    {
        double t0, t1; // solutions for t if the ray intersects
        // geometric solution
        Point3D L = pointCenter.moins(ray.getP1().getElem());
        double tca = L.prodScalaire(ray.getVector());
        // if (tca < 0) return false;
        double d2 = L.prodScalaire(L) - tca * tca;
        if (d2 > radius) throw new NullPointerException();
        double thc = Math.sqrt(radius*radius - d2);
        t0 = tca - thc;
        t1 = tca + thc;
        if (t0 > t1) {
            double tmp = t0;
            t0 = t1;
            t1 = tmp;
        }

        if (t0 < 0) {
            t0 = t1; // if t0 is negative, let's use t1 instead
            if (t0 < 0) throw new NullPointerException();
        }

        double t = t0;

        return ray.getP1().getElem().plus(ray.getVector().mult(t));
    }
    public ArcBall2(Camera camera, Point3D point, double radius, ZBufferImpl zBuffer)
    {
       currentCamera = camera;
        pointCenter = point;
        this.radius = radius;
        this.zBuffer = zBuffer;
    }

    public void init(Representable representable) {
        this.representable = representable;
        Point p = currentCamera.coordonneesPoint2D(pointCenter, zBuffer);
        lastX = p.getX();
        lastY = p.getY();

    }
    public void moveTo(int x, int y) {
        Point3D mult = currentCamera.getMatrice().tild().mult(new Point3D((double) x, (double) y, 0.0));
        Point3D intersect = intersect(new Axe(currentCamera.eye(), mult));
        computeMatrix(pointCenter, currentPosition, intersect);
        currentPosition = intersect;
    }
    public void computeMatrix(Point3D p0, Point3D intersect1, Point3D intersect2)
    {
        if(p0!=null && intersect1!=null && intersect2!=null)
        {
            Matrix33 matrix33 = representable.getRotation().getElem().getRot().getElem();
            matrix33.getRowVectors();
            matrix33.getColVectors();


        }
    }
    public Double [] matrix()
    {
        return null;
    }
}
