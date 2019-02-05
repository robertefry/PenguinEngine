
package robertefry.penguin.engine.listener;

/**
 * @author Robert E Fry
 * @date 4 Feb 2019
 */
public interface EngineStateListener {

	default void onEngineSuspend() {
	}

	default void onEngineResume() {
	}

}
