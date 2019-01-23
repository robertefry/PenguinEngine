
package core.target;

import java.util.Collection;
import java.util.Iterator;

import core.Engine;

public class TargetManager extends Target implements Collection<Targetable> {

	private Engine renderEngine;
	
	public Engine getRenderEngine() {
		return renderEngine;
	}
	
	public void setRenderEngine(Engine renderEngine) {
		this.renderEngine = renderEngine;
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
