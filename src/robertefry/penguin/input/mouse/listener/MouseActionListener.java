
package robertefry.penguin.input.mouse.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.EventListener;

/**
 * @author Robert E Fry
 * @date 14 Feb 2019
 */
public interface MouseActionListener extends EventListener {

	public void onMouseEnter( MouseEvent e );

	public void onMouseExit( MouseEvent e );

	public void onMouseMove( MouseEvent e );

	public void onMouseDrag( MouseEvent e );

	public void onWheelAction( MouseWheelEvent e );

}
