
package robertefry.penguin.engine.render;

import robertefry.penguin.engine.Startable;
import robertefry.penguin.target.TargetManager;

/**
 * @author Robert E Fry
 * @date 19 Feb 2019
 */
public class Renderer implements Runnable, Startable {

	private final Thread thread = new Thread( this );
	private volatile boolean active = false;

	private TargetManager targetManager;
	private volatile boolean render = false;

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

	public void requestRender() {
		render = true;
	}

	@Override
	public void run() {
		while ( active ) {
			if ( render ) {
				targetManager.render();
				render = false;
			}
		}
	}

	@Override
	public boolean isActive() {
		return active;
	}

	public void setTargetManager( TargetManager targetManager ) {
		this.targetManager = targetManager;
	}

	public TargetManager getTargetManager() {
		return targetManager;
	}

}
