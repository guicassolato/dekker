public class Produtor implements Runnable {
  Dekker dekker;

  public Produtor(Dekker dekker) {
    this.dekker = dekker;
  }
  
  public void run() {
    while (!dekker.done) {
      
      // Faz qualquer coisa antes da região crítica
      int x = 2; for (int i=1; i<=128; i++) { x *= 2; }
      
      System.out.println("O produtor quer entrar na região crítica");
      dekker.produtorWantsToEnter = true;
      while (dekker.consumidorWantsToEnter) {
        if (dekker.favoredThread == 2) {
          dekker.produtorWantsToEnter = false;
          while (dekker.favoredThread == 2);
          dekker.produtorWantsToEnter = true;
        }
      }

      // Início da região crítica
      System.out.println("O produtor conseguiu entrar na região crítica");
      // Faz qualquer coisa dentro da região crítica
      x = 2; for (int i=1; i<=256; i++) { x *= 2; }
      // Fim da região crítica      

      dekker.favoredThread = 2;
      dekker.produtorWantsToEnter = false;
      System.out.println("O produtor saiu da região crítica");
      
      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        System.err.println("ERRO: " + e.getMessage());
      }
    }
    
    System.out.println("O produtor acabou");    
  }
}