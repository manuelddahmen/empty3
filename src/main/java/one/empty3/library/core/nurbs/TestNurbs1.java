/*
 * Copyright (c) 2016. Tous les fichiers dans ce programme sont soumis à la License Publique Générale GNU créée par la Free Softxware Association, Boston.
 * La plupart des licenses de parties tièrces sont compatibles avec la license principale.
 * Les parties tierces peuvent être soumises à d'autres licenses.
 * Montemedia : Creative Commons
 * ECT : Tests à valeur artistique ou technique.
 * La partie RayTacer a été honteusement copiée sur le Net. Puis traduite en Java et améliorée.
 * Java est une marque de la société Oracle.
 *
 * Pour le moment le programme est entièrement accessible sans frais supplémentaire. Get the sources, build it, use it, like it, share it.
 */

/**
 * *
 * Global license : * Microsoft Public Licence
 * <p>
 * author Manuel Dahmen <ibiiztera.it@gmail.com>
 * <p>
 * *
 */
package one.empty3.library.core.nurbs;

import one.empty3.library.Camera;
import one.empty3.library.Point3D;
import one.empty3.library.TextureCol;
import one.empty3.library.core.testing.TestObjetSub;

import java.awt.*;

/**
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public class TestNurbs1 extends TestObjetSub {

    public static void main(String[] args) {
        TestNurbs1 n = new TestNurbs1();
        n.loop(false);
        n.setGenerate(GENERATE_MODEL | GENERATE_IMAGE);
        new Thread(n).start();
    }

    @Override
    public void testScene() throws Exception {
        NurbsSurface n = new NurbsSurface();
        n.setMaillage(new Point3D[][]{
                {
                        new Point3D(0d, 1d, 2d),
                        new Point3D(2d, 3d, 0d),
                        new Point3D(4d, 4d, -2d)},
                {
                        new Point3D(3d, 2d, 5d),
                        new Point3D(8d, 4d, 4d),
                        new Point3D(5d, 4d, 4d)},
                {
                        new Point3D(1d, 2d, 1d),
                        new Point3D(4d, 7d, 4d),
                        new Point3D(5d, 7d, 5d)}
        }, new double[][]{
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        });

        n.setDegreU(5);
        n.setDegreV(5);

        n.setReseauFonction(new double[][]{
                {0, 0, 0, 0.5, 1, 1, 1},
                {0, 0, 0, 0.5, 1, 1, 1}
        });

        n.texture(new TextureCol(Color.WHITE));

        n.setStartU(0.0);
        n.setStartV(0.0);
        n.setEndU(1.0);
        n.setEndV(1.0);
        n.setIncrU(0.01);
        n.setIncrV(0.01);

        n.creerNurbs();

        scene().add(n);
        System.out.println(n);
        //Axes axes = new Axes();

        //scene().add(axes);
        scene().cameraActive(new Camera(Point3D.Z.mult(-10d), Point3D.O0));
    }

    @Override
    public void finit() {
    }

    @Override
    public void ginit() {
    }
}
