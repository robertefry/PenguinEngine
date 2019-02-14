
package robertefry.penguin.input.mouse;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;
import robertefry.penguin.input.InputReciever;

/**
 * @author Robert E Fry
 * @date 14 Feb 2019
 */
public class Button implements InputReciever, MouseListener {

	private final int code;

	private final TransferQueue< MouseEvent > buttonPressEventQueue = new LinkedTransferQueue<>();
	private final TransferQueue< MouseEvent > buttonReleaseEventQueue = new LinkedTransferQueue<>();
	private final TransferQueue< MouseEvent > buttonClickEventQueue = new LinkedTransferQueue<>();

	public Button( int code ) {
		this.code = code;
	}

	@Override
	public void register( Component component ) {
		component.addMouseListener( this );
	}

	@Override
	public void tick() {
	}

	@Override
	public void mousePressed( MouseEvent e ) {
		try {
			buttonPressEventQueue.put( e );
		} catch ( InterruptedException e1 ) {
		}
	}

	@Override
	public void mouseReleased( MouseEvent e ) {
		try {
			buttonReleaseEventQueue.put( e );
		} catch ( InterruptedException e1 ) {
		}
	}

	@Override
	public void mouseClicked( MouseEvent e ) {
		try {
			buttonClickEventQueue.put( e );
		} catch ( InterruptedException e1 ) {
		}
	}

	@Override
	@Deprecated
	public void mouseEntered( MouseEvent e ) {
	}

	@Override
	@Deprecated
	public void mouseExited( MouseEvent e ) {
	}

	public int getCode() {
		return code;
	}

	public MouseEvent pollButtonPressEventQueue() {
		return buttonPressEventQueue.poll();
	}

	public MouseEvent pollButtonReleaseEventQueue() {
		return buttonReleaseEventQueue.poll();
	}

	public MouseEvent pollButtonClickEventQueue() {
		return buttonClickEventQueue.poll();
	}

}
