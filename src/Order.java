/*
    Assigment 1 - sdcCOSC603Assign1 - Class Name Order
    This class will contain the code to represent an Order.
    Author: Rodrigo Farinango - SDC - ID#: 000482153
 */
import java.io.Serializable;

public class Order implements Serializable {
    private String orderNumber;

    public Order(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public String toString() {
        return getOrderNumber();
    }
}
