package basic_schdulers;

public class DiningPhilosophers {

	public void  execute( ) {
		int num = 5;
                Chopstick left = null, right = null;
		Chopstick[] chopsticks = new Chopstick[num];
		Philosopher[] philosophers = new Philosopher[num];
		for (int i = 0; i < num; i++) {
                    System.out.println("true");
			chopsticks[i] = new Chopstick(true);
		}
		for (int i = 0; i < num; i++) {
			if (i > 0) {
			left = chopsticks[i-1];
			right = chopsticks[i];
			}
			if (i == 0) {
                                 left = chopsticks[num-1];
				 right = chopsticks[i];
			}


			philosophers[i] = new Philosopher(i, 2, left, right);
                        Thread thread = new Thread(philosophers[i]);
                        thread.start();

		}

		System.out.print("\n");
	}
}


