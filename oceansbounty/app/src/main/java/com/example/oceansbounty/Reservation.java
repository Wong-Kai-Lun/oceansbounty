package com.example.oceansbounty;

public class Reservation {
    private int id;
    private String meal;
    private String seatingArea;
    private int tableSize;
    private String date;

    //Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public String getSeatingArea() {
        return seatingArea;
    }

    public void setSeatingArea(String seatingArea) {
        this.seatingArea = seatingArea;
    }

    public int getTableSize() {
        return tableSize;
    }

    public void setTableSize(int tableSize) {
        this.tableSize = tableSize;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
