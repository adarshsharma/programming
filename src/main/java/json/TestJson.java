package json;

import java.io.IOException;

/**
 * Created by adarsh.sharma on 12/12/17.
 */
public class TestJson {
    public static void main(String[] args) throws IOException {
        TestModel2 testModel2 = new TestModel2();
        testModel2.name = "name";
        testModel2.value = 2;


        String json = MyObjectMapper.getDefaultJson(testModel2);
        System.out.println(json);

//        TestModel testModel = MyObjectMapper.getClassObjectByDefaultMapper(json, TestModel.class);
        System.out.println("test");

    }
}
