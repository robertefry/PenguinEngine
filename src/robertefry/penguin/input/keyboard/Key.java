
package robertefry.penguin.input.keyboard;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import robertefry.penguin.input.InputReciever;
import robertefry.penguin.input.keyboard.listener.KeyboardListener;

/**
 * @author Robert E Fry
 * @date 14 Feb 2019
 */
public class Key implements InputReciever, KeyboardListener {

	public final int code;
	private boolean down = false;
	private boolean justDown = false, justUp = false;

	private final InstanceListener listener = new InstanceListener();

	public Key( int code ) {
		this.code = code;
	}

	@Override
	public void register( Component component ) {
		component.addKeyListener( listener );
	}

	@Override
	public void tick() {
		setState( down, false, false );
	}

	@Override
	public void onKeyPress( KeyEvent e ) {
		setState( true, true, false );
	}

	@Override
	public void onKeyRelease( KeyEvent e ) {
		setState( false, false, true );
	}

	@Override
	public void onKeyType( KeyEvent e ) {
		setState( false, true, true );
	}

	private final void setState( boolean down, boolean justDown, boolean justUp ) {
		this.down = down;
		this.justDown = justDown;
		this.justUp = justUp;
	}

	public boolean isDown() {
		return down;
	}

	public boolean isJustDown() {
		return justDown;
	}

	public boolean isJustUp() {
		return justUp;
	}

	private final class InstanceListener implements KeyListener {

		@Override
		public void keyPressed( KeyEvent e ) {
			onKeyPress( e );
		}

		@Override
		public void keyReleased( KeyEvent e ) {
			onKeyRelease( e );
		}

		@Override
		public void keyTyped( KeyEvent e ) {
			onKeyType( e );
		}

	}

}
