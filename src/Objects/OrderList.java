package Objects;

import FlowerManagerment.MenuFlowerChoice;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.Source;

public class OrderList extends ArrayList<Order> {

    public OrderList() {
    }

    public void addOrder(SetFlower flowerList) {
        Scanner box = new Scanner(System.in);
        Order orderAdd = new Order();
        String orderIdAdd, orderDateAdd, orderCustomerNameAdd;

        // input order ID
        String orderIdForm = "^\\d{4}";
        do {

            System.out.print("Input a new order'sID (Order's ID must be in this form '####'):  ");
            orderIdAdd = box.nextLine();
            if (orderIdAdd.endsWith("0000")) {
                System.out.println("Invalid ID");
                System.out.println("Try again");
            }
            if (!orderIdAdd.trim().matches(orderIdForm)) {
                System.out.println("Invalid Order's ID");
                System.out.println("Try again!");

            } else if (this.isOrderExist(orderIdAdd)) {
                System.out.println("Error: Order's ID is already existed!");
                System.out.println("Try Again!!!");
            } else {
                System.out.println("Valid Order ID");
            }
        } while (!orderIdAdd.trim().matches(orderIdForm) || this.isOrderExist(orderIdAdd) || orderIdAdd.endsWith("0000"));
        orderAdd.setIdHeader(orderIdAdd.trim());

        // input customer name
        do {
            System.out.print("Input customer name: ");
            orderCustomerNameAdd = box.nextLine();
            if (orderCustomerNameAdd.trim().isEmpty()) {
                System.out.println("Invalid customer name. Name cannot be a blank");
                System.out.println("Try again!!!");

            } else {
                break;
            }
        } while (true);
        orderAdd.setCustomerName(orderCustomerNameAdd);

        // input order date
        System.out.print("Input order date: ");
        Date orderDate = inputDate();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        orderDateAdd = formatter.format(orderDate);
        orderAdd.setDateOrder(orderDateAdd);

        // set total price
        DetailList newDetailList = new DetailList();
        double total = getTotalOrderPrice(flowerList, newDetailList);
        orderAdd.setTotalOrderPrice(total);

        orderAdd.detailList = newDetailList;
        this.add(orderAdd);

//        ArrayList<String> flowerOption = getFLowerMenu(flowerList);
//        int choice;
        // - display set of flower để người mua chọn loại hoa, và chốt số lượng mỗi loại
        // - sau đó khởi tạo đối tượng Detail và set giá trị
        // - khi người dùng chọn option ko mua nữa thì tính tổng tiền và set total price vào order
    }

    private double getTotalOrderPrice(SetFlower flowerList, DetailList detailList) {
        Scanner box = new Scanner(System.in);
        System.out.println("\t\t\tFlower Categories");
        flowerList.display();
        int choice;
        int detailID = 0;
        boolean option = true;
        int quantityOfFLower;
        double totalPriceOfOrder = 0;
        do {
            while (true) {
                System.out.print("Type a flower ID that you want to buy: ");
                try {
                    choice = Integer.parseInt(box.nextLine());
                    break;
                } catch (Exception e) {
                    e.getStackTrace();
                    System.out.println("Invalid ID");
                    System.out.println("Try again");
                }
            }
            if (flowerList.isFlowerExistsByID(flowerList, choice)) {

                if (checkExistFLowerType(choice, detailList)) {

                    for (Detail detail : detailList) {
                        if (choice == detail.flower.getId()) {

                            while (true) {
                                System.out.print("Input quantity: ");
                                try {
                                    quantityOfFLower = Integer.parseInt(box.nextLine());
                                    break;
                                } catch (Exception e) {
                                    e.getStackTrace();
                                    System.out.println("Invalid input");
                                    System.out.println("Try again");
                                }
                            }
                            detail.setCost(detail.getQuantity() + quantityOfFLower, detail.flower.getUnitPrice());

                        } else {
                            continue;
                        }
                    }
                    option = wantBuyMore();
                } else {
                    Detail flowersInAType = new Detail();

                    detailID++;

                    flowersInAType.setDetailID(detailID);
                    flowersInAType.flower = flowerList.findFlowerById(flowerList, choice);
                    flowersInAType.flower.beInDetails.put(detailID, flowersInAType);

                    quantityOfFLower = 0;
                    while (true) {
                        System.out.print("Input quantity: ");
                        try {
                            quantityOfFLower = Integer.parseInt(box.nextLine());
                            break;
                        } catch (Exception e) {
                            e.getStackTrace();
                            System.out.println("Invalid input");
                            System.out.println("Try again");
                        }
                    }
                    flowersInAType.setQuantity(quantityOfFLower);
                    flowersInAType.setCost(quantityOfFLower, flowersInAType.flower.getUnitPrice());

                    detailList.add(flowersInAType);

                    option = wantBuyMore();
                }

            } else {
                System.out.println("Invalid flower ID");
                System.out.println("Try again");
            }

        } while (!flowerList.isFlowerExistsByID(flowerList, choice) || option == true);

        for (Detail detail : detailList) {
            totalPriceOfOrder += detail.cost;
        }

        return totalPriceOfOrder;
    }

    private boolean isValidDate(int year, int month, int day) {
        // Check if the given year, month, and day form a valid date
        if (year < 1 || month < 0 || month > 11 || day < 1) {
            return false;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setLenient(false);
        calendar.set(year, month, 1); // Set the day to 1 to ensure month validity
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        return day <= maxDay;
    }

    public boolean checkExistFLowerType(int choice, DetailList detailList) {
        if (detailList.isEmpty() || detailList == null) {
            return false;
        }
        for (Detail detail : detailList) {
            if (detail.flower.getId() == choice) {
                return true;
            }
        }
        return false;
    }

    public boolean wantBuyMore() {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        do {
            System.out.print("Do you want to buy more flower (y/n): ");
            userInput = scanner.nextLine().toLowerCase();
        } while (!userInput.equals("y") && !userInput.equals("n"));

        return userInput.equals("y");
    }

    public ArrayList<String> getFLowerMenu(SetFlower flowerList) {
        ArrayList<String> flowerOption = new ArrayList<String>();
        for (Flower flowerType : flowerList) {
            flowerOption.add(flowerType.getCategory());
        }
        flowerOption.add("Exit");

        return flowerOption;
    }

    public boolean isOrderExist(String orderIdCheck) {
        if (this.isEmpty()) {
            return false;
        } else {
            for (Order order : this) {
                if (order.getIdHeader().equalsIgnoreCase(orderIdCheck)) {
                    return true;
                }
            }
            return false;
        }
    }

    public Date inputDate() {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        dateFormat.setLenient(false);

        Date date = null;
        boolean isValidInput = false;

        do {
            System.out.print("\nEnter a date (dd/MM/yy): ");
            String userInput = scanner.nextLine();

            try {
                date = dateFormat.parse(userInput);
                isValidInput = true;

                // Check for additional validation
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                if (!isValidDate(year, month, day)) {
                    isValidInput = false;
                    System.out.println("Invalid date. Please enter a valid date.");
                }
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter a valid date");
            }
        } while (!isValidInput);
        return date;
    }

    public void displayOrderInDateRange(OrderList orderList) {

        Date beginDate, endDate;
        System.out.println("Require a START date and END date");
        do {
            System.out.print("START");
            beginDate = inputDate();
            System.out.print("END");
            endDate = inputDate();

            if (beginDate.after(endDate)) {
                System.out.println("Invalid range of date. Start date cannot be after end date");
                System.out.println("Try again");

            } else {
                break;
            }
        } while (true);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        Date dateConverted = null;
        int count = 0;

        System.out.printf("%-9s  %-11s  %-12s  %-13s  %-11s%n", "Order Id", "Order Date", "Customer", "Flower Count", "Order Total");
        System.out.println("---------------------------------------------------------------");
        for (Order temporaryOrder : orderList) {

            try {
                dateConverted = dateFormat.parse(temporaryOrder.getOrderDate());

            } catch (ParseException ex) {
                ex.getStackTrace();
            }

            if (dateConverted.after(beginDate) && dateConverted.before(endDate)) {
                temporaryOrder.info();;
                count++;
            }
        }

        if (count == 0) {
            System.out.println("There is no any order");
        }
    }

//    public void sortPatientOption() {
//        Scanner box = new Scanner(System.in);
//
//        int optionField = 0;
//        int optionOrder = 0;
//
//        do {
//            System.out.println("Which field: ");
//            System.out.println("1 - Sorting by ID");
//            System.out.println("2 - Sorting by Date");
//            System.out.println("3 - Sorting by Name");
//            System.out.println("4 - Sorting by Total Price");
//
//            try {
//                System.out.print("Choose your option:  ");
//                optionField = Integer.parseInt(box.nextLine());
//
//            } catch (Exception e) {
//                System.out.println("Invalid input");
//                System.out.println("Try again!!");
//            }
//
//        } while (optionField > 4 || optionField < 1);
//
//        do {
//            System.out.println("Which order: ");
//            System.out.println("1 - Sorting asending in order");
//            System.out.println("2 - Sorting descending in order");
//            try {
//                System.out.print("Choose your option:  ");
//                optionOrder = Integer.parseInt(box.nextLine());
//
//            } catch (Exception e) {
//                System.out.println("Invalid input");
//                System.out.println("Try again!!");
//            }
//
//        } while (optionOrder != 1 && optionOrder != 2);
//
//        if (optionField == 1 && optionOrder == 1) {
//            sortByIDASC();
//            System.out.println("After sorting: ");
//
//        }
//        if (optionField == 1 && optionOrder == 2) {
//            sortByIdDESC();
//            System.out.println("After sorting: ");
//
//        }
//        if (optionField == 2 && optionOrder == 1) {
//            sortByNameASC();
//            System.out.println("After sorting: ");
//
//        }
//        if (optionField == 2 && optionOrder == 2) {
//            sortByNameDESC();
//            System.out.println("After sorting: ");
//            displayAllPatients();
//        }
//    }
}
