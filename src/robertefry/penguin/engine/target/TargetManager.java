
package robertefry.penguin.engine.target;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author Robert E Fry
 * @date 22 Jan 2019
 */
public class TargetManager extends Target implements Targetable, Collection<Targetable> {

	@Override
	public boolean add( Targetable arg0 ) {
		return targets.add( arg0 );
	}

	@Override
	public boolean addAll( Collection<? extends Targetable> arg0 ) {
		return targets.addAll( arg0 );
	}

	@Override
	public void clear() {
		targets.clear();
	}

	@Override
	public boolean contains( Object arg0 ) {
		return targets.contains( arg0 );
	}

	@Override
	public boolean containsAll( Collection<?> arg0 ) {
		return targets.containsAll( arg0 );
	}

	@Override
	public boolean isEmpty() {
		return targets.isEmpty();
	}

	@Override
	public Iterator<Targetable> iterator() {
		return targets.iterator();
	}

	@Override
	public boolean remove( Object arg0 ) {
		return targets.remove( arg0 );
	}

	@Override
	public boolean removeAll( Collection<?> arg0 ) {
		return targets.removeAll( arg0 );
	}

	@Override
	public boolean retainAll( Collection<?> arg0 ) {
		return targets.retainAll( arg0 );
	}

	@Override
	public int size() {
		return targets.size();
	}

	@Override
	public Object[] toArray() {
		return targets.toArray();
	}

	@Override
	public < T > T[] toArray( T[] arg0 ) {
		return targets.toArray( arg0 );
	}

}
