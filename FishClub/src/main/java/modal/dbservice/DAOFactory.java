package modal.dbservice;

import modal.dbservice.dao.*;
import modal.dbservice.daoimpl.*;
import org.hibernate.SessionFactory;

public class DAOFactory {
    private static UserDAO userDAO = null;
    private static FisherDAO fisherDAO = null;
    private static LakeDAO lakeDAO = null;
    private static LureDAO lureDAO = null;
    private static FishDAO fishDAO = null;
    private static DAOFactory instance = null;
    private static SessionFactory sessionFactory = null;

    public static synchronized DAOFactory getInstance(SessionFactory sessionFactory) {
        if (instance == null) {
            instance = new DAOFactory(sessionFactory);
        }
        return instance;
    }

    private DAOFactory(SessionFactory sessionFactory) {
        DAOFactory.sessionFactory = sessionFactory;
    }

    public UserDAO getUserDAO() {
        if (userDAO == null) {
            userDAO = new UserDAOImpl(sessionFactory);
        }
        return userDAO;
    }

    public FisherDAO getFisherDAO() {
        if (fisherDAO == null) {
            fisherDAO = new FisherDAOImpl(sessionFactory);
        }
        return fisherDAO;
    }

    public LakeDAO getLakeDAO() {
        if (lakeDAO == null) {
            lakeDAO = new LakeDAOImpl(sessionFactory);
        }
        return lakeDAO;
    }

    public LureDAO getLureDAO() {
        if (lureDAO == null) {
            lureDAO = new LureDAOImpl(sessionFactory);
        }
        return lureDAO;
    }

    public FisherDAO getFishDAO() {
        if (fishDAO == null) {
            fishDAO = new FishDAOImpl(sessionFactory);
        }
        return fisherDAO;
    }
}
