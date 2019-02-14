
package robertefry.penguin.input.mouse.listener;

import java.awt.event.MouseEvent;

/**
 * @author Robert E Fry
 * @date 14 Feb 2019
 */
public interface MouseEgressAdapter extends MouseEgressListener {

	@Override
	default void mouseEntered( MouseEvent e ) {
	}

	@Override
	default void mouseExited( MouseEvent e ) {
	}

}
