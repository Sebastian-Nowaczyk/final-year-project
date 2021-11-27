/**
 * 
 */
package tta_vm_base;

/**
 * @author cmpsnowa
 *
 */
public interface Component { // interface that makes component classes interchangeable (apart from number of
								// registers)
	public Integer register_amount(); // checks number of registers, assumes continuous range from 0 to result of this
										// function - 1

	public Boolean tick(); // signals time flow in simulation has gone forward by 1 time unit, returns
							// false if problem happened

	public Integer read_register(Integer register_no); // reads register connected to a socket

	public Boolean set_register(Integer register_no, Integer value); // sets register connected to a socket

	public Boolean set_triggered(Integer register_no); // mark register as triggered by a move in

	public Boolean is_register_valid(Integer register_no); // is register_no in range?
}
