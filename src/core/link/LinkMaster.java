
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
	
}
