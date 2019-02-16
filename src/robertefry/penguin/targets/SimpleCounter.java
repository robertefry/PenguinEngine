
package robertefry.penguin.targets;

import robertefry.penguin.target.TargetBlank;

public class SimpleCounter extends TargetBlank {

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
