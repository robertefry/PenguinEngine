
package robertefry.penguin.input.mouse.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author Robert E Fry
 * @date 14 Feb 2019
 */
public interface MouseEgressListener extends MouseListener {

	@Override
	@Deprecated
	default void mouseClicked( MouseEvent e ) {
	}

	@Override
	@Deprecated
	default void mousePressed( MouseEvent e ) {
	}

	@Override
	@Deprecated
	default void mouseReleased( MouseEvent e ) {
	}

}
