import sun.awt.windows.ThemeReader;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Philosopher implements Runnable{
    Spork s1;
    Spork s2;
    public Philosopher(Spork s1, Spork s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    @Override
    public void run() {
        Logger l = Logger.getLogger("philosophers");

        Random r = new Random();
        while (true) {
          l.log(Level.INFO, "Thinking");

          try {
              Thread.sleep(r.nextInt(10000));
          } catch (InterruptedException e) {
              e.printStackTrace();
          }

          l.log(Level.INFO, "Picking up spork 1");
          synchronized (this.s1) {
              try {
                  Thread.sleep(r.nextInt(10000));
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }

              l.log(Level.INFO, "Picking up spork 2");

              synchronized (this.s2) {
                  l.log(Level.INFO, "Eating");
              }
          }
      }
    }
}
