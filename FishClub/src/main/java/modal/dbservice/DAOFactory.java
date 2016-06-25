package modal.dbservice;

import modal.dbservice.dao.*;
import modal.dbservice.daoimpl.*;
import modal.dbservice.daoimpl.joindao.*;
import org.hibernate.SessionFactory;

/**
 * Синглтон класс-фактории для создания DAO
 */
public class DAOFactory {
    private static UserDAO userDAO = null;
    private static FisherDAO fisherDAO = null;
    private static LakeDAO lakeDAO = null;
    private static LureDAO lureDAO = null;
    // переменная для работы с таблие Рыбы
    private static FishDAO fishDAO = null;

    private static DAOFactory instance = null;

    private static LivedDAO livedDAO = null;
    private static PeckDAO peckDAO = null;
    private static AvailabilityDAO availabilityDAO = null;
    // переменная для работы с таблице расстояние
    private static DistanceDAO distanceDAO = null;
    private static PrefersDAO prefersDAO = null;
    private static SessionFactory sessionFactory = null;

    /**
     * Метод для создание класса, гарантирующий что данный объект
     * будет тольк в единственном экземпляре
     *
     * @param sessionFactory для создание сессий
     * @return возвращает обхект класса
     */
    public static synchronized DAOFactory getInstance(SessionFactory sessionFactory) {
        if (instance == null) {
            instance = new DAOFactory(sessionFactory);
        }
        return instance;
    }

    private DAOFactory(SessionFactory sessionFactory) {
        DAOFactory.sessionFactory = sessionFactory;
    }

    /**
     * Метод получения обеъкта для работы с таблицей пользователи
     *
     * @return UserDAO
     */
    public UserDAO getUserDAO() {
        if (userDAO == null) {
            userDAO = new UserDAOImpl(sessionFactory);
        }
        return userDAO;
    }

    /**
     * Метод для получения объект для работы с таблицей рыбаки
     *
     * @return FisherDAO
     */
    public FisherDAO getFisherDAO() {
        if (fisherDAO == null) {
            fisherDAO = new FisherDAOImpl(sessionFactory);
        }
        return fisherDAO;
    }

    /**
     * Метод для получения объекта для работы с таблицей озера
     *
     * @return LakeDAO
     */
    public LakeDAO getLakeDAO() {
        if (lakeDAO == null) {
            lakeDAO = new LakeDAOImpl(sessionFactory);
        }
        return lakeDAO;
    }

    /**
     * Метод для получения объекта для работы с таблицей Наживки
     *
     * @return LureDAO
     */
    public LureDAO getLureDAO() {
        if (lureDAO == null) {
            lureDAO = new LureDAOImpl(sessionFactory);
        }
        return lureDAO;
    }

    /**
     * Метод для получения объекта для работы с таблицей Рыбы
     *
     * @return FishDAO
     */
    public FishDAO getFishDAO() {
        if (fishDAO == null) {
            fishDAO = new FishDAOImpl(sessionFactory);
        }
        return fishDAO;
    }

    /**
     * Метод для получение объекта для работы с таблице Обитает
     *
     * @return LivedDAO
     */
    public LivedDAO getLivedDAO() {
        if (livedDAO == null) {
            livedDAO = new LivedDAO(sessionFactory);
        }
        return livedDAO;
    }

    /**
     * Метод для получения объекта для работы с таблицей Клюёт
     *
     * @return PeckDAO
     */
    public PeckDAO getPeckDAO() {
        if (peckDAO == null) {
            peckDAO = new PeckDAO(sessionFactory);
        }
        return peckDAO;
    }

    /**
     * Метод для получения объекта для работы с таблицей Имеется
     * @return AvailabilityDAO
     */
    public AvailabilityDAO getAvailabilityDAO() {
        if (availabilityDAO == null) {
            availabilityDAO = new AvailabilityDAO(sessionFactory);
        }
        return availabilityDAO;
    }

    /**
     * Метод для получения объекта для работы с таблицей Расстояние
     * @return DistanceDAO
     */
    public DistanceDAO getDistanceDAO() {
        if (distanceDAO == null) {
            distanceDAO = new DistanceDAO(sessionFactory);
        }
        return distanceDAO;
    }

    /**
     * Метод для получения объекта для работы с таблицей Предпочитает
     * @return PrefersDAO
     */
    public PrefersDAO getPrefersDAO() {
        if (prefersDAO == null) {
            prefersDAO = new PrefersDAO(sessionFactory);
        }
        return prefersDAO;
    }

}
