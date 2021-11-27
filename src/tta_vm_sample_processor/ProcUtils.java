package tta_vm_sample_processor;

import tta_vm_base.Processor;

public class ProcUtils {
	public static int addcomponent(Processor proc, Integer component, Integer start) throws Exception { // utility
																										// function
																										// filling
																										// consecutive
																										// sockets with
																										// the component
		// get processor, component number and start socket
		// return next free socket
		for (int i = 0; i < proc.get_component(component).register_amount(); i++) {
			if (!proc.connect(i + start, component, i))
				throw new Exception("Didn't connect the socket!");
		}
		return start + proc.get_component(component).register_amount();
	}
}
