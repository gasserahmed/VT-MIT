package business.order;

public class LineItem {

    private long bookId;
    private long orderId;
    private int quantity;

    public LineItem(long bookId, long orderId, int quantity) {
        this.bookId = bookId;
        this.orderId = orderId;
        this.quantity = quantity;
    }

    public long getBookId() {
        return bookId;
    }

    public long getOrderId() {
        return orderId;
    }

    public int getQuantity() { return quantity; }

    @Override
    public String toString() {
        return "LineItem{" +
                "bookId=" + bookId +
                ", orderId=" + orderId +
                ", quantity=" + quantity +
                '}';
    }
}