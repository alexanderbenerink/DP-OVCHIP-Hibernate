package nl.hu.dp.ovchip.domein;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table( name = "ov_chipkaart")
public class OVChipkaart {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "kaart_nummer")
    private Long kaartNummer;

//    @Temporal(TemporalType.DATE)
    @Column(name = "geldig_tot")
    private Date geldigTot;

    private int klasse;
    private double saldo;

    @Column(name = "reiziger_id")
    private int reizigerId;

    @ManyToOne
    @JoinColumn(name = "reiziger_id", insertable = false, updatable = false)
    private Reiziger reiziger;

    @ManyToMany
    @JoinTable(name = "ov_chipkaart_product", joinColumns = {@JoinColumn(name = "kaart_nummer")}, inverseJoinColumns = {@JoinColumn(name = "product_nummer")})
    private List<Product> producten;

    public OVChipkaart() {}

    public OVChipkaart(Long kn, Date gt, int kl, double sl, int ri) {
        this.kaartNummer = kn;
        this.geldigTot = gt;
        this.klasse = kl;
        this.saldo = sl;
        this.reizigerId = ri;
    }

    public OVChipkaart(Long kn, Date gt, int kl, double sl, int ri, List<Product> pr) {
        this.kaartNummer = kn;
        this.geldigTot = gt;
        this.klasse = kl;
        this.saldo = sl;
        this.reizigerId = ri;
        this.producten = pr;
    }

    public Long getKaartNummer() {
        return kaartNummer;
    }

    public void setKaartNummer(Long kaartNummer) {
        this.kaartNummer = kaartNummer;
    }

    public Date getGeldigTot() {
        return geldigTot;
    }

    public void setGeldigTot(Date geldigTot) {
        this.geldigTot = geldigTot;
    }

    public int getKlasse() {
        return klasse;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getReizigerId() {
        return reizigerId;
    }

    public void setReizigerId(int reizigerId) {
        this.reizigerId = reizigerId;
    }

    public List<Product> getProduct() {
        return producten;
    }

    public void setProduct(List<Product> product) {
        this.producten = product;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    @Override
    public String toString() {
        return "OV-Chipkaart : {kaartnummer: \"" + this.kaartNummer + "\"; geldig_tot: \"" + this.geldigTot + "\"; klasse: \"" + this.klasse + "\"; saldo: \"" + this.saldo + "\"; reiziger_id: \"" + this.reizigerId + "\" producten: " + producten + ";}";
    }
}
