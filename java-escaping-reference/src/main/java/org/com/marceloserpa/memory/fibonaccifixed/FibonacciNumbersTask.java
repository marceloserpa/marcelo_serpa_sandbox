package org.com.marceloserpa.memory.fibonaccifixed;
import java.util.ArrayList;
import java.util.List;

public class FibonacciNumbersTask implements Runnable {

	private List<Integer> fibonaccis = new ArrayList<Integer>();
	private Boolean finished;

	public void taskComplete() {
		finished = true;
	}

	public int getSize() {
		synchronized (this) {
			return (fibonaccis.size());
		}
	}

	public Integer getNextNumber() {
		synchronized (this) {
			if (fibonaccis.size() >0) {
				return fibonaccis.remove(0);
			}
			else return null;
		}
	}


	@Override
	public void run() {
		finished = false;
		int a = 0;
		int b = 1;

		synchronized (this) {
			fibonaccis.add(a);
			fibonaccis.add(b);
		}

		while (!finished) {
			//only the add really needs to be synchronized.

			synchronized (this) {
				//we need to stop the fibonnaci numbers growing too quickly so we'll pause if there are > 100 waiting to be collected
				if (fibonaccis.size() < 100) {
					int c = a+b;
					fibonaccis.add(c);
					a = b;
					b = c;
				}
			}

		}

	}

}
