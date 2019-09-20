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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package one.empty3.library;

import java.util.*;

/**
 * @author Atelier
 */
public class RepresentableConteneur extends Representable implements IMovable, IScalable {

    private StructureMatrix<Representable> re = new StructureMatrix(1, Representable.class);

    public RepresentableConteneur() {
    }


    public synchronized void add(Representable r) {
        re.add(1, r);
    }

    public synchronized void clear() {
        re.getData1d().clear();
    }


    public Iterator<Representable> iterator() {
        return re.getData1d().iterator();
    }

    public synchronized void remove(Representable r2) {
        re.getData1d().remove(r2);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("conteneur (\n\n");

        Iterator<Representable> rs = iterator();

        while (rs.hasNext()) {
            Representable next = rs.next();

            sb.append(next.toString());
        }

        sb.append("\n\n)\n\n");

        return sb.toString();
    }

    @Override
    public void moveAdd(Point3D add) {
        Iterator<Representable> rs = iterator();

        while (rs.hasNext()) {
            Representable next = rs.next();

            if (next instanceof IMovable) {
                ((IMovable) next).moveAdd(add);
            }
        }

    }

    @Override
    public void moveTo(Point3D to) {

        Iterator<Representable> rs = iterator();

        while (rs.hasNext()) {
            Representable next = rs.next();
            if (next instanceof IMovable) {
                ((IMovable) next).moveTo(to);
            }

        }
    }

    @Override
    public void scale(Point3D center, double scale) {

        Iterator<Representable> rs = iterator();

        while (rs.hasNext()) {
            Representable next = rs.next();

            if (next instanceof IScalable) {
                ((IScalable) next).scale(center, scale);
            }
        }
    }

    @Override
    public void scale(double scale) {

        Iterator<Representable> rs = iterator();

        while (rs.hasNext()) {
            Representable next = rs.next();

            if (next instanceof IScalable) {
                ((IScalable) next).scale(scale);
            }
        }
    }

    @Override
    public void declareProperties() {
        super.declareProperties();
        getDeclaredDataStructure().put("listRepresentable", re);
    }

    public ArrayList<Representable> getListRepresentable() {
        return (ArrayList<Representable>) re.getData1d();
    }

}
