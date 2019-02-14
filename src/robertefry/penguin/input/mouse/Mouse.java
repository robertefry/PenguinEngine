
package robertefry.penguin.input.mouse;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;
import javax.swing.event.MouseInputListener;
import robertefry.penguin.input.InputReciever;

/**
 * @author Robert E Fry
 * @date 14 Feb 2019
 */
public class Mouse implements InputReciever, MouseInputListener, MouseWheelListener {

	private final Set< MouseInputListener > inputListeners = new HashSet<>();
	private final Set< MouseWheelListener > wheelListeners = new HashSet<>();
	private final Map< Integer, Button > buttons = new HashMap<>();

	private final TransferQueue< MouseEvent > mouseEnterEventQueue = new LinkedTransferQueue<>();
	private final TransferQueue< MouseEvent > mouseExitEventQueue = new LinkedTransferQueue<>();
	private final TransferQueue< MouseEvent > mouseMoveEventQueue = new LinkedTransferQueue<>();
	private final TransferQueue< MouseEvent > mouseDragEventQueue = new LinkedTransferQueue<>();
	private final TransferQueue< MouseWheelEvent > mouseWheelEventQueue = new LinkedTransferQueue<>();

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
		inputListeners.forEach( listener -> listener.mouseClicked( e ) );
		getButton( e.getButton() ).mouseClicked( e );
	}

	@Override
	public void mousePressed( MouseEvent e ) {
		inputListeners.forEach( listener -> listener.mousePressed( e ) );
		getButton( e.getButton() ).mousePressed( e );
	}

	@Override
	public void mouseReleased( MouseEvent e ) {
		inputListeners.forEach( listener -> listener.mouseReleased( e ) );
		getButton( e.getButton() ).mouseReleased( e );
	}

	@Override
	public void mouseEntered( MouseEvent e ) {
		inputListeners.forEach( listener -> listener.mouseEntered( e ) );
		try {
			mouseEnterEventQueue.put( e );
		} catch ( InterruptedException e1 ) {
		}
	}

	@Override
	public void mouseExited( MouseEvent e ) {
		inputListeners.forEach( listener -> listener.mouseExited( e ) );
		try {
			mouseExitEventQueue.put( e );
		} catch ( InterruptedException e1 ) {
		}
	}

	@Override
	public void mouseMoved( MouseEvent e ) {
		inputListeners.forEach( listener -> listener.mouseMoved( e ) );
		try {
			mouseMoveEventQueue.put( e );
		} catch ( InterruptedException e1 ) {
		}
	}

	@Override
	public void mouseDragged( MouseEvent e ) {
		inputListeners.forEach( listener -> listener.mouseDragged( e ) );
		try {
			mouseDragEventQueue.put( e );
		} catch ( InterruptedException e1 ) {
		}
	}

	@Override
	public void mouseWheelMoved( MouseWheelEvent e ) {
		wheelListeners.forEach( listener -> listener.mouseWheelMoved( e ) );
		try {
			mouseWheelEventQueue.put( e );
		} catch ( InterruptedException e1 ) {
		}
	}

	public Button getButton( int code ) {
		Button button = buttons.get( code );
		if ( button == null ) {
			button = new Button( code );
			buttons.put( code, button );
		}
		return button;
	}

	public MouseEvent pollMouseEnterEventQueue() {
		return mouseEnterEventQueue.poll();
	}

	public MouseEvent pollMouseExitEventQueue() {
		return mouseExitEventQueue.poll();
	}

	public MouseEvent pollMouseMoveEventQueue() {
		return mouseMoveEventQueue.poll();
	}

	public MouseEvent pollMouseDragEventQueue() {
		return mouseDragEventQueue.poll();
	}

	public MouseEvent pollMouseWheelEventQueue() {
		return mouseWheelEventQueue.poll();
	}

	public void addMouseInputListener( MouseInputListener listener ) {
		inputListeners.add( listener );
	}

	public void removeMouseInputListener( MouseInputListener listener ) {
		inputListeners.remove( listener );
	}

	public void addMouseWheelListener( MouseWheelListener listener ) {
		wheelListeners.add( listener );
	}

	public void removeMouseWheelListener( MouseWheelListener listener ) {
		wheelListeners.remove( listener );
	}

}
