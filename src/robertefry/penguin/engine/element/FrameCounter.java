
package robertefry.penguin.engine.element;

import java.io.PrintStream;
import robertefry.penguin.engine.Engine;
import robertefry.penguin.engine.target.Targetable;

/**
 * @author Robert E Fry
 * @date 25 Jan 2019
 */
public class FrameCounter implements Targetable {

	private final PrintStream printer;
	private final long duration;

	private long lasttime = 0;
	private int count = 0;

	public FrameCounter( PrintStream printer, long duration ) {
		this.printer = printer;
		this.duration = duration;
	}

	@Override
	public void init( Engine engine ) {
		Targetable.super.init( engine );
		lasttime = System.currentTimeMillis();
	}

	@Override
	public void update( Engine engine ) {
		Targetable.super.update( engine );
		count++;
		long currenttime = System.currentTimeMillis();
		if (currenttime - lasttime >= duration) {
			lasttime = currenttime;
			printer.println( "frames=" + count );
			count = 0;
		}
	}

}
