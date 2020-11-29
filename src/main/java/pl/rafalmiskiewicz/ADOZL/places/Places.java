package pl.rafalmiskiewicz.ADOZL.places;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "places")
public class Places {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_places")
    private int id_places;

    @Column(name = "description")
    @NotNull
    private String description;

    @Column(name = "address_google")
    @NotNull
    private String address_google;


    public int getId_places() {
        return id_places;
    }

    public void setId_places(int id_places) {
        this.id_places = id_places;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress_google() {
        return address_google;
    }

    public void setAddress_google(String address_google) {
        this.address_google = address_google;
    }
}
