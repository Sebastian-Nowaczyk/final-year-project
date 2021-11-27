package tta_vm_sample_components;

import tta_vm_base.Component;

public class StaticByte implements Component { // return a byte
	private Integer registers = 256;
	private Integer regs[] = new Integer[registers];
	private Boolean triggered[] = new Boolean[registers];

	@Override
	public Integer register_amount() {
		return registers;
	}

	@Override
	public Boolean tick() {
		for (int i = 0; i < registers; i++) {
			if (triggered[i] == true) {
				regs[i] = i; // just in case
				triggered[i] = false;
				break;
			}
		}
		return true;
	}

	public StaticByte() {
		super();
		for (int i = 0; i < registers; i++) {
			regs[i] = i;
		}
		for (int i = 0; i < registers; i++) {
			triggered[i] = false;
		}

	}

	@Override
	public Integer read_register(Integer register_no) {
		if (!this.is_register_valid(register_no))
			return null;
		return regs[register_no];
	}

	@Override
	public Boolean set_register(Integer register_no, Integer value) { // ignores writes silently
		if (!this.is_register_valid(register_no))
			return false;
		// regs[register_no] = value;
		return true;
	}

	@Override
	public Boolean set_triggered(Integer register_no) { // ignores triggers (writes do nothing anyway)
		if (!this.is_register_valid(register_no))
			return false;
		//triggered[register_no] = true;
		return true;
	}

	@Override
	public Boolean is_register_valid(Integer register_no) {
		if ((register_no < registers) && (register_no >= 0))
			return true;
		else
			return false;
	}
}
