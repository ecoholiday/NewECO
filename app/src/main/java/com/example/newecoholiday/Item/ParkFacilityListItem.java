package com.example.newecoholiday.Item;

public class ParkFacilityListItem {
    private String parkFacilityName,ParkFacilityLatitude;
    private String parkFacilityLongitude,parkFacilitySourceType,description;
    private int parkFacilityID;
    private float trackLength,trackTime;

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
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public int getParkFacilityID() {
        return parkFacilityID;
    }

    public void setParkFacilityID(int parkFacilityID) {
        this.parkFacilityID = parkFacilityID;
    }

    public float getTrackLength() {
        return trackLength;
    }

    public void setTrackLength(float trackLength) {
        this.trackLength = trackLength;
    }

    public float getTrackTime() {
        return trackTime;
    }

    public void setTrackTime(float trackTime) {
        this.trackTime = trackTime;
    }
}