package com.example.newecoholiday.Item;

public class ParkFacilityListItem {
    private String parkFacilityName;
    private String ParkFacilityLatitude;
    private String parkFacilityLongitude;
    private String parkFacilitySourceType;
    private int parkFacilityID;

    public String getParkFacilitySourceType() {
        return parkFacilitySourceType;
    }

    public void setParkFacilitySourceType(String parkFacilitySourceType) {
        this.parkFacilitySourceType = parkFacilitySourceType;
    }

    public String getParkFacilityLatitude() {
        return ParkFacilityLatitude;
    }

    public void setParkFacilityLatitude(String parkFacilityLatitude) {
        ParkFacilityLatitude = parkFacilityLatitude;
    }

    public String getParkFacilityLongitude() {
        return parkFacilityLongitude;
    }

    public void setParkFacilityLongitude(String parkFacilityLongitude) {
        this.parkFacilityLongitude = parkFacilityLongitude;
    }

    public String getParkFacilityName() {
        return parkFacilityName;
    }

    public void setParkFacilityName(String parkFacilityName) {
        this.parkFacilityName = parkFacilityName;
    }

    public int getParkFacilityID() {
        return parkFacilityID;
    }

    public void setParkFacilityID(int parkFacilityID) {
        this.parkFacilityID = parkFacilityID;
    }
}