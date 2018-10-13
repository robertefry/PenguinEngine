
package extra;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedTransferQueue;

public class Pipeline extends LinkedTransferQueue<Runnable> {
	private static final long serialVersionUID = -7308685182033853359L;

	public void executeLater(Runnable job) {
		this.add(job);
	}
	
	public <T> T executeLater(Callable<T> call) throws InterruptedException, ExecutionException {
		FutureCall<T> job = new FutureCall<>(call);
		this.add(job);
		return job.get();
	}
	
	public void executeNext() {
		Runnable job = poll();
		if (job != null) job.run();
	}
	
}
