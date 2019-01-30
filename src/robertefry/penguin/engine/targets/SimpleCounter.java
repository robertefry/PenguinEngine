
package robertefry.penguin.engine.targets;

import robertefry.penguin.engine.Engine;
import robertefry.penguin.engine.target.Targetable;

public class SimpleCounter implements Targetable {

	private long count = 0;

	@Override
	public void tick( Engine engine ) {
		Targetable.super.tick( engine );
		count++;
	}

	public long getCount() {
		return count;
	}

	@Override
	public void reset() {
		count = 0;
	}

}
