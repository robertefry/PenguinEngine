
package core.link;

import java.util.HashSet;
import java.util.Set;

import core.control.CoreControl;
import core.link.control.LinkControl;

public class LinkSlave extends CoreControl implements LinkControl {
	
	private LinkControl master = this;
	private final Set<LinkControl> slaves = new HashSet<>();

	@Override
	public void linkedstart() {
		Linkable.getTree(this).forEach(LinkControl::start);
	}

	@Override
	public void linkedstop() {
		Linkable.getTree(this).forEach(LinkControl::stop);
	}

	@Override
	public void linkedinit() {
		Linkable.getTree(this).forEach(LinkControl::init);
	}

	@Override
	public void linkeddispose() {
		Linkable.getTree(this).forEach(LinkControl::dispose);
	}

	@Override
	public void linkedsuspend() {
		Linkable.getTree(this).forEach(LinkControl::suspend);
	}

	@Override
	public void linkedresume() {
		Linkable.getTree(this).forEach(LinkControl::resume);
	}

	@Override
	public LinkControl getMaster() {
		return master;
	}

	@Override
	public void setMaster(LinkControl master) {
		this.master = master;
	}

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
	
}
