
package robertefry.penguin.target;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Robert E Fry
 * @date 22 Jan 2019
 */
public class Target implements Targetable {

	protected final Set< Targetable > targets = new HashSet<>();

	@Override
	public void init() {
		targets.stream().parallel().forEach( Targetable::init );
	}

	@Override
	public void dispose() {
		targets.stream().parallel().forEach( Targetable::dispose );
	}

	@Override
	public void pollInput() {
		targets.stream().parallel().forEach( Targetable::pollInput );
	}

	@Override
	public void update() {
		targets.stream().parallel().forEach( Targetable::update );
	}

	@Override
	public void render() {
		targets.stream().forEach( Targetable::render );
	}

	@Override
	public void reset() {
		targets.stream().parallel().forEach( Targetable::reset );
	}

	public Set< Targetable > getTargets() {
		return targets;
	}

}
