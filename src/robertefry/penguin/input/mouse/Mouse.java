
package robertefry.penguin.input.mouse;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.event.MouseInputListener;
import robertefry.penguin.input.InputReciever;

/**
 * @author Robert E Fry
 * @date 14 Feb 2019
 */
public class Mouse implements InputReciever, MouseInputListener, MouseWheelListener {

	private final Map< Integer, Button > buttons = new HashMap<>();

	@Override
	public void register( Component component ) {
		component.addMouseListener( this );
		component.addMouseMotionListener( this );
		component.addMouseWheelListener( this );
	}

	@Override
	public void tick() {
		buttons.values().forEach( Button::tick );
	}

	@Override
	public void mouseClicked( MouseEvent e ) {
		getButton( e.getButton() ).mouseClicked( e );
	}

	@Override
	public void mousePressed( MouseEvent e ) {
		getButton( e.getButton() ).mousePressed( e );
	}

	@Override
	public void mouseReleased( MouseEvent e ) {
		getButton( e.getButton() ).mouseReleased( e );
	}

	@Override
	public void mouseEntered( MouseEvent e ) {
		getButton( e.getButton() ).mouseEntered( e );
	}

	@Override
	public void mouseExited( MouseEvent e ) {
		getButton( e.getButton() ).mouseExited( e );
	}

	@Override
	public void mouseDragged( MouseEvent e ) {
		getButton( e.getButton() ).mouseDragged( e );
	}

	@Override
	public void mouseMoved( MouseEvent e ) {
		getButton( e.getButton() ).mouseMoved( e );
	}

	@Override
	public void mouseWheelMoved( MouseWheelEvent e ) {
		getButton( e.getButton() ).mouseWheelMoved( e );
	}

	public Button getButton( int code ) {
		Button button = buttons.get( code );
		if ( button == null ) {
			button = new Button( code );
			buttons.put( code, button );
		}
		return button;
	}

}
