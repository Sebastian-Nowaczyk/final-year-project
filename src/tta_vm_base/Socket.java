package tta_vm_base;

// deprecated when debugging, needlessly but oh well, like I want to change all that code back
/*
public class Socket {
	public Socket(Integer component_id, Integer register_id) {
		super();
		this.component_id = component_id;
		this.register_id = register_id;
	}
	public Integer getComponent_id() {
		return component_id;
	}
	public void setComponent_id(Integer component_id) {
		this.component_id = component_id;
	}
	public Integer getRegister_id() {
		return register_id;
	}
	public void setRegister_id(Integer register_id) {
		this.register_id = register_id;
	}
	public Integer component_id;
	public Integer register_id;
}
*/

public class Socket {
	public static Integer[] make(Integer cid, Integer rid) {
		return new Integer[] { cid, rid };
	}
}