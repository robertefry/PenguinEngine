
package robertefry.penguin.input.mouse.listener;

import java.awt.event.MouseEvent;
import java.util.EventListener;

/**
 * @author Robert E Fry
 * @date 14 Feb 2019
 */
public interface MouseButtonListener extends EventListener {

	public void onButtonPress( MouseEvent e );

	public void onButtonRelease( MouseEvent e );

	public void onButtonClick( MouseEvent e );

}
