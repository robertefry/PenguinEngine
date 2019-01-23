
package extra;

import java.util.concurrent.Semaphore;

public class Permission implements Permissible {
	
	private final int maxPermits;
	private volatile Semaphore permits = new Semaphore(0);
	
	public Permission() {
		this(1);
	}
	
	public Permission(int maxPermits) {
		this.maxPermits = maxPermits;
	}
	
	@Override
	public void awaitPermission() throws InterruptedException {
		permits.acquire();
	}
	
	@Override
	public void grantPermission() {
		if (permits.availablePermits() < maxPermits) permits.release();
	}
	
}
