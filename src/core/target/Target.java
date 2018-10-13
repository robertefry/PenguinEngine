
package core.target;

public class Target implements Targetable {
	
	private volatile boolean loaded = false;

	@Override
	public void load() {
		loaded = true;
	}

	@Override
	public void unload() {
		loaded = false;
	}

	@Override
	public boolean isLoaded() {
		return loaded;
	}

	@Override
	public void update() {}

	@Override
	public void render() {}
	
}
