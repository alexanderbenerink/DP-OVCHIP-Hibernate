package nl.hu.dp.ovchip.domein;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reiziger")
public class Reiziger {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "reiziger_id")
    private Long id;

    @Column
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private Date geboortedatum;

    @OneToOne
    @JoinColumn(name = "reiziger_id")
    private Adres adres;

    @OneToMany
    @JoinColumn(name = "reiziger_id")
    private List<OVChipkaart> ovchipkaarten = new ArrayList<>();

    public Reiziger() {}

    public Reiziger(Long id, String vl, String tv, String an, Date gb) {
        this.id = id;
        this.voorletters = vl;
        this.tussenvoegsel = tv;
        this.achternaam = an;
        this.geboortedatum = gb;
        this.adres = null;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVoorletters() {
        return this.voorletters;
    }

    public String getTussenvoegsel() {
        return this.tussenvoegsel;
    }

    public String getAchternaam() {
        return this.achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public Date getGeboortedatum() {
        return this.geboortedatum;
    }

    public String getNaam() {
        return this.voorletters + " " + this.tussenvoegsel + " " + this.achternaam;
    }

    public Adres getAdres() {
        return this.adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public List<OVChipkaart> getOvchipkaarten() {
        return ovchipkaarten;
    }

    public void setOvchipkaarten(List<OVChipkaart> ovchipkaarten) {
        this.ovchipkaarten = ovchipkaarten;
    }

    @Override
    public String toString() {
        return "Reiziger: {id: \"" + this.id + "\"; voorletters: \"" + this.voorletters + "\"; tussenvoegsel: \"" + this.tussenvoegsel + "\"; achternaam: \"" + this.achternaam + "\"; geboortedatum: \"" + this.geboortedatum + "\"; adres: \"" + this.adres + "\"" + "ovchipkaarten: " + ovchipkaarten + ";}";
    }
}
