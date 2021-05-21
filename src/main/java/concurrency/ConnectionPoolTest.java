package concurrency;

import java.util.List;

/**
 * Created by adarsh.sharma on 07/09/18.
 */
public class ConnectionPoolTest {
    public class MyConnectionPool implements ConnectionPool {

        public MyConnectionPool(List<Connection> connections) {
            // implement
        }

        @Override
        public Connection getConnection() {
            // implement
            return null;
        }
    }

//    public interface ConnectionPool {
//        Connection getConnection();
//    }
//
//    public interface Connection {
//        void close();
//
//        // this is the least interesting part to this problem
//        Object execute(Object query);
//    }
}
