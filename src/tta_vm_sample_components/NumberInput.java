package tta_vm_sample_components;

import java.util.Scanner;

import tta_vm_base.Component;

public class NumberInput implements Component { // input a number from console
	public NumberInput() {
		super();
		for (int i = 0; i < registers; i++) {
			regs[i] = 0;
		}
		for (int i = 0; i < registers; i++) {
			triggered[i] = false;
		}
	}

	// value&trigger = 1 register
	private Integer registers = 1;
	private Integer regs[] = new Integer[registers];
	private Boolean triggered[] = new Boolean[registers];

	final Scanner console = new Scanner(System.in);

	@Override
	public Integer register_amount() {
		return registers;
	}

	@Override
	public Boolean tick() {
		if (triggered[0]) {// is a value requested?
			System.out.print("Input a number for input component: ");
			regs[0] = console.nextInt(); // hangs the simulator
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
