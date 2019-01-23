
package engine.target;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Robert E Fry
 * @date 22 Jan 2019
 * @time 23:38:14
 */

public class Target implements Targetable {

	protected final Set<Targetable> targets = new HashSet<>();
	
	@Override
	public void init() {
		targets.forEach(Targetable::init);
	}
	
	@Override
	public void dispose() {
		targets.forEach(Targetable::dispose);
	}

	@Override
	public void update() {
		targets.forEach(Targetable::update);
	}

	public void addSubTarget(Targetable... targets) {
		for (Targetable target : targets) this.targets.add(target);
	}

	public void removeSubTarget(Targetable... targets) {
		for (Targetable target : targets) this.targets.remove(target);
	}
	
}
