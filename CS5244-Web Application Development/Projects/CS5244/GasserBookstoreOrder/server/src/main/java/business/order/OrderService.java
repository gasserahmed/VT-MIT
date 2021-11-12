package business.order;

import business.cart.ShoppingCart;
import business.customer.CustomerForm;

public interface OrderService {

    long placeOrder(CustomerForm form, ShoppingCart cart);

    OrderDetails getOrderDetails(long orderId);

}
