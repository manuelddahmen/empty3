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

package be.manudahmen.empty3.core.tribase;

import be.manudahmen.empty3.Barycentre;
import be.manudahmen.empty3.BezierCubique2D;
import be.manudahmen.empty3.Point3D;
import be.manudahmen.empty3.core.nurbs.ParametricSurface;

@SuppressWarnings("serial")
public class TRIBezier2D extends ParametricSurface {

    private BezierCubique2D bez;
    private Barycentre position;

    public TRIBezier2D(BezierCubique2D bez) {
        this.bez = bez;
        setCirculaireX(false);
        setCirculaireY(false);
    }

    @Override
    public Point3D calculerPoint3D(double u, double v) {
        return bez.calculerPoint3D(u, v);
    }

    @Override
    public Point3D coordPoint3D(int a, int b) {
        return bez.calculerPoint3D(1.0 * a / getMaxX(), 1.0 * b / getMaxY());
    }

}
