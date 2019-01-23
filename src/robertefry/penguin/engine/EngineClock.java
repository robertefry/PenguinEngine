
package robertefry.penguin.engine;

import robertefry.penguin.engine.target.Targetable;

/**
 * @author Robert E Fry
 * @date 23 Jan 2019
 */
public class EngineClock implements Targetable {

	private long tickcount = 0;
	private long starttime = 0;

	@Override
	public void init( Engine engine ) {
		Targetable.super.init( engine );
		starttime = System.currentTimeMillis();
	}

	@Override
	public void update( Engine engine ) {
		Targetable.super.update( engine );
		tickcount++;
	}
	
	public long getStartTime() {
		return starttime;
	}
	
	public long getTickCount() {
		return tickcount;
	}

}
