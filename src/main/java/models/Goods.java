package models;

public class Goods {
    private String name;
    private int quantity;
    private double price;
    private String temperature;
    private double sizeWeight;
    private String photoUrl;

    public Goods(String name, int quantity, double price, String temperature, double sizeWeight, String photoUrl) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.temperature = temperature;
        this.sizeWeight = sizeWeight;
        this.photoUrl = photoUrl;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public String getTemperature() {
        return temperature;
    }

    public double getSizeWeight() {
        return sizeWeight;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setSizeWeight(double sizeWeight) {
        this.sizeWeight = sizeWeight;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public double totalPrice() {
        return price * quantity;
    }

    public boolean removeQuantity(int value) {
        if (value <= 0) {
            return false;
        }
        if (value >= this.quantity) {
            quantity = 0;
            return true;
        }
        else  {
            quantity -= value;
            return true;
        }
    }

    @Override
    public String toString() {
        return "Goods [name=" + name + ", quantity=" + quantity + ", price=" + price  + ", temperature=" + temperature + ", sizeWeight=" + sizeWeight  + "ml/g, photoUrl=" + photoUrl + "]";
    }

}

