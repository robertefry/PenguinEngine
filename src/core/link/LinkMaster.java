
package core.link;

import java.util.HashSet;
import java.util.Set;

import core.control.CoreControl;
import core.link.control.LinkControl;

public class LinkMaster extends CoreControl implements LinkControl {
	
	private final Set<LinkControl> slaves = new HashSet<>();

	@Override
	public LinkControl getMaster() {
		return this;
	}

	@Override
	public void setMaster(LinkControl master) {}

	@Override
	public Set<LinkControl> getSlaves() {
		return slaves;
	}

	@Override
	public void addSlave(LinkControl slave) {
		slave.setMaster(this);
		slaves.add(slave);
	}

	@Override
	public void removeSlave(LinkControl slave) {
		slaves.remove(slave);
		slave.setMaster(slave);
	}

	@Override
	public void linkedstart() {
		Linkable.getTree(getSuperMaster()).forEach(LinkControl::start);
	}

	@Override
	public void linkedstop() {
		Linkable.getTree(getSuperMaster()).forEach(LinkControl::stop);
	}

	@Override
	public void linkedinit() {
		Linkable.getTree(getSuperMaster()).forEach(LinkControl::init);
	}

	@Override
	public void linkeddispose() {
		Linkable.getTree(getSuperMaster()).forEach(LinkControl::dispose);
	}

	@Override
	public void linkedsuspend() {
		Linkable.getTree(getSuperMaster()).forEach(LinkControl::suspend);
	}

	@Override
	public void linkedresume() {
		Linkable.getTree(getSuperMaster()).forEach(LinkControl::resume);
	}
	
}
