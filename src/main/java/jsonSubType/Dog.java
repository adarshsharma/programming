package jsonSubType;

/**
 * Created by adarsh.sharma on 03/11/17.
 */
public class Dog extends Animal {
    private String breed;

    public Dog() {
        super(AnimalType.DOG);
    }

    public Dog(String name, String breed) {
        this();
        setName(name);
        setBreed(breed);
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @Override
    public String describe() {
        return "I am a Dog with name: "+ name;
    }
}
