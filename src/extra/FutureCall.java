
package extra;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;

import logging.Logger;

public class FutureCall<T> extends CompletableFuture<T> implements Runnable {
	
	private final Callable<T> call;
	
	public FutureCall(Callable<T> call) {
		this.call = call;
	}

	@Override
	public void run() {
		try {
			super.complete(call.call());
		} catch (Exception e) {
			Logger.log(Logger.WARNING, (Object[]) e.getStackTrace());
		}
	}
	
}
