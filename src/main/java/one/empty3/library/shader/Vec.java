package one.empty3.library.shader;

public class Vec
{
private int dims;
private StructureMatrix <Double> vecVal
    = new StructureMatrix (1, Double.class);
    
private StructureMatrix <Vec> vec
    = new StructureMatrix (1, Vec.class);
    
    public Vec(Double... comps) {
       //  Vec v = new Vec() ;
         for(Double d : comps) 
             vecVal.add(d);
} 
    public Vec(Vec... comps) {
        for(Vec v : comps)
            vec.add(v);
            
} 

    public int getDims() {
        int dims =0;
        if(vecVal.data1d.size()>0) {
            this.dims =vecVal.data1d.size();
            return dims;
        if(vec.data1d.size()>0)
            for(int i=0;i<vec.data1d.size();i++)
                dims += vec.data1d.size():
          
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
} 
