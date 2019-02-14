
package robertefry.penguin.input.mouse.listener;

import java.awt.event.MouseEvent;

/**
 * @author Robert E Fry
 * @date 14 Feb 2019
 */
public interface MouseButtonAdapter extends MouseButtonListener {

	@Override
	default void mouseClicked( MouseEvent e ) {
	}

	@Override
	default void mousePressed( MouseEvent e ) {
	}

	@Override
	default void mouseReleased( MouseEvent e ) {
	}

}
