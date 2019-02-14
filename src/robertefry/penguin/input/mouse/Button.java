
package robertefry.penguin.input.mouse;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;
import javax.swing.event.MouseInputAdapter;
import robertefry.penguin.input.InputReciever;

/**
 * @author Robert E Fry
 * @date 14 Feb 2019
 */
public class Button extends MouseInputAdapter implements InputReciever {

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
		component.addMouseMotionListener( this );
		component.addMouseWheelListener( this );
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