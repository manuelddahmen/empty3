package one.empty3.library;
import java.awt.Color;
import java.util.*;

public class Vec {
    
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


public class Point3D extends Representable {
     
     public int length() ;
         
     
     

     public Point3D() ;

     public Point3D(int n) ;

     public Point3D(Point3D... v) ;
     public static void start() ;
         
     public static void end() ;
         
  
    /*__
     * *
     * axe X vector
     */
    public static final Point3D X = new Point3D(1d, 0d, 0d);
    /*__
     * *
     * axe Y vector
     */
    public static final Point3D Y = new Point3D(0d, 1d, 0d);
    /*__
     * *
     * axe Z vector
     */
    public static final Point3D Z = new Point3D(0d, 0d, 1d);
    /*__
     * *
     * O0 origin
     */
    public static final Point3D O0 = new Point3D(0d, 0d, 0d);
    /*__
     * *
     * Point "Infinite" limite pour Z-Buffer
     */
    public static final Point3D INFINI = new Point3D(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
    /*__
     * *
     * Pour le tracé de surface normale au point
     */
    protected Point3D normale;
    /*__
     * *
     * id
     */

    
    /*__
     * *
     *
     * @param x0 coordArr-coordonnée
     */
    public Point3D(double... x0) ;
/*__
     * *
     *
     * @param x0 coordArr-coordonnée
     * @param y0 y-coordonnée
     * @param z0 z-coordonnée
     */
    public Point3D(Double x0, Double y0, Double z0, ITexture t) ;
      


    public Point3D(Double[] x0, ITexture t) ;
  

    public Point3D(StructureMatrix<Double> coordArr) ;
    

    public static Point3D n(Double a, Double b, Double c) ;
    public static Point3D n(int a, int b, int c) ;
       

    /*__
     * *
     * Distance cartésienne entre 2 points
     *
     * @param p1 Point1
     * @param p2 Point2
     * @return
     */
    public static Double distance(Point3D p1, Point3D p2);
        


    public static Point3D random(Double d);
        
    public static Point3D random(Double d, int n) ;
        
    public static Point3D random2(Double d);

      
    @Override
    public Object clone() {
        return new Point3D(this);
    }

    public Double get(int i) {;
       
    public Point3D scale() 

    public List<Double> getDoubleArray() ;


    public Point3D getNormale() {
        return normale;
    }

    public void setNormale(Point3D normale) {
        this.normale = normale;
    }

    public Double getY() {
        return get(1);
    }

    public void setY(Double x0) {
        set( 1, x0);

    }
    public Double getZ() {
        return get(2);
    }

    public void setZ(Double x0) {
        set(2, x0);

    }
    public Double getX() {
        return get(0);
    }

    public void setX(Double x0) {
        set(0, x0);

    }


    public Point3D plus(Point3D p){
         if(p==null)
              return this;
        Point3D p1 = new Point3D(this);
        for(int i=0;i<n; i++)
            p1.set(i, get(i)+p.get(i));
        
        return p1;
    }
    
    public Point3D moins(Point3D p) {
         if(p==null)
              return this;
        Point3D p1 = new Point3D(this);
        for(int i=0;i<n; i++)
            p1.set(i, get(i)-p.get(i));
        
        return p1;
    }

    /*__
     * *
     * Multiplication
     *
     * @param xFactor facteur
     * @return
     */

    public Point3D mult(Point3D p) {
         if(p==null)
              return this;
        Point3D p1 = new Point3D(this);
        for(int i=0;i<n; i++)
            p1.set(i, get(i)*p.get(i));
        
        return p1;
    }
     public Point3D mult(double d) {
        Point3D p1 = new Point3D(this);
        for(int i=0;i<n; i++)
            p1.set(i, get(i)*d);
        
        return p1;
}
    /*
     public vec mult(vec point3D) {
        return Matrix33.YZX.mult(Matrix33.ZXY.mult(Matrix33.XYZ.mult(point3D)));
       }*/
    /*__
     * *
     * norme d'un vecteur (distance du point à l'origine)
     *
     * @return
     */
    public Double norme() {
        double an = 0.0;
        for(int i=0; i<n; i++)
            an+= get(i)*get(i);
        return Math.sqrt(an);
    }

    /*__
     * *
     * "direction" (norme1)
     *
     * @return Vecteur normalisé à 1
     */
    public Point3D norme1() {
        return mult(1 / norme());
    }

    /*__
     * *
     * Ajoute @param i à chaque coordonnée
     *
     * @param i
     * @return
     */
    public Point3D plus(Double d) {
         
        Point3D p = new Point3D(this);
        for(int i=0; i<n; i++)
            p.set(i, get(i)+d);
        return p;
    }

   

    /*__
     * *
     * Produit scalaire
     *
     * @param p2
     * @return
     */
    public Double prodScalaire(Point3D p2) {
         if(p2==null)
              return Double.NaN;
        double s = 0.0;
        if(p2!=null) {
            for(int i=0; i<n; i++)
            s +=get(i) * p2.get(i);
        }
        else
            throw new NullPointerException("Exception prodScalre p2==null");
        return s;
    }

    /*__/*__
     * *
     * Produit scalaire
     *
     * @param p2
     * @return
     */
    public Double dot(Point3D p2) {
        return this.prodScalaire(p2);
    }

     /* *
     * produit vectoriel
     *
     * @param p1
     * @return
     */
    public Point3D prodVect(Point3D p1) {
        return new Point3D(p1.getY() * getZ() + -p1.getZ() * getY(), p1.getZ()
                * getX() - p1.getX() * getZ(), p1.getX() * getY() - p1.getY()
                * getX());
    }

    public void set(int i, Double d) {
        da.setDoubles(start+ i, d);

    }

    public String toLongString() {
        String s = "p ( \n\t(";
        for(int i = 0; i<n; i++) {
             s+= get(i);
             if(i<n-1)
                  s += ", ";
        }
        s += " )\n\t("
                + texture.toString()
                + ")\n)\n";
        return s;
    }

    @Override
    public String toString() {
        return toLongString();
    }

    @Override
    public boolean ISdrawStructureDrawFastIMPLEMENTED(ZBuffer z) {
         return false;//To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawStructureDrawFast(ZBuffer z) {

      

    }

    public Point2D get2D() {
        return new Point2D(get(0), get(1));
    }
    public Point3D ord(int x, int y, int z) {
        return new Point3D(get(x), get(y), get(z));
    }
    public void normalize() {
        Double norme = norme();
        for (int i = 0; i < n; i++)
            set(i, get(i) / norme);
    }

    public Point2D to2DwoZ() {
        return get2D();
    }

    public Double NormeCarree() {
        return norme()*norme();
    }
    @Override
    public boolean equals(Object p) {
         if(this==p) 
              return true;
         
         boolean e = true;
              
             
         if((p!=null) && ( p instanceof Point3D)) {
              Point3D p1 = (Point3D) p;
              if(this.length()!=p1.length())
                   return false;
              for(int i=0; i<n; i++) 
                   e = (
                       e & get(i).equals(p1.get(i)) 
                   ) ;
         } 
         else
              e = false;
         System.out.println("equals " + e + "\np1: "
                   +this+"\np2: "+p);
         return e;
     } 

    public Point3D changeTo(Point3D dst) {
        for (int i = 0; i < 3; i++)
            this.set(i, dst.get(i));

        texture(dst.texture());
        return this;
    }

    public void declareProperties() {
        super.declareProperties();
        getDeclaredDataStructure().put("coordArr/coordonnées", null);
    }
/*
    public Double get(int i) {
        return i<coordArr.data1d.size()?coordArr.getElem(i):Double.NaN;
    }*/
    public StructureMatrix<Double> getCoordArr() {
        StructureMatrix<Double> coordArr = new StructureMatrix<>(1, Double.class);
         for(int i= 0; i<n; i++)
            coordArr.data1d.add(get(i));
         return null;
    }

    public void setCoordArr(StructureMatrix<Double> coordArr) {
        for(int i= 0; i<n; i++)
            set(i, coordArr.data1d.get(i));
    }

    
    
    /***
     * @param p double or array or matrix
     * copy<- * copy-> * add-> * <- mult .,* min max->
     * exp div set <- sub div  get sum fx? 
     * new start end 
    *//*
    public static int[] op(String po, int... p1){
        switch(po) {
                case "+":
                for(int i=0; i<n; i++)
                     set(i,get(i)+p.get(i));
                break;
                case "-":
                    for(int i=0; i<n; i++)
                        set(i, coordArr.getElem(i)-p.get(i));
                break;
                    case "*":
                        
        for(int i=0; i<n; i++)
              set( i,  get(i)*p.get(i));
                break;
                    case "/":
                
        for(int i=0; i<n; i++)
              set(i, get(i)/p.get(i));
                break;
                case ".":
                double sum = 0.0;
                for(int i=0; i<n; i++)
              
                  sum += get(i)*p.get(i);
                   set(0, sum);
                break;
                }
           
        
        return this;
    }

*/
}
