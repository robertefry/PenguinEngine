
package robertefry.penguin.engine.listener;

/**
 * @author Robert E Fry
 * @date 14 Feb 2019
 */
public interface EngineThreadAdapter extends EngineThreadListener {

	@Override
	default void preInitialisationTask() {
	}

	@Override
	default void postInitialisationTask() {
	}

	@Override
	default void preDisposalTask() {
	}

	@Override
	default void postDisposalTask() {
	}

}
