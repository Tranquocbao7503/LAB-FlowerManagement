package FlowerManagerment;

import Objects.Detail;
import Objects.Flower;
import Objects.Order;
import Objects.OrderList;
import Objects.SetFlower;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        HashMap<String, Order> listOrderContainFLower = new HashMap<String, Order>();
        SetFlower set = new SetFlower();
        String filePath = new File("").getAbsolutePath() + "\\src\\DataWareHouse\\Flower.txt";

        if (set.loadFromFile(listOrderContainFLower, filePath)) {
            System.out.println("Loading successfully");
        }

        set.display();

//        set.addFlower(listOrderContainFLower);
        //
        //        
                OrderList orderList = new OrderList();
                orderList.addOrder(set);
                orderList.addOrder(set);
        //        orderList.addOrder(set);
        //
        //        //4
        //        set.deleteFlower();
        //
        //        //6. 
        //        orderList.displayOrderInDateRange();
        //
        //        //7 
        //        orderList.sortOrderOption();
    }
}
