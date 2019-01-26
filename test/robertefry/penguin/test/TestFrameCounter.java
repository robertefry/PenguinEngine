
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
		engine.getTargetManager().add( new FrameCounter( System.out, 1000 ) );
		engine.start();
		
	}

}
