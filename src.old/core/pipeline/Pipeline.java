
package core.pipeline;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Pipeline extends LinkedTransferQueue<FutureTask<?>> {
	private static final long serialVersionUID = -8451738911476502321L;

	public Future<Void> executeLater(Runnable task) {
		FutureTask<Void> futuretask = new FutureTask<>(task, null);
		add(futuretask);
		return futuretask;
	}
	
	public void executeNow(Runnable task) throws InterruptedException, ExecutionException {
		executeLater(task).get();
	}
	
	public void executeNow(Runnable task, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		executeLater(task).get(timeout, unit);
	}
	
	public <T> Future<T> executeLater(Callable<T> task) {
		FutureTask<T> futuretask = new FutureTask<>(task);
		add(futuretask);
		return futuretask;
	}
	
	public <T> T executeNow(Callable<T> call) throws InterruptedException, ExecutionException {
		return executeLater(call).get();
	}
	
	public <T> T executeNow(Callable<T> call, long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		return executeLater(call).get(timeout, unit);
	}
	
	public void executeNext() throws Exception {
		FutureTask<?> task = poll();
		if (task != null) task.run();
	}
	
}
