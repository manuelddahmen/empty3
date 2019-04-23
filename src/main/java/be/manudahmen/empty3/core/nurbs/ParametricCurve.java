/*
 * Copyright (c) 2017. Tous les fichiers dans ce programme sont soumis à la License Publique Générale GNU créée par la Free Softxware Association, Boston.
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
package be.manudahmen.empty3.core.nurbs;

import be.manudahmen.empty3.Point3D;
import be.manudahmen.empty3.Representable;
import be.manudahmen.empty3.ZBuffer;

/**
 * @author Manuel Dahmen <ibiiztera.it@gmail.com>
 */
public abstract class ParametricCurve extends Representable {
    private static ParametricCurve.Globals globals;

    static {
        if(globals==null)

        {
            Globals globals1 = new Globals();
            ParametricCurve.setGlobals(globals1);
            globals1.setIncrU(0.0001);
        }
    }

    public double incr = 0.001;
    protected double start;
    protected double end;
    protected boolean connected = true;
    private Parameters parameters = new Parameters(true);

    public static void setGlobals(Globals globals) {
        ParametricCurve.globals = globals;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    public abstract Point3D calculerPoint3D(double t);

    public abstract Point3D calculerVitesse3D(double t);

    public double end() {
        return end;
    }

    public void end(double e) {
        end = e;
    }

    public double getIncr() {
        double incr = 0;
        if (parameters.isGlobal()) {
            incr = parameters.getIncrU();
        } else {
            incr = globals.getIncrU();
        }
        double incr0 = incr == 0 ? 0.01 : incr;
        return incr0;
    }

    public double start() {
        return start;
    }

    public void start(double s) {
        start = s;
    }

    @Override
    public boolean ISdrawStructureDrawFastIMPLEMENTED(ZBuffer z) {
        return true;
    }

    @Override
    public void drawStructureDrawFast(ZBuffer z) {
        for (int i = 0; i < 100; i++) {
            Point3D d = calculerPoint3D(0.0 + 1.0 * i / NFAST);
            d.texture(CFAST);
            if (d.ISdrawStructureDrawFastIMPLEMENTED(z)) {
                d.drawStructureDrawFast(z);

            } else
                ;
        }
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public static class Globals {
        private double incrU;

        public double getIncrU() {
            return incrU;
        }

        public void setIncrU(double incrU) {
            this.incrU = incrU;
        }

    }

    public class Parameters {

        private boolean isGlobal;
        private double incrU;

        public Parameters(double incrU) {
            this.setIncrU(incrU);
        }

        public Parameters(boolean isGlobal) {
            setGlobal(isGlobal);
        }

        public double getIncrU() {
            return incrU;
        }

        public void setIncrU(double incrU) {
            this.incrU = incrU;
        }

        public boolean isGlobal() {
            return isGlobal;
        }

        public void setGlobal(boolean global) {
            this.isGlobal = global;
        }
    }
}


