
package extra;

public interface Permissible {
	
	public void awaitPermission() throws InterruptedException;
	
	public void grantPermission();
	
}
