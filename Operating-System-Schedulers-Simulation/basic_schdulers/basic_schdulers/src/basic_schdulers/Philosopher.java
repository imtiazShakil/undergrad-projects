package basic_schdulers;
import java.util.Random;

public class Philosopher implements Runnable {

	private int index;
	private int cycles;
	private Random random = new Random();
	private Chopstick left;
	private Chopstick right;
        public Thread t;

	public Philosopher(int index, int cycles, Chopstick left, Chopstick right) {
           // System.out.println("creating");
		this.index = index;
		this.cycles = cycles;
		this.left = left;
		this.right = right;
               // t = new Thread(this);
	}

	public void print(String message) {
		System.out.print("Philosopher " + index + message+"\n");
	}

	public void run() {
		try {
			for (int i = 0; i < cycles; i++) {
				print("is thinking");
				Thread.sleep(random.nextInt(10000));
                             //   t.sleep(random.nextInt(10000));
				print("is hungry");
				if (index % 2 == 1) {
					print("is grabbing left chopstick");
					left.takeChopstick();
                                        print("has grabbed left chopstick");
					print("is grabbing right chopstick");
					right.takeChopstick();
                                        print("has grabbed right chopstick");
				}
                                else {

                                        print("is grabbing right chopstick");
                                        right.takeChopstick();
                                        print("has grabbed right chopstick");
                                        print("is grabbing left chopstick");
                                        left.takeChopstick();
                                        print("has grabbed left chopstick");
				}

				print("is eating");
				Thread.sleep(random.nextInt(10000));
				print("is putting down chopsticks");
				left.returnChopstick();
				right.returnChopstick();
				print("is finished eating");
			}
		} catch (InterruptedException e) {
			print("is interrupted");
		}
	}
}



