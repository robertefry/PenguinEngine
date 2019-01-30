
package robertefry.penguin.engine.targets;

import robertefry.penguin.engine.Engine;
import robertefry.penguin.engine.target.Targetable;

/**
 * @author Robert E Fry
 * @date 30 Jan 2019
 */
public class SimpleTimer implements Targetable {

	private long lasttime = 0, currenttime = 0;

	@Override
	public void init(Engine engine) {
		Targetable.super.init(engine);
		lasttime = currenttime = System.currentTimeMillis();
	}

	@Override
	public void tick(Engine engine) {
		Targetable.super.tick(engine);
		lasttime = currenttime;
		currenttime = System.currentTimeMillis();
	}

	public long getLastTime() {
		return lasttime;
	}

	public long getCurrentTime() {
		return currenttime;
	}

	public long getDelta() {
		return currenttime - lasttime;
	}

}
