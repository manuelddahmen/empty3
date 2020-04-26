import one.empty3.library.core.testing.*;
public class TestRun {
    public static void runTest(TestObjet to) {
        new Thread(to).start();
    } 

     public static void main(String [] args) {
try {
          Class cl = Class.forName(args[0]);  
         
              Object t=  cl.newInstance () ;
              if(t instanceof TestObjet) 
                   runTest((TestObjet ) t) ;
         } catch(Exception ex) {
              ex.printStackTrace();
         }
     } 
} 