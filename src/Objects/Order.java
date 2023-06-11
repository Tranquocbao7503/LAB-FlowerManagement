package Objects;

import java.util.HashMap;

public class Order {

    public String idHeader;
    public String orderDate;
    public String customerName;
    public DetailList detailList;
  


    public String getIdHeader() {
        return idHeader;
    }

    public void setIdHeader(String idHeader) {
        this.idHeader = idHeader;
    }

    public String getDateOrder() {
        return orderDate;
    }

    public void setDateOrder(String dateOrder) {
        this.orderDate = dateOrder;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public DetailList getDetailList() {
        return detailList;
    }

    public void setDetailList(DetailList detailList) {
        this.detailList = detailList;
    }

    public Order() {
    }

    public Order(String idHeader, String dateOrder, String customerName) {
        this.idHeader = idHeader;
        this.orderDate = dateOrder;
        this.customerName = customerName;
    }

}
