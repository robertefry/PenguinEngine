
package robertefry.penguin.input.keyboard;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import robertefry.penguin.input.InputReciever;

/**
 * @author Robert E Fry
 * @date 14 Feb 2019
 */
public class Key implements KeyListener, InputReciever {

	public final int code;
	private boolean down = false;
	private boolean justDown = false, justUp = false;

	public Key( int code ) {
		this.code = code;
	}

	@Override
	public void register( Component component ) {
		component.addKeyListener( this );
	}

	@Override
	public void tick() {
		setState( down, false, false );
	}

	@Override
	public void keyPressed( KeyEvent e ) {
		setState( true, true, false );
	}

	@Override
	public void keyReleased( KeyEvent e ) {
		setState( false, false, true );
	}

	@Override
	public void keyTyped( KeyEvent e ) {
		setState( false, true, true );
	}

	private void setState( boolean down, boolean justDown, boolean justUp ) {
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

}