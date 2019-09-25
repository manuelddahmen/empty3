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

package one.empty3.apps.pad.trikombat;

import one.empty3.library.Point3D;
import one.empty3.library.core.tribase.ApproximationFonction1D;
import one.empty3.library.core.tribase.ApproximationFonction2D;
import one.empty3.library.core.tribase.Tubulaire;

/**
 * Created by manuel on 03-09-16.
 */
public class TriComplexStruct {
    private Tubulaire tube;
    private ApproximationFonction1D diam;
    private ApproximationFonction1D nbSpires;


    /**
     * Paramètre global, statique
     */
    private Point3D vitesseGlobale;
    /**
     * Vitesse dynamique, propriétés de la fonction à déterminer
     */
    private ApproximationFonction2D vitesseDansLePlanNormaleAuTube;


}