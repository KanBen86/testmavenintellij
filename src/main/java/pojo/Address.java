package pojo;

import enums.ENUM_PERSON_TITEL;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@Audited
public class Address implements Serializable {
    @Id
    private long id;
    @MapsId
    private Person person;
    @Column
    private String anschrift;
    @Column
    private String strasse;
    @Column
    private String plz;
    @Column
    private String ort;
    @Column
    private String land;
    @Column
    private ENUM_PERSON_TITEL Titel;

    public void setId(long id) {
        this.id = id;
    }

    public void setAnschrift(String anschrift) {
        this.anschrift = anschrift;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public void setTitel(ENUM_PERSON_TITEL titel) {
        Titel = titel;
    }

    public long getId() {
        return id;
    }

    public String getAnschrift() {
        return anschrift;
    }

    public String getStrasse() {
        return strasse;
    }

    public String getPlz() {
        return plz;
    }

    public String getOrt() {
        return ort;
    }

    public String getLand() {
        return land;
    }

    public ENUM_PERSON_TITEL getTitel() {
        return Titel;
    }
}
