
package robertefry.penguin.input.mouse;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.event.MouseInputListener;
import robertefry.penguin.input.InputReciever;

/**
 * @author Robert E Fry
 * @date 14 Feb 2019
 */
public class Button implements InputReciever, MouseInputListener, MouseWheelListener {

	private final int code;
	public MouseEvent eventClick = null;
	public MouseEvent eventPress = null;
	public MouseEvent eventRelease = null;
	public MouseEvent eventEnter = null;
	public MouseEvent eventExit = null;
	public MouseEvent eventDrag = null;
	public MouseEvent eventMove = null;
	public MouseWheelEvent eventWheel = null;

	public Button( int code ) {
		this.code = code;
	}

	@Override
	public void register( Component component ) {
		component.addMouseListener( this );
		component.addMouseMotionListener( this );
		component.addMouseWheelListener( this );
	}

	@Override
	public void tick() {
		eventClick = null;
		eventPress = null;
		eventRelease = null;
		eventEnter = null;
		eventExit = null;
		eventDrag = null;
		eventMove = null;
		eventWheel = null;
	}

	@Override
	public void mouseClicked( MouseEvent e ) {
		eventClick = e;
	}

	@Override
	public void mousePressed( MouseEvent e ) {
		eventPress = e;
	}

	@Override
	public void mouseReleased( MouseEvent e ) {
		eventRelease = e;
	}

	@Override
	public void mouseEntered( MouseEvent e ) {
		eventEnter = e;
	}

	@Override
	public void mouseExited( MouseEvent e ) {
		eventExit = e;
	}

	@Override
	public void mouseDragged( MouseEvent e ) {
		eventDrag = e;
	}

	@Override
	public void mouseMoved( MouseEvent e ) {
		eventMove = e;
	}

	@Override
	public void mouseWheelMoved( MouseWheelEvent e ) {
		eventWheel = e;
	}

	public int getCode() {
		return code;
	}

}
