import java.io.*;
import java.util.Date;

public class Dekker {
  int favoredThread = 1;
  boolean produtorWantsToEnter = false;
  boolean consumidorWantsToEnter = false;  
  boolean done = false;

  public static void main(String args[]) throws Exception {
    // Sistema
    Dekker d = new Dekker();
    
    Produtor p = new Produtor(d);
    Consumidor c = new Consumidor(d);
    
    new Thread(p).start();
    new Thread(c).start();
    
    Date d1 = new Date();
    Date d2;
    long dif = 0;
    
    while (dif < (10*1000)) {
      d2 = new Date();
      dif = d2.getTime() - d1.getTime();
      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        System.err.println("ERRO: " + e.getMessage());
      }
    }
    
    d.done = true;
    System.out.println("O programa acabou");    
  }
}