package nl.hu.dp.ovchip.data.persistence;

import nl.hu.dp.ovchip.data.AdresDAO;
import nl.hu.dp.ovchip.domain.Adres;
import nl.hu.dp.ovchip.domain.Reiziger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class AdresDAOHibernate implements AdresDAO {
    private Session session;

    public AdresDAOHibernate(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(Adres adres) {
        try {
            Transaction transaction = this.session.beginTransaction();
            this.session.save(adres);

            transaction.commit();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Adres adres) {
        try {
            Transaction transaction = this.session.beginTransaction();
            this.session.update(adres);

            transaction.commit();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Adres adres) {
        try {
            Transaction transaction = this.session.beginTransaction();
            this.session.delete(adres);

            transaction.commit();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Adres findByReiziger(Reiziger reiziger) {
        try {
            Transaction transaction = this.session.beginTransaction();
            Adres adres = this.session.createQuery("FROM Adres WHERE reiziger_id = " + reiziger.getId(), Adres.class).getSingleResult();

            transaction.commit();
            return adres;
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
    public Adres findById(int id) {
        try {
            Transaction transaction = this.session.beginTransaction();
            Adres adres = this.session.createQuery("FROM Adres WHERE id = " + id, Adres.class).getSingleResult();

            transaction.commit();
            return adres;
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
    public List<Adres> findAll() {
        try {
            Transaction transaction = this.session.beginTransaction();
            List<Adres> adres = this.session.createQuery("FROM Adres", Adres.class).getResultList();

            transaction.commit();
            return adres;
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
