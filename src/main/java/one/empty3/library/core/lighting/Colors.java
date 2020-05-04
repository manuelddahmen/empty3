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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package one.empty3.library.core.lighting;

import java.awt.*;

/*__
 * @author Manuel Dahmen _manuel.dahmen@gmx.com_
 */
public class Colors {
    /*
    public class ColorDist implements Comparable {
        public Color color;
        public double dist;
        
        public int compareTo(Object o) {
            if (o instanceof ColorDist) {
                ColorDist cd = (ColorDist) o;
                return dist<cd.dist?-1:(dist==cd.dist?0:1);
           } else 
                return 0;//throw??
        }
    }
    */
    public static Color TRANSPARENT = new Color(1f,0f,0f,.5f );
    public static Color random() {
        return new Color(
                (float) Math.random(),
                (float) Math.random(),
                (float) Math.random()
        );
    }
    
    
    public abstract class FArrayElem {
        public abstract double op(double d);
    }
    /***
     * moyenne ponderee
      */
    public static Color mean(Color[] c, double[] d, double norm) {
      int compNo = 4;
        if(c==null || d==null || c.length!=d.length)
            throw new NullPointerException("index not equals or null");
        float [] r = new float[compNo];
        float [] f = new float[compNo];
        float sum = 0f;
          for (int j=0; j <compNo; j++) 
              r[j] = 0f;
        for(int i = 0; i<c.length; i++)
{
            float proximityTerm = ((float)d[i]);
            sum += proximityTerm;
        c[i].getRGBComponents(f);
            for (int j=0; j <compNo; j++) 
                r[j] += (float)(f[j]*proximityTerm*norm);
        }
        for(int i = 0 ; i<compNo; i++) {
            r[i] /= sum;
            if (Float.isNaN(r[i])||Float.isInfinite(r[i]))
                r[i] = 1f;
            }
            return new Color(r[0], r[1], r[2]);
    }
    
    /***
     * moyenne ponderee
      */
    public static Color proxymity(Color[] c, double[] d, double norm) {
      int compNo = 4;
        if(c==null || d==null || c.length!=d.length)
            throw new NullPointerException("index not equals or null");
        float [] r = new float[compNo];
        float [] f = new float[compNo];
        float sum = 0f;
          for (int j=0; j <compNo; j++) 
              r[j] = 0f;
        for(int i = 0; i<c.length; i++)
{
        
        }
       // float sum=0f;
      for(int i = 0; i<c.length; i++)
{
      
      // besoin de distMin pour faire partiviper les autres?
      float proxymityTerm = (float)Math.exp(-((float)d[i])/(1f+(float)d[i]));
            
            sum += proxymityTerm;
        c[i].getRGBComponents(f);
            for (int j=0; j <compNo; j++) 
                r[j] += (float)(f[j]*proxymityTerm*norm);
        }
        for(int i = 0 ; i<compNo; i++) {
            r[i] /= sum;
            if (Float.isNaN(r[i])||Float.isInfinite(r[i]))
                r[i] = 1f;
            }
            return new Color(r[0], r[1], r[2]);
    }
    
    
    
    /***
     * moyenne ponderee
      */
    public static Color proxymity(ColorDist[] cd, double norm) {
      int compNo = 4;
        if(cd==null)
            throw new NullPointerException("index not equals or null");
        float [] r = new float[compNo];
        float [] f = new float[compNo];
        float sum = 0f;
          for (int j=0; j <compNo; j++) 
              r[j] = 0f;
        for(int i = 0; i<cd.length; i++)
{
        
        }
       // float sum=0f;
      for(int i = 0; i<cd.length; i++)
{
      
      // besoin de distMin pour faire partiviper les autres?
      float proxymityTerm = (float)Math.exp(-((float)(cd[i].dist))/(1f+(float)(cd[i].dist)));
            
            sum += proxymityTerm;
        cd[i].color.getRGBComponents(f);
            for (int j=0; j <compNo; j++) 
                r[j] += (float)(f[j]*proxymityTerm*norm);
        }
        for(int i = 0 ; i<compNo; i++) {
            r[i] /= sum;
            if (Float.isNaN(r[i])||Float.isInfinite(r[i]))
                r[i] = 1f;
            }
            return new Color(r[0], r[1], r[2]);
    }
}
