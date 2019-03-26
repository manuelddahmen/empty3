/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.manudahmen.empty3.library.tests.spherestexturees;

import be.manudahmen.empty3.Camera;
import be.manudahmen.empty3.TextureCol;
import be.manudahmen.empty3.Point3D;
import be.manudahmen.empty3.core.testing.TestObjetSub;
import be.manudahmen.empty3.core.tribase.TRISphere;

import java.awt.*;

/**
 * @author Manuel DAHMEN
 */
public class Spheres extends TestObjetSub {
    TRISphere[] s;
    Point3D[] v;
    int N;
    double V;
    double D = 1;

    public Spheres(int n, double v) {
        N = n;
        V = v;
    }

    public static void main(String[] args) {
        Spheres s = new Spheres(100, 0.05);

        s.loop(true);

        s.run();
    }

    @Override
    public void ginit() {
        s = new TRISphere[N];
        v = new Point3D[N];
        for (int i = 0; i < N; i++) {
            s[i] = new TRISphere(Point3D.O0, 0.1);

            s[i].texture(new TextureCol(Color.WHITE));

            scene().add(s[i]);

            v[i] = new Point3D(Math.random() * (V / 2 - V), Math.random() * (V / 2 - V), Math.random() * (V / 2 - V));

        }


        scene().cameraActive(new Camera(Point3D.Z.mult(-5), Point3D.O0));
    }

    public void bounce(int i) {
        s[i].setCentre(s[i].getCentre().plus(v[i]));

        float[] co = new float[3];

        for (int j = 0; j < 3; j++) {
            co[j] = (float) ((v[i].get(j) + V / 2) / V);
        }

        s[i].texture(new TextureCol(new Color(co[0], co[1], co[2])));

        if (s[i].getCentre().getX() > D && v[i].getX() > 0) {
            v[i].setX(-v[i].getX());
        }
        if (s[i].getCentre().getX() < -D && v[i].getX() < 0) {
            v[i].setX(-v[i].getX());
        }
        if (s[i].getCentre().getY() > D && v[i].getY() > 0) {
            v[i].setY(-v[i].getY());
        }
        if (s[i].getCentre().getY() < -D && v[i].getY() < 0) {
            v[i].setY(-v[i].getY());
        }
        if (s[i].getCentre().getZ() > D && v[i].getZ() > 0) {
            v[i].setZ(-v[i].getZ());
        }
        if (s[i].getCentre().getZ() < -D && v[i].getZ() < 0) {
            v[i].setZ(-v[i].getZ());
        }
    }

    @Override
    public void testScene() throws Exception {
        for (int i = 0; i < N; i++)
            bounce(i);
    }

}
