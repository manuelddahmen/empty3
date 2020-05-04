package test3;
import one.empty3.library.core.testing.*;
import one.empty3.library.*;
import one.empty3.library.core.lighting.Colors;
import java.awt.*;
import one.empty3.library.core.nurbs.*;
import java.awt.image.*;
import java.util.*;
public class TestVoronoi extends TestObjetSub {
public int pointsSize = 10;
   private CourbeParametriquePolynomialeBezier[] curves = new CourbeParametriquePolynomialeBezier[pointsSize];
  // private Double [][][] distancesSum;
   //private ArrayList<Point3D> pointsList;
   private Double maxDist;
   private int nPointsDist = pointsSize;
   private double[][][] pointDist;
   private int[][][] pointNo;
   private int[][] colorsArr;
   private Colors.ColorDist [][][] cds;
    

   protected void addRand(CourbeParametriquePolynomialeBezier c) {
       c.getCoefficients().add(new Point3D(Math.random()*getResx(),
                                  Math.random()*getResy(),
                                  0.0));

}
   
   private Color [] colors = new Color[pointsSize];
   public void ginit() {
        pointDist = new double[getResx()][getResy()][pointsSize];
        maxDist = 0.0;
      //  distancesSum = new Double [getResx()][getResy()][pointsSize];
     // pointNo = new int[getResx()][getResy()][pointsSize];
    cds = new Colors.ColorDist[getResx()][getResy()][pointsSize];
    for(int i = 0; i<pointsSize; i++) {
          //pointsList.add(new Point3D(Math.random()*getResx(),
          //                        Math.random()*getResy(),
          //                        0.0));
          curves[i] = new CourbeParametriquePolynomialeBezier();
         curves[i].getCoefficients().data1d.clear();
          for(int p=0;p<4;p++) {
              addRand(curves[i]);
          }
         colors[i] = Colors.random();
         
         curves[i].declareProperties();
         
      }
      colorsArr = new int[getResx()][getResy()];
      //colors[0] = Color.BLACK;
   }
   public void finit() {
      double dist = 0.0;
        maxDist = 0.0;
        for(int i= 0;i<getResx(); i++)
             for(int j= 0;j<getResy(); j++) {

                  //distancesSum [i][j][] = 0.0;
                  Point3D p = new Point3D((double)i, (double)j, 0.0);

                int pointNoIjk = 0;
                   double [] distMin = new double [nPointsDist];
                 // distMin[k] = Double.MAX_VALUE; //Math.max(getResx(),getResy());
                    for(int k=0; k<pointsSize; k++) {
                cds[i][j][k] = new Colors.ColorDist();
                      pointDist[i][j][k] = dist;
                    // distMin[k] = Double.MAX_VALUE; //Math.max(getResx(),getResy());
                            /*distancesSum[i][j][k] */dist= Point3D.distance(p, curves[k].calculerPoint3D(((double)frame())/25.0/1.0));
                       if(dist/*distancesSum[i][j][k]*/>maxDist)
                           maxDist = dist;// distancesSum[i][j][k];
                 //    pointNo[i][j][k] = k;
             cds[i][j][k].dist = dist;
                 cds[i][j][k].color = colors[k];
                  
                
                      if(distMin[k]>dist) {
                         distMin[k] = dist;
                         pointNoIjk = k+1;
                     }
                  }
                
                //Arrays.sort(pointNo[i][j], Collections.reverseOrder());
                for(int c = 0 ; c<pointsSize; c++)
                    colorsArr[i][j] = Colors.mean(colors, pointDist[i][j], 1.0).getRGB();
                     
                     
                  
                  
             }
        for(int i= 0;i<getResx(); i++)
             for(int j= 0;j<getResy(); j++) {
                  //distancesSum[i][j][] /= maxDist;
             }
// Laplace derivation 2
 for(int i= 0;i<getResx(); i++)
             for(int j= 0;j<getResy(); j++) {
                 // laplacien[i][j]
             }
   }


   public void afterRenderFrame() { 
       BufferedImage image = img();
       for(int i= 0;i<getResx(); i++)
             for(int j= 0;j<getResy(); j++) {
                //int c = ((int)(double) distancesSum[i][j][])*256+ 255<<24;
                  image.setRGB(i, j, colorsArr[i][j]);
       }
   }
}
