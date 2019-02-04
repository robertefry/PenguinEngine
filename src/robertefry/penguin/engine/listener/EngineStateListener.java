
package robertefry.penguin.engine.listener;

/**
 * @author Robert E Fry
 * @date 4 Feb 2019
 */
public interface EngineStateListener {

	default void engineStarting() {
	}

	default void engineStarted() {
	}

	default void engineStopping() {
	}

	default void engineStopped() {
	}

	default void engineSuspending() {
	}

	default void engineSuspended() {
	}

	default void engineResuming() {
	}

	default void engineResumed() {
	}

}
