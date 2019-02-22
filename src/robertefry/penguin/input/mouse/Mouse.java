
package robertefry.penguin.input.mouse;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.event.MouseInputListener;
import robertefry.penguin.input.EngineInputReciever;
import robertefry.penguin.input.mouse.listener.MouseActionListener;
import robertefry.penguin.input.mouse.listener.MouseButtonListener;
import robertefry.penguin.input.mouse.listener.MouseObjectListener;

/**
 * @author Robert E Fry
 * @date 14 Feb 2019
 */
public class Mouse implements EngineInputReciever, MouseButtonListener, MouseActionListener {

	private final Map< Integer, Button > buttons = new HashMap<>();
	private final InstanceListener listener = new InstanceListener();

	private final Set< MouseButtonListener > mouseButtonListeners = new HashSet<>();
	private final Set< MouseActionListener > mouseActionListeners = new HashSet<>();

	@Override
	public void register( Component component ) {
		component.addMouseListener( listener );
		component.addMouseMotionListener( listener );
		component.addMouseWheelListener( listener );
	}

	@Override
	public void update() {
		buttons.values().forEach( Button::update );
	}

	@Override
	public void onButtonPress( MouseEvent e ) {
		getButton( e.getButton() ).onButtonPress( e );
		mouseButtonListeners.forEach( listener -> listener.onButtonPress( e ) );
	}

	@Override
	public void onButtonRelease( MouseEvent e ) {
		getButton( e.getButton() ).onButtonRelease( e );
		mouseButtonListeners.forEach( listener -> listener.onButtonRelease( e ) );
	}

	@Override
	public void onButtonClick( MouseEvent e ) {
		getButton( e.getButton() ).onButtonClick( e );
		mouseButtonListeners.forEach( listener -> listener.onButtonClick( e ) );
	}

	@Override
	public void onMouseEnter( MouseEvent e ) {
		mouseActionListeners.forEach( listener -> listener.onMouseEnter( e ) );
	}

	@Override
	public void onMouseExit( MouseEvent e ) {
		mouseActionListeners.forEach( listener -> listener.onMouseExit( e ) );
	}

	@Override
	public void onMouseMove( MouseEvent e ) {
		mouseActionListeners.forEach( listener -> listener.onMouseMove( e ) );
	}

	@Override
	public void onMouseDrag( MouseEvent e ) {
		mouseActionListeners.forEach( listener -> listener.onMouseDrag( e ) );
	}

	@Override
	public void onWheelAction( MouseWheelEvent e ) {
		mouseActionListeners.forEach( listener -> listener.onWheelAction( e ) );
	}

	public Button getButton( int code ) {
		Button button = buttons.get( code );
		if ( button == null ) {
			button = new Button( code );
			buttons.put( code, button );
		}
		return button;
	}

	public void addMouseButtonListener( MouseButtonListener mouseButtonListener ) {
		mouseButtonListeners.add( mouseButtonListener );
	}

	public void removeMouseButtonListener( MouseButtonListener mouseButtonListener ) {
		mouseButtonListeners.remove( mouseButtonListener );
	}

	public void addMouseActionListener( MouseActionListener mouseActionListener ) {
		mouseActionListeners.add( mouseActionListener );
	}

	public void removeMouseActionListener( MouseActionListener mouseActionListener ) {
		mouseActionListeners.remove( mouseActionListener );
	}

	public void addMouseObjectListener( MouseObjectListener mouseObjectListener ) {
		addMouseButtonListener( mouseObjectListener );
		addMouseActionListener( mouseObjectListener );
	}

	public void removeObjectActionListener( MouseObjectListener mouseObjectListener ) {
		removeMouseButtonListener( mouseObjectListener );
		removeMouseActionListener( mouseObjectListener );
	}

	private final class InstanceListener implements MouseInputListener, MouseWheelListener {

		@Override
		public void mousePressed( MouseEvent e ) {
			onButtonPress( e );
		}

		@Override
		public void mouseReleased( MouseEvent e ) {
			onButtonRelease( e );
		}

		@Override
		public void mouseClicked( MouseEvent e ) {
			onButtonClick( e );
		}

		@Override
		public void mouseEntered( MouseEvent e ) {
			onMouseEnter( e );
		}

		@Override
		public void mouseExited( MouseEvent e ) {
			onMouseExit( e );
		}

		@Override
		public void mouseDragged( MouseEvent e ) {
			onMouseDrag( e );
		}

		@Override
		public void mouseMoved( MouseEvent e ) {
			onMouseMove( e );
		}

		@Override
		public void mouseWheelMoved( MouseWheelEvent e ) {
			onWheelAction( e );
		}

	}

}
