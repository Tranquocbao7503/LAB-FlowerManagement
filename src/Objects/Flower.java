package Objects;

import java.util.HashMap;

import java.util.Objects;

public class Flower {

    private int id;
    private String description;
    private String importDate;
    private double unitPrice;
    private String category;
    public HashMap<Integer, Detail> beInDetails;

    Flower() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImportDate() {
        return importDate;
    }

    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Flower(int id, String description, String importDate, double unitPrice, String category, HashMap<Integer, Detail> beInDetails) {
        this.id = id;
        this.description = description;
        this.importDate = importDate;
        this.unitPrice = unitPrice;
        this.category = category;
        this.beInDetails = beInDetails;
    }

    

   

    @Override
    public String toString() {
        return "<" + this.id + "," + this.description + "," + this.importDate + "," + this.unitPrice + "," + this.category + ">";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Flower)) {
            return false;
        }
        Flower flower = (Flower) o;
        return id == flower.id && Objects.equals(category, flower.category);
    }

}
