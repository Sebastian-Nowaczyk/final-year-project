package tta_vm_sample_components;

import tta_vm_base.Component;
import tta_vm_sample_processor.Instruction;

public class MakeInstruction implements Component { // creates an instruction for the processor from 2 addresses

	public MakeInstruction() {
		super();
		for (int i = 0; i < registers; i++) {
			regs[i] = 0;
		}
		for (int i = 0; i < registers; i++) {
			triggered[i] = false;
		}
	}

	// 2 regs (source and destination), result accessible by processor
	private Integer registers = 2;
	private Integer regs[] = new Integer[registers];
	private Boolean triggered[] = new Boolean[registers];
	@SuppressWarnings("unused")
	private Long inst = (long) 0;

	public Long getInst() {
		return inst;
	}

	@Override
	public Integer register_amount() {
		return registers;
	}

	@Override
	public Boolean tick() {
		if (triggered[0] || triggered[1]) {// is there a new value?
			inst = Instruction.make(regs[0], regs[1]);
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
