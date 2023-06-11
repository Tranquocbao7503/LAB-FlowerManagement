package FlowerManagerment;

import Objects.Flower;
import Objects.SetFlower;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        SetFlower set = new SetFlower();
        set.addFlowerInstance(new Flower(1, "Sale", "1/1/2023", 50, "Rose"));
        set.addFlowerInstance(new Flower(2, "Sa", "2/2/2023", 60, "rose"));
        Flower flower = new Flower(2, "Sa", "2/2/2023", 80, "rose");

        for (Flower fl : set) {
            if (flower.getId() == fl.getId()) {
                fl.setUnitPrice(flower.getUnitPrice());
            }
        }
        
        set.display();

    }

}
