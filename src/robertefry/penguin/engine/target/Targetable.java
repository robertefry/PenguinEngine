
package robertefry.penguin.engine.target;

import robertefry.penguin.engine.Engine;

/**
 * @author Robert E Fry
 * @date 22 Jan 2019
 */
public interface Targetable {

	default void init( Engine engine ) {
	}

	default void dispose( Engine engine ) {
	}

	default void update( Engine engine ) {
	}

}
