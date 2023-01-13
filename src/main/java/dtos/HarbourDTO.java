package dtos;

import entities.Boat;
import entities.Harbour;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HarbourDTO {
    private int harbourID;
    private String harbourName;
    private String harbourAddress;
    private int harbourCapacity;
    private List<String> boats;

    public HarbourDTO(Harbour harbour) {
        if (harbour.getHarbourID() != null) {
            this.harbourID = harbour.getHarbourID();
        }
        this.harbourName = harbour.getHarbourName();
        this.harbourAddress = harbour.getHarbourAddress();
        this.harbourCapacity = harbour.getHarbourCapacity();
        this.boats = harbour.getBoatsAsStrings();
    }

    public Harbour getEntity() {
        Harbour harbour = new Harbour();
        if (this.harbourID != 0) {
            harbour.setHarbourID(this.harbourID);
        }
        harbour.setHarbourName(this.harbourName);
        harbour.setHarbourAddress(this.harbourAddress);
        harbour.setHarbourCapacity(this.harbourCapacity);
        harbour.getBoatsAsStrings();

        return harbour;
    }

    public int getHarbourID() {
        return harbourID;
    }

    public void setHarbourID(int harbourID) {
        this.harbourID = harbourID;
    }

    public String getHarbourName() {
        return harbourName;
    }

    public void setHarbourName(String harbourName) {
        this.harbourName = harbourName;
    }

    public String getHarbourAddress() {
        return harbourAddress;
    }

    public void setHarbourAddress(String harbourAddress) {
        this.harbourAddress = harbourAddress;
    }

    public int getHarbourCapacity() {
        return harbourCapacity;
    }

    public void setHarbourCapacity(int harbourCapacity) {
        this.harbourCapacity = harbourCapacity;
    }

    public List<String> getBoats() {
        return boats;
    }

    public void setBoats(List<String> boats) {
        this.boats = boats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HarbourDTO)) return false;
        HarbourDTO that = (HarbourDTO) o;
        return getHarbourID() == that.getHarbourID();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHarbourID());
    }

    @Override
    public String toString() {
        return "HarbourDTO{" +
                "harbourID=" + harbourID +
                ", harbourName='" + harbourName + '\'' +
                ", harbourAddress='" + harbourAddress + '\'' +
                ", harbourCapacity=" + harbourCapacity +
                ", boatDTOList=" + boats +
                '}';
    }
}
