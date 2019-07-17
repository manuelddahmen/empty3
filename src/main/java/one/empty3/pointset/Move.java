package one.empty3.pointset;

import one.empty3.library.Point3D;
import one.empty3.library.TextureCol;
import one.empty3.library.core.lighting.Colors;
import one.empty3.library.core.raytracer.tree.AlgebraicFormulaSyntaxException;
import one.empty3.library.core.raytracer.tree.AlgebricTree;
import one.empty3.library.core.raytracer.tree.TreeNodeEvalException;

import java.util.HashMap;
import java.util.function.Consumer;

/**
 * Created by manue on 16-07-19.
 */
public class Move {

    private int itereFrame = 10;
    private static final double G = 10;
    protected final PCont<Gravity> container;
    private AlgebricTree x;
    private HashMap<Gravity, ComposanteForceSurface> composanteForceSurface;
    private ComposanteForceAttraction composanteForceAttraction;
    private HashMap<String, Double> map;

    public Move(PCont<Gravity> cont)

    {
        container = cont;
        composanteForceAttraction = new ComposanteForceAttraction();
        composanteForceSurface = new HashMap<>();
    }


    public void computeMoveAttraction() {
        container.iterator().forEachRemaining(new Consumer<Gravity>() {
            public void accept(Gravity t1) {
                t1.clearTemp();
            }
        });
        container.iterator().forEachRemaining(new Consumer<Gravity>() {
            public void accept(Gravity t1) {
                container.iterator().forEachRemaining(new Consumer<Gravity>() {
                    public void accept(Gravity t2) {
                        if (t1 == t2) {

                        } else {
                            t2.dv1 = composanteForceAttraction.fun(t1, t2, G, -2, 1, 1);
                        }
                    }
                });
            }
        });
        container.iterator().forEachRemaining(new Consumer<Gravity>() {
            public void accept(Gravity t1) {
                t1.changeTo(t1.plus(t1.dv1));
            }
        });
    }

    public void initMoveSurface(String formula, HashMap<String, Double> map) throws AlgebraicFormulaSyntaxException {
        x = new AlgebricTree(formula, map).construct();
    }

    public void computeMoveSurface(Gravity t1) {
        t1.clearTemp();
        try {
            if (!composanteForceSurface.containsKey(t1))
                composanteForceSurface.put(t1, new ComposanteForceSurface(x, t1.dv));

            int i=0;
            while(i<itereFrame)
                composanteForceSurface.get(t1).diff();

            t1.changeTo(
                    new Point3D(composanteForceSurface.get(t1).map2.get("x"),
                            composanteForceSurface.get(t1).map2.get("y"),
                            composanteForceSurface.get(t1).map2.get("z")
                    )
            );
            t1.texture(new TextureCol(Colors.random()));
        } catch (TreeNodeEvalException e) {
            e.printStackTrace();
        } catch (AlgebraicFormulaSyntaxException e) {
            e.printStackTrace();
        }
    }


    public void step(double dt) {

    }

    public int getItereFrame() {
        return itereFrame;
    }

    public void setItereFrame(int itereFrame) {
        this.itereFrame = itereFrame;
    }

    public static double getG() {
        return G;
    }

    public PCont<Gravity> getContainer() {
        return container;
    }

}
