package tta_sample_programs;

import java.util.Arrays;

import tta_vm_sample_components.Arithmetics;
import tta_vm_sample_components.Counter;
import tta_vm_sample_components.If0ElseBranch;
import tta_vm_sample_components.MakeInstruction;
import tta_vm_sample_components.NumberInput;
import tta_vm_sample_components.NumberOutput;
import tta_vm_sample_components.RAM;
import tta_vm_sample_components.StaticByte;
import tta_vm_sample_processor.SampleProcessor;
import tta_vm_sample_processor.Instruction;
import tta_vm_sample_processor.ProcUtils;

public class SampleVonNeumann {
	public static void main(String[] args) throws Exception {
		Integer ins[] = new Integer[2000];
		Arrays.fill(ins, 0);
		int i = 0;

		// print 1
		ins[i++] = 1;
		ins[i++] = 275;

		// print 2
		ins[i++] = 2;
		ins[i++] = 275;

		// print 3
		ins[i++] = 3;
		ins[i++] = 275;

		// get number
		ins[i++] = 0;
		ins[i++] = 274;

		// print that number
		ins[i++] = 274;
		ins[i++] = 275;

		// get number
		ins[i++] = 0;
		ins[i++] = 274;

		// request memory cell referred to by that number (overshoot will return null
		// and crash the program)
		ins[i++] = 274;
		ins[i++] = 273;

		// print contents of that cell
		ins[i++] = 273;
		ins[i++] = 275;

		// get number
		ins[i++] = 0;
		ins[i++] = 274;

		// write that number to counter (jump)
		ins[i++] = 274;
		ins[i++] = 256;

		// terminate
		ins[i++] = 0;
		ins[i++] = 0;

		// make processor and add components
		SampleProcessor p = new SampleProcessor();
		int staticbytes = p.add_component(new StaticByte());
		int count = p.add_component(new Counter());
		int ari = p.add_component(new Arithmetics());
		int ifbranch = p.add_component(new If0ElseBranch());
		int makeinstr = p.add_component(new MakeInstruction());
		int ram = p.add_component(new RAM(ins));

		int numin = p.add_component(new NumberInput());
		int numout = p.add_component(new NumberOutput());

		int n = 0;

		// 0-255 static bytes
		n = ProcUtils.addcomponent(p, staticbytes, n);
		// 256-257 counter
		n = ProcUtils.addcomponent(p, count, n);
		// 258-265 arithmetics
		n = ProcUtils.addcomponent(p, ari, n);
		// 266-268 branch
		n = ProcUtils.addcomponent(p, ifbranch, n);
		// 269-270 make instruction
		n = ProcUtils.addcomponent(p, makeinstr, n);
		// 271-273 ram
		n = ProcUtils.addcomponent(p, ram, n);
		// 274 number in
		n = ProcUtils.addcomponent(p, numin, n);
		// 275 number out
		n = ProcUtils.addcomponent(p, numout, n);

		Integer temp1, temp2, x = 0;
		Long instruction = (long) 0;
		Counter c = null;
		RAM r = null;
		while (true) {
			// assumes some kind of special circuitry for fetching instructions to not break
			// programs
			c = (Counter) p.get_component(count);
			x = c.return_value();
			r = (RAM) p.get_component(ram);
			temp1 = r.read_addr(x);
			temp2 = r.read_addr(x + 1);
			c.set_value(x + 2);
			instruction = Instruction.make(temp1, temp2);
			if (instruction == 0) // some way to stop it and moving 0 literal to itself would be meaningless
				break;
			p.run_instruction(instruction);
			p.tick();
		}
	}

}
