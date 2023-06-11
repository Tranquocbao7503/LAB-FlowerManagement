package Objects;

import Objects.Flower;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;

public class SetFlower extends HashSet<Flower> {

    public SetFlower() {

    }

    public void addFlowerInstance(Flower flower) {
        this.add(flower);
    }

    public void addFlower() {
        Flower flowerAdd = new Flower();
        Scanner box = new Scanner(System.in);
        System.out.println("Add a new flower: ");

        // input staffID
        String flowerAddID, flowerAddDescription, flowerAddImportDate, flowerAddCategory;

        double flowerAddUnitPrice;
        String formatFlowerID = "\\d{1}$";
        do {

            System.out.print("Input a new flower's ID: ");
            flowerAddID = box.nextLine();
            if (!flowerAddID.trim().matches(formatFlowerID)) {
                System.out.println("Invalid flower's ID");
                System.out.println("Try again!");

            } else if (this.isFlowerExistsByID(this, Integer.parseInt(flowerAddID))) {
                System.out.println("Error: Flower's ID is already existed!");
                System.out.println("Try Again!!!");
            } else {
                System.out.println("Valid Flower ID");
            }
        } while (!flowerAddID.trim().matches(formatFlowerID) || this.isFlowerExistsByID(this, Integer.parseInt(flowerAddID)));
        // set staffID
        flowerAdd.setId(Integer.parseInt(flowerAddID.trim()));

        //input description
        do {
            System.out.print("Input description: ");
            flowerAddDescription = box.nextLine();
            if (flowerAddDescription.trim().isEmpty()) {
                System.out.println("Invalid input. description cannot be a blank");
                System.out.println("Try again!!!");
            } else if (!(flowerAddDescription.trim().length() >= 3 && flowerAddDescription.trim().length() <= 50)) {
                System.out.println("Invalid input. description must from 3 to 50 characters");
                System.out.println("Try again!!!");
            } else {
                break;
            }
        } while (true);

        flowerAdd.setDescription(flowerAddDescription.trim());

        //input import date
        System.out.print(
                "Input import date: ");
        Date importDate = inputDate();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        flowerAddImportDate = formatter.format(importDate);

        flowerAdd.setImportDate(flowerAddImportDate);

        // input unit price
        do {
            System.out.print("Input unit price: ");
            flowerAddUnitPrice = Double.parseDouble(box.nextLine());
            if (flowerAddUnitPrice <= 0) {
                System.out.println("Invalid input. Unit price must be a positive number");
                System.out.println("Try again!!!");
            } else {
                break;
            }
        } while (true);
        flowerAdd.setUnitPrice(flowerAddUnitPrice);

        // input catergory
        do {
            System.out.print("Input catergory: ");
            flowerAddCategory = box.nextLine();
            if (flowerAddCategory.trim().isEmpty()) {
                System.out.println("Invalid catergory. Catergory cannot be a blank");
                System.out.println("Try again!!!");
            } else {
                break;
            }
        } while (true);
        flowerAdd.setCategory(flowerAddCategory);

        this.add(flowerAdd);

    }

    public void display() {
        for (Flower fl : this) {
            System.out.println(fl.toString());
        }
    }

    public boolean isFlowerExistsByID(HashSet<Flower> flowerSet, int targetId) {
        for (Flower flower : flowerSet) {
            if (flower.getId() == targetId) {
                return true;
            }
        }
        return false;
    }

    public boolean isFlowerExistsByName(HashSet<Flower> flowerSet, String name) {
        for (Flower flower : flowerSet) {
            if (flower.getCategory().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public Date inputDate() {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yy");
        dateFormat.setLenient(false);

        Date date = null;
        boolean isValidInput = false;

        do {
            System.out.print("\nEnter a date (dd/mm/yy): ");
            String userInput = scanner.nextLine();

            try {
                date = dateFormat.parse(userInput);
                isValidInput = true;
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter a date in the format dd/mm/yy");
            }
        } while (!isValidInput);
        return date;
    }

    // find a flower
    public Flower findFlowerById(HashSet<Flower> flowerSet, int targetId) {

        System.out.println("Flower found with ID '" + targetId + "':");
        for (Flower flower : flowerSet) {
            if (flower.getId() == targetId) {
                System.out.println(flower.toString());

                return flower;
            }
        }

        return null;

    }

    public Flower findFlowerByName(HashSet<Flower> flowerSet, String name) {
        int count = 0;
        System.out.println("Flower found with name '" + name + "':");
        for (Flower flower : flowerSet) {
            if (flower.getCategory().equalsIgnoreCase(name)) {
                System.out.println(flower.toString());
                count++;
                return flower;
            }
        }
        if (count == 0) {
            System.out.println("Flower with name '" + name + "' not found.");

        }
        return null;
    }

    public void findFlower() {
        Scanner box = new Scanner(System.in);

        int option = 0;

        do {
            System.out.println("Which option: ");
            System.out.println("1 - Finding by ID");
            System.out.println("2 - Finding by Name");
            try {
                System.out.print("Choose your option:  ");
                option = Integer.parseInt(box.nextLine());

            } catch (Exception e) {
                System.out.println("Invalid input");
                System.out.println("Try again!!");
            }

        } while (option != 1 && option != 2);

        if (option == 1) {
            System.out.print("Input a flower ID: ");
            int targetID = Integer.parseInt(box.next());

            Flower flowerCheck = findFlowerById(this, targetID);
            if (flowerCheck != null) {
                System.out.println(flowerCheck.toString());
            } else {
                System.out.println("Flower found with ID '" + targetID + "':");
            }
        }

        if (option == 2) {
            System.out.print("Input a flower name: ");
            String nameContain = box.next();
            //findFlowerByName(this, name);

            int count = 0;

            System.out.println("Flower found with name '" + nameContain + "':");
            for (Flower flower : this) {
                if (flower.getCategory().toLowerCase().contains(nameContain.toLowerCase())) {
                    System.out.println(flower.toString());
                    count++;

                }
            }
            if (count == 0) {
                System.out.println("Flower with name '" + nameContain + "' not found.");

            }
        }
    }

    // update
    public boolean update() {
        Scanner box = new Scanner(System.in);
        String flowerAddID, flowerAddDescription, flowerAddImportDate, flowerAddCategory;
        double flowerAddUnitPrice;

        String[] attributes = {"description", "import date", "unit price", "Catergory"};

        String searchFlower;
        System.out.println("\t\t\t\tUpdate Program");

        System.out.print("Input flower's name to update:  ");
        do {

            searchFlower = box.nextLine();
            if (searchFlower.trim().isEmpty()) {
                System.out.println("Invalid name. Name cannot be a blank");
                System.out.println("Try again!!!");
            } else {
                break;
            }
        } while (true);

        try {
            if (isFlowerExistsByName(this, searchFlower)) {
                Flower flowerUpdate = findFlowerByName(this, searchFlower);

                // update description
                if (chooseYesOrNo(attributes[0])) {

                    do {
                        System.out.print("Update description: ");
                        flowerAddDescription = box.nextLine();
                        if (flowerAddDescription.trim().isEmpty()) {
                            System.out.println("Invalid input. description cannot be a blank");
                            System.out.println("Try again!!!");
                        } else if (!(flowerAddDescription.trim().length() >= 3 && flowerAddDescription.trim().length() <= 50)) {
                            System.out.println("Invalid input. description must from 3 to 50 characters");
                            System.out.println("Try again!!!");
                        } else {
                            break;
                        }
                    } while (true);

                    flowerUpdate.setDescription(flowerAddDescription.trim());
                }

                if (chooseYesOrNo(attributes[1])) {
                    System.out.print("Update import date: ");
                    Date importDate = inputDate();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    flowerAddImportDate = formatter.format(importDate);

                    // update import date
                    flowerUpdate.setImportDate(flowerAddImportDate);
                }
                // input unit price
                if (chooseYesOrNo(attributes[2])) {
                    do {
                        System.out.print("Update unit price: ");
                        String input = box.nextLine();
                        try {
                            flowerAddUnitPrice = Double.parseDouble(input);
                            if (flowerAddUnitPrice <= 0) {
                                System.out.println("Invalid input. Unit price must be a positive number");
                                System.out.println("Try again!!!");
                            } else {
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid number");
                            System.out.println("Try again!!!");
                        }
                    } while (true);
                    flowerUpdate.setUnitPrice(flowerAddUnitPrice);
                }

                if (chooseYesOrNo(attributes[3])) {
                    // input catergory
                    do {
                        System.out.print("Update catergory: ");
                        flowerAddCategory = box.nextLine();
                        if (flowerAddCategory.trim().isEmpty()) {
                            System.out.println("Invalid catergory. Catergory cannot be a blank");
                            System.out.println("Try again!!!");
                        } else {
                            break;
                        }
                    } while (true);
                    flowerUpdate.setCategory(flowerAddCategory);

                }
                return true;
            } else {
                System.out.println("The flower does not exist");

            }
        } catch (Exception e) {
            e.getStackTrace();
            return false;
        }
        this.display();
        return true;

    }

    public boolean chooseYesOrNo(String attribute) {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        do {
            System.out.print("Do you want to edit this flower's " + attribute + " (y/n): ");
            userInput = scanner.nextLine().toLowerCase();
        } while (!userInput.equals("y") && !userInput.equals("n"));

        return userInput.equals("y");
    }

}
