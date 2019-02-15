
package robertefry.penguin.engine.listener;

/**
 * @author Robert E Fry
 * @date 4 Feb 2019
 */
public interface EngineStateListener extends EngineListener {

	public void onSuspend();

	public void onResume();

}
