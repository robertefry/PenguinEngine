
package robertefry.penguin.target.api;

import java.awt.Graphics;

/**
 * @author Robert E Fry
 * @date 16 Feb 2019
 */
@FunctionalInterface
public interface Renderable {

	public void render( Graphics g );

}
