
package core.engines;

public class EngineNames {
	
	public static int idcounter = 0;
	
	public static String generateName() {
		return new String("engine-" + idcounter++);
	}
	
}
