package modal.dbservice;

import modal.dbservice.dao.FisherDAO;
import modal.dbservice.dao.UserDAO;
import modal.dbservice.daoimpl.FisherDAOImpl;
import modal.dbservice.daoimpl.UserDAOImpl;
import org.hibernate.SessionFactory;

public class DAOFactory {
    private static UserDAO userDAO = null;
    private static FisherDAO fisherDAO = null;
    private static DAOFactory instance = null;
    private static SessionFactory sessionFactory = null;

    public static synchronized DAOFactory getInstance(SessionFactory sessionFactory) {
        if(instance == null) {
            instance = new DAOFactory(sessionFactory);
        }
        return instance;
    }

    private DAOFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public UserDAO getUserDAO() {
        if(userDAO == null) {
            userDAO = new UserDAOImpl(sessionFactory);
        }
        return userDAO;
    }

    public FisherDAO getFisherDAO() {
        if(fisherDAO == null) {
            fisherDAO = new FisherDAOImpl(sessionFactory);
        }
        return fisherDAO;
    }
}
