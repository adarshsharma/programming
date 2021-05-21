package concurrency;

/**
 * Created by adarsh.sharma on 05/09/18.
 */
public class MyConnection implements Connection {
    Connection connection;
    MyConnectionPool myConnectionPool;

    public MyConnection(Connection connection, MyConnectionPool myConnectionPool) {
        this.connection = connection;
        this.myConnectionPool = myConnectionPool;
    }

    @Override
    public void close() {
        if (connection != null) {
            myConnectionPool.returnConnection(connection);
            connection = null;
        } else {

        }
    }

    @Override
    public Object execute(Object query) {
        return connection.execute(query);
    }
}
