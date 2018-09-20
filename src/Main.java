import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {
    public static void main(String[] argv) {
        Logger l = Logger.getLogger("philosophers");
        l.setUseParentHandlers(false);
        Handler conHdlr = new ConsoleHandler();
        conHdlr.setFormatter(new SimpleFormatter());
        l.addHandler(conHdlr);

        Spork[] sporks = new Spork[6];
        for (int i = 0 ; i < 6 ; i++) {
            sporks[i] = new Spork();
        }

        Philosopher[] philosophers = new Philosopher[6];
        for (int i = 0 ; i < 6 ; i++) {
            if (i == 0) {
		// First philosopher gets forks in reverse order, i.e. he picks up the left fork first
                philosophers[i] = new Philosopher(sporks[i + 1], sporks[i]);
            } else {
                philosophers[i] = new Philosopher(sporks[i], sporks[i >= 5 ? 0 : i + 1]);
            }

        }

        for (Philosopher philosopher : philosophers) {
            new Thread(philosopher).start();
        }
    }
}
