package stopCriteria;

import java.util.logging.Logger;

import core.GeneticAlgorithmProcessor;
import population.Population;

/**
 * @authors Ahmed Youssouf ZIYYAT, Steve DEPRES, Guillaume COURTIN, Nathan DUBERNARD
 */
public class TimeCriteria implements IStopCriteria, Runnable {

	/* Logger */
	private final static Logger LOGGER = Logger.getLogger(TimeCriteria.class.getName());
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
				LOGGER.info("interrupted" + String.valueOf(currentTime));
			}
		}
	}

	public void startCompteur() {
		Thread cpt = new Thread(this);
		cpt.start();
	}

}
