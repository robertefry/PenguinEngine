
package robertefry.penguin.engine.listener;
/**
 * @author Robert E Fry
 * @date 5 Feb 2019
 */
public interface EngineThreadListener {
	
	default void enginePreInitialisationTask() {
	}
	
	default void enginePostInitialisationTask() {
	}
	
	default void enginePreDisposalTask() {
	}
	
	default void enginePostDisposalTask() {
	}

}
