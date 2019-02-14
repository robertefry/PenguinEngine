
package robertefry.penguin.engine.listener;

/**
 * @author Robert E Fry
 * @date 5 Feb 2019
 */
public interface EngineThreadListener {

	default void preInitialisationTask() {
	}

	default void postInitialisationTask() {
	}

	default void preDisposalTask() {
	}

	default void postDisposalTask() {
	}

}
