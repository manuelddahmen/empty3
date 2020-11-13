package one.empty3.library.shader;
import one.empty3.library.*;
public class Vec
{
private int dims;
private StructureMatrix <Double> vecVal
    = new StructureMatrix (1, Double.class);
    
private StructureMatrix <Vec> vec
    = new StructureMatrix (1, Vec.class);
    public Vec(Point3D p) {
for(int i = 0; i<3; i++) {
      
      vecVal.add(p.get(i));
    }
}
    public Vrc(Double d) {
        vecVal.data1.add(d);
    }
    public Vec(Double... comps) {
        
         for(int i=0;i<comps.length;i++){
           
             vecVal.add(1, comps[i]);
         }
    } 
    public Vec(Vec... comps) {
         for(Vec v : comps.data1){
       for(Double d : v){
            vecVal.add(1, d);
            }
}
    }


    public int getDims() {
        int dims =0;
        if(vecVal.data1d.size()>0) {
            this.dims +=vecVal.data1d.size();
            }
            return dims;
          
       
        
} 
    public String toString() {
        String s = "vec" +getDims() + 
           "(";
        if(vecVal.data1d.size()>0) 
            for(int i=0;i<vecVal.data1d.size();
                 i++)
                s+=vecVal.
getElem(i)+", ";
        else
for(int i=0;i<vec.data1d.size();
                 i++)
                s+= vec.
getElem(i).toString()+", ";
s+=")";
return s;
}
    public double norme() {
double d =0.0;
        for(int i=0; i<vecVal.data1.size(); i++)
       d+=vecVal.data1d.get(i)*vecVal.data1d.get(i);
return Math.sqrt(d);
} 

   public Double value(int i, int j) {
        if(i>=0 && i<j && j<= getDims() )
             return new Vec(i,j);
        return vecVal.data1.get(i);
} 

    public Double[] value() {
        return vecVal.data1.get(i);/*
        Double [] da;
        if(vecVal.data1d.size()>0) {
            da = new Double[getDims() ];
            int i = 0;
            for(i=0;i<vecVal.data1d.get(i); i++) {
                Double a = vecVal.getElem(i);
                da[i] = a;
                i++;
            } 

        } else {
            da = new Double[getDims()];
            int i = 0; // TODO
            int j = 0;

//Double [] d = new Double[ vec. data1d. size()] ;
            for(i=0; i<vec.data1d.size(); i++) {
             Double [] d = (Double[] )( vec.getElem(i).value()) ;
                for(Double a : d) {
                    da[j] = a;
                    j++;
                } 

            } 
        } */
    return da;
    } 
} 
