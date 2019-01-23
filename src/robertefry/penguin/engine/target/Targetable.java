package robertefry.penguin.engine.target;

/**
 * @author Robert E Fry
 * @date 22 Jan 2019
 * @time 23:38:14
 */

public interface Targetable {
	
	default void init() {}
	
	default void dispose() {}
	
	default void update() {}
	
}
