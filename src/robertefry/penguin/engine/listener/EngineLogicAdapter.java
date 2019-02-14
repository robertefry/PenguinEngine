
package robertefry.penguin.engine.listener;

/**
 * @author Robert E Fry
 * @date 14 Feb 2019
 */
public interface EngineLogicAdapter extends EngineLogicListener {

	@Override
	default void prePollInput() {
	}

	@Override
	default void postPollInput() {
	}

	@Override
	default void preTick() {
	}

	@Override
	default void postTick() {
	}

	@Override
	default void preRender() {
	}

	@Override
	default void postRender() {
	}

}
