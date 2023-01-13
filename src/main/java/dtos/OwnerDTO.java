package dtos;

import entities.Owner;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OwnerDTO {
    private int ownerID;
    private String ownerName;
    private String ownerAddress;
    private int ownerPhone;
    private UserDTO user;
    private List<String> boats;

    public OwnerDTO(Owner owner){
        if(owner.getOwnerID() != null){
            this.ownerID = owner.getOwnerID();
        }
        this.ownerName = owner.getOwnerName();
        this.ownerAddress = owner.getOwnerAddress();
        this.ownerPhone = owner.getOwnerPhone();
        this.user = new UserDTO(owner.getUser());
        this.boats = owner.getBoatsAsStrings();
    }

    public Owner getEntity() {
        Owner owner = new Owner();
        if (this.ownerID > 0) {
            owner.setOwnerID(this.ownerID);
        }
        owner.setOwnerName(this.ownerName);
        owner.setOwnerAddress(this.ownerAddress);
        owner.setOwnerPhone(this.ownerPhone);
        owner.setUser(this.user.getEntity());
        owner.getBoatsAsStrings();

        return owner;
    }

    public static List<OwnerDTO> getOwnerDTOs(List<Owner> owners){
        List<OwnerDTO> ownerDTOs = new ArrayList<>();
        owners.forEach(owner -> ownerDTOs.add(new OwnerDTO(owner)));
        return ownerDTOs;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerAddress() {
        return ownerAddress;
    }

    public void setOwnerAddress(String ownerAddress) {
        this.ownerAddress = ownerAddress;
    }

    public int getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(int ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
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
        if (!(o instanceof OwnerDTO)) return false;
        OwnerDTO ownerDTO = (OwnerDTO) o;
        return getOwnerID() == ownerDTO.getOwnerID();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOwnerID());
    }

    @Override
    public String toString() {
        return "OwnerDTO{" +
                "ownerID=" + ownerID +
                ", ownerName='" + ownerName + '\'' +
                ", ownerAddress='" + ownerAddress + '\'' +
                ", ownerPhone=" + ownerPhone +
                ", userDTO=" + user +
                ", boats=" + boats +
                '}';
    }
}
