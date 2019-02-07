
package robertefry.penguin.engine.target;

import robertefry.penguin.engine.Engine;
import robertefry.penguin.engine.core.Resetable;

/**
 * @author Robert E Fry
 * @date 22 Jan 2019
 */
public interface Targetable extends Resetable {

	public void init( Engine engine );

	public void dispose( Engine engine );

	public void tick( Engine engine );

	public void render( Engine engine );

	public void reset();

}
