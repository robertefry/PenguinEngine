
package robertefry.penguin.engine.target;

import java.util.HashSet;
import java.util.Set;

import robertefry.penguin.engine.Engine;

/**
 * @author Robert E Fry
 * @date 22 Jan 2019
 */
public class Target implements Targetable {

	protected final Set<Targetable> targets = new HashSet<>();

	@Override
	public void init(Engine engine) {
		Targetable.super.init(engine);
		targets.forEach(target -> target.init(engine));
	}

	@Override
	public void dispose(Engine engine) {
		Targetable.super.dispose(engine);
		targets.forEach(target -> target.dispose(engine));
	}

	@Override
	public void tick(Engine engine) {
		Targetable.super.tick(engine);
		targets.forEach(target -> target.tick(engine));
	}

	@Override
	public void render(Engine engine) {
		Targetable.super.render(engine);
		targets.forEach(target -> target.render(engine));
	}

	public void addSubTarget(Targetable... targets) {
		for (Targetable target : targets)
			this.targets.add(target);
	}

	public void removeSubTarget(Targetable... targets) {
		for (Targetable target : targets)
			this.targets.remove(target);
	}

}
