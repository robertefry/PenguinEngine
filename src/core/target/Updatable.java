package core.target;

import core.Engine;

@FunctionalInterface
public interface Updatable {
	
	public void update(Engine source);
	
}
