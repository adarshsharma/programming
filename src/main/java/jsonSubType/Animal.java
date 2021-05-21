package jsonSubType;

import com.fasterxml.jackson.annotation.*;

/**
 * Created by adarsh.sharma on 03/11/17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME
        ,include = JsonTypeInfo.As.EXISTING_PROPERTY
        ,property = "animalType"
        ,visible = true
        ,defaultImpl = AnimalType.class
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Dog.class, name = "DOG"),
        @JsonSubTypes.Type(value = Animal.class, name = "ANIMAL"),
        @JsonSubTypes.Type(value = Cat.class, name = "CAT") }
)
public class Animal {

    protected AnimalType animalType;
    protected String name;

    @JsonCreator
    public Animal(@JsonProperty("animalType") final AnimalType animalType) {
        this.animalType = animalType;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String describe() {
        return "I am an animal with name: " + name;
    }

}