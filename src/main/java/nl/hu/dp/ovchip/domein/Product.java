package nl.hu.dp.ovchip.domein;

import org.hibernate.annotations.Columns;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name = "product")
public class Product {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int product_nummer;

    @Column
    private String naam;
    private String beschrijving;
    private double prijs;

    @ManyToMany(mappedBy = "producten", targetEntity = OVChipkaart.class)
    private List<OVChipkaart> ovChipkaarten;

    public Product() {}

    public Product(int product_nummer, String naam, String beschrijving, double prijs) {
        this.product_nummer = product_nummer;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
        this.ovChipkaarten = new ArrayList<OVChipkaart>();
    }

    public Product(int product_nummer, String naam, String beschrijving, double prijs, List<OVChipkaart> ovChipkaarten) {
        this.product_nummer = product_nummer;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
        this.ovChipkaarten = ovChipkaarten;
    }

    public int getProduct_nummer() {
        return product_nummer;
    }

    public void setProduct_nummer(int product_nummer) {
        this.product_nummer = product_nummer;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public List<OVChipkaart> getOvChipkaarten() {
        return ovChipkaarten;
    }

    public void setOvChipkaarten(List<OVChipkaart> ovChipkaarten) {
        this.ovChipkaarten = ovChipkaarten;
    }

    @Override
    public String toString() {
        return "Product: {nummer: \"" + this.product_nummer + "\"; naam: \"" + this.naam + "\"; beschrijving: \"" + this.beschrijving + "\"; prijs: \"" + this.prijs + "\"}";
    }
}
