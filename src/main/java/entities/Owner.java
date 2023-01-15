package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQuery(name = "Owner.deleteAllRows", query = "DELETE from Owner")
@Table(name = "owner")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_id", nullable = false)
    private Integer OwnerID;

    @Column(name = "owner_name", nullable = false)
    private String ownerName;

    @Column(name = "owner_address", nullable = false)
    private String ownerAddress;

    @Column(name = "owner_phone", nullable = false)
    private Integer ownerPhone;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="user_name", referencedColumnName = "user_name")
    private User user;

    @ManyToMany
    @JoinTable(name = "owner_boat",
            joinColumns = @JoinColumn(name = "owner_id"),
            inverseJoinColumns = @JoinColumn(name = "boat_id"))
    private List<Boat> boats = new ArrayList<>();

    public Owner() {
    }

    public Owner(String ownerName, String ownerAddress, Integer ownerPhone, User user) {
        this.ownerName = ownerName;
        this.ownerAddress = ownerAddress;
        this.ownerPhone = ownerPhone;
        this.user = user;
    }

    public List<String> getBoatsAsStrings(){
        if(boats.isEmpty()) {
            return null;
        }
        List<String> boatsAsStrings = new ArrayList<>();
        boats.forEach((b ->{
            boatsAsStrings.add(b.getBoatBrand());
            boatsAsStrings.add(b.getBoatMake());
            boatsAsStrings.add(b.getBoatName());
            boatsAsStrings.add(String.valueOf(b.getBoatImage()));
            boatsAsStrings.add(String.valueOf(b.getHarbour().getHarbourID()));
        }));
        return boatsAsStrings;
    }

    public Integer getOwnerID() {
        return OwnerID;
    }

    public void setOwnerID(Integer ownerID) {
        OwnerID = ownerID;
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

    public Integer getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(Integer ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Boat> getBoats() {
        return boats;
    }

    public void setBoats(List<Boat> boats) {
        this.boats = boats;
    }

    public void addBoat(Boat ownerBoat) {
        boats.add(ownerBoat);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Owner)) return false;
        Owner owner = (Owner) o;
        return OwnerID.equals(owner.OwnerID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(OwnerID);
    }

    @Override
    public String toString() {
        return "Owner{" +
                "OwnerID=" + OwnerID +
                ", ownerName='" + ownerName + '\'' +
                ", ownerAddress='" + ownerAddress + '\'' +
                ", ownerPhone='" + ownerPhone + '\'' +
                ", user=" + user +
                ", boats=" + boats +
                '}';
    }
}
