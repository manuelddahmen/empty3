public class TestRun {
    public static void runTest(TestObjet to) {
        new Thread(to).start();
    } 

     public static void main(String [] args) {
          String cl = args[0];  
          Object t=  cl.newInstance () ;
          if(t instanceof TestObjet) 
              runTest((TestObjet ) t) ;

     } 
} 
