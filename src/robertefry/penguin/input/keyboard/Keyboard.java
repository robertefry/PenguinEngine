
package robertefry.penguin.input.keyboard;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import robertefry.penguin.input.InputReciever;

/**
 * @author Robert E Fry
 * @date 14 Feb 2019
 */
public class Keyboard implements KeyListener, InputReciever {

	private final Set< KeyListener > keyListeners = new HashSet<>();
	private final Map< Integer, Key > keys = new HashMap<>();

	@Override
	public void register( Component component ) {
		component.addKeyListener( this );
	}

	@Override
	public void tick() {
		keys.values().forEach( Key::tick );
	}

	@Override
	public void keyPressed( KeyEvent e ) {
		keyListeners.forEach( listener -> listener.keyPressed( e ) );
		getKey( e.getKeyCode() ).keyPressed( e );
	}

	@Override
	public void keyReleased( KeyEvent e ) {
		keyListeners.forEach( listener -> listener.keyReleased( e ) );
		getKey( e.getKeyCode() ).keyReleased( e );
	}

	@Override
	public void keyTyped( KeyEvent e ) {
		keyListeners.forEach( listener -> listener.keyTyped( e ) );
		getKey( e.getKeyCode() ).keyTyped( e );
	}

	public Key getKey( int code ) {
		Key key = keys.get( code );
		if ( key == null ) {
			key = new Key( code );
			keys.put( code, key );
		}
		return key;
	}

	public void addKeyListener( KeyListener listener ) {
		keyListeners.add( listener );
	}

	public void removeKeyListener( KeyListener listener ) {
		keyListeners.remove( listener );
	}

}
