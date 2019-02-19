
package robertefry.penguin.input;

import java.awt.Component;
import robertefry.penguin.target.api.Updatable;

/**
 * @author Robert E Fry
 * @date 14 Feb 2019
 */
public interface EngineInputReciever extends Updatable {

	public void register( Component component );

}
