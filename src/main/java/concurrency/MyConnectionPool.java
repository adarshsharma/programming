package concurrency;


import java.util.List;

/**
 * Created by adarsh.sharma on 05/09/18.
 */
// Connection Pool Code
public class MyConnectionPool implements ConnectionPool {

    public MyConnectionPool(List<Connection> connections) {
        //
    }

    @Override
    public Connection getConnection() {
        // implement
        return null;
    }

    public void returnConnection(Connection connection) {

    }

//    public static void main(String[] args) {
//        MyConnectionPool connectionPool = new MyConnectionPool(new ArrayList<>());
//        Connection con = connectionPool.getConnection();
//        try {
//            con.execute(query);
//        } finally {
//            con.close();
//        }
//    }

}
