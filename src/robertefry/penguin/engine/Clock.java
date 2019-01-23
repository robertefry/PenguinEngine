
package robertefry.penguin.engine;

import robertefry.penguin.engine.target.Targetable;

/**
 * @author Robert E Fry
 * @date 23 Jan 2019
 */
public class Clock implements Targetable {

	private long tickcount = 0;
	private long starttime = 0;

	@Override
	public void init() {
		Targetable.super.init();
		starttime = System.currentTimeMillis();
	}

	@Override
	public void update() {
		Targetable.super.update();
		tickcount++;
	}
	
	public long getStartTime() {
		return starttime;
	}
	
	public long getTickCount() {
		return tickcount;
	}

}
