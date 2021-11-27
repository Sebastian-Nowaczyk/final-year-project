package tta_vm_base;

public interface Processor { // interface that makes processor classes interchangeable
	public Integer add_component(Component c); // adds component to internal component list, returns ID of that
												// component

	public Boolean connect(Integer socket_id, Integer component_id, Integer register_no); // connects component register
																							// to socket, false if error
																							// happened

	public Boolean disconnect(Integer socket_id); // disconnects register from given socket, false if error happened

	public Boolean is_component_valid(Integer component_id); // true if component is on the list

	public Boolean is_socket_valid(Integer socket_id); // true if socket is valid and can be used

	public Boolean is_socket_connected(Integer socket_id); // true if socket is occupied

	public Integer component_connected_to_socket(Integer socket_id); // component ID of component on the socket

	public Integer register_connected_to_socket(Integer socket_id); // register ID of component's register on the socket

	public Component get_component(Integer component_id); // get registered component

	public Boolean set_socket(Integer socket_id, Integer[] value); // set register connected to socket, false on error

	public Integer[] get_socket(Integer socket_id); // get register value from register connected to socket, null on
													// error

	public Boolean move(Integer source_socket_id, Integer destinantion_socket_id); // move value from one register to
																					// another

	public Boolean tick(); // tell processor and its components that one unit of time passed also run this
							// after instruction

	public Boolean run_instruction(long instruction); // run instruction (and possibly run a tick) (nope), 64bit
														// instruction telling

	public Boolean is_register_valid(Integer component_id, Integer register_no); // check if register of component is
																					// valid
}
