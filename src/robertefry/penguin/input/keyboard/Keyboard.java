
package robertefry.penguin.input.keyboard;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import robertefry.penguin.input.EngineInputReciever;
import robertefry.penguin.input.keyboard.listener.KeyboardListener;

/**
 * @author Robert E Fry
 * @date 14 Feb 2019
 */
public class Keyboard implements EngineInputReciever, KeyboardListener {

	private final Map< Integer, Key > keys = new HashMap<>();
	private final InstanceListener listener = new InstanceListener();

	private final Set< KeyboardListener > keyboardListeners = new HashSet<>();

	@Override
	public void register( Component component ) {
		component.addKeyListener( listener );
	}

	@Override
	public void update() {
		keys.values().forEach( Key::update );
	}

	@Override
	public void onKeyPress( KeyEvent e ) {
		keyboardListeners.forEach( listener -> listener.onKeyPress( e ) );
		getKey( e.getKeyCode() ).onKeyPress( e );
	}

	@Override
	public void onKeyRelease( KeyEvent e ) {
		keyboardListeners.forEach( listener -> listener.onKeyRelease( e ) );
		getKey( e.getKeyCode() ).onKeyRelease( e );
	}

	@Override
	public void onKeyType( KeyEvent e ) {
		keyboardListeners.forEach( listener -> listener.onKeyType( e ) );
		getKey( e.getKeyCode() ).onKeyType( e );
	}

	public Key getKey( int code ) {
		Key key = keys.get( code );
		if ( key == null ) {
			key = new Key( code );
			keys.put( code, key );
		}
		return key;
	}

	public void addKeyboardListener( KeyboardListener listener ) {
		keyboardListeners.add( listener );
	}

	public < T extends KeyboardListener > void addKeyboardListeners( T[] listeners ) {
		keyboardListeners.addAll( Arrays.asList( listeners ) );
	}

	public < T extends KeyboardListener > void addKeyboardListeners( Collection< T > listeners ) {
		keyboardListeners.addAll( listeners );
	}

	public void removeKeyboardListener( KeyboardListener listener ) {
		keyboardListeners.remove( listener );
	}

	public < T extends KeyboardListener > void removeKeyboardListeners( T[] listeners ) {
		keyboardListeners.removeAll( Arrays.asList( listeners ) );
	}

	public < T extends KeyboardListener > void removeKeyboardListeners( Collection< T > listeners ) {
		keyboardListeners.removeAll( listeners );
	}

	private final class InstanceListener implements KeyListener {

		@Override
		public void keyTyped( KeyEvent e ) {
		}

		@Override
		public void keyPressed( KeyEvent e ) {
		}

		@Override
		public void keyReleased( KeyEvent e ) {
		}

	}

}
