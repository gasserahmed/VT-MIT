package business.order;

import business.cart.ShoppingCart;
import business.customer.CustomerForm;

/**
 * An order form arrives as JSON from the client and
 * is de-serialized into an instance of this class.
 */
public class OrderForm {

	private ShoppingCart cart;
	private CustomerForm customerForm;

	public OrderForm() {
	}

	public ShoppingCart getCart() {
		return cart;
	}

	public void setCart(ShoppingCart cart) {
		this.cart = cart;
	}

	public CustomerForm getCustomerForm() {
		return customerForm;
	}

	public void setCustomerForm(CustomerForm customerForm) {
		this.customerForm = customerForm;
	}
}
