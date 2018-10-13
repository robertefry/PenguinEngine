
package core.link;

import java.util.HashSet;
import java.util.Set;

public interface Linkable<T extends Linkable<T>> {
	
	public T getMaster();
	
	public void setMaster(T master);
	
	public Set<T> getSlaves();
	
	public void addSlave(T slave);
	
	public void removeSlave(T slave);
	
	default T getSuperMaster() {
		T supermaster = getMaster();
		while (supermaster.getMaster() != supermaster) {
			supermaster = supermaster.getMaster();
		}
		return supermaster;
	}
	
	default boolean isMaster() {
		return getMaster() == this;
	}
	
	default boolean isSuperMaster() {
		return getSuperMaster() == this;
	}
	
	default boolean isSlave() {
		return getSlaves().contains(this);
	}
	
	default boolean isInSlaveTree() {
		return isSlave() || getSlaveTree().contains(this);
	}
	
	default void addSlaves(Set<T> slaves) {
		slaves.forEach(slave -> addSlave(slave));
	}
	
	default void addSlaves(T[] slaves) {
		for (T slave : slaves) addSlave(slave);
	}
	
	default Set<T> getSlaveTree() {
		Set<T> slavetree = new HashSet<>();
		getSlaves().forEach(slave -> {
			slavetree.add(slave);
			slavetree.addAll(slave.getSlaveTree());
		});
		return slavetree;
	}
	
	public static <T extends Linkable<T>> Set<T> getTree(T t) {
		Set<T> tree = t.getSlaveTree();
		tree.add(t);
		return tree;
	}
	
}
