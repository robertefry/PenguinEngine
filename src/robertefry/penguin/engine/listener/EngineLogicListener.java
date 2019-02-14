
package robertefry.penguin.engine.listener;

/**
 * @author Robert E Fry
 * @date 5 Feb 2019
 */
public interface EngineLogicListener {
	
	public void prePollInput();
	
	public void postPollInput();

	public void preTick();

	public void postTick();

	public void preRender();

	public void postRender();

}
