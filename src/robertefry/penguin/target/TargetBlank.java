
package robertefry.penguin.target;

import java.awt.Graphics;

/**
 * @author Robert E Fry
 * @date 7 Feb 2019
 */
public class TargetBlank implements Targetable {

	private boolean initialized = false;

	@Override
	public void init() {
		initialized = true;
	}

	@Override
	public void dispose() {
		initialized = false;
	}

	@Override
	public void pollInput() {
	}

	@Override
	public void update() {
	}

	@Override
	public void render( Graphics g ) {
	}

	@Override
	public void reset() {
	}

	@Override
	public boolean isInitialized() {
		return initialized;
	}

}
