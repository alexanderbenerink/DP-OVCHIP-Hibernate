package nl.hu.dp.ovchip.domein;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table( name = "adres")
public class Adres {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "adres_id")
    private Long id;

    private String postcode;
    private String huisnummer;
    private String straat;
    private String woonplaats;

    @OneToOne
    @JoinColumn(name = "adres_id")
    private Reiziger reiziger;

    public Adres(Long id, String pc, String hn, String st, String wp) {
        this.id = id;
        this.postcode = pc;
        this.huisnummer = hn;
        this.straat = st;
        this.woonplaats = wp;
    }

    public Adres(Long id, String pc, String hn, String st, String wp, Reiziger rg) {
        this.id = id;
        this.postcode = pc;
        this.huisnummer = hn;
        this.straat = st;
        this.woonplaats = wp;
        this.reiziger = rg;
    }

    public Adres() {

    }

    public Long getId() {
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

    @Override
    public String toString() {
        return "Adres: {id: \"" + this.id + "\"; postcode: \"" + this.postcode + "\"; huisnummer: \"" + this.huisnummer + "\"; straat: \"" + this.straat + "\"; woonplaats: \"" + this.woonplaats + "\";}";
    }
}
