package tta_vm_sample_components;

import tta_vm_base.Component;

public class Counter implements Component { // counts up when reg 1 triggered, set when reg 0 triggered (and it's an
											// output)
	public Counter() {
		super();
		for (int i = 0; i < registers; i++) {
			regs[i] = 0;
		}
		for (int i = 0; i < registers; i++) {
			triggered[i] = false;
		}
	}

	private Integer registers = 2;
	private Integer regs[] = new Integer[registers];
	private Boolean triggered[] = new Boolean[registers];

	@Override
	public Integer register_amount() {
		return registers;
	}

	@Override
	public Boolean tick() {
		if (triggered[0]) // set
			regs[0] = regs[0]; // kind of redundant but yeah
		else if (triggered[1]) // count up
			regs[0] = regs[0] + 1;
		for (int i = 0; i < registers; i++) {
			triggered[i] = false;
		}
		return true;
	}

	@Override
	public Integer read_register(Integer register_no) {
		if (!this.is_register_valid(register_no))
			return null;
		return regs[register_no];
	}

	@Override
	public Boolean set_register(Integer register_no, Integer value) {
		if (!this.is_register_valid(register_no))
			return false;
		regs[register_no] = value;
		return true;
	}

	@Override
	public Boolean set_triggered(Integer register_no) {
		if (!this.is_register_valid(register_no))
			return false;
		triggered[register_no] = true;
		return true;
	}

	@Override
	public Boolean is_register_valid(Integer register_no) {
		if ((register_no < registers) && (register_no >= 0))
			return true;
		else
			return false;
	}

	public Integer return_value() {
		return regs[0];
	}

	public Boolean set_value(Integer n) {
		regs[0] = n;
		return true;
	}

}
