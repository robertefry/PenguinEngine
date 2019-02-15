
package robertefry.penguin.input.keyboard.listener;

import java.awt.event.KeyEvent;
import java.util.EventListener;

/**
 * @author Robert E Fry
 * @date 15 Feb 2019
 */
public interface KeyboardListener extends EventListener {

	public void onKeyPress( KeyEvent e );

	public void onKeyRelease( KeyEvent e );

	public void onKeyType( KeyEvent e );

}
