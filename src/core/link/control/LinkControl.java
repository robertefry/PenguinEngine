
package core.link.control;

import core.control.Initializable;
import core.control.Startable;
import core.control.Suspendable;
import core.link.Linkable;

public interface LinkControl extends Linkable<LinkControl>, Startable, Initializable, Suspendable, LinkedStartable, LinkedInitializable, LinkedSuspendable {

	@Override
	default void linkedstart() {
		Linkable.getTree(this).forEach(LinkControl::start);
	}

	@Override
	default void linkedstop() {
		Linkable.getTree(this).forEach(LinkControl::stop);
	}

	@Override
	default void linkedinit() {
		Linkable.getTree(this).forEach(LinkControl::init);
	}

	@Override
	default void linkeddispose() {
		Linkable.getTree(this).forEach(LinkControl::dispose);
	}

	@Override
	default void linkedsuspend() {
		Linkable.getTree(this).forEach(LinkControl::suspend);
	}

	@Override
	default void linkedresume() {
		Linkable.getTree(this).forEach(LinkControl::resume);
	}
	
}
