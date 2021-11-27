package tta_vm_sample_processor;

public class Instruction {
	public static Long make(Integer src, Integer dst) { // utility to make an instruction from 2 integers
		return (Long) (long) (int) src << 32 | dst;
	}
}
