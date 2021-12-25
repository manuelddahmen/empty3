/*__
 Global license :

 Microsoft Public Licence

 author Manuel Dahmen <manuel.dahmen@gmx.com>
 ***/


package tests.tests2.cadeau;

import one.empty3.library.*;
import one.empty3.library.core.testing.TestObjetSub;
import one.empty3.library.core.tribase.TRISphere;

import java.awt.*;

/*__
 * @author Manuel Dahmen <manuel.dahmen@gmx.com>
 */
public class SphereCube12 extends TestObjetSub {
    private final double t0 = -1;
    private final double t1 = 1;
    double d = 90;
    private TRISphere s;

    private double F = 3;
    private Camera cam;

    public static void main(String[] args) {


        SphereCube12 sc = new SphereCube12();

        sc.setMaxFrames(500);

        sc.loop(true);

        new Thread(sc).start();


    }

    @Override
    public void ginit() {

        Cube c;

        //c.texture(new TextureCol(Color.RED));
        c = new Cube(d / 10, Point3D.O0);


        c.texture(new TextureCol(Color.BLUE));

        s = new TRISphere(Point3D.X.mult(t0), d / 10);

        s.texture(new TextureCol(Color.YELLOW));

        scene().add(c);
        scene().add(s);

        cam = new Camera(s.getCentre().mult(F), Point3D.O0);

        scene().cameraActive(cam);

        scene().lumieres().add(new LumierePonctuelle(Point3D.O0, Color.GREEN));
    }

    @Override
    public void testScene() throws Exception {

        double pc = 1.0 * frame() / getMaxFrames();

        double TT;
        TT = t0 + (t1 - t0) * pc;

        s.setCentre(Point3D.X.mult(TT * d));

        cam.setEye(s.getCentre().plus(Point3D.Z.mult(d / 3)).plus(Point3D.X.mult(-t0 - d / 5)));
    }

    @Override
    public void finit() {
    }

}
