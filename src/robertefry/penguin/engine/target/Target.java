
package robertefry.penguin.engine.target;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import robertefry.penguin.engine.Engine;

/**
 * @author Robert E Fry
 * @date 22 Jan 2019
 */
public class Target implements Targetable {

	protected final Set< Targetable > targets = new HashSet<>();

	@Override
	public void init( Engine engine ) {
		targets.forEach( target -> target.init( engine ) );
	}

	@Override
	public void dispose( Engine engine ) {
		targets.forEach( target -> target.dispose( engine ) );
	}

	@Override
	public void pollInput( Engine engine ) {
		targets.forEach( target -> target.pollInput( engine ) );
	}

	@Override
	public void tick( Engine engine ) {
		targets.forEach( target -> target.tick( engine ) );
	}

	@Override
	public void render( Engine engine ) {
		targets.forEach( target -> target.render( engine ) );
	}

	@Override
	public void reset() {
		targets.forEach( target -> target.reset() );
	}

	public void addSubTarget( Targetable target ) {
		this.targets.add( target );
	}

	public < T extends Targetable > void addSubTargets( T[] targets ) {
		this.targets.addAll( Arrays.asList( targets ) );
	}

	public < T extends Targetable > void addSubTargets( Collection< T > targets ) {
		this.targets.addAll( targets );
	}

	public void removeSubTarget( Targetable target ) {
		this.targets.remove( target );
	}

	public < T extends Targetable > void removeSubTargets( T[] targets ) {
		this.targets.removeAll( Arrays.asList( targets ) );
	}

	public < T extends Targetable > void removeSubTargets( Collection< T > targets ) {
		this.targets.removeAll( targets );
	}

}
