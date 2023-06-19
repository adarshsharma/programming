package leetcode;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class FoodRatings2353 {

  Map<String, Integer> foodRatings;
  Map<String, TreeMap<Integer, TreeSet<String>>> cuisineMap;
  Map<String, String> foodCuisineMap;

  public FoodRatings2353(String[] foods, String[] cuisines, int[] ratings) {
    foodRatings = new HashMap<>();
    cuisineMap = new HashMap<>();
    foodCuisineMap = new HashMap<>();
    int n = foods.length;
    for (int i = 0; i < n; i++) {
      String food = foods[i];
      String cuisine = cuisines[i];
      int rating = ratings[i];
      foodRatings.put(food, rating);
      foodCuisineMap.put(food, cuisine);
      TreeMap<Integer, TreeSet<String>> ratingMap = cuisineMap.computeIfAbsent(cuisine,
          k -> new TreeMap<>(Collections.reverseOrder()));
      TreeSet<String> foodSet = ratingMap.computeIfAbsent(rating, k -> new TreeSet<>());
      foodSet.add(food);
    }
  }

  public void changeRating(String food, int newRating) {
    String cuisine = foodCuisineMap.get(food);
    TreeMap<Integer, TreeSet<String>> ratingMap = cuisineMap.get(cuisine);
    TreeSet<String> foodSet = ratingMap.get(foodRatings.get(food));
    foodSet.remove(food);
    if (foodSet.size() == 0) {
      ratingMap.remove(foodRatings.get(food));
    }
    foodRatings.put(food, newRating);
    foodSet = ratingMap.computeIfAbsent(newRating, k -> new TreeSet<>());
    foodSet.add(food);
  }

  public String highestRated(String cuisine) {
    if (cuisineMap.containsKey(cuisine)) {
      TreeMap<Integer, TreeSet<String>> ratingMap = cuisineMap.get(cuisine);
      return ratingMap.firstEntry().getValue().first();
    }

    return null;
  }

  public static void main(String[] args) {
    String[] foods = {"kimchi","miso","sushi","moussaka","ramen","bulgogi"};
    String[] cuisines = {"korean","japanese","japanese","greek","japanese","korean"};
    int[] ratings = {9,12,8,15,14,7};
    FoodRatings2353 foodRatings2353 = new FoodRatings2353(foods, cuisines, ratings);
    System.out.println(foodRatings2353.highestRated("korean"));
    System.out.println(foodRatings2353.highestRated("japanese"));
    foodRatings2353.changeRating("sushi",16);
    System.out.println(foodRatings2353.highestRated("japanese"));
    foodRatings2353.changeRating("ramen",16);
    System.out.println(foodRatings2353.highestRated("japanese"));
  }

}
