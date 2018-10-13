
package core.link;

import java.util.Set;

import core.Engine;
import core.link.control.LinkControl;

public class LinkedEngine extends Engine implements LinkControl {
	
	private final LinkControl linkControl = new LinkSlave();

	@Override
	public LinkControl getMaster() {
		return linkControl.getMaster();
	}

	@Override
	public void setMaster(LinkControl master) {
		linkControl.setMaster(master);
	}

	@Override
	public Set<LinkControl> getSlaves() {
		return linkControl.getSlaves();
	}

	@Override
	public void addSlave(LinkControl slave) {
		linkControl.addSlave(slave);
	}

	@Override
	public void removeSlave(LinkControl slave) {
		linkControl.removeSlave(slave);
	}

	@Override
	public void linkedstart() {
		linkControl.linkedstart();
	}

	@Override
	public void linkedstop() {
		linkControl.linkedstop();
	}

	@Override
	public void linkedinit() {
		linkControl.linkedinit();
	}

	@Override
	public void linkeddispose() {
		linkControl.linkeddispose();
	}

	@Override
	public void linkedsuspend() {
		linkControl.linkedsuspend();
	}

	@Override
	public void linkedresume() {
		linkControl.linkedresume();
	}
	
}
