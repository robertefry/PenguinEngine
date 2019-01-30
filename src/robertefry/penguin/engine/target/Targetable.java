
package robertefry.penguin.engine.target;

import robertefry.penguin.engine.Engine;
import robertefry.penguin.engine.core.Resetable;

/**
 * @author Robert E Fry
 * @date 22 Jan 2019
 */
public interface Targetable extends Resetable {

	default void init( Engine engine ) {
	}

	default void dispose( Engine engine ) {
	}

	default void tick( Engine engine ) {
	}

	default void render( Engine engine ) {
	}
	
	default void reset() {
	}

}
