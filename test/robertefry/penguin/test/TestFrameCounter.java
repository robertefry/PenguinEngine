
package robertefry.penguin.test;

import robertefry.penguin.engine.Engine;
import robertefry.penguin.engine.targets.FrameCounter;

/**
 * @author Robert E Fry
 * @date 25 Jan 2019
 */
public class TestFrameCounter {

	public static void main( String[] args ) {

		Engine engine = new Engine();

		engine.getTargetManager().add( new FrameCounter( System.out, 1e3 ) );

		engine.setRefreshRate( 10000 );
		engine.suspend();
		engine.start();

		System.out.println( "Sleeping 1 seconds..." );
		try {
			Thread.sleep( 1000 );
		} catch ( InterruptedException e ) {
			e.printStackTrace();
		}

		engine.resume();

	}

}
