
package robertefry.penguin.targets;

import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.logging.LogFactory;

/**
 * @author Robert E Fry
 * @date 25 Jan 2019
 */
public class FrameCounter extends SimpleCounter {

	private final OutputStream out;
	private final double duration;

	private long lasttime = 0;

	public FrameCounter( OutputStream out, double duration ) {
		this.out = out;
		this.duration = duration;
	}

	@Override
	public void init() {
		lasttime = System.currentTimeMillis();
	}

	@Override
	public void update() {
		super.update();
		long currenttime = System.currentTimeMillis();
		if ( currenttime - lasttime >= duration ) {
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
