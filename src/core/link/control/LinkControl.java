
package core.link.control;

import core.control.CoreControllable;
import core.link.Linkable;

public interface LinkControl extends Linkable<LinkControl>, CoreControllable, LinkedStartable, LinkedInitializable, LinkedSuspendable {
	
}
