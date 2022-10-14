package nl.hu.dp.ovchip.domain;

import nl.hu.dp.ovchip.data.persistence.AdresDAOHibernate;
import nl.hu.dp.ovchip.data.persistence.OVChipkaartDAOHibernate;
import nl.hu.dp.ovchip.data.persistence.ProductDAOHibernate;
import nl.hu.dp.ovchip.data.persistence.ReizigerDAOHibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;
import java.sql.Date;
import java.sql.SQLException;

/**
 * Testklasse - deze klasse test alle andere klassen in deze package.
 *
 * System.out.println() is alleen in deze klasse toegestaan (behalve voor exceptions).
 *
 * @author tijmen.muller@hu.nl
 */
public class Main {
    // Creëer een factory voor Hibernate sessions.
    private static final SessionFactory factory;

    static {
        try {
            // Create a Hibernate session factory
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    /**
     * Retouneer een Hibernate session.
     *
     * @return Hibernate session
     * @throws HibernateException
     */
    private static Session getSession() throws HibernateException {
        return factory.openSession();
    }

    public static void main(String[] args) throws SQLException {
        testFetchAll();
        testDAOHibernate();
    }

    /**
     * P6. Haal alle (geannoteerde) entiteiten uit de database.
     */
    private static void testFetchAll() {
        Session session = getSession();
        try {
            Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                Query query = session.createQuery("from " + entityType.getName());

                System.out.println("[Test] Alle objecten van type " + entityType.getName() + " uit database:");
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
                System.out.println();
            }
        } finally {
            session.close();
        }
    }

    /**
     * P7. Test de CRUD-functionaliteiten op de vier domeinklassen.
     */
    private static void testDAOHibernate() {
        AdresDAOHibernate adao = new AdresDAOHibernate(getSession());
        OVChipkaartDAOHibernate odao = new OVChipkaartDAOHibernate(getSession());
        ProductDAOHibernate pdao = new ProductDAOHibernate(getSession());
        ReizigerDAOHibernate rdao = new ReizigerDAOHibernate(getSession());

        Reiziger reiziger = new Reiziger(50, "A", " ", "Benerink", Date.valueOf("2001-12-08"));
        Adres adres = new Adres(50, "1234AB", "12", "Heidelberglaan", "Utrecht", 50);
        OVChipkaart ovChipkaart = new OVChipkaart(50, Date.valueOf("2023-10-13"), 2, 20.0, 50);
        Product product = new Product(50, "Test Product", "P7 Hibernate crud testen", 20.0);

        /* Test Reiziger */
        System.out.println("----- Test Reiziger DAO Hibernate -----");
        reiziger.getOvchipkaarten().add(ovChipkaart);

        // Save
        rdao.save(reiziger);
        System.out.println(rdao.findAll() + "\n");

        // Update
        reiziger.setAchternaam("Jansen");
        reiziger.setAdres(adres);

        rdao.update(reiziger);
        System.out.println(rdao.findById(reiziger.getId()) + "\n");

        /* Test Adres */
        System.out.println("----- Test Adres DAO Hibernate -----");

        // Save
        adao.save(adres);
        System.out.println(adao.findAll() + "\n");

        // Update
        adres.setHuisnummer("10");
        adao.update(adres);
        System.out.println(adao.findByReiziger(reiziger) + "\n");

        /* Test OVChipkaart */
        System.out.println("----- Test OVChipkaart DAO Hibernate -----");

        // Save
        odao.save(ovChipkaart);
        System.out.println(odao.findAll() + "\n");

        // Update
        ovChipkaart.setKlasse(1);
        odao.update(ovChipkaart);
        System.out.println(odao.findByReiziger(reiziger) + "\n");

        /* Test Product */
        System.out.println("----- Test Product DAO Hibernate -----");

        // Save
        pdao.save(product);
        System.out.println(pdao.findAll() + "\n");

        // Update
        product.setPrijs(15.0);
        product.getOvChipkaarten().add(ovChipkaart);
        pdao.update(product);
        System.out.println(pdao.findByOVChipkaart(ovChipkaart) + "\n");

        /* Delete all */
        System.out.println("----- Delete all -----");

        // Adres
        adao.delete(adres);
        System.out.println("Adres: " + adao.findById(adres.getId()) + "\n");

        // Product
        pdao.delete(product);
        System.out.println("Product: " + pdao.findByOVChipkaart(ovChipkaart) + "\n");

        // OVChipkaart
        odao.delete(ovChipkaart);
        System.out.println("OVChipkaart: " + odao.findByReiziger(reiziger) + "\n");

        // Reiziger
        rdao.delete(reiziger);
        System.out.println("Reiziger: " + rdao.findById(reiziger.getId()) + "\n");

    }
}