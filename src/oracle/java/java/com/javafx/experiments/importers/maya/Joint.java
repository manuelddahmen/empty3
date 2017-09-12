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

package com.javafx.experiments.importers.maya;

import javafx.scene.Group;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

/**
 * Joint -  A Joint is equivalent to a Maya Joint Node
 * <p/>
 * If you are post-multiplying matrices, To transform a point p from object-space to world-space you would need to
 * post-multiply by the worldMatrix. (p' = p * wm) matrix = [S][SO][R][JO][IS][T] where R = [RX][RY][RZ]  (Note: order
 * is determined by rotateOrder)
 * <p/>
 * If you are pre-multiplying matrices, to transform a point p from object-space to world-space you would need to
 * pre-multiply by the worldMatrix. (p' = wm * p) matrix = [T][IS][JO][R][SO][S] where R = [RZ][RY][RX]  (Note: order is
 * determined by rotateOrder) Of these sub-matrices we can set [SO] to identity, so matrix = [T][IS][JO][R][S]
 */
public class Joint extends Group {
    public final Translate t = new Translate();

    public final Rotate jox = new Rotate();

    { jox.setAxis(Rotate.X_AXIS); }

    public final Rotate joy = new Rotate();

    { joy.setAxis(Rotate.Y_AXIS); }

    public final Rotate joz = new Rotate();

    { joz.setAxis(Rotate.Z_AXIS); }

    public final Rotate rx = new Rotate();

    { rx.setAxis(Rotate.X_AXIS); }

    public final Rotate ry = new Rotate();

    { ry.setAxis(Rotate.Y_AXIS); }

    public final Rotate rz = new Rotate();

    { rz.setAxis(Rotate.Z_AXIS); }

    public final Scale s = new Scale();
    public final Scale is = new Scale();
    // should bind "is" to be in the inverse of the parent's "s"

    public Joint() {
        super();
        getTransforms().addAll(t, is, joz, joy, jox, rz, ry, rx, s);
    }
}


