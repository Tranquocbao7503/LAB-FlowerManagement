package Objects;

import java.util.HashSet;

public class DetailList extends HashSet<Detail> {

    public DetailList() {
        
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (Detail detail : this) {
            totalPrice += detail.cost;
        }
        return totalPrice;
    }

}
