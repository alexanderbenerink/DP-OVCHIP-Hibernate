package nl.hu.dp.ovchip.data.persistence;

import nl.hu.dp.ovchip.data.ProductDAO;
import nl.hu.dp.ovchip.domain.OVChipkaart;
import nl.hu.dp.ovchip.domain.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ProductDAOHibernate implements ProductDAO {
    private Session session;

    public ProductDAOHibernate(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(Product product) {
        try {
            Transaction transaction = this.session.beginTransaction();
            this.session.save(product);

            transaction.commit();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Product product) {
        try {
            Transaction transaction = this.session.beginTransaction();
            this.session.update(product);

            transaction.commit();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Product product) {
        try {
            Transaction transaction = this.session.beginTransaction();
            this.session.delete(product);

            transaction.commit();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Product> findByOVChipkaart(OVChipkaart ovChipkaart) {
        try {
            Transaction transaction = this.session.beginTransaction();
            List<Product> producten = this.session.createQuery("FROM Product", Product.class).getResultList();
            List<Product> ovChipkaartProducten = new ArrayList<>();

            for (Product product : producten) {
                if (product.getOvChipkaarten().contains(ovChipkaart)) {
                    ovChipkaartProducten.add(product);
                }
            }

            transaction.commit();
            return ovChipkaartProducten;
        }
        catch (Exception e) {
            if (!e.getClass().getSimpleName().equals("NoResultException")) {
                e.printStackTrace();
                return null;
            }
            return null;
        }
    }

    @Override
    public List<Product> findAll() {
        try {
            Transaction transaction = this.session.beginTransaction();
            List<Product> producten = this.session.createQuery("FROM Product", Product.class).getResultList();

            transaction.commit();
            return producten;
        }
        catch (Exception e) {
            if (!e.getClass().getSimpleName().equals("NoResultException")) {
                e.printStackTrace();
                return null;
            }
            return null;
        }
    }
}
