package leetcode;

import java.util.TreeSet;

public class SequentiallyOrdinalRankTracker2102 {

  TreeSet<Loc> bottom;
  TreeSet<Loc> top;

  static private class Loc implements Comparable<Loc> {

    String name;
    int score;

    public Loc(String name, int score) {
      this.name = name;
      this.score = score;
    }

    @Override
    public int compareTo(Loc b) {
      if (this.score != b.score) {
        return this.score - b.score;
      }

      return b.name.compareTo(this.name);
    }
  }

  public SequentiallyOrdinalRankTracker2102() {
    this.bottom = new TreeSet<>();
    this.top = new TreeSet<>();
  }

  public void add(String name, int score) {
    Loc loc = new Loc(name, score);
    bottom.add(loc);
    if(top.size() > 0 && bottom.last().compareTo(top.first()) > 0) {
      Loc temp1 = top.pollFirst();
      Loc temp2 = bottom.pollLast();
      bottom.add(temp1);
      top.add(temp2);
    }
  }


  public String get() {
    top.add(bottom.pollLast());
    return top.first().name;
  }

  public static void main(String[] args) {
    SequentiallyOrdinalRankTracker2102 tracker = new SequentiallyOrdinalRankTracker2102(); // Initialize the tracker system.
    // tracker.add("x", 56);
    // System.out.println(tracker.get());
    // tracker.add("t", 93);
    // tracker.add("q", 32);
    // System.out.println(tracker.get());
    tracker.add("bradford", 2); // Add location with name="bradford" and score=2 to the system.
    tracker.add("branford", 3); // Add location with name="branford" and score=3 to the system.
    System.out.println(
        tracker.get());              // The sorted locations, from best to worst, are: branford, bradford.
    // Note that branford precedes bradford due to its higher score (3 > 2).
    // This is the 1st time get() is called, so return the best location: "branford".
    tracker.add("alps", 2);     // Add location with name="alps" and score=2 to the system.
    System.out.println(tracker.get());               // Sorted locations: branford, alps, bradford.
    // Note that alps precedes bradford even though they have the same score (2).
    // This is because "alps" is lexicographically smaller than "bradford".
    // Return the 2nd best location "alps", as it is the 2nd time get() is called.
    tracker.add("orland", 2);   // Add location with name="orland" and score=2 to the system.
    System.out.println(
        tracker.get());               // Sorted locations: branford, alps, bradford, orland.
    // Return "bradford", as it is the 3rd time get() is called.
    tracker.add("orlando", 3);  // Add location with name="orlando" and score=3 to the system.
    System.out.println(
        tracker.get());               // Sorted locations: branford, orlando, alps, bradford, orland.
    // Return "bradford".
    tracker.add("alpine", 2);   // Add location with name="alpine" and score=2 to the system.
    System.out.println(
        tracker.get());               // Sorted locations: branford, orlando, alpine, alps, bradford, orland.
    // Return "bradford".
    System.out.println(
        tracker.get());               // Sorted locations: branford, orlando, alpine, alps, bradford, orland.
    // Return "orland".
  }

}
