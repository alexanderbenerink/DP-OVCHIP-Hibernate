package nl.hu.dp.ovchip.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table( name = "adres")
public class Adres {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "adres_id")
    private int id;

    private String postcode;
    private String huisnummer;
    private String straat;
    private String woonplaats;
    private int reiziger_id;

    @OneToOne
    @JoinColumn(name = "adres_id")
    private Reiziger reiziger;

    public Adres(int id, String pc, String hn, String st, String wp) {
        this.id = id;
        this.postcode = pc;
        this.huisnummer = hn;
        this.straat = st;
        this.woonplaats = wp;
    }

    public Adres(int id, String pc, String hn, String st, String wp, int rg) {
        this.id = id;
        this.postcode = pc;
        this.huisnummer = hn;
        this.straat = st;
        this.woonplaats = wp;
        this.reiziger_id = rg;
    }

    public Adres() {

    }

    public int getId() {
        return this.id;
    }

    public String getPostcode() {
        return this.postcode;
    }

    public void setPostcode(String pc) {
        this.postcode = pc;
    }

    public String getHuisnummer() {
        return this.huisnummer;
    }

    public void setHuisnummer(String hn) {
        this.huisnummer = hn;
    }

    public String getStraat() {
        return this.straat;
    }

    public String getWoonplaats() {
        return this.woonplaats;
    }

    public Reiziger getReiziger() {
        return this.reiziger;
    }

    public void setReiziger(Reiziger rg) {
        this.reiziger = rg;
    }

    public int getReiziger_id() {
        return reiziger_id;
    }

    public void setReiziger_id(int reiziger_id) {
        this.reiziger_id = reiziger_id;
    }

    @Override
    public String toString() {
        return "Adres: {id: \"" + this.id + "\"; postcode: \"" + this.postcode + "\"; huisnummer: \"" + this.huisnummer + "\"; straat: \"" + this.straat + "\"; woonplaats: \"" + this.woonplaats + "\";}";
    }
}
