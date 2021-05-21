package json;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Map;

/**
 * Created by sanjay.rajput on 28/02/15.
 */

public class MyObjectMapper {
    private static ObjectMapper mapper;
    private static ObjectMapper bigfootMapper;

    public MyObjectMapper() {
    }

    static {
        mapper = new ObjectMapper();
        mapper.registerModule(new JodaModule());
        Hibernate4Module hbm = new Hibernate4Module();
        hbm.enable(Hibernate4Module.Feature.FORCE_LAZY_LOADING);
        mapper.registerModule(hbm);
        mapper.setVisibilityChecker(mapper.getSerializationConfig().getDefaultVisibilityChecker()
                .withFieldVisibility(JsonAutoDetect.Visibility.ANY)
                .withGetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withSetterVisibility(JsonAutoDetect.Visibility.NONE)
                .withCreatorVisibility(JsonAutoDetect.Visibility.NONE)
                .withIsGetterVisibility(JsonAutoDetect.Visibility.NONE));
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        bigfootMapper = mapper.copy();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        bigfootMapper.setDateFormat(df);
    }

    public static String getJsonString(Object object) throws JsonProcessingException {
        try {
            if (object != null)
                return mapper.writeValueAsString(object);
        } catch (Exception e) {
            throw e;
        }
        return null;
    }

    public static String getJsonStringNoException(Object object) throws JsonProcessingException {
        try {
            if (object != null)
                return mapper.writeValueAsString(object);
        } catch (Exception e) {
            throw e;
        }
        return null;
    }

    public static String getDefaultJson(Object object) throws JsonProcessingException {
        try {
            if (object != null)
                return mapper.writeValueAsString(object);
        } catch (Exception e) {
            throw e;
        }
        return null;
    }

    public static String getDefaultJsonForBigfoot(Object object) throws JsonProcessingException {
        try {
            if (object != null)
                return bigfootMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw e;
        }
        return null;
    }

    public static <T> T getClassObject(String jsonString, Class<T> valueType, ObjectMapper mapper) throws IOException {
        try {
            return mapper.readValue(jsonString, valueType);
        } catch (Exception e) {
            throw e;
        }
    }

    public static <T> T getClassObjectByDefaultMapper(String jsonString, Class<T> valueType) throws IOException {
        if (jsonString == null) {
            return null;
        }

        try {
            return mapper.readValue(jsonString, valueType);
        } catch (Exception e) {
            throw e;
        }
    }

    public static Collection getCollectionObjectWithType(String response, Class valueType, Class modelClass) throws IOException {
        if (response == null) {
            return null;
        }

        try {
            JavaType type = mapper.getTypeFactory().constructCollectionType(valueType, modelClass);
            return mapper.readValue(response, type);
        } catch (IOException e) {
            throw e;
        }
    }

    public static JsonNode valueToTree(Map<String, Object> map) {
        return mapper.valueToTree(map);
    }

    public static ObjectNode valueToTree(Object object) {
        return mapper.valueToTree(object);
    }

    public static JsonNode readTree(String context) throws IOException {
        try {
            return mapper.readTree(context);
        } catch (IOException e) {
            throw e;
        }
    }

    public static <T> T convertValue(JsonNode jsonNode, Class<T> valueType) {
        return mapper.convertValue(jsonNode, valueType);
    }

    public static <T> T convertValue(Object object, Class<T> valueType) {
        return mapper.convertValue(object, valueType);
    }

    public static <T> T treeToValue(JsonNode jsonNode, Class<T> valueType) throws JsonProcessingException {
        return mapper.treeToValue(jsonNode, valueType);
    }
}
