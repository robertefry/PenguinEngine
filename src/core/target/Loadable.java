
package core.target;

import core.Engine;

public interface Loadable {
	
	public void load(Engine source);
	
	public void unload(Engine source);
	
	public boolean isLoaded();
	
}
