package stopCriteria;

import population.Population;

public class TimeCriteria implements IStopCriteria, Runnable {

	/**
	 * time of algorithm execution in second
	 */
	private int timeCounter;

	/**
	 * time counter in second
	 */
	private int currentTime;

	public TimeCriteria(int untilThisTime) {
		this.timeCounter = untilThisTime;
		this.currentTime = 0;
	}

	@Override
	public boolean stopAlgorithm(Population pop) {
		return this.currentTime >= this.timeCounter;
	}

	@Override
	public void run() {

		while (!stopAlgorithm(null)) {
			try {
				Thread.sleep(1000);
				this.currentTime += 1;
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}

	/*public void startCompteur() {
		Thread cpt = new Thread(this);
		cpt.start();
	}*/

}
