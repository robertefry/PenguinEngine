
package utility;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.TransferQueue;

/**
 * @author Robert E Fry
 * @date 22 Jan 2019
 * @time 23:41:58
 */

public class Pipeline {
	
	private final TransferQueue<FutureTask<?>> queue = new LinkedTransferQueue<>();

	public Future<Void> executeLater(Runnable task) {
		FutureTask<Void> futuretask = new FutureTask<>(task, null);
		queue.add(futuretask);
		return futuretask;
	}
	
	public <T> Future<T> executeLater(Callable<T> task) {
		FutureTask<T> futuretask = new FutureTask<>(task);
		queue.add(futuretask);
		return futuretask;
	}
	
	public void executeNow(Runnable task) throws InterruptedException, ExecutionException {
		executeLater(task).get();
	}
	
	public <T> T executeNow(Callable<T> call) throws InterruptedException, ExecutionException {
		return executeLater(call).get();
	}

	public void executeNow(Runnable task, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		executeLater(task).get(timeout, unit);
	}
	
	public <T> T executeNow(Callable<T> call, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		return executeLater(call).get(timeout, unit);
	}
	
	public void executeNext() throws Exception {
		FutureTask<?> task = queue.poll();
		if (task != null) task.run();
	}
	
	public TransferQueue<FutureTask<?>> getQueue() {
		return queue;
	}
	
}
