
package robertefry.penguin.target;

import java.util.Collection;
import java.util.Iterator;

/**
 * @author Robert E Fry
 * @date 22 Jan 2019
 */
public class TargetManager extends Target implements Collection< Targetable > {

	@Override
	public boolean add( Targetable arg0 ) {
		if ( isInitialized() && !arg0.isInitialized() ) arg0.init();
		return targets.add( arg0 );
	}

	@Override
	public boolean addAll( Collection< ? extends Targetable > arg0 ) {
		if ( isInitialized() ) targets.stream().parallel().forEach( target -> {
			if ( !target.isInitialized() ) target.init();
		} );
		return targets.addAll( arg0 );
	}

	@Override
	public void clear() {
		targets.stream().parallel().forEach( target -> {
			if ( target.isInitialized() ) target.dispose();
		} );
		targets.clear();
	}

	@Override
	public boolean contains( Object arg0 ) {
		return targets.contains( arg0 );
	}

	@Override
	public boolean containsAll( Collection< ? > arg0 ) {
		return targets.containsAll( arg0 );
	}

	@Override
	public boolean isEmpty() {
		return targets.isEmpty();
	}

	@Override
	public Iterator< Targetable > iterator() {
		return targets.iterator();
	}

	@Override
	public boolean remove( Object arg0 ) {
		( (Targetable)arg0 ).dispose();
		return targets.remove( arg0 );
	}

	@Override
	public boolean removeAll( Collection< ? > arg0 ) {
		arg0.stream().parallel()
			.filter( elem -> elem instanceof Targetable )
			.map( elem -> (Targetable)elem )
			.forEach( Targetable::dispose );
		return targets.removeAll( arg0 );
	}

	@Override
	public boolean retainAll( Collection< ? > arg0 ) {
		targets.stream().parallel()
			.filter( target -> !arg0.contains( target ) )
			.forEach( Targetable::dispose );
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
