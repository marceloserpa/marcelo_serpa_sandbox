package org.com.marceloserpa.memory.fibonaccifixed;
import java.util.ArrayList;
import java.util.List;

public class PrimeNumbersTask implements Runnable {

	private List<Integer> primes = new ArrayList<Integer>();
	private Integer lastNumberChecked1;
	private int lastNumberChecked2;
	private Integer lastNumberRetrieved = 0;
	private NumberChecker checker;
	private Boolean finished;

	private int targetVersion = 1;

	public void setTargetVersion(int version){
		this.targetVersion = version;
	}


	private void generateNextPrime1() {

		//only the add really needs to be synchronized
		synchronized (this) {
			Integer testNumber = lastNumberChecked1 + 1;
			while (!checker.isPrime1(testNumber)) {
				testNumber++;
			}
			lastNumberChecked1 = testNumber;
			primes.add(testNumber);
		}
	}

	private void generateNextPrime2() {

		//only the add really needs to be synchronized
		synchronized (this) {
			int testNumber = lastNumberChecked2 + 1;
			while (!checker.isPrime2(testNumber)) {
				testNumber++;
			}
			lastNumberChecked2 = testNumber;
			primes.add(testNumber);
		}
	}

	public void taskComplete() {
		finished = true;
	}

	public int getSize() {
		synchronized (this) {
			return (primes.size());
		}
	}

	public Integer getNextNumber() {
			if (primes.size() > lastNumberRetrieved) {
				//doesn't need to be synchronized as not amending code
				lastNumberRetrieved++;
				return primes.get(lastNumberRetrieved-1);
			}
			else return null;
		}
	

	@Override
	public void run() {
		finished = false;
		checker= new NumberChecker();
		synchronized (this) {
			primes.add(2);
		}

		lastNumberChecked1 = 2;
		lastNumberChecked2 = 2;

		while (!finished) {
			if(this.targetVersion == 1) {
				generateNextPrime1();
			} else {
				generateNextPrime2();
			}

		}
	}
}
