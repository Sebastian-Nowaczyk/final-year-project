package tta_vm_sample_components;

import tta_vm_base.Component;

public class RAM implements Component { // ram, initialized at instantiation

	public RAM(Integer[] memory) {
		super();
		for (int i = 0; i < registers; i++) {
			regs[i] = 0;
		}
		for (int i = 0; i < registers; i++) {
			triggered[i] = false;
		}
		this.memory = memory;
	}

	private Integer[] memory;
	// write value + addr write + addr read/output = 3
	private Integer registers = 3;
	private Integer regs[] = new Integer[registers];
	private Boolean triggered[] = new Boolean[registers];

	@Override
	public Integer register_amount() {
		return registers;
	}

	@Override
	public Boolean tick() {
		if (triggered[1]) {// write request
			if (memory.length > regs[1])
				memory[regs[1]] = regs[0];
			else
				regs[0] = null;
		} else if (triggered[2]) {// read request
			if (memory.length > regs[2]) {
				int x = regs[2];
				int y = memory[x];
				regs[2] = y;
			} else
				regs[2] = null;
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

	public Integer read_addr(Integer addr) {
		if (memory.length > addr)
			return memory[addr];
		else
			return null;
	}

}
