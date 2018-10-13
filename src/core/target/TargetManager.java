
package core.target;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TargetManager implements Targetable, Iterable<Targetable> {
	
	private final Set<Targetable> targets = new HashSet<>();
	private volatile boolean loaded = false;

	@Override
	public void update() {
		forEach(Targetable::update);
	}

	@Override
	public void render() {
		forEach(Targetable::render);
	}

	@Override
	public void load() {
		loaded = true;
		forEach(targetable -> {
			if (!targetable.isLoaded()) targetable.load();
		});
	}

	@Override
	public void unload() {
		forEach(targetable -> {
			if (targetable.isLoaded()) targetable.unload();
		});
		loaded = false;
	}
	
	@Override
	public boolean isLoaded() {
		return loaded;
	}
	
	public int size() {
		return targets.size();
	}
	
	public boolean contains(Targetable targetable) {
		return targets.contains(targetable);
	}
	
	public void add(Targetable... targetables) {
		for (Targetable targetable : targetables) targets.add(targetable);
	}
	
	public void remove(Targetable... targetables) {
		for (Targetable targetable : targetables) targets.remove(targetable);
	}

	@Override
	public Iterator<Targetable> iterator() {
		return targets.iterator();
	}
	
}
