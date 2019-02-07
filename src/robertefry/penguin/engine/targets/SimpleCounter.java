
package robertefry.penguin.engine.targets;

import robertefry.penguin.engine.Engine;
import robertefry.penguin.engine.target.TargetAdapter;

public class SimpleCounter implements TargetAdapter {

	private long count = 0;

	@Override
	public void tick( Engine engine ) {
		TargetAdapter.super.tick( engine );
		count++;
	}

	@Override
	public void reset() {
		TargetAdapter.super.reset();
		count = 0;
	}

	public long getCount() {
		return count;
	}

}
