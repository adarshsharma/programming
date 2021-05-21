package json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by adarsh.sharma on 12/12/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestModel {
    @JsonProperty("name")
    String name;

    @JsonProperty("val")
    Long value;
}
