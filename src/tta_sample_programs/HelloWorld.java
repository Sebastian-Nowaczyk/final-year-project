package tta_sample_programs;

import tta_vm_sample_components.ByteToInt;
import tta_vm_sample_components.CharOutput;
import tta_vm_sample_components.StaticByte;
import tta_vm_sample_processor.Instruction;
import tta_vm_sample_processor.LinearHarvardRead;
import tta_vm_sample_processor.SampleProcessor;

public class HelloWorld {

	public static void main(String[] args) throws Exception {
		// initialize processor
		SampleProcessor p = new SampleProcessor();
		// initialize and add components
		Integer bytenum = p.add_component(new StaticByte());
		Integer btinum = p.add_component(new ByteToInt());
		Integer charnum = p.add_component(new CharOutput());

		// connect components to sockets
		for (int i = 0; i < 256; i++) {
			if (!p.connect(i, bytenum, i))
				throw new Exception("Didn't connect the socket!");
		}
		for (int i = 0; i < 5; i++) {
			if (!p.connect(i + 256, btinum, i))
				throw new Exception("Didn't connect the socket!");
		}
		if (!p.connect(261, charnum, 0))
			throw new Exception("Didn't connect the socket!");
		// 0-255 constants, 256-259 low to high bytes in int, 260 constructed int, 261
		// ascii output

		Long inst[] = new Long[] { // make program
				// preset some bytes in bytetoint
				Instruction.make(0, 257), Instruction.make(0, 258),
				// get ascii code for H to bytetoint
				Instruction.make(72, 256),
				// trigger bytetoint
				Instruction.make(0, 259),
				// move made int to output
				Instruction.make(260, 261),
				// that was to show more complicated functional units
				// staticbyte outputs integers that are fine for this use anyway
				// e
				Instruction.make(101, 261),
				// l
				Instruction.make(108, 261),
				// l
				Instruction.make(108, 261),
				// o
				Instruction.make(111, 261),
				// space
				Instruction.make(32, 261),
				// W
				Instruction.make(87, 261),
				// o
				Instruction.make(111, 261),
				// r
				Instruction.make(114, 261),
				// l
				Instruction.make(108, 261),
				// d
				Instruction.make(100, 261),
				// new line
				Instruction.make(10, 261) };

		// initialize reader
		LinearHarvardRead h = new LinearHarvardRead(inst, p);

		// run reader
		while (true) {
			if (!h.tick())
				break;
		}

		return;
	}

}
