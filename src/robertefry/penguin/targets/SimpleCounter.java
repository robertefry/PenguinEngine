
package robertefry.penguin.targets;

import robertefry.penguin.engine.target.TargetAdapter;

public class SimpleCounter extends TargetAdapter {

	private long count = 0;

	@Override
	public void update() {
		count++;
	}

	@Override
	public void reset() {
		count = 0;
	}

	public long getCount() {
		return count;
	}

}
