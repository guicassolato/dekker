public class Consumidor implements Runnable {
  Dekker dekker;
  
  public Consumidor(Dekker dekker) {
    this.dekker = dekker;
  }
  
  public void run() {
    while (!dekker.done) {
      
      // Faz qualquer coisa antes da região crítica
      int x = 3; for (int i=1; i<=64; i++) { x *= 3; }
      
      System.out.println("O consumidor quer entrar na região crítica");
      dekker.consumidorWantsToEnter = true;
      while (dekker.produtorWantsToEnter) {
        if (dekker.favoredThread == 1) {
          dekker.consumidorWantsToEnter = false;
          while (dekker.favoredThread == 1);
          dekker.consumidorWantsToEnter = true;
        }
      }

      // Início da região crítica
      System.out.println("O consumidor conseguiu entrar na região crítica");
      // Faz qualquer coisa dentro da região crítica
      float y = 100000; while (y > 0) { y /= 3; }
      // Fim da região crítica      

      dekker.favoredThread = 1;
      dekker.consumidorWantsToEnter = false;
      System.out.println("O consumidor saiu da região crítica");
      
      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        System.err.println("ERRO: " + e.getMessage());
      }
    }
        
    System.out.println("O produtor acabou");    
  }
}