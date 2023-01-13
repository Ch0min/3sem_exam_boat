package dtos;

import entities.Boat;
import entities.Owner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BoatDTO {
    private int boatID;
    private String boatBrand;
    private String boatMake;
    private String boatName;
    private String boatImage;
    private HarbourDTO harbour;
    private List<OwnerDTO> owners = new ArrayList<>();

    public BoatDTO(Boat boat){
        if(boat.getBoatID() != null){
            this.boatID = boat.getBoatID();
        }
        this.boatBrand = boat.getBoatBrand();
        this.boatMake = boat.getBoatMake();
        this.boatName = boat.getBoatName();
        this.boatImage = boat.getBoatImage();
        this.harbour = new HarbourDTO(boat.getHarbour());
        if (boat.getOwners().size() > 0) {
            boat.getOwners().forEach((owner -> {
                owners.add(new OwnerDTO(owner));
            }));
        }
    }

    public Boat getEntity() {
        Boat boat = new Boat();
        if (this.boatID > 0) {
            boat.setBoatID(this.boatID);
        }
        boat.setBoatBrand(this.boatBrand);
        boat.setBoatMake(this.boatMake);
        boat.setBoatName(this.boatName);
        boat.setBoatImage(this.boatImage);
        boat.setHarbour(this.harbour.getEntity());
        if(this.owners != null){
            List<Owner> ownersList = new ArrayList<>();
            this.owners.forEach(ownerDTO -> ownersList.add(ownerDTO.getEntity()));
            boat.setOwners(ownersList);
        }

        return boat;
    }

    public static List<BoatDTO> getBoatDTOs(List<Boat> boats){
        List<BoatDTO> boatDTOs = new ArrayList<>();
        boats.forEach(boat -> boatDTOs.add(new BoatDTO(boat)));
        return boatDTOs;
    }

    public int getBoatID() {
        return boatID;
    }

    public void setBoatID(int boatID) {
        this.boatID = boatID;
    }

    public String getBoatBrand() {
        return boatBrand;
    }

    public void setBoatBrand(String boatBrand) {
        this.boatBrand = boatBrand;
    }

    public String getBoatMake() {
        return boatMake;
    }

    public void setBoatMake(String boatMake) {
        this.boatMake = boatMake;
    }

    public String getBoatName() {
        return boatName;
    }

    public void setBoatName(String boatName) {
        this.boatName = boatName;
    }

    public String getBoatImage() {
        return boatImage;
    }

    public void setBoatImage(String boatImage) {
        this.boatImage = boatImage;
    }

    public HarbourDTO getHarbour() {
        return harbour;
    }

    public void setHarbour(HarbourDTO harbour) {
        this.harbour = harbour;
    }

    public List<OwnerDTO> getOwners() {
        return owners;
    }

    public void setOwners(List<OwnerDTO> owners) {
        this.owners = owners;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BoatDTO)) return false;
        BoatDTO boatDTO = (BoatDTO) o;
        return getBoatID() == boatDTO.getBoatID();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBoatID());
    }

    @Override
    public String toString() {
        return "BoatDTO{" +
                "boatID=" + boatID +
                ", boatBrand='" + boatBrand + '\'' +
                ", boatMake='" + boatMake + '\'' +
                ", boatName='" + boatName + '\'' +
                ", boatImage=" + boatImage +
                ", harbour=" + harbour +
                ", owners=" + owners +
                '}';
    }
}
