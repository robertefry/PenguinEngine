
package core.target;

import java.util.HashSet;
import java.util.Set;

public class Target implements Targetable {
	
	protected final Set<Targetable> targets = new HashSet<>();
	
	@Override
	public void makeRenderable() {
		targets.forEach(Targetable::makeRenderable);
	}
	
	@Override
	public void breakRenderable() {
		targets.forEach(Targetable::breakRenderable);
	}

	@Override
	public void update() {
		targets.forEach(Targetable::update);
	}

	@Override
	public void render() {
		targets.forEach(Targetable::render);
	}

	public void addSubTarget(Targetable... targets) {
		for (Targetable target : targets) this.targets.add(target);
	}

	public void removeSubTarget(Targetable... targets) {
		for (Targetable target : targets) this.targets.remove(target);
	}
	
}
