package FlowerManagerment;

import Objects.Detail;
import Objects.Flower;
import Objects.Order;
import Objects.OrderList;
import Objects.SetFlower;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner box = new Scanner(System.in);
        HashMap<String, Order> listOrderContainFLower = new HashMap<String, Order>();
        SetFlower setFLower = new SetFlower();
        OrderList orderList = new OrderList();

        String filePathLoadFlower = new File("").getAbsolutePath() + "\\src\\DataWareHouse\\Flower.txt";
        String filePathSaveFlower = new File("").getAbsolutePath() + "\\src\\DataWareHouse\\Flower.dat";
        String filePathSaveOrder = new File("").getAbsolutePath() + "\\src\\DataWareHouse\\Order.dat";

        if (setFLower.loadFromFile(listOrderContainFLower, filePathLoadFlower)) {
            System.out.println("Loading successfully");
        }

        ArrayList<String> optionStatement = new ArrayList<String>();
        optionStatement.add("Add a flower");
        optionStatement.add("Find the flower");
        optionStatement.add("Update the flower");
        optionStatement.add("Delete the flower");
        optionStatement.add("Add a order");
        optionStatement.add("Display orders");
        optionStatement.add("Sort orders");
        optionStatement.add("Save data");
        optionStatement.add("Load data");
        optionStatement.add("Exit program");
        int option;
        System.out.println("List of current FLower");
        setFLower.display();

        String confirmation = "";
        do {
            System.out.println("************************* Flower manager program **************************");
            option = MenuFlowerChoice.getChoice(optionStatement);

            switch (option) {
                case 1:

                    setFLower.addFlower(listOrderContainFLower);
                    setFLower.display();
                    break;
                case 2:
                    setFLower.findFlower();
                    break;
                case 3:
                    setFLower.update();
                    System.out.println("\t\t\tAfter updating:");
                    setFLower.display();
                    break;
                case 4:
                    setFLower.deleteFlower();
                    break;
                case 5:
                    orderList.addOrder(setFLower);
                    break;
                case 6:
                    orderList.displayOrderInDateRange();
                    break;
                case 7:

                    orderList.sortOrderOption();

                    break;
                case 8:
                    setFLower.saveFlowerToBinaryFile(filePathSaveFlower);
                    orderList.saveOrderToBinaryFile(filePathSaveOrder);
                    break;
                case 9:

                    if (setFLower.loadFromFile(listOrderContainFLower, filePathLoadFlower)) {
                        System.out.println("Load data successfully");
                    } else {
                        System.out.println("Failed to load file");
                    }

                    break;
                case 10:
                    do {
                        System.out.print("Are you sure to quite this program(y/n): ");
                        confirmation = box.nextLine().toLowerCase();
                    } while (!confirmation.equals("n") && !confirmation.equals("y"));
                    setFLower.saveFlowerToBinaryFile(filePathSaveFlower);
                    orderList.saveOrderToBinaryFile(filePathSaveOrder);
                    break;

                default:
                    System.out.println("Please enter your options in range 1-10");
            }
        } while (option != 10 || (confirmation.equalsIgnoreCase("n") && option == 10) || option < 0 || option > 10);

    }
}
