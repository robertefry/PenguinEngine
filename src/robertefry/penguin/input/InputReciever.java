
package robertefry.penguin.input;

import java.awt.Component;

/**
 * @author Robert E Fry
 * @date 14 Feb 2019
 */
public interface InputReciever {
	
	public void register( Component component );

	public void tick();

}
