/**
 *
 */
package be.manudahmen.empty3.library.tests.tests;

import be.manudahmen.empty3.Camera;
import be.manudahmen.empty3.Point3D;
import be.manudahmen.empty3.Scene;
import be.manudahmen.empty3.TextureCol;
import be.manudahmen.empty3.core.export.STLExport;
import be.manudahmen.empty3.core.tribase.TRICylindre;
import be.manudahmen.empty3.core.tribase.TRIEllipsoide;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Manuel DAHMEN
 *
 */
@Deprecated
public class TestPersoSTL {
    public static String path = "f:\\bin\\";
    public static Scene scene = new Scene();

    public static Scene scene() {
        return scene;
    }

    /**
     * @param args
     * @throws IOException
     * @throws FileNotFoundException
     */
    public static void main(String[] args) {
        TRIEllipsoide ell = new TRIEllipsoide(new Point3D(0, 175, 0), 10, 30,
                10);
        ell.texture(new TextureCol(Color.PINK));
        ell.setMaxX(20);
        ell.setMaxY(20);
        scene().add(ell);

        TRICylindre cyl = new TRICylindre(Point3D.Y.mult(100), 15, 10);
        cyl.texture(new TextureCol(Color.PINK));
        cyl.setMaxX(20);
        cyl.setMaxY(20);
        scene().add(cyl);

        scene().cameraActive(new Camera(new Point3D(0, 170, -400), Point3D.O0));

        File f;
        try {
            f = File.createTempFile("TMP", "perso.STL", new File("c:\\objets\\stl\\"));
            STLExport.save(f, scene(), true);
        } catch (IOException e) {
            //
            e.printStackTrace();
        }

    }
}
