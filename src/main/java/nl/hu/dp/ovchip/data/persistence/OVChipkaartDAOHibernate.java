package nl.hu.dp.ovchip.data.persistence;

import nl.hu.dp.ovchip.data.OVChipkaartDAO;
import nl.hu.dp.ovchip.domain.OVChipkaart;
import nl.hu.dp.ovchip.domain.Reiziger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class OVChipkaartDAOHibernate implements OVChipkaartDAO {
    private Session session;

    public OVChipkaartDAOHibernate(Session session) {
        this.session = session;
    }

    @Override
    public boolean save(OVChipkaart ovChipkaart) {
        try {
            Transaction transaction = this.session.beginTransaction();
            this.session.save(ovChipkaart);

            transaction.commit();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(OVChipkaart ovChipkaart) {
        try {
            Transaction transaction = this.session.beginTransaction();
            this.session.update(ovChipkaart);

            transaction.commit();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(OVChipkaart ovChipkaart) {
        try {
            Transaction transaction = this.session.beginTransaction();
            this.session.delete(ovChipkaart);

            transaction.commit();
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<OVChipkaart> findAll() {
        try {
            Transaction transaction = this.session.beginTransaction();
            List<OVChipkaart> ovChipkaarten = this.session.createQuery("FROM OVChipkaart", OVChipkaart.class).getResultList();

            transaction.commit();
            return ovChipkaarten;
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
    public List<OVChipkaart> findByReiziger(Reiziger reiziger) {
        try {
            Transaction transaction = this.session.beginTransaction();
            List<OVChipkaart> ovChipkaarten = this.session.createQuery("FROM OVChipkaart WHERE reizigerId = " + reiziger.getId(), OVChipkaart.class).getResultList();

            transaction.commit();
            return ovChipkaarten;
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
