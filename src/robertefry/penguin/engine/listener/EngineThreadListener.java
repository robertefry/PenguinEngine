
package robertefry.penguin.engine.listener;

/**
 * @author Robert E Fry
 * @date 5 Feb 2019
 */
public interface EngineThreadListener extends EngineListener {

	public void preInitialisationTask();

	public void postInitialisationTask();

	public void preDisposalTask();

	public void postDisposalTask();

}
