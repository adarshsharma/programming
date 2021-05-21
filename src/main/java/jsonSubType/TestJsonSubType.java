package jsonSubType;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adarsh.sharma on 03/11/17.
 */
public class TestJsonSubType {
    public static void main(String[] args) {

        ObjectMapper objectMapper = new ObjectMapper();

        Animal myDog = new Dog("ruffus", "english shepherd");

        Animal myCat = new Cat("goya", "mice");

        Animal animal = new Animal(AnimalType.ANIMAL);
        animal.setName("animal_name");

        try {
            Zoo zoo = new Zoo();
            List<Animal> animals = new ArrayList<>();
            animals.add(myDog);
            animals.add(myCat);
            animals.add(animal);
            zoo.setAnimals(animals);

            String zooJson = objectMapper.writeValueAsString(zoo);
            System.out.println(zooJson);

            Zoo myZoo = objectMapper.readValue(zooJson, Zoo.class);
            myZoo.getAnimals().forEach(a -> System.out.println(a.describe()));

            System.out.println("jsonSubType/test");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
