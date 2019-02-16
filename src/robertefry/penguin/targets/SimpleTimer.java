
package robertefry.penguin.targets;

import java.util.function.Supplier;
import robertefry.penguin.engine.target.TargetAdapter;

/**
 * @author Robert E Fry
 * @date 4 Feb 2019
 */
public class SimpleTimer extends TargetAdapter {

	protected volatile long timePrecede, timeLast;
	protected final Supplier< Long > timeSupplier;

	public SimpleTimer() {
		timeSupplier = () -> System.currentTimeMillis();
	}

	public SimpleTimer( Supplier< Long > timeSupplier ) {
		this.timeSupplier = timeSupplier;
	}

	@Override
	public synchronized void init() {
		timePrecede = timeLast = timeSupplier.get();
	}

	@Override
	public synchronized void update() {
		timePrecede = timeLast;
		timeLast = timeSupplier.get();
	}

	public synchronized long getTimePrecede() {
		return timePrecede;
	}

	public synchronized long getTimeLast() {
		return timeLast;
	}

	public synchronized long getDelta() {
		return timeLast - timePrecede;
	}

}
