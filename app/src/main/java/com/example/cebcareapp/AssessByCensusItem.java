package com.example.cebcareapp;

public class AssessByCensusItem {
    private int quantity;
    private float hoursADay;
    private boolean selected ;
    private String id ;
    private String appliance ;
    private float unitsPerHour ;

    // Constructors
    public AssessByCensusItem(){}

    public AssessByCensusItem(int quantity, float hoursADay, boolean selected, String id, String appliance, float unitsPerHour) {
        this.quantity = quantity;
        this.hoursADay = hoursADay;
        this.selected = selected;
        this.id = id;
        this.appliance = appliance;
        this.unitsPerHour = unitsPerHour;
    }

    // Getters and setters
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getHoursADay() {
        return hoursADay;
    }
    public void setHoursADay(float hoursADay) {
        this.hoursADay = hoursADay;
    }

    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getAppliance() {
        return appliance;
    }
    public void setAppliance(String appliance) {
        this.appliance = appliance;
    }

    public float getUnitsPerHour() { return unitsPerHour; }
    public void setUnitsPerHour(float unitsPerHour) { this.unitsPerHour = unitsPerHour; }
}
