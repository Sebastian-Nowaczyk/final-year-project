package tta_vm_sample_components;

import tta_vm_base.Component;

public class ByteToInt implements Component { // 4 bytes to int
	public ByteToInt() {
		super();
		for (int i = 0; i < registers; i++) {
			regs[i] = 0;
		}
		for (int i = 0; i < registers; i++) {
			triggered[i] = false;
		}
	}

	// 4 input regs (lowest to highest), trigger on highest + output
	private Integer registers = 5;
	private Integer regs[] = new Integer[registers];
	private Boolean triggered[] = new Boolean[registers];

	@Override
	public Integer register_amount() {
		return registers;
	}

	@Override
	public Boolean tick() {
		if (triggered[3]) { // highest byte
			regs[4] = regs[0] | regs[1] << 8 | regs[2] << 16 | regs[3] << 24;
		}
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
