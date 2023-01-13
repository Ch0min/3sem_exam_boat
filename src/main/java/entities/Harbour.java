package entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQuery(name = "Harbour.deleteAllRows", query = "DELETE from Harbour")
@Table(name = "harbour")
public class Harbour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "harbour_id", nullable = false)
    private Integer HarbourID;

    @Column(name = "harbour_name", nullable = false)
    private String harbourName;

    @Column(name = "harbour_address", nullable = false)
    private String harbourAddress;

    @Column(name = "harbour_capacity", nullable = false)
    private Integer harbourCapacity;

    @OneToMany(mappedBy = "harbour", cascade = CascadeType.ALL)
    private List<Boat> boats = new ArrayList<>();

    public Harbour() {
    }

    public Harbour(String harbourName, String harbourAddress, Integer harbourCapacity) {
        this.harbourName = harbourName;
        this.harbourAddress = harbourAddress;
        this.harbourCapacity = harbourCapacity;
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


    public Integer getHarbourID() {
        return HarbourID;
    }

    public void setHarbourID(Integer harbourID) {
        HarbourID = harbourID;
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

    public Integer getHarbourCapacity() {
        return harbourCapacity;
    }

    public void setHarbourCapacity(Integer harbourCapacity) {
        this.harbourCapacity = harbourCapacity;
    }

    public List<Boat> getBoats() {
        return boats;
    }

    public void setBoats(List<Boat> boats) {
        this.boats = boats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Harbour)) return false;
        Harbour harbour = (Harbour) o;
        return getHarbourID().equals(harbour.getHarbourID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHarbourID());
    }

    @Override
    public String toString() {
        return "Harbour{" +
                "HarbourID=" + HarbourID +
                ", harbourName='" + harbourName + '\'' +
                ", harbourAddress='" + harbourAddress + '\'' +
                ", harbourCapacity=" + harbourCapacity +
                ", boats=" + boats +
                '}';
    }
}
