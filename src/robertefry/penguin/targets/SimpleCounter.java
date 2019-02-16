
package robertefry.penguin.targets;

import robertefry.penguin.engine.Engine;
import robertefry.penguin.engine.target.Targetable;

public class SimpleCounter implements Targetable {

	private long count = 0;

	@Override
	public void init( Engine engine ) {
	}

	@Override
	public void dispose( Engine engine ) {
	}

	@Override
	public void pollInput( Engine engine ) {
	}

	@Override
	public void tick( Engine engine ) {
		count++;
	}

	@Override
	public void render( Engine engine ) {
	}

	@Override
	public void reset() {
		count = 0;
	}

	public long getCount() {
		return count;
	}

}
