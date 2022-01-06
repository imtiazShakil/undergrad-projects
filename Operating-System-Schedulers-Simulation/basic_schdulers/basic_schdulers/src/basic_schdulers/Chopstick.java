
package basic_schdulers;


public class Chopstick {

	private boolean isAvailable;

	public Chopstick(boolean isAvailable) {
	this.isAvailable = isAvailable;
	}

	public synchronized void returnChopstick() {
		isAvailable = true;
		notifyAll();
	}

	public synchronized void takeChopstick() throws InterruptedException {
		while (!isAvailable){
			wait();
		}
		isAvailable = false;
	}
}


