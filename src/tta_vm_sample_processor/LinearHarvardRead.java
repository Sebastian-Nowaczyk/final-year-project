package tta_vm_sample_processor;

import tta_vm_base.Processor;

public class LinearHarvardRead { // wrapper for the processor implementing a Harvard architecture
	private Long instructions[]; // assumes it's continuous
	private Integer counter = 0;
	private Processor proc;

	public LinearHarvardRead(Long[] instructions, Processor proc) {
		super();
		this.instructions = instructions;
		this.proc = proc;
	}

	public Long[] getInstructions() {
		return instructions;
	}

	public void setInstructions(Long[] instructions) {
		this.instructions = instructions;
	}

	public Integer getCounter() {
		return counter;
	}

	public void setCounter(Integer counter) {
		this.counter = counter;
	}

	public Processor getProc() {
		return proc;
	}

	public void setProc(Processor proc) {
		this.proc = proc;
	}

	public Boolean tick() { // this is a proof of concept, cannot even jump
		if (instructions.length <= counter) {
			return false;
		}
		if (!proc.run_instruction(instructions[counter]))
			return false;
		if (!proc.tick())
			return false;
		counter++;
		return true;
	}
}
