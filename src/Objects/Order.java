package Objects;

import java.util.HashMap;

public class Order {

    public String idHeader;
    public String orderDate;
    public String customerName;
    public DetailList detailList;
    private double totalOrderPrice;

    public double getTotalOrderPrice() {
        return totalOrderPrice;
    }

    public void setTotalOrderPrice(double totalOrderPrice) {
        this.totalOrderPrice = totalOrderPrice;
    }

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
        this.idHeader = "";
        this.orderDate = "";
        this.customerName = "";
        this.detailList = null;
        this.totalOrderPrice = 0;
    }

    public Order(String idHeader, String orderDate, String customerName, DetailList detailList, double totalOrderPrice) {
        this.idHeader = idHeader;
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.detailList = detailList;
        this.totalOrderPrice = totalOrderPrice;
    }

    public int flowerCount() {
        int flowerCount = 0;

        for (Detail temporaryDatail : this.detailList) {
            flowerCount += temporaryDatail.quantity;
        }
        return flowerCount;
    }

    public double orderTotal() {
        double orderTotal = 0;
        for (Detail temporaryDatail : this.detailList) {
            orderTotal += temporaryDatail.cost;
        }
        return orderTotal;
    }

    public void info() {

        System.out.printf("%s       %s     %-8s   %-10d      $ %-13.2f \n", this.idHeader, this.orderDate, this.customerName, flowerCount(), orderTotal());
    }

    @Override
    public String toString() {
        return idHeader + "," + orderDate + "," + customerName + "," + detailList + "," + totalOrderPrice + ",";
    }

}
