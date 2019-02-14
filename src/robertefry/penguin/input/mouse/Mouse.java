
package robertefry.penguin.input.mouse;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;
import robertefry.penguin.input.InputReciever;

/**
 * @author Robert E Fry
 * @date 14 Feb 2019
 */
public class Mouse implements InputReciever, MouseListener, MouseMotionListener, MouseWheelListener {

	private final Map< Integer, Button > buttons = new HashMap<>();

	private final Set< MouseListener > mouseButtonListeners = new HashSet<>();
	private final Set< MouseListener > mouseEgressListeners = new HashSet<>();
	private final Set< MouseMotionListener > mouseMotionListeners = new HashSet<>();
	private final Set< MouseWheelListener > mouseWheelListeners = new HashSet<>();

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
		mouseButtonListeners.forEach( listener -> listener.mouseClicked( e ) );
		getButton( e.getButton() ).mouseClicked( e );
	}

	@Override
	public void mousePressed( MouseEvent e ) {
		mouseButtonListeners.forEach( listener -> listener.mousePressed( e ) );
		getButton( e.getButton() ).mousePressed( e );
	}

	@Override
	public void mouseReleased( MouseEvent e ) {
		mouseButtonListeners.forEach( listener -> listener.mouseReleased( e ) );
		getButton( e.getButton() ).mouseReleased( e );
	}

	@Override
	public void mouseEntered( MouseEvent e ) {
		mouseEgressListeners.forEach( listener -> listener.mouseEntered( e ) );
		try {
			mouseEnterEventQueue.put( e );
		} catch ( InterruptedException e1 ) {
		}
	}

	@Override
	public void mouseExited( MouseEvent e ) {
		mouseEgressListeners.forEach( listener -> listener.mouseExited( e ) );
		try {
			mouseExitEventQueue.put( e );
		} catch ( InterruptedException e1 ) {
		}
	}

	@Override
	public void mouseMoved( MouseEvent e ) {
		mouseMotionListeners.forEach( listener -> listener.mouseMoved( e ) );
		try {
			mouseMoveEventQueue.put( e );
		} catch ( InterruptedException e1 ) {
		}
	}

	@Override
	public void mouseDragged( MouseEvent e ) {
		mouseMotionListeners.forEach( listener -> listener.mouseDragged( e ) );
		try {
			mouseDragEventQueue.put( e );
		} catch ( InterruptedException e1 ) {
		}
	}

	@Override
	public void mouseWheelMoved( MouseWheelEvent e ) {
		mouseWheelListeners.forEach( listener -> listener.mouseWheelMoved( e ) );
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

	public void addMouseButtonListener( MouseListener listener ) {
		mouseButtonListeners.add( listener );
	}

	public void removeMouseButtonListener( MouseListener listener ) {
		mouseButtonListeners.remove( listener );
	}

	public void addMouseEgressListener( MouseListener listener ) {
		mouseEgressListeners.add( listener );
	}

	public void removeMouseEgressListener( MouseListener listener ) {
		mouseEgressListeners.remove( listener );
	}

	public void addMouseMotionListener( MouseMotionListener listener ) {
		mouseMotionListeners.add( listener );
	}

	public void removeMouseMotionListener( MouseMotionListener listener ) {
		mouseMotionListeners.remove( listener );
	}

	public void addMouseWheelListener( MouseWheelListener listener ) {
		mouseWheelListeners.add( listener );
	}

	public void removeMouseWheelListener( MouseWheelListener listener ) {
		mouseWheelListeners.remove( listener );
	}

}
