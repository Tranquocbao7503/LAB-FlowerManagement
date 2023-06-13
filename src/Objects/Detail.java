package Objects;

import java.util.HashMap;
import java.util.Objects;

public class Detail {

    public int detailID;
    public Flower flower;
    public int quantity;
    public double cost;

    public Detail() {
    }

    public int getDetailID() {
        return detailID;
    }

    public void setDetailID(int detailID) {
        this.detailID = detailID;
    }

    public Flower getFlower() {
        return flower;
    }

    public void setFlower(Flower flower) {
        this.flower = flower;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(int quantity, double unitPrice) {
        this.cost = quantity * unitPrice;
    }

    @Override
    public int hashCode() {
        return Objects.hash(detailID);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Detail)) {
            return false;
        }
        Detail detail = (Detail) obj;
        return detailID == detail.detailID;
    }

}
