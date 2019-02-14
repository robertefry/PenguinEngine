
package robertefry.penguin.engine.target;

import robertefry.penguin.engine.Engine;

/**
 * @author Robert E Fry
 * @date 7 Feb 2019
 */
public interface TargetAdapter extends Targetable {

	@Override
	default void init( Engine engine ) {
	}

	@Override
	default void dispose( Engine engine ) {
	}

	@Override
	default void pollInput( Engine engine ) {
	}

	@Override
	default void tick( Engine engine ) {
	}

	@Override
	default void render( Engine engine ) {
	}

	@Override
	default void reset() {
	}

}
