
package robertefry.penguin.engine.listener;

/**
 * @author Robert E Fry
 * @date 5 Feb 2019
 */
public interface EngineLogicListener {
	
	default void prePollInput() {
	}
	
	default void postPollInput() {
	}

	default void preTick() {
	}

	default void postTick() {
	}

	default void preRender() {
	}

	default void postRender() {
	}
	
	default void preReset() {
	}
	
	default void postReset() {
	}

}
