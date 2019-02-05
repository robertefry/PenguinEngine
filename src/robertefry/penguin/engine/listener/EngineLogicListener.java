
package robertefry.penguin.engine.listener;
/**
 * @author Robert E Fry
 * @date 5 Feb 2019
 */
public interface EngineLogicListener {
	
	default void onEngineTick() {
	}
	
	default void onEngineRender() {
	}

}
