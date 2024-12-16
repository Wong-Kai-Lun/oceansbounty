package com.example.oceansbounty;
import com.google.gson.annotations.SerializedName;

public class PatchBookingRequest {

    @SerializedName("meal")
    private String meal;

    @SerializedName("seatingArea")
    private String seatingArea;

    @SerializedName("tableSize")
    private int tableSize;

    @SerializedName("date")
    private String date;

    public PatchBookingRequest(String meal, String seatingArea, int tableSize, String date) {
        this.meal = meal;
        this.seatingArea = seatingArea;
        this.tableSize = tableSize;
        this.date = date;
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
