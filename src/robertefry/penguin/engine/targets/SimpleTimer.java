
package robertefry.penguin.engine.targets;

import java.util.function.Supplier;
import robertefry.penguin.engine.Engine;
import robertefry.penguin.engine.target.TargetAdapter;

/**
 * @author Robert E Fry
 * @date 4 Feb 2019
 */
public class SimpleTimer implements TargetAdapter {

	protected volatile long timePrecede, timeLast;
	protected final Supplier<Long> timeSupplier;

	public SimpleTimer() {
		timeSupplier = () -> System.currentTimeMillis();
	}

	public SimpleTimer( Supplier<Long> timeSupplier ) {
		this.timeSupplier = timeSupplier;
	}

	@Override
	public synchronized void init( Engine engine ) {
		TargetAdapter.super.init( engine );
		timePrecede = timeLast = timeSupplier.get();
	}

	@Override
	public synchronized void tick( Engine engine ) {
		TargetAdapter.super.tick( engine );
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
