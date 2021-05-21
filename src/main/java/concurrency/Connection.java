package concurrency;

/**
 * Created by adarsh.sharma on 05/09/18.
 */
public interface Connection {
    void close();

    // this is the least interesting part to this problem
    Object execute(Object query);
}
