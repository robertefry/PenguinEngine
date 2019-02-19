
package robertefry.penguin.engine.render;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import robertefry.penguin.engine.Startable;

/**
 * @author Robert E Fry
 * @date 19 Feb 2019
 */
public class Renderer implements Runnable, Startable {

	private final Thread thread = new Thread( this );
	private volatile boolean active = false;

	private final Queue< Runnable > tasks = new ConcurrentLinkedQueue<>();

	public Renderer() {
		thread.setDaemon( true );
	}

	@Override
	public void start() {
		if ( !isActive() && !thread.isAlive() ) {
			active = true;
			thread.start();
		}
	}

	@Override
	public void stop() {
		active = false;
	}

	@Override
	public void run() {
		while ( active ) {
			if ( !tasks.isEmpty() ) tasks.poll().run();
		}
	}

	public void enqueue( Runnable task ) {
		this.tasks.offer( task );
	}

	@Override
	public boolean isActive() {
		return active;
	}

}
