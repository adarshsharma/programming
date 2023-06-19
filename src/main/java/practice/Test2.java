package practice;

import java.util.HashSet;
import java.util.Set;

public class Test2 {
  static class Tree {
    int x;
    Tree l;
    Tree r;


    public Tree(int x) {
      this.x = x;
    }

    public Tree(int x, Tree l, Tree r) {
      this.x = x;
      this.l = l;
      this.r = r;
    }
  }

  int solution(Tree root) {
    Set<Integer> found = new HashSet<>();
    return recursive(found, root);
  }

  int recursive(Set<Integer> found, Tree tree) {
    if(tree == null || found.contains(tree.x)) {
      return found.size();
    }
    found.add(tree.x);
    int left = recursive(found, tree.l);
    int right = recursive(found, tree.r);
    found.remove(tree.x);
    return Math.max(left, right);
  }

  public static void main(String[] args) {
    Tree t = new Tree(1);

    t.l = new Tree(2);
    t.r = new Tree(3);

    t.l.l = new Tree(3);
    t.l.r = new Tree(6);
    t.r.l = new Tree(3);
    t.r.r = new Tree(1);

    t.l.l.l = new Tree(2);
    t.r.r.l = new Tree(5);
    t.r.r.r = new Tree(6);
    System.out.println(new Test2().solution(t));
  }
}
