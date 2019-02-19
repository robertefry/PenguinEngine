
package robertefry.penguin.input.mouse;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import robertefry.penguin.input.EngineInputReciever;
import robertefry.penguin.input.mouse.listener.MouseButtonListener;

/**
 * @author Robert E Fry
 * @date 14 Feb 2019
 */
public class Button implements EngineInputReciever, MouseButtonListener {

	private final int code;
	private final InstanceListener listener = new InstanceListener();

	private final Queue< MouseEvent > buttonPressEventQueue = new ConcurrentLinkedQueue<>();
	private final Queue< MouseEvent > buttonReleaseEventQueue = new ConcurrentLinkedQueue<>();
	private final Queue< MouseEvent > buttonClickEventQueue = new ConcurrentLinkedQueue<>();

	public Button( int code ) {
		this.code = code;
	}

	@Override
	public void register( Component component ) {
		component.addMouseListener( listener );
	}

	@Override
	public void update() {
	}

	@Override
	public void onButtonPress( MouseEvent e ) {
		buttonPressEventQueue.offer( e );
	}

	@Override
	public void onButtonRelease( MouseEvent e ) {
		buttonReleaseEventQueue.offer( e );
	}

	@Override
	public void onButtonClick( MouseEvent e ) {
		buttonClickEventQueue.offer( e );
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

	public int getCode() {
		return code;
	}

	private final class InstanceListener extends MouseAdapter {

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

	}

}
