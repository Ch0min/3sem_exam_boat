package entities;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQuery(name = "Boat.deleteAllRows", query = "DELETE from Boat")
@Table(name = "boat")
public class Boat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "boat_id", nullable = false)
    private Integer boatID;

    @Column(name = "boat_brand", nullable = false)
    private String boatBrand;

    @Column(name = "boat_make", nullable = false)
    private String boatMake;

    @Column(name = "boat_name", nullable = false)
    private String boatName;

    @Column(name = "boat_image", nullable = false)
    private String boatImage;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "harbour_id", nullable = false)
    private Harbour harbour;

    @ManyToMany
    @JoinTable(name = "owner_boat",
            joinColumns = @JoinColumn(name = "boat_id"),
            inverseJoinColumns = @JoinColumn(name = "owner_id"))
    private List<Owner> owners = new ArrayList<>();


    public Boat() {
    }

    public Boat(String boatBrand, String boatMake, String boatName, String boatImage, Harbour harbour) {
        this.boatBrand = boatBrand;
        this.boatMake = boatMake;
        this.boatName = boatName;
        this.boatImage = boatImage;
        this.harbour = harbour;
    }

    public Boat(Integer boatID, String boatBrand, String boatMake, String boatName, String boatImage, Harbour harbour) {
        this.boatID = boatID;
        this.boatBrand = boatBrand;
        this.boatMake = boatMake;
        this.boatName = boatName;
        this.boatImage = boatImage;
        this.harbour = harbour;
    }

    public Integer getBoatID() {
        return boatID;
    }

    public void setBoatID(Integer boatID) {
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

    public Harbour getHarbour() {
        return harbour;
    }

    public void setHarbour(Harbour harbour) {
        this.harbour = harbour;
    }

    public List<Owner> getOwners() {
        return owners;
    }

    public void setOwners(List<Owner> owners) {
        this.owners = owners;
    }

    public void addOwner(Owner boatOwner) {
        owners.add(boatOwner);
    }

    public void assignHarbour(Harbour harbour) {
        if (harbour != null) {
            this.harbour = harbour;
            harbour.getBoats().add(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Boat)) return false;
        Boat boat = (Boat) o;
        return getBoatID().equals(boat.getBoatID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBoatID());
    }

    @Override
    public String toString() {
        return "Boat{" +
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
