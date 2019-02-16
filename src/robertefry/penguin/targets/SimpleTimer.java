
package robertefry.penguin.targets;

import java.util.function.Supplier;
import robertefry.penguin.engine.Engine;
import robertefry.penguin.engine.target.Targetable;

/**
 * @author Robert E Fry
 * @date 4 Feb 2019
 */
public class SimpleTimer implements Targetable {

	protected volatile long timePrecede, timeLast;
	protected final Supplier< Long > timeSupplier;

	public SimpleTimer() {
		timeSupplier = () -> System.currentTimeMillis();
	}

	public SimpleTimer( Supplier< Long > timeSupplier ) {
		this.timeSupplier = timeSupplier;
	}

	@Override
	public synchronized void init( Engine engine ) {
		timePrecede = timeLast = timeSupplier.get();
	}

	@Override
	public void dispose( Engine engine ) {
	}

	@Override
	public void pollInput( Engine engine ) {
	}

	@Override
	public synchronized void tick( Engine engine ) {
		timePrecede = timeLast;
		timeLast = timeSupplier.get();
	}

	@Override
	public void render( Engine engine ) {
	}

	@Override
	public void reset() {
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
