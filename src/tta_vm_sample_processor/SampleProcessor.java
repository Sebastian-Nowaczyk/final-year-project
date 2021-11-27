package tta_vm_sample_processor;

import tta_vm_base.Component;
import tta_vm_base.Processor;

public class SampleProcessor implements Processor { // sample processor, 1 bus with 10 000 sockets (modifiable,
													// technical maximum in max of int) and max 1000 components (again
													// technical limit as-is is max of int)
	private static Integer max_components = 1000;
	private static Integer max_sockets = 10000;
	private Component component_array[] = new Component[max_components];
	private Integer[][] bus = new Integer[max_sockets][2]; // 0 is component, 1 is register
	private Integer top_component = 0;

	@Override
	public Integer add_component(Component c) {
		component_array[top_component] = c;
		top_component++;
		return top_component - 1;
	}

	@Override
	public Boolean is_register_valid(Integer component_id, Integer register_no) {
		if (!this.is_component_valid(component_id))
			return false;
		return component_array[component_id].is_register_valid(register_no);
	}

	@Override
	public Boolean connect(Integer socket_id, Integer component_id, Integer register_no) {
		if (!this.is_register_valid(component_id, register_no))
			return false;
		bus[socket_id][0] = component_id;
		bus[socket_id][1] = register_no;
		return true;
	}

	@Override
	public Boolean disconnect(Integer socket_id) {
		bus[socket_id][0] = null;
		bus[socket_id][1] = null;
		return true;
	}

	@Override
	public Boolean is_component_valid(Integer component_id) {
		if (component_id < top_component)
			return true;
		else
			return false;
	}

	@Override
	public Boolean is_socket_valid(Integer socket_id) {
		if (max_sockets > socket_id)
			return true;
		else
			return false;
	}

	@Override
	public Boolean is_socket_connected(Integer socket_id) {
		if (bus[socket_id][0] == null || bus[socket_id][1] == null)
			return false;
		else
			return true;
	}

	@Override
	public Integer component_connected_to_socket(Integer socket_id) {
		if (!this.is_socket_connected(socket_id))
			return null;
		else
			return bus[socket_id][0];
	}

	@Override
	public Integer register_connected_to_socket(Integer socket_id) {
		if (!this.is_socket_connected(socket_id))
			return null;
		else
			return bus[socket_id][1];
	}

	@Override
	public Component get_component(Integer component_id) {
		if (!this.is_component_valid(component_id))
			return null;
		else
			return component_array[component_id];
	}

	@Override
	public Boolean set_socket(Integer socket_id, Integer[] value) {
		if (!this.is_socket_valid(socket_id))
			return false;
		bus[socket_id] = value;
		return true;

	}

	@Override
	public Integer[] get_socket(Integer socket_id) {
		if (!this.is_socket_valid(socket_id))
			return null;
		return bus[socket_id];
	}

	public Component get_component_from_socket(Integer socket_id) {
		if (!this.is_socket_connected(socket_id))
			return null;
		return this.get_component(bus[socket_id][0]);
	}

	@Override
	public Boolean move(Integer source_socket_id, Integer destinantion_socket_id) { // every instruction lands here
		// if(!this.is_register_valid(bus[source_socket_id].getComponent_id(),
		// bus[source_socket_id].getRegister_id()))
		// return false;
		// if(!this.is_register_valid(bus[destinantion_socket_id].getComponent_id(),
		// bus[destinantion_socket_id].getRegister_id()))
		// return false;
		Component src = this.get_component(bus[source_socket_id][0]);
		Integer readval = src.read_register(bus[source_socket_id][1]);
		Component dst = this.get_component(bus[destinantion_socket_id][0]);
		Integer destreg = bus[destinantion_socket_id][1];
		dst.set_register(destreg, readval);
		dst.set_triggered(destreg);
		return true;
	}

	@Override
	public Boolean tick() { // signifies time passing for the components
		for (int i = 0; i < top_component; i++) {
			component_array[i].tick();
		}
		return true;
	}

	@Override
	public Boolean run_instruction(long instruction) { // decode and run part of the processor
		int src_addr = (int) (instruction >> 32);
		int dest_addr = (int) instruction;
		return this.move((Integer) src_addr, (Integer) dest_addr);
	}

}
