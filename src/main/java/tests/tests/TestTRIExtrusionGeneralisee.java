package tests.tests;

import be.manudahmen.empty3.Camera;
import be.manudahmen.empty3.Point3D;
import be.manudahmen.empty3.SegmentDroite;
import be.manudahmen.empty3.TextureCol;
import be.manudahmen.empty3.core.testing.TestObjetSub;
import be.manudahmen.empty3.core.tribase.CheminDroite;
import be.manudahmen.empty3.core.tribase.SurfaceCercle;
import be.manudahmen.empty3.core.tribase.TRIExtrusionGeneralisee;

import java.awt.*;

public class TestTRIExtrusionGeneralisee extends TestObjetSub {

    public static void main(String[] args) {
        TestTRIExtrusionGeneralisee te = new TestTRIExtrusionGeneralisee();

        te.publishResult(true);
        te.loop(false);

        te.run();
    }

    public void testScene() {
        TRIExtrusionGeneralisee te = new TRIExtrusionGeneralisee();
        te.chemin = new CheminDroite(new SegmentDroite(Point3D.Y.mult(-1), Point3D.Y.mult(1)));
        te.surface = new SurfaceCercle(1d);
        te.texture(new TextureCol(Color.red));
        te.setMaxX(20);
        te.setMaxY(20);
        scene().add(te);

        scene().cameraActive(new Camera(Point3D.Z.mult(4), Point3D.O0));
    }


}
