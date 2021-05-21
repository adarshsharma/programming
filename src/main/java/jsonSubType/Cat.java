package jsonSubType;

/**
 * Created by adarsh.sharma on 03/11/17.
 */
public class Cat extends Animal {

    public String getFavoriteToy() {
        return favoriteToy;
    }

    public Cat() {
        super(AnimalType.CAT);
    }

    public Cat(String name, String favoriteToy) {
        this();
        setName(name);
        setFavoriteToy(favoriteToy);
    }

    public void setFavoriteToy(String favoriteToy) {
        this.favoriteToy = favoriteToy;
    }

    private String favoriteToy;

    @Override
    public String describe() {
        return "I am a cat with name: " + name;
    }
}
