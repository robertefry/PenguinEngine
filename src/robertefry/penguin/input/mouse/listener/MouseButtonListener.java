
package robertefry.penguin.input.mouse.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author Robert E Fry
 * @date 14 Feb 2019
 */
public interface MouseButtonListener extends MouseListener {

	@Override
	@Deprecated
	default void mouseEntered( MouseEvent e ) {
	}

	@Override
	@Deprecated
	default void mouseExited( MouseEvent e ) {
	}

}
