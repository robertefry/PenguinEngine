
package core.target;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import core.Engine;

public class Target implements Targetable, Iterable<Targetable> {
	
	protected final Set<Targetable> targets = new HashSet<>();
	protected volatile boolean created = false, loaded = false;
	
	@Override
	public void create() {
		created = true;
	}
	
	@Override
	public void destroy() {
		created = false;
	}
	
	@Override
	public boolean isCreated() {
		return created;
	}

	@Override
	public void load(Engine source) {
		loaded = true;
		forEach(targetable -> {
			if (!targetable.isLoaded()) targetable.load(source);
		});
	}

	@Override
	public void unload(Engine source) {
		forEach(targetable -> {
			if (targetable.isLoaded()) targetable.unload(source);
		});
		loaded = false;
	}

	@Override
	public boolean isLoaded() {
		return loaded;
	}

	@Override
	public void update(Engine source) {
		forEach(targetable -> targetable.update(source));
	}

	@Override
	public void render(Engine source) {
		forEach(targetable -> targetable.render(source));
	}
	
	public void addSubTargets(Targetable... targetables) {
		for (Targetable targetable : targetables) targets.add(targetable);
	}
	
	public void removeSubTargets(Targetable... targetables) {
		for (Targetable targetable : targetables) targets.remove(targetable);
	}

	@Override
	public Iterator<Targetable> iterator() {
		return targets.iterator();
	}
	
}
