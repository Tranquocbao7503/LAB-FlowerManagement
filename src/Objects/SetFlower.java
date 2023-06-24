package Objects;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.sound.midi.Soundbank;
import org.omg.CORBA.portable.InputStream;

public class SetFlower extends HashSet<Flower> {

    public SetFlower() {

    }

    public void addFlowerInstance(Flower flower) {
        this.add(flower);
    }

    public void addFlower(HashMap<String, Order> listOrderContainTheFlower) {
        Flower flowerAdd = new Flower();
        Scanner box = new Scanner(System.in);
        System.out.println("Add a new flower: ");

        // input staffID
        String flowerAddID = "", flowerAddDescription, flowerAddImportDate, flowerAddCategory;

        double flowerAddUnitPrice = 0;
        String formatFlowerID = "\\d+$";
        do {
            try {

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
            } catch (NumberFormatException e) {
                e.getStackTrace();
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
            try {
                flowerAddUnitPrice = Double.parseDouble(box.nextLine());

            } catch (Exception e) {
                System.out.println("Invalid input");
                System.out.println("Try again");
                e.getStackTrace();
            }
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

        // input the list order contain the flower
        flowerAdd.setListOrderContainTheFlower(listOrderContainTheFlower);
        this.add(flowerAdd);

        System.out.println("Adding successfullly");

    }

    public void display() {
        for (Flower fl : this) {
            System.out.println(fl.toString());
        }
    }

    public void displayToOrder() {
        for (Flower fl : this) {
            System.out.println(fl.listToPick());
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

    // find a flower
    public Flower findFlowerById(HashSet<Flower> flowerSet, int targetId) {

        for (Flower flower : flowerSet) {
            if (flower.getId() == targetId) {
                System.out.println(flower.listToPick());

                return flower;
            }
        }

        return null;

    }

    public Flower findFlowerByName(HashSet<Flower> flowerSet, String name) {
        int count = 0;

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
            if (option != 1 && option != 2) {
                System.out.println("Input is out of valid option");
                System.err.println("Try again");
            }
        } while (option != 1 && option != 2);

        int targetID = 0;
        if (option == 1) {
            System.out.print("Input a flower ID: ");
            try {
                targetID = Integer.parseInt(box.next());

            } catch (Exception e) {
                System.out.println("ID must be a positive number");
                System.out.println("Try again");
                e.getStackTrace();
            }

            Flower flowerCheck = findFlowerById(this, targetID);
            if (flowerCheck == null) {

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

    public void deleteFlower() {
        Scanner box = new Scanner(System.in);
        System.out.println("\t\t\tDelete Program");
        this.display();
        int deleteID;

        while (true) {
            try {
                System.out.print("Input a flower's ID to delete: ");
                deleteID = Integer.parseInt(box.nextLine());
                break;
            } catch (Exception e) {
                e.getStackTrace();
                System.out.println("Invalid input");
                System.out.println("Try again");

            }
        }

        Flower deleteFlower = findFlowerById(this, deleteID);
        if (deleteFlower != null) {

            if (!isExistBeInDetails(deleteFlower)) {
                if (confirmDelete()) {
                    this.remove(deleteFlower);
                    System.out.println("Deleting flower ID: " + deleteID + " successfully!");

                } else {
                    System.out.println("Deleting flower ID: " + deleteID + " unsuccessfully!");
                }

            } else {
                System.out.println("Deleting flower ID: " + deleteID + " unsuccessfully!");
                System.out.println("Due to existing in order details");

            }

        } else {
            System.out.println("Flower ID doesn't exist");
        }
    }

    public boolean isExistBeInDetails(Flower flower) {
        if (flower.listOrderContainTheFlower.isEmpty() || flower.listOrderContainTheFlower == null) {
            return false;

        }
        return true;
    }

    public boolean confirmDelete() {
        Scanner box = new Scanner(System.in);
        String confirmOption;
        do {
            System.out.print("Are you sure to delete this nurse(y/n): ");
            confirmOption = box.nextLine().toLowerCase();
        } while (!confirmOption.equals("y") && !confirmOption.equals("n"));
        return confirmOption.equals("y");
    }

    public boolean loadFromFile(HashMap<String, Order> listOrderContainTheFlower, String filename) {
        File f = new File(filename);

        if (!f.isFile()) {
            System.out.println("File doesn't exist");
            return false;
        } else {

            try {
                FileInputStream fileInputStream = new FileInputStream(filename);
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();

                while (line != null) {
                    StringTokenizer tokenizer = new StringTokenizer(line, ",");

                    int flowerId = Integer.parseInt(tokenizer.nextToken().trim());
                    String description = tokenizer.nextToken().trim();
                    String importDate = tokenizer.nextToken().trim();
                    double unitPrice = Double.parseDouble(tokenizer.nextToken().trim());
                    String category = tokenizer.nextToken().trim();

                    Flower flower = new Flower(flowerId, description, importDate, unitPrice, category, listOrderContainTheFlower);
                    this.add(flower);
                    line = reader.readLine();
                }
                reader.close();
                fileInputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
    }

    public void saveFlowerToBinaryFile(String fileName) {

        List<String> data = new ArrayList<>();

        for (Flower flower : this) {
            flower.toString();
        }
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(data);
            System.out.println("Data saved to " + fileName + " successfully.");
        } catch (IOException e) {
            System.out.println("Error occurred while saving data to " + fileName + ": " + e.getMessage());
        }
    }

}
