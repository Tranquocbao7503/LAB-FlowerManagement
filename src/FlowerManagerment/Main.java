package FlowerManagerment;

import Objects.Flower;
import Objects.SetFlower;

public class Main {

    public static void main(String[] args) {

        SetFlower set = new SetFlower();
        set.addFlowerInstance(new Flower(1, "Sale", "1/1/2023", 50, "Rose"));
        set.addFlowerInstance(new Flower(1, "Sa", "2/2/2023", 60, "Rose"));
        set.addFlower();
        set.display();
        set.findFlower();
        set.update();
        set.display();

    }

}
