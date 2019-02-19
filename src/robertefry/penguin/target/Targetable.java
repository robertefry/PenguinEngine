
package robertefry.penguin.target;

import robertefry.penguin.engine.Resetable;
import robertefry.penguin.target.api.Initializable;
import robertefry.penguin.target.api.Inputable;
import robertefry.penguin.target.api.Renderable;
import robertefry.penguin.target.api.Updatable;

/**
 * @author Robert E Fry
 * @date 22 Jan 2019
 */
public interface Targetable extends Initializable, Inputable, Updatable, Renderable, Resetable {

}
