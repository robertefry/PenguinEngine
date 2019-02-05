
package robertefry.penguin.engine.targets;

import java.util.function.Supplier;
import robertefry.penguin.engine.Engine;
import robertefry.penguin.engine.target.Targetable;

/**
 * @author Robert E Fry
 * @date 4 Feb 2019
 */
public class SimpleTimer implements Targetable {

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
		Targetable.super.init( engine );
		timePrecede = timeLast = timeSupplier.get();
	}

	@Override
	public synchronized void tick( Engine engine ) {
		Targetable.super.tick( engine );
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