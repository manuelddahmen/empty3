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

package one.empty3.library.core.raytracer.tree.solvers;

import one.empty3.library.core.raytracer.tree.AlgebricTree;
import one.empty3.library.core.raytracer.tree.Constraint;

import java.util.ArrayList;

/**
 * Created by manuel on 29-12-16.
 */
public abstract class Solver {
    protected ArrayList<AlgebricTree> equations = new ArrayList<>();
    protected ArrayList<Constraint> solution;


    public Solver(ArrayList<AlgebricTree> equations, ArrayList<Constraint> solution) {
        this.equations = equations;
        this.solution = solution;
    }

    public abstract ArrayList<Constraint> solve();
}
