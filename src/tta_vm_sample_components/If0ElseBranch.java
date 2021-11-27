package tta_vm_sample_components;

import tta_vm_base.Component;

public class If0ElseBranch implements Component { // add conditional values then add value to be tested (trigger), gives
													// appropriate value

	public If0ElseBranch() {
		super();
		for (int i = 0; i < registers; i++) {
			regs[i] = 0;
		}
		for (int i = 0; i < registers; i++) {
			triggered[i] = false;
		}
	}

	// value in/out + value if 0 + value else
	private Integer registers = 3;
	private Integer regs[] = new Integer[registers];
	private Boolean triggered[] = new Boolean[registers];

	@Override
	public Integer register_amount() {
		return registers;
	}

	@Override
	public Boolean tick() {
		if (triggered[0]) {// is there a value
			if (regs[0] == 0)
				regs[0] = regs[1];
			else
				regs[0] = regs[2];
		}
		for (int i = 0; i < registers; i++) {
			triggered[i] = false;
		}
		return true;
	}

	@Override
	public Integer read_register(Integer register_no) {
		if (this.is_register_valid(register_no)) {
			return regs[register_no];
		}
		return null;
	}

	@Override
	public Boolean set_register(Integer register_no, Integer value) {
		if (this.is_register_valid(register_no)) {
			regs[register_no] = value;
			return true;
		}
		return false;
	}

	@Override
	public Boolean set_triggered(Integer register_no) {
		if (this.is_register_valid(register_no)) {
			triggered[register_no] = true;
			return true;
		}
		return false;
	}

	@Override
	public Boolean is_register_valid(Integer register_no) {
		if ((register_no < registers) && (register_no >= 0))
			return true;
		return false;
	}

}
