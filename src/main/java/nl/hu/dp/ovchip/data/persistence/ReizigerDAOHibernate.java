package nl.hu.dp.ovchip.data.persistence;

import nl.hu.dp.ovchip.data.ReizigerDAO;
import nl.hu.dp.ovchip.domain.Reiziger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ReizigerDAOHibernate implements ReizigerDAO {
    private Session session;

    public ReizigerDAOHibernate(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(Reiziger reiziger) {
        try {
            Transaction transaction = this.session.beginTransaction();
            this.session.save(reiziger);

            transaction.commit();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Reiziger reiziger) {
        try {
            Transaction transaction = this.session.beginTransaction();
            this.session.update(reiziger);

            transaction.commit();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Reiziger reiziger) {
        try {
            Transaction transaction = this.session.beginTransaction();
            this.session.delete(reiziger);

            transaction.commit();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Reiziger findById(int id) {
        try {
            Transaction transaction = this.session.beginTransaction();
            Reiziger reiziger = this.session.createQuery("FROM Reiziger WHERE id = " + id, Reiziger.class).getSingleResult();

            transaction.commit();
            return reiziger;
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
    public List<Reiziger> findByGbdatum(String datum) {
        try {
            Transaction transaction = this.session.beginTransaction();
            List<Reiziger> reizigers = this.session.createQuery("FROM Reiziger WHERE geboortedatum = " + datum, Reiziger.class).getResultList();

            transaction.commit();
            return reizigers;
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
    public List<Reiziger> findAll() {
        try {
            Transaction transaction = this.session.beginTransaction();
            List<Reiziger> reizigers = this.session.createQuery("FROM Reiziger", Reiziger.class).getResultList();

            transaction.commit();
            return reizigers;
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
