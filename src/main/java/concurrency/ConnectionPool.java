package concurrency;

/**
 * Created by adarsh.sharma on 05/09/18.
 */
public interface ConnectionPool {
    Connection getConnection();
}
