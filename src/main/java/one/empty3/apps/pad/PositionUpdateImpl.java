/*
 *  This file is part of Empty3.
 *
 *     Empty3 is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     Empty3 is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Empty3.  If not, see <https://www.gnu.org/licenses/>. 2
 */

/*
 * This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>
 */

package one.empty3.apps.pad;

import one.empty3.library.*;
import one.empty3.library.core.nurbs.ParametricLine;

import java.awt.*;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.ResourceBundle;

public class PositionUpdateImpl implements PositionUpdate, Runnable {
    protected Path path;
    protected static int STATE_GAME_IN_PROGRESS;
    private final ResourceBundle bundle;
    private final Ciel bleu;
    protected Point3D positionOrigine;
    protected Point3D position = positionOrigine;
    protected double positionIncrement;
    protected double positionIncrement2;
    protected double directionNorme;
    protected Point3D directionOrigine;
    protected Point3D direction = directionOrigine;
    protected double angle;
    protected double angleY;
    protected CompletePositionMobile positionMobile;
    private double unitPerMillis;
    private double rotationPerNano;
    private Point2D position2D;
    private Point2D direction2D;
    private double hauteur;
    private int score = 1000;
    private boolean gagne = false;
    private double collision_distance;
    private Circuit circuit;
    private Terrain terrain;
    private Bonus bonus;
    private double energy;
    private Point2D direction2DY = new Point2D(1, 0);
    private Plotter3D plotter3D;
    private double positionEpsilon = 0.000001;
    private Player player;
    private double calibrage = 1;
    private long accera = 0;

    public PositionUpdateImpl(Terrain t, Player player) {
        bundle = ResourceBundle.getBundle("one.empty3.apps.pad.Bundle"); // NOI18N
        unitPerMillis = Double.parseDouble(bundle.getString("unitPerMillis"));
        rotationPerNano = Double.parseDouble(bundle.getString("rotationPerNanos"));
        hauteur = Double.parseDouble(bundle.getString("hauteur"));
        positionOrigine = new Point3D(0.5, 0.5, hauteur);
        position = positionOrigine;
        positionIncrement = Double.parseDouble(bundle.getString("positionIncrement"));
        positionIncrement2 = Double.parseDouble(bundle.getString("positionIncrement2"));
        directionNorme = Double.parseDouble(bundle.getString("directionNorme"));
        directionOrigine = new Point3D(Double.parseDouble(bundle.getString("directionOrigine.x")),
                Double.parseDouble(bundle.getString("directionOrigine.y")),
                Double.parseDouble(bundle.getString("directionOrigine.z")));
        direction = new Point3D(directionOrigine);
        angle = Double.parseDouble(bundle.getString("angle"));

        if(player==null) {
            player = new Player("Manu", Color.GRAY, 0);
            score = (int) player.score();
        }
        STATE_GAME_IN_PROGRESS = Integer.parseInt(bundle.getString("STATE_GAME_IN_PROGRESS"));
        gagne = Boolean.parseBoolean(bundle.getString("gagne"));
        energy = Double.parseDouble(bundle.getString("energy"));
        this.terrain = t;
        bonus = new Bonus();
        collision_distance = Double.parseDouble(bundle.getString("collision_distance"));
        bleu = new Ciel();
        circuit = new Circuit(bonus);
        path = new Path();
        positionMobile = new CompletePositionMobile(this);
        positionMobile.getPositionSol().setX(Double.parseDouble(bundle.getString("position2d.x")));
        positionMobile.getPositionSol().setY(Double.parseDouble(bundle.getString("position2d.y")));
    }

    @Override
    public double getAngle() {
        return angle;
    }

    @Override
    public Point2D directionY() {
        return direction2DY;
    }

    public Circuit getCircuit() {
        return circuit;
    }

    @Override
    public double energy() {
        return energy;
    }

    @Override
    public void update() {
    }

    protected boolean isPositionOk(Point3D p, boolean repositionne) {
        //System.out.println("candidate new position : " + p);
        if (p.getX() >= -positionEpsilon && p.getX() <= 1 + positionEpsilon
                && p.getY() >= -positionEpsilon && p.getY() <= 1 + positionEpsilon)
            return true;
        else {
            if (repositionne) {
                p.setX(0.5);
                p.setY(0.5);
                p.setZ(hauteur);
                // System.out.println("CORRECT position : " + p);
            }

            return false;
        }
    }

    public Point3D getVecDir2D() {
        Point3D dir2D = new Point3D(Math.cos(positionMobile.getAngleVisee().getZ() * Math.PI * 2),
                Math.sin(positionMobile.getAngleVisee().getZ() * Math.PI * 2),
                0.0).mult(directionNorme * 1);
        return dir2D;
    }

    @Override
    public void acc(long timeNano) {
        Point3D direction2D = getVecDir2D().mult(-positionIncrement2);
        accera += timeNano;
        Point3D p2 = positionMobile.getPositionSol().plus(direction2D.mult((double) timeNano));
        //System.out.println("acc:" + p2.toString());
        if (isPositionOk(p2, true)) {
            positionMobile.setPositionSol(p2);
        }
    }

    @Override
    public void dec(long timeNano) {
        Point3D direction2D = getVecDir2D().mult(positionIncrement2);
        accera -= timeNano;
        Point3D p2 = positionMobile.getPositionSol().plus(direction2D.mult((double) timeNano));
        if (isPositionOk(p2, true)) {
            positionMobile.setPositionSol(p2);
        }
    }

    @Override
    public boolean plot(Point3D position, Point3D deplacement, IMovable plottee) {
        return getPlotter3D().plot(position, deplacement, plottee);
    }

    @Override
    public void moveUp(long timeKeyPress) {
        double z = positionMobile.getPositionSol().getZ() + timeKeyPress * unitPerMillis;
        if (isPositionOk(new Point3D(positionMobile.getPositionSol().getX(), positionMobile.getPositionSol().getY(), z), false))

        {
            positionMobile.getPositionSol().setZ(z);
        }
    }

    @Override
    public void moveDown(long timeKeyPress) {
        double z = positionMobile.getPositionSol().getZ() - timeKeyPress * unitPerMillis;
        if (isPositionOk(new Point3D(positionMobile.getPositionSol().getX(), positionMobile.getPositionSol().getY(), z), false))

        {
            positionMobile.getPositionSol().setZ(z);
        }
    }

    public int state() {
        return STATE_GAME_IN_PROGRESS;
    }

    @Override
    public void rotationGauche(long timeNano) {
        angle = positionMobile.getAngleVisee().getZ() + rotationPerNano * timeNano;
        positionMobile.getAngleVisee().setZ(angle);
    }

    @Override
    public void rotationDroite(long timeNano) {
        angle = positionMobile.getAngleVisee().getZ() - rotationPerNano * timeNano;
        positionMobile.getAngleVisee().setZ(angle);
    }

    public synchronized void testCollision(PositionMobile positionMobile) {
        if (bonus == null) {
            return;
        }

        Point3D pos = terrain.p3(positionMobile.getPositionSol());

        try {
            for (Iterator<Representable> it = bonus.getListRepresentable().iterator();
                 it.hasNext(); ) {
                Representable bon;
                if ((bon = it.next()) != null && bon instanceof TRISphere2
                        && Point3D.distance(((TRISphere2) bon).getCentre(), pos) < ((TRISphere2) bon).getCircle().getRadius()) {
//                    bonus.remove(bon);

                    double points = 0.0;

                    points = ((TRISphere2) bon).getGameObject().getValue();

                    score += points;

                    Sounds.playSoundBonusHit();

                    Mouvement mouvement = new Mouvement(bon, 10000, new ParametricLine(new LineSegment(((TRISphere2) bon).getCentre(), ((TRISphere2) bon).getCentre().plus(P.n(0, 0, 10))))) {


                        @Override
                        protected synchronized void action(Representable representable, double moveTimeRatio) {

                        }

                        @Override
                        protected synchronized void setPosition(Point3D position) {
                            if (representable instanceof TRISphere2) {
                                ((TRISphere2) representable).setCentre(position);
                            }

                        }

                        @Override
                        protected synchronized void startMoveAction() {
                            path.add(((TRISphere2) bon).getCoords());

                        }

                        @Override
                        protected synchronized void endMoveAction() {
                            bonus.removeBonus(bon);
                        }
                    };
                    mouvement.start();

                    System.out.println(score);


                    //circuit = new Circuit(bonus);

                }
            }
        } catch (ConcurrentModificationException | java.util.NoSuchElementException ex) {
        }
    }


    @Override
    public void ennemi(Bonus e) {
        this.bonus = e;
    }

    @Override
    public int score() {
        return score;
    }

    private void win() {
        gagne = true;
    }

    @Override
    public boolean estGagnant() {
        return bonus.getListRepresentable().isEmpty();
    }

    @Override
    public Point3D calcCposition() {
        return positionMobile.calcPosition();
    }

    @Override
    public Point3D calcDirection() {
        return positionMobile.calcDirection();
    }

    public double getHauteur() {
        if (getPlotter3D().isActive()) {
            return positionMobile.getPositionMobile().getZ();
        } else {
            return positionMobile.getPositionSol().getZ();
        }
    }


    @Override
    public int STATE_GAME_IN_PROGRESS() {
        return STATE_GAME_IN_PROGRESS;
    }

    @Override
    public Point3D calculerPositionAuSol(Point2D position2D) {
        return getTerrain().calcCposition(position2D.getX(), position2D.getY());
    }

    @Override
    public Point3D calculerPositionModule(Point2D position2D) {
        return getTerrain().p3(position);
    }

    public Terrain getTerrain() {
        return terrain;
    }

    @Override
    public void setTerrain(Terrain t) {
        this.terrain = t;
    }

    @Override
    public void run() {
        while (true) {
            testCollision(positionMobile);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Plotter3D getPlotter3D() {
        return plotter3D;
    }

    public void setPlotter3D(Plotter3D plotter3D) {
        this.plotter3D = plotter3D;
    }

    @Override
    public PositionMobile getPositionMobile() {
        return positionMobile;
    }

    public synchronized Path getPath() {
        return path;
    }

    public synchronized void setPath(Path path) {
        this.path = path;
    }

    public Player getPlayer() {
        return player;
    }
}
