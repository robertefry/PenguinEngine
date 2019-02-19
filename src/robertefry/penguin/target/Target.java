
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
		targets.parallelStream().forEach( Targetable::init );
	}

	@Override
	public void dispose() {
		targets.parallelStream().forEach( Targetable::dispose );
	}

	@Override
	public void pollInput() {
		targets.parallelStream().forEach( Targetable::pollInput );
	}

	@Override
	public void update() {
		targets.parallelStream().forEach( Targetable::update );
	}

	@Override
	public void render() {
		targets.parallelStream().forEach( Targetable::render );
	}

	@Override
	public void reset() {
		targets.parallelStream().forEach( Targetable::reset );
	}

	public Set< Targetable > getTargets() {
		return targets;
	}

}
