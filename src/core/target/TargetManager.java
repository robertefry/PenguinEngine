
package core.target;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TargetManager extends Target implements Collection<Targetable> {

	protected final Set<Targetable> targets = new HashSet<>();

	@Override
	public void update() {
		targets.forEach(target -> target.update());
	}

	@Override
	public void render() {
		targets.forEach(target -> target.render());
	}

	@Override
	public int size() {
		return targets.size();
	}

	@Override
	public boolean isEmpty() {
		return targets.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return targets.contains(o);
	}

	@Override
	public Iterator<Targetable> iterator() {
		return targets.iterator();
	}

	@Override
	public Object[] toArray() {
		return targets.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return targets.toArray(a);
	}

	@Override
	public boolean add(Targetable e) {
		return targets.add(e);
	}

	@Override
	public boolean remove(Object o) {
		return targets.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return targets.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends Targetable> c) {
		return targets.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return targets.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return targets.retainAll(c);
	}

	@Override
	public void clear() {
		targets.clear();
	}
	
}
