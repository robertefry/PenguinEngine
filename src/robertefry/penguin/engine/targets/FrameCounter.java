
package robertefry.penguin.engine.targets;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.logging.LogFactory;
import robertefry.penguin.engine.Engine;

/**
 * @author Robert E Fry
 * @date 25 Jan 2019
 */
public class FrameCounter extends SimpleCounter {

	private final OutputStream out;
	private final long duration;

	private long lasttime = 0;

	public FrameCounter( OutputStream out, long duration ) {
		this.out = out;
		this.duration = duration;
	}

	@Override
	public void init( Engine engine ) {
		super.init( engine );
		lasttime = System.currentTimeMillis();
	}

	@Override
	public void tick( Engine engine ) {
		super.tick( engine );

		long currenttime = System.currentTimeMillis();
		if (currenttime - lasttime >= duration) {
			lasttime = currenttime;
			try {
				out.write( String.format( "%s\n", getCount() ).getBytes() );
			} catch ( IOException e ) {
				LogFactory.getLog( getClass() ).warn(
					"exception occoured when writing to the output stream",
					e
				);
			}
			reset();
		}

	}

}
