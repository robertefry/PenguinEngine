
package robertefry.penguin.engine.listener;

/**
 * @author Robert E Fry
 * @date 14 Feb 2019
 */
public interface EngineStateAdapter extends EngineStateListener {

	@Override
	default void onSuspend() {
	}

	@Override
	default void onResume() {
	}

}
