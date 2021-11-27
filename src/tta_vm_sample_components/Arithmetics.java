package tta_vm_sample_components;

import tta_vm_base.Component;

public class Arithmetics implements Component { // maths part
	public Arithmetics() {
		super();
		for (int i = 0; i < registers; i++) {
			regs[i] = 0;
		}
		for (int i = 0; i < registers; i++) {
			triggered[i] = false;
		}
	}

	// result + operand + add + subtract + multiply + divide + shift left + shift
	// right = 8 registers
	private Integer registers = 8;
	private Integer regs[] = new Integer[registers];
	private Boolean triggered[] = new Boolean[registers];

	@Override
	public Integer register_amount() {
		return registers;
	}

	@Override
	public Boolean tick() {
		if (triggered[2]) // add
			regs[0] = regs[1] + regs[2];
		else if (triggered[3]) // subtract
			regs[0] = regs[1] - regs[3];
		else if (triggered[4]) // multiply
			regs[0] = regs[1] * regs[4];
		else if (triggered[5]) // divide
			regs[0] = regs[1] / regs[5];
		else if (triggered[6]) // shift left
			regs[0] = regs[6] << 1;
		else if (triggered[7]) // shift right
			regs[0] = regs[7] >> 1;
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

}
