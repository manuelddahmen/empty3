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
package com.javafx.experiments.shape3d.symbolic;

/**
 * A 3D geometric point array that has the x, y, z coordinates of every point
 * as a function of other variables.
 */
public abstract class SymbolicPointArray {
    final public float[] data;
    final public int numPoints;
    // x, y, z as stated.
    static final int NUM_COMPONENTS_PER_POINT = 3;

    protected SymbolicPointArray(float[] data) {
        this.data = data;
        this.numPoints = data.length / NUM_COMPONENTS_PER_POINT;
    }

    /**
     * Updates the variables x, y, z based on the state of the other variables
     * that this symbolic point depends on.
     */
    public abstract void update();
}
